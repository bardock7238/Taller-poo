public class LineaApuesta {

    private EventoDeportivo evento;
    private TipoPronostico pronostico;
    private double cuotaSeleccionada;

    public LineaApuesta(EventoDeportivo evento, TipoPronostico pronostico) {
        this.evento = evento;
        this.pronostico = pronostico;
        // la cuota se asigna sola según el pronóstico
        this.cuotaSeleccionada = evento.getCuotaPorPronostico(pronostico);
    }

    // getters
    public EventoDeportivo getEvento() { return evento; }

    public TipoPronostico getPronostico() { return pronostico; }

    public double getCuotaSeleccionada() { return cuotaSeleccionada; }

    @Override
    public String toString() {
        return String.format("Evento: %s | Pronóstico: %s | Cuota: %.2f",
                evento.getDescripcion(), pronostico, cuotaSeleccionada);
    }
}