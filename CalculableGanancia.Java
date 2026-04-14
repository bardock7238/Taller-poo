// contrato para cualquier clase que calcule ganancias
public interface CalculableGanancia {

    // recibe el monto apostado y devuelve la ganancia potencial
    double calcularGananciaPotencial(double monto);
}