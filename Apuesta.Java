// clase abstracta, no se instancia directamente
public abstract class Apuesta implements CalculableGanancia {

    // protected para que las subclases accedan sin getters
    protected double monto;
    protected Jugador jugador;

    public Apuesta(double monto, Jugador jugador) {
        this.monto = monto;
        this.jugador = jugador;
    }

    // cada subclase define su propio cálculo
    @Override
    public abstract double calcularGananciaPotencial(double monto);

    // getters y setters
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }
}