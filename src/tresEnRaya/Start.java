package tresEnRaya;

import java.util.Scanner;

public class Start {

	public static void main(String[] args) throws Exception {
		Tablero t = new Tablero();

		play(t);
	}	
	
	/**
	 * Método con el que comienza el juego, en el cual se configura la partida y los jugadores colocan sus fichas.
	 * @param tablero, tablero en el cual se desarrolla la partida.
	 */
	private static void play(Tablero tablero) {
		Scanner sc = new Scanner(System.in);
		
		int numJugadores;
		// POR DEFECTO LA FICHA DEL JUGADOR1 VA A SER LA 0 (79).
		char ficha = Tablero.FICHA_JUGADOR;
		
		System.out.println("Comienza la partida");
		System.out.println("Número de jugadores (1/2): ");
		numJugadores = sc.nextInt();
		
		// SI SON DOS JUGADORES, CADA JUGADOR PUEDE ESCOGER SU FICHA.
		if(numJugadores == 2) {
			do {
				System.out.println("Ficha del jugador1: ");
				System.out.println("X ---> 88");
				System.out.println("0 ---> 79");
				
				ficha = (char)(sc.nextInt());
			}while(ficha != Tablero.FICHA_JUGADOR && ficha != Tablero.FICHA_MAQUINA);
		}
		
		tablero.imprimirTablero();

		// MIENTRAS NO EXISTA GANADOR O QUEDEN POSIBLES MOVIMIENTOS.
		while(!tablero.getGanador() && tablero.getColocadas() < 9) {

			// EL PRIMER JUGADORES JUEGA
			turnoJugador(tablero, ficha);
			tablero.imprimirTablero();
			
			// HAGO LOS CAMBIOS DE TURNO, DEPENDIENDO SI JUEGO CONTRA LA MAQUINA O NO.
			if(numJugadores == 1) {
				tablero.jugarMaquina();
				tablero.imprimirTablero();
			}else ficha = cambiarTurno(ficha);
			
		}
		
		// IMPRIMO LOS GANADORES
		ganador(tablero, ficha, numJugadores);
		
	}
	
	/**
	 * Método que me pide por teclado la posicion en la que se va a colocar una determinada ficha.
	 * @param tablero, tablero en el que se esta jugando la partida.
	 * @param ficha, ficha que se desea colocar en una determinada posicion.
	 */
	private static void turnoJugador(Tablero tablero, char ficha) {
		Scanner sc = new Scanner(System.in);
		
		int fila, columna;
		int contador = 0;
		
		if(ficha == 88) {
			System.out.println("Es el turno de las X");
		}else System.out.println("Es el turno de las 0");
		
		System.out.println("Introduzca la posición.");
		do {
			if(contador != 0) {
				System.out.println("Introduce bien la posición.");
			}
			System.out.println("Introduzca la fila (0-2): ");
			fila = sc.nextInt();
			System.out.println("Introduzca la columna (0-2): ");
			columna = sc.nextInt();
		}while(!tablero.existeHueco(fila, columna) || !tablero.comprobarPosicion(fila, columna));
		
		tablero.colocarFicha(fila, columna, ficha);
	}
	
	/**
	 * Método por el cual se imprime por pantalla quien es el jugadore que ha ganado la partida.
	 * @param tablero, tablero en el que se juega la partida.
	 * @param ficha, la ultima ficha que se ha colocado, depende si estas jugando contra la maquina o no. 
	 * @param jugadores
	 */
	private static void ganador(Tablero tablero, char ficha, int jugadores) {
		
		if(tablero.getGanador()) {
			if(jugadores == 1) {
				if(ficha == Tablero.FICHA_JUGADOR) {
					System.out.println("El JUGADOR ha ganado a la MAQUINA");
				}else System.out.println("La MAQUINA ha ganado al JUGADOR");
			}else {
				if(ficha == 79) {
					System.out.println("El JUGADOR con las X ha ganado");
				}else System.out.println("El JUGADOR con las 0 ha ganado");
			}
		}else System.out.println("La partida ha terminado en EMPATE");
	}
	
	/**
	 * Método que me cambia el turno cuando en la partida hay dos jugadores.
	 * @param ficha, la ultima ficha que se ha colocado
	 * @return INT, me devuelve la ficha a la que le toca el siguiente turno
	 */
	private static char cambiarTurno(char ficha) {
		if(ficha == 88) {
			return 79;
		}else return 88;
	}
}
