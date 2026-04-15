public abstract class Persona {

    // accesible desde subclases
    protected String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // cada subclase define cómo se identifica
    public abstract String getIdentificador();
}