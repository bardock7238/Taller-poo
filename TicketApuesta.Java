import java.util.ArrayList;
import java.util.List;

public class TicketApuesta {

    private String codigo;
    private Jugador jugador;
    // composición: las líneas viven dentro del ticket
    private List<LineaApuesta> lineas;
    private double montoTotal;

    public TicketApuesta(String codigo, Jugador jugador, double montoTotal) {
        this.codigo = codigo;
        this.jugador = jugador;
        this.montoTotal = montoTotal;
        this.lineas = new ArrayList<>();
    }

    // agrega una línea al ticket
    public void agregarLinea(LineaApuesta linea) {
        lineas.add(linea);
    }

    // getters
    public String getCodigo() { return codigo; }

    public Jugador getJugador() { return jugador; }

    public List<LineaApuesta> getLineas() { return lineas; }

    public double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(double montoTotal) { this.montoTotal = montoTotal; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Ticket: %s | Jugador: %s | Monto: $%.2f%n",
                codigo, jugador.getNombre(), montoTotal));
        for (LineaApuesta linea : lineas) {
            sb.append("- ").append(linea).append("\n");
        }
        return sb.toString();
    }
}