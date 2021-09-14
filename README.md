## BoardService 

La clase BoardService es la clase principal encarga del manipulacion del tablero, se utiliza el patron FACADE para delegar en este las tareas para que no recaigan en el controlador. 

BoardService implementa la interfaz IBoardService que le indica los metodos que debe implementar 

Internamente tiene las propiedades  
```
private Graph _graph; 

private Boolean[] _arr; 

private final int _minLightsQty = 6; 

private int _matLength; 

private int _winCount; 
```
_graph : Es un grafo con una cantidad de vertices igual a la cantidad de casilleros del tablero, ademas al inicializar la clase recorre una matriz de vertices que detecta los vertices adjacentes y crea una arista entre cada uno de ellos y el vertice actual del recorrido 

_arr: Un arreglo de booleanos que que es utilizado para encender y apagar las luces del tablero, basicamente, representa el tablero 

_minLightsQty: Cantidad minima de luces que pueden estar encendidas en el tablero inicialmente 

_matLength: Propiedad que facilita la consulta del tamanio que deberia tener el tablero si fuera representado como una matriz de N X N

_winCount: Contador que especifica la cantidad de luces encendidas en el tablero, si la cantidad es igual al tamanio del arreglo de booleanos el usuario gana la partida 

Se encarga de:
- inicializar el tablero con luces prendidas aleatoriamente
- obtener una matriz de booleanos que representa el tablero haciendo una conversion de arreglo a matriz
- actualizar las luces del tablero a partir de un vertice dado
- obtener un vertice a partir de una fila y columna
- saber si gano o no la partida
- Obtener la longitud de la matriz del tablero

## BoardController 

La clase BoardController utiliza la interfaz IBoardService mediante inyeccion de dependencia para que en tiempo de ejecucion en el constructor se determine cual implementacion de la interfaz usar

Se encarga de, mediante IBoardService:
- obtener una matriz de booleanos que representa el tablero
- actualizar las luces del tablero a partir de una combinacion de fila-columna dada
- saber si gano o no la partida

## MainWindow

Clase que se encarga de la visualizacion del juego en una ventana

Utiliza un tablero, un boton para iniciar, un contador de puntaje y un mensaje de saludo al ganar.

Se puede jugar apretando las celdas del tablero y cada click contara como 1 movimiento y el tablero se obtiene a partir del boardController