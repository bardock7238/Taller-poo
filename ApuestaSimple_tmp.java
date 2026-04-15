public class ApuestaSimple extends Apuesta {

    // una sola línea de apuesta
    private LineaApuesta linea;

    public ApuestaSimple(double monto, Jugador jugador, LineaApuesta linea) {
        super(monto, jugador);
        this.linea = linea;
    }

    // ganancia = monto × cuota de la línea
    @Override
    public double calcularGananciaPotencial(double monto) {
        return monto * linea.getCuotaSeleccionada();
    }

    public LineaApuesta getLinea() { return linea; }

    @Override
    public String toString() {
        return String.format("ApuestaSimple | %s | Monto: $%.2f | %s | Ganancia potencial: $%.2f",
                jugador.getNombre(), monto, linea, calcularGananciaPotencial(monto));
    }
}