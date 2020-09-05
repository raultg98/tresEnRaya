package tresEnRaya;

import java.util.Random;

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
	/**
	 * Método que me devulve si hay un ganador o no.
	 * @return TRUE en caso de existir un ganador. FALSE, en caso contrario.
	 */
	public boolean getGanador(){
		return this.ganador;
	}
	
	/**
	 * Método que me devuelve las fichas que tengo colocadas.
	 * @return INT, número de fichas colocadas.
	 */
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
			colocadas++;
			// Comprobamos si ya hay ganador.
			if(hayGanador(fila, columna, ficha)) {
				this.ganador = true;
			}
		}

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
	
	/**
	 * Método que me comprueba si con la última ficha que coloque he ganado verticalmente.
	 * @param fila, fila en la que he colocado la última ficha.
	 * @param columna, columna en la que he colocado la última columna.
	 * @param ficha, última ficha que he colocado.
	 * @return TRUE en caso de hacer la linea. FALSE, en caso contrario.
	 */
	public boolean comprobarVertical(int fila, int columna, char ficha) {
		boolean encontrado = true;
		int i = fila-2;
		int contador = 0;
		
		while(encontrado && i<=fila+2 && contador < 3) {
			if(comprobarPosicion(i, columna)) {
				if(tablero[i][columna] != ficha) {
					encontrado = false;
				}else contador++;
				
			}
			i++;
		}
		
		if(contador != 3) {
			encontrado = false;
		}
		
		return encontrado;
	}
	
	/**
	 * Método que me comprueba si con la última ficha que coloque he ganado Horizontalmente.
	 * @param fila, fila en la que he colocado la última ficha.
	 * @param columna, columna en la que he colocado la última columna.
	 * @param ficha, última ficha que he colocado.
	 * @return TRUE en caso de hacer la linea. FALSE, en caso contrario.
	 */
	public boolean comprobarHorizontal(int fila, int columna, char ficha) {
		boolean encontrado = true;
		int contador = 0;
		int j = columna-2;
		
		while(encontrado && j<=columna+2 && contador < 3) {
			if(comprobarPosicion(fila, j)) {
				if(tablero[fila][j] != ficha) {
					encontrado = false;
				}else contador++;
			}
			j++;
		}
		
		if(contador != 3) {
			encontrado = false;
		}
		
		return encontrado;
	}
	
	/**
	 * Método que me comprueba si con la última ficha que coloque he ganado en la diagonal principal.
	 * @param fila, fila en la que he colocado la última ficha.
	 * @param columna, columna en la que he colocado la última columna.
	 * @param ficha, última ficha que he colocado.
	 * @return TRUE en caso de hacer la linea. FALSE, en caso contrario.
	 */
	public boolean comprobarDiagonalPrin(int fila, int columna, char ficha) {
		boolean encontrado = true;
		int contador = 0;
		
		int i = fila-2;
		int j = columna-2;
		
		while(encontrado && i<=fila+2 && j<=columna+2 && contador < 3) {
		
			if(comprobarPosicion(i, j)) {
				if(tablero[i][j] != ficha) {
					encontrado = false;
				}else contador++;
			}
			
			i++; 
			j++;
		}
		
		if(contador != 3) {
			encontrado = false;
		}
		
		return encontrado;
	}
	
	/**
	 * Método que me comprueba si con la última ficha que coloque he ganado en la diagonal secundaria.
	 * @param fila, fila en la que he colocado la última ficha.
	 * @param columna, columna en la que he colocado la última columna.
	 * @param ficha, última ficha que he colocado.
	 * @return TRUE en caso de hacer la linea. FALSE, en caso contrario.
	 */
	public boolean comprobarDiagonalSec(int fila, int columna, char ficha) {
		boolean encontrado = true;
		int contador = 0;
		
		int i = fila-2;
		int j = columna+2;

		while(encontrado && i<=fila+2 && j>=columna-2 && contador < 3) {
			if(comprobarPosicion(i, j)) {
				if(tablero[i][j] != ficha) {
					encontrado = false;
				}else contador++;
			}
			
			i++; 
			j--;
		}
		
		if(contador < 3) {
			encontrado = false;
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
		if(tablero[i][j] != 0) {
			return false;
		}else return true;
	}
	
	/**
	 * Método que me imprime el tablero.
	 */
	public void imprimirTablero() {
		for(int i=-1; i<tablero.length; i++) {
			if(i != -1) {
				System.out.print(i);
				System.out.print("| ");
			}else {
				
			}
			for(int j=0; j<tablero.length; j++) {
				 if(i == -1) {
					 System.out.print("   "+j);
				 }else {
					 if(tablero[i][j] == 0) {
						 System.out.print("*" +" | ");
					 }else System.out.print(tablero[i][j] +" | ");
				 }
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/*****    MÉTODOS DE VALIDACIÓN    *****/   
	/**
	 * Método mediante el cual compruebo si las posiciones que se le he pasado son correctas o no.
	 * @param fila, fila que se desea comprobar si esta dentro del tablero.
	 * @param columna, columna que se desea comprobar si esta dentro del tablero.
	 * @return TRUE, en caso de que la posicion sea valida. FALSE, en caso contrario.
	 */
	public boolean comprobarPosicion(int fila, int columna) {
		if((fila >= 0 && fila <= 2)&&(columna >= 0 && columna <= 2)) {
			return true;
		}else return false;
	}
	
	/*****    INTERACIÓN MÁQUINA    *****/
	/**
	 * Método con el que consigo simular las acciones de un jugador, es decir, hago de IA del ordenador.
	 */
	public void jugarMaquina() {
		Random r = new Random();
		
		int fila, columna;
		
		do {
			fila = r.nextInt(3);
			columna = r.nextInt(3);
		}while(!existeHueco(fila, columna));
		
		colocarFicha(fila, columna, FICHA_MAQUINA);
	}
	
}