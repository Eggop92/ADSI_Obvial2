package org.obvial.obvial;

import java.util.Scanner;
/**@unUsed
 * 
 * MODIFICADO POR BOOLEAN
 *
 */
//Esta clase la hicimos por si no nos daba tiempo a acabar la interfaz grafica. Si esto ocurriese,
//con esta clase podriamos comprobar el correcto funcionamiento del modelo.

public class Visualizacion {
	
	private static Visualizacion miVisualizacion = new Visualizacion();
	private static Scanner sc = new Scanner(System.in);
	
	public Visualizacion(){
		
	}
	
	public static Visualizacion getVisualizacion(){
		return miVisualizacion;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Inicializar.getInicializar().InicializarCartas();
		Inicializar.getInicializar().InicializarCasillas();
		miVisualizacion.iniciarJugadores();
		Tablero.getTablero().jugar();
		
	}
	
	public Boolean visualizarCartayRecogerRespuesta(Carta pCarta){
		System.out.println(pCarta.getPregunta());
		System.out.println("A)"+pCarta.getResp1());
		System.out.println("B)"+pCarta.getResp2());
		System.out.println("C)"+pCarta.getResp3());
		System.out.println("D)"+pCarta.getResp4());
		System.out.print("Respuesta: ");
		String respuesta = sc.nextLine();
		return pCarta.esCorrecta(respuesta);
	}
	
	//Aqui no hemos tenido en cuenta que haya mas de un jugador con el mismo color de ficha
	//Si esto pasase, a la hora de añadirlos a la casilla, con uno de ellos nos daria el error
	//de que no se puede aniadir una ficha que ya esta en la casilla, ya que se comprueba el color
	//de la ficha del jugador.
	public void iniciarJugadores(){
		
		int respuesta = 100;
		
		do{
			int cont = 0;
			System.out.println("¿Cuántos jugadores van a jugar?");
			respuesta = sc.nextInt();
			
			if(respuesta >= 1 && respuesta <= 4){
				while(cont<respuesta){
					System.out.println("Nombre: ");
					String nombre = sc.next();
					System.out.println("Color de la ficha: ");
					String color = sc.next();
					//anadirJugador(Usuario pUsuario,Casilla pCasilla, boolean pLeToca, String pColor, int pTunosBloqueados)
					//ListaJugadores.getListaJugadores().anadirJugador(nombre, color);
					cont++;
				}
			}
			
		}while(respuesta>4);
		
	}
	
	public void visualizarTirada(String pNombre, int pTirada){
		System.out.println(pNombre+" ha sacado "+pTirada);
		System.out.println("Pulsa enter");
		sc.nextLine();
	}
	
	//Al ser una clase de prueba, hay casos que no hemos tenido en cuenta. Por ejemplo, si caemos en la casilla
	//6, avanzamos hasta la 12. Pero si caemos en la 12 retrocedemos hasta la 6. A la hora de retroceder,
	//el mensaje seguiria mostrando que se ha avanzado, aunque realmente ha retrocedido.
	public void visualizarAvance(Jugador pJugador){
		System.out.println(pJugador.getUsuario().getNombre()+" ha avanzado hasta la casilla "+pJugador.getPosicion());
		System.out.println("Pulsa enter");
		sc.nextLine();
	}
	
	public void visualizarTraslado(Jugador pJugador){
		System.out.println(pJugador.getUsuario().getNombre()+" ha sido trasladado hasta la casilla "+pJugador.getPosicion());
		System.out.println("Pulsa enter");
		sc.nextLine();
	}
	
	public void visualizarTurnosDeRetencion(Jugador pJugador, int pRetencion){
		System.out.println(pJugador.getUsuario().getNombre()+" estará retenido "+pRetencion);
		System.out.println("Pulsa enter");
		sc.nextLine();
	}
	
	public void visualizarRetencionPendiente(Jugador pJugador){
		System.out.println("Ha "+pJugador.getUsuario().getNombre()+" le queda/n "+pJugador.getRetencion()+" turno/s de retención ");
		System.out.println("Pulsa enter");
		sc.nextLine();
	}
	
	public void visualizarEnhorabuena(){
		System.out.println("Enhorabuena, has acertado");
		System.out.println("Vuelve a tirar");
		System.out.println("Pulsa enter");
		sc.nextLine();
	}
	
	public void visualizarConsuelo(){
		System.out.println("Lo siento, no has acertado");
		System.out.println("Turno del siguiente jugador");
		System.out.println("Pulsa enter");
		sc.nextLine();
	}
	
	public void visualizarHaGanado(Jugador pJugador){
		System.out.println("¡¡¡Enhorabuena!!! Has contestado bien todas preguntas");
		System.out.println(pJugador.getUsuario().getNombre()+" ha ganado el juego");
	}

}
