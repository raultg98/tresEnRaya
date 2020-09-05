package tresEnRaya;

import java.util.Random;
import java.util.Scanner;

public class Start {

	// HACER EL SCANNER CON UN WHILE, TIPO LOS DE PYTHON PARA QUE LAS FICHAS Y POSICIONES INTRODUCIDAS 
	// SEAN VALIDAS. ENTONCES LAS POSICIONES Y FICHAS QUE YO PASO COMO PARAMETRO SIEMPRE SERAN VALIDAS
	// PREGUNTAR AL PRINCIPIO DE LA PARTIDA SI QUEREIS SER DOS PERSONAS O SOLAMENTE UNA. Y TENER 
	// ASIGANDA UNA DETERMINADA FICHA PARA CADA JUGADOR, EL JUGADOR NO PUEDE ESCOGER LA FICHA CON LA 
	// QUE QUIERE JUGAR. QUITAR TODO TIPO DE LANZAMIENTO DE EXCEPCIONES HASTA QUE NO LAS DOMINE DEL TODO.

	public static void main(String[] args) throws Exception {
		Tablero t = new Tablero();
		
		play(t);
	}	
	
	private static void play(Tablero tablero) {
		Scanner sc = new Scanner(System.in);
		Random r = new Random(2);
		
		int fila, columna;
		char turno = Tablero.FICHA_JUGADOR;
		
		System.out.println("Comienza la partida");
		tablero.imprimirTablero();
		System.out.println("Juegas con '0'");
		
		
		// MIENTRAS NO EXISTA GANADOR O QUEDEN POSIBLES MOVIMIENTOS.
		while(!tablero.getGanador() && tablero.getColocadas() < 9) {
			// TURNO JUGADOR.
			if(turno == Tablero.FICHA_JUGADOR) {
				// PEDIR LA POSICION
				do {
					System.out.println("Introduzca la fila (0-2): ");
					fila = sc.nextInt();
					System.out.println("Introduzca la columna (0-2): ");
					columna = sc.nextInt();
				}while(!tablero.validacionPosicion(fila, columna) && !tablero.existeHueco(fila, columna));
				
				// COLOCAR LA FICHA
					tablero.colocarFicha(fila, columna, turno);
				// CAMBIAR TURNO
				turno = Tablero.FICHA_MAQUINA;
			}
			// TURNO DE LA MÁQUINA.
			else {
				 //GENERAR POSICION
				do {
					fila = r.nextInt(3);
					columna = r.nextInt(3);
				}while(!tablero.validacionPosicion(fila, columna) && !tablero.existeHueco(fila, columna));
				
				// COLOCAR LA FICHA
				tablero.colocarFicha(fila, columna, turno);
				
				// CAMBIAR TURNO
				turno = Tablero.FICHA_JUGADOR;
				
			}
			tablero.imprimirTablero();
		}
		
		if(tablero.getGanador()) {
			if(turno == Tablero.FICHA_MAQUINA) {
				System.out.println("Ha ganado el jugador, la maquina ha perdido");
			}else System.out.println("Ha ganado la Maquina");
		}else System.out.println("La partida ha terminado en tablas");
	}
}

