import java.util.Scanner;

public class Main {

    private static CasaDeApuestas casa;
    private static Scanner scanner;
    private static int contadorTickets = 1;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        casa = new CasaDeApuestas("Apuestas menu");

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opción: ");
            procesarOpcion(opcion);
        } while (opcion != 0);

        scanner.close();
        System.out.println("Hasta pronto!");
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Menu de apuestas ===");
        System.out.println("1. Registrar jugador");
        System.out.println("2. Registrar evento deportivo");
        System.out.println("3. Crear apuesta simple");
        System.out.println("4. Crear apuesta multiple");
        System.out.println("5. Listar tickets");
        System.out.println("0. Salir");
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> registrarJugador();
            case 2 -> registrarEvento();
            case 3 -> crearApuestaSimple();
            case 4 -> crearApuestaMultiple();
            case 5 -> casa.listarTickets();
            case 0 -> {
            }
            default -> System.out.println("Opción no válida.");
        }
    }

    // --- registros ---

    private static void registrarJugador() {
        System.out.println("\n-- Registrar jugador --");
        String id = leerTexto("ID: ");
        if (casa.buscarJugadorPorId(id) != null) {
            System.out.println("Ya existe un jugador con ese ID.");
            return;
        }
        String nombre = leerTexto("Nombre: ");
        double saldo = leerDouble("Saldo inicial: $");
        casa.registrarJugador(new Jugador(id, nombre, saldo));
    }

    private static void registrarEvento() {
        System.out.println("\n-- Registrar evento --");
        String codigo = leerTexto("Código: ");
        if (casa.buscarEventoPorCodigo(codigo) != null) {
            System.out.println("Ya existe un evento con ese código.");
            return;
        }
        String descripcion = leerTexto("Descripción: ");
        double cuotaLocal     = leerDouble("Cuota local: ");
        double cuotaEmpate    = leerDouble("Cuota empate: ");
        double cuotaVisitante = leerDouble("Cuota visitante: ");
        casa.registrarEvento(new EventoDeportivo(codigo, descripcion,
                cuotaLocal, cuotaEmpate, cuotaVisitante));
    }

    // --- apuestas ---

    private static void crearApuestaSimple() {
        System.out.println("\n-- Apuesta simple --");

        Jugador jugador = seleccionarJugador();
        if (jugador == null) return;

        EventoDeportivo evento = seleccionarEvento();
        if (evento == null) return;

        TipoPronostico pronostico = seleccionarPronostico(evento);
        if (pronostico == null) return;

        double monto = leerDouble("Monto: $");

        // validación de saldo
        if (!jugador.tieneSaldo(monto)) {
            System.out.printf("Saldo insuficiente. Saldo actual: $%.2f%n", jugador.getSaldo());
            return;
        }

        LineaApuesta linea = new LineaApuesta(evento, pronostico);
        String codigo = "TKT-" + String.format("%03d", contadorTickets++);
        TicketApuesta ticket = new TicketApuesta(codigo, jugador, monto);
        ticket.agregarLinea(linea);

        // polimorfismo: ApuestaSimple referenciada como Apuesta
        Apuesta apuesta = new ApuestaSimple(monto, jugador, linea);

        jugador.descontarSaldo(monto);
        casa.registrarTicket(ticket);

        System.out.println("\nApuesta registrada!");
        System.out.println(apuesta);
        System.out.printf("Nuevo saldo: $%.2f%n", jugador.getSaldo());
    }

    private static void crearApuestaMultiple() {
        System.out.println("\n-- Apuesta multiple --");

        Jugador jugador = seleccionarJugador();
        if (jugador == null) return;

        double monto = leerDouble("Monto: $");

        if (!jugador.tieneSaldo(monto)) {
            System.out.printf("Saldo insuficiente. Saldo actual: $%.2f%n", jugador.getSaldo());
            return;
        }

        // polimorfismo: ApuestaMultiple referenciada como Apuesta
        Apuesta apuesta = new ApuestaMultiple(monto, jugador);
        ApuestaMultiple apuestaMultiple = (ApuestaMultiple) apuesta;

        String codigo = "TKT-" + String.format("%03d", contadorTickets++);
        TicketApuesta ticket = new TicketApuesta(codigo, jugador, monto);

        // agregar líneas
        boolean agregar = true;
        while (agregar) {
            EventoDeportivo evento = seleccionarEvento();
            if (evento != null) {
                TipoPronostico pronostico = seleccionarPronostico(evento);
                if (pronostico != null) {
                    LineaApuesta linea = new LineaApuesta(evento, pronostico);
                    apuestaMultiple.agregarLinea(linea);
                    ticket.agregarLinea(linea);
                    System.out.println("Línea agregada.");
                }
            }
            System.out.print("¿Agregar otra línea? (s/n): ");
            agregar = scanner.nextLine().trim().equalsIgnoreCase("s");
        }

        if (apuestaMultiple.getLineas().isEmpty()) {
            System.out.println("No se agregó ninguna línea. Apuesta cancelada.");
            contadorTickets--;
            return;
        }

        jugador.descontarSaldo(monto);
        casa.registrarTicket(ticket);

        System.out.println("\nApuesta registrada!");
        System.out.println(apuesta);
        System.out.printf("Nuevo saldo: $%.2f%n", jugador.getSaldo());
    }

    // --- helpers de selección ---

    private static Jugador seleccionarJugador() {
        if (casa.getJugadores().isEmpty()) {
            System.out.println("No hay jugadores registrados.");
            return null;
        }
        System.out.println("\nJugadores:");
        for (Jugador j : casa.getJugadores()) {
            System.out.println("  " + j);
        }
        String id = leerTexto("ID del jugador: ");
        Jugador jugador = casa.buscarJugadorPorId(id);
        if (jugador == null) System.out.println("Jugador no encontrado.");
        return jugador;
    }

    private static EventoDeportivo seleccionarEvento() {
        if (casa.getEventos().isEmpty()) {
            System.out.println("No hay eventos registrados.");
            return null;
        }
        System.out.println("\nEventos:");
        for (EventoDeportivo e : casa.getEventos()) {
            System.out.println("  " + e);
        }
        String codigo = leerTexto("Código del evento: ");
        EventoDeportivo evento = casa.buscarEventoPorCodigo(codigo);
        if (evento == null) System.out.println("Evento no encontrado.");
        return evento;
    }

    private static TipoPronostico seleccionarPronostico(EventoDeportivo evento) {
        System.out.printf("1. LOCAL (%.2f)  2. EMPATE (%.2f)  3. VISITANTE (%.2f)%n",
                evento.getCuotaLocal(), evento.getCuotaEmpate(), evento.getCuotaVisitante());
        int op = leerEntero("Pronóstico (1/2/3): ");
        switch (op) {
            case 1 -> {
                return TipoPronostico.LOCAL;
            }
            case 2 -> {
                return TipoPronostico.EMPATE;
            }
            case 3 -> {
                return TipoPronostico.VISITANTE;
            }
            default -> {
                System.out.println("Opción inválida.");
                return null;
            }
        }
    }

    // --- helpers de entrada ---

   private static String leerTexto(String mensaje) {
        while (true) {
           System.out.print(mensaje);
           String valor = scanner.nextLine().trim();
           if (valor.isEmpty()) {
            System.out.println("El campo no puede estar vacío.");
            continue;
           }
           return valor;
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim());
                if (valor <= 0) {
                    System.out.println("El valor debe ser mayor a 0.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
            }
        }
    }
}