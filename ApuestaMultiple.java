import java.util.ArrayList;
import java.util.List;

public class ApuestaMultiple extends Apuesta {

    // varias líneas de apuesta
    private List<LineaApuesta> lineas;

    public ApuestaMultiple(double monto, Jugador jugador) {
        super(monto, jugador);
        this.lineas = new ArrayList<>();
    }

    // agrega una línea a la apuesta
    public void agregarLinea(LineaApuesta linea) {
        lineas.add(linea);
    }

    // ganancia = monto × producto de todas las cuotas
    @Override
    public double calcularGananciaPotencial(double monto) {
        double productoCuotas = 1.0;
        for (LineaApuesta linea : lineas) {
            productoCuotas *= linea.getCuotaSeleccionada();
        }
        return monto * productoCuotas;
    }

    public List<LineaApuesta> getLineas() { return lineas; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ApuestaMultiple | %s | Monto: $%.2f%n", jugador.getNombre(), monto));
        for (LineaApuesta linea : lineas) {
            sb.append("  → ").append(linea).append("\n");
        }
        sb.append(String.format("  Ganancia potencial: $%.2f", calcularGananciaPotencial(monto)));
        return sb.toString();
    }
}