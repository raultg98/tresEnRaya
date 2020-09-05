package tresEnRaya;

public class Tablero {
	/*****    CONSTANTES    *****/
	public final static char FICHA_JUGADOR = 79; // O
	public final static char FICHA_MAQUINA = 88; // X
	
	/*****    ATRIBUTOS    *****/
	private char[][] tablero;
	private boolean ganador;
	private int colocadas;
	
	/*****    CONSTRUCTOR    *****/
	public Tablero() {
		tablero = new char[3][3];
		ganador  = false;
		colocadas = 0;
	}
	
	/*****    SETTER / GETTERS / ADD    *****/  
	public boolean getGanador(){
		return this.ganador;
	}
	
	public int getColocadas() {
		return this.colocadas;
	}
	
	/**
	 * Método que me coloca una ficha en un determinada posición siempre que se pueda.
	 * @param i, posicion I donde se quiere colocar la ficha.
	 * @param j, posicion J donde se quiere colocar la ficha.
	 * @param ficha, ficha que se desea colocar.
	 * @return TRUE en caso de colocarse la ficha correctamente.
	 * @throws Exception, Si alguno de los parametros no sea válido.
	 */
	public void colocarFicha(int fila, int columna, char ficha) {
		// Tenemos que comprobar que tenemos hueco en esa posición
		if(existeHueco(fila, columna)) {
			// Colocamos la ficha.
			tablero[fila][columna] = ficha;
			// Comprobamos si ya hay ganador.
			if(hayGanador(fila, columna, ficha)) {
				this.ganador = true;
				
			}
			
		}// ARRIBA ESPAÑA BRO

	}
	
	/*****    MÉTODOS    *****/
	/* Método que me comprueba si al poner una ficha tenemos un ganador o no.
	 * @param fila, fila en la que se ha colocado la ultima ficha.
	 * @param columna, columna en la que se ha colocado la ultima ficha.
	 * @return TRUE en caso de existir ganador. FALSE en caso contrario.
	 */
	public boolean hayGanador(int fila, int columna, char ficha) {
		boolean ganador = false;
		
		if(!ganador) {
			ganador = comprobarHorizontal(fila, columna, ficha);
		}
		if(!ganador) {
			ganador = comprobarVertical(fila, columna, ficha);
		}
		if(!ganador) {
			ganador = comprobarDiagonalPrin(fila, columna, ficha);
		}
		if(!ganador) {
			ganador = comprobarDiagonalSec(fila, columna, ficha);
		}

		return ganador;
	}
	
	public boolean comprobarHorizontal(int fila, int columna, char ficha) {
		boolean encontrado = false;
		int contadorSeguidos = 0;
		int i=fila-2;
		
		// Recorremos el tablero de forma horizontal.
		while(!encontrado && i <= fila+2 && contadorSeguidos < 3) {
			// Tenemos que comprobar de que la posicion sea valida.
			if(i >= 0 && i <=2) {
				// Tenemos que comprobar si se corresponde con la ficha indicada
				if(tablero[i][columna] == ficha) {
					// En caso de coincidir incrementamos el contador.
					contadorSeguidos++;
				}else 
					// En caso contrario ponemos el contador a 0
					contadorSeguidos = 0;
			}
			i++;
		}
		
		if(contadorSeguidos == 3) {
			encontrado = true;
		}

		return encontrado;
	}
	public boolean comprobarVertical(int fila, int columna, char ficha) {
		boolean encontrado = false;
		int contadorSeguidos = 0;
		int j=columna-2;
		
		// Recorremos el tablero de forma horizontal.
		while(!encontrado && j <= columna+2 && contadorSeguidos < 3) {
			// Tenemos que comprobar de que la posicion sea valida.
			if(j >= 0 && j <=2) {
				// Tenemos que comprobar si se corresponde con la ficha indicada
				if(tablero[fila][j] == ficha) {
					// En caso de coincidir incrementamos el contador.
					contadorSeguidos++;
				}else 
					// En caso contrario ponemos el contador a 0
					contadorSeguidos = 0;
			}
			j++;
		}
		
		if(contadorSeguidos == 3) {
			encontrado = true;
		}

		return encontrado;
	}
	
	public boolean comprobarDiagonalPrin(int fila, int columna, char ficha) {
		boolean encontrado = false;
		int contadorSeguidos = 0;
		
		int i = fila-2;
		int j = columna-2;
		
		// Recorremos el tablero de forma horizontal.
		while(!encontrado && i <= fila+2 && j<= columna+2 && contadorSeguidos < 3) {
			// Tenemos que comprobar que la posicion sea valida
			if((i >= 0 && i <= 2)&&(j >= 0 && j <= 2)) {
				// Tenemos que comprobar si se corresponde con la ficha indicada
				if(tablero[i][j] == ficha) {
					//En caso de coincidir incrementamos el contador.
					contadorSeguidos++;
				}else contadorSeguidos = 0;
			}
			i++; j++;
		}
		
		if(contadorSeguidos == 3) {
			encontrado = true;
		}

		return encontrado;
	}
	
	public boolean comprobarDiagonalSec(int fila, int columna, char ficha) {
		boolean encontrado = false;
		int contadorSeguidos = 0;
		
		int i = fila-2;
		int j = columna-2;
		
		// Recorremos el tablero de forma horizontal.
		while(!encontrado && i <= fila+2 && j<= columna-2 && contadorSeguidos < 3) {
			// Tenemos que comprobar que la posicion sea valida
			if((i >= 0 && i <= 2)&&(j >= 0 && j <= 2)) {
				// Tenemos que comprobar si se corresponde con la ficha indicada
				if(tablero[i][j] == ficha) {
					//En caso de coincidir incrementamos el contador.
					contadorSeguidos++;
				}else contadorSeguidos = 0;
			}
			i++; j++;
		}
		
		if(contadorSeguidos == 3) {
			encontrado = true;
		}

		return encontrado;
		
	}
	
	/**
	 * Método que me comprueba si una posicion en concreto esta vacia o no.
	 * @param i, posicion i que se desea comprobar.
	 * @param j, posicion j que se desea comprobar.
	 * @return TRUE en casao de existir hueco. FALSE en caso contrario.
	 */
	boolean existeHueco(int i, int j) {
		if(tablero[i][j] == 0) {
			return true;
		}else return false;
	}
	
	public void imprimirTablero() {
		for(int i=0; i<tablero.length; i++) {
			System.out.print("| ");
			for(int j=0; j<tablero.length; j++) {
				if(tablero[i][j] == 0) {
					System.out.print("*" +" | ");
				}else System.out.print(tablero[i][j] +" | ");
				
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/*****    MÉTODOS DE VALIDACIÓN    *****/   
	public boolean validacionPosicion(int fila, int columna) {
		if((fila >= 0 && fila <= 2)&&(columna >= 0 && columna <= 2)) {
			return true;
		}else return false;
	}
}