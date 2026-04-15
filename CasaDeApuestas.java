import java.util.ArrayList;
import java.util.List;

public class CasaDeApuestas {

    private String nombre;
    // agregación: jugadores y eventos existen independientemente
    private List<Jugador> jugadores;
    private List<EventoDeportivo> eventos;
    private List<TicketApuesta> tickets;

    public CasaDeApuestas(String nombre) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
        this.eventos = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    // agrega un jugador a la casa
    public void registrarJugador(Jugador j) {
        jugadores.add(j);
        System.out.println("Jugador registrado: " + j);
    }

    // agrega un evento a la casa
    public void registrarEvento(EventoDeportivo e) {
        eventos.add(e);
        System.out.println("Evento registrado: " + e);
    }

    // agrega un ticket y lo vincula al jugador (asociación bidireccional)
    public void registrarTicket(TicketApuesta t) {
        tickets.add(t);
        t.getJugador().agregarTicket(t);
        System.out.println("Ticket registrado: " + t.getCodigo());
    }

    // muestra todos los tickets registrados
    public void listarTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets registrados.");
            return;
        }
        System.out.println("\n=== TICKETS ===");
        for (TicketApuesta ticket : tickets) {
            System.out.println(ticket);
        }
    }

    // busca un jugador por su id
    public Jugador buscarJugadorPorId(String id) {
        for (Jugador j : jugadores) {
            if (j.getId().equalsIgnoreCase(id)) return j;
        }
        return null;
    }

    // busca un evento por su codigo
    public EventoDeportivo buscarEventoPorCodigo(String codigo) {
        for (EventoDeportivo e : eventos) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) return e;
        }
        return null;
    }

    // getters
    public String getNombre() { return nombre; }

    public List<Jugador> getJugadores() { return jugadores; }

    public List<EventoDeportivo> getEventos() { return eventos; }

    public List<TicketApuesta> getTickets() { return tickets; }
}