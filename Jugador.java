import java.util.ArrayList;
import java.util.List;

public class Jugador extends Persona {

    private String id;
    private double saldo;
    // asociación bidireccional con TicketApuesta
    private List<TicketApuesta> tickets;

    public Jugador(String id, String nombre, double saldo) {
        super(nombre); // llama al constructor de Persona
        this.id = id;
        this.saldo = saldo;
        this.tickets = new ArrayList<>();
    }

    // implementación obligatoria del método abstracto de Persona
    @Override
    public String getIdentificador() {
        return id;
    }

    // agrega un ticket a la lista del jugador
    public void agregarTicket(TicketApuesta ticket) {
        tickets.add(ticket);
    }

    // verifica si el jugador puede pagar el monto
    public boolean tieneSaldo(double monto) {
        return saldo >= monto;
    }

    // descuenta el monto al registrar una apuesta
    public void descontarSaldo(double monto) {
        this.saldo -= monto;
    }

    // getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public List<TicketApuesta> getTickets() { return tickets; }

    @Override
    public String toString() {
        return String.format("[%s] %s | Saldo: $%.2f | Tickets: %d",
                id, nombre, saldo, tickets.size());
    }
}