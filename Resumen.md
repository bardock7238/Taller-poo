# Taller POO

## Agregación
La clase `CasaDeApuestas` agrupa instancias de `Jugador` y `Evento`. Sin embargo, estos objetos pueden existir de forma independiente.  
Es decir, si la casa de apuestas deja de existir, los jugadores y eventos continúan, por lo que la relación es de agregación y no de composición.

## Composición
Las `LineaApuesta` forman parte de un `TicketApuesta` y no tienen sentido fuera de él.  
Si el ticket se elimina, todas sus líneas asociadas también desaparecen.  
Esto representa una relación de composición.

## Asociación
La clase `Jugador` mantiene una lista de sus `TicketApuesta`.  
En este caso, el jugador solo referencia los tickets, pero no controla directamente su ciclo de vida (creación o destrucción).  
Esta relación se define como una asociación.

## Clase abstracta
Las clases `Persona` y `Apuesta` son abstractas, lo que significa que no pueden instanciarse directamente.  
Su propósito es definir una estructura base y comportamiento común que será implementado o extendido por sus subclases.

## Interfaz
La interfaz `CalculableGanancia` establece un contrato que obliga a implementar el método de cálculo de ganancias.  
Gracias a esto, diferentes tipos de apuestas, como `ApuestaSimple` y `ApuestaMultiple`, pueden ser tratadas de manera uniforme, independientemente de cómo calculen internamente sus resultados.