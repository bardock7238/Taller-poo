public class EventoDeportivo {

    private String codigo;
    private String descripcion;
    private double cuotaLocal;
    private double cuotaEmpate;
    private double cuotaVisitante;

    public EventoDeportivo(String codigo, String descripcion,
                           double cuotaLocal, double cuotaEmpate, double cuotaVisitante) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cuotaLocal = cuotaLocal;
        this.cuotaEmpate = cuotaEmpate;
        this.cuotaVisitante = cuotaVisitante;
    }

    // devuelve la cuota según el pronóstico elegido
    public double getCuotaPorPronostico(TipoPronostico pronostico) {
        return switch (pronostico) {
            case LOCAL -> cuotaLocal;
            case EMPATE -> cuotaEmpate;
            case VISITANTE -> cuotaVisitante;
            default -> 1.0;
        };
    }

    // getters y setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getCuotaLocal() { return cuotaLocal; }
    public void setCuotaLocal(double cuotaLocal) { this.cuotaLocal = cuotaLocal; }

    public double getCuotaEmpate() { return cuotaEmpate; }
    public void setCuotaEmpate(double cuotaEmpate) { this.cuotaEmpate = cuotaEmpate; }

    public double getCuotaVisitante() { return cuotaVisitante; }
    public void setCuotaVisitante(double cuotaVisitante) { this.cuotaVisitante = cuotaVisitante; }

    @Override
    public String toString() {
        return String.format("[%s] %s | Local: %.2f | Empate: %.2f | Visitante: %.2f",
                codigo, descripcion, cuotaLocal, cuotaEmpate, cuotaVisitante);
    }
}