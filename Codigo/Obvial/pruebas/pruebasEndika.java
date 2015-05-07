/**TEST REALIZADO POR ENDIKA*/

import java.sql.Date;
import java.util.LinkedList;

import org.obvial.obvial.GestorPartida;
import org.obvial.obvial.Jugador;
import org.obvial.obvial.ListaJugadores;
import org.obvial.obvial.Partida;
import org.obvial.obvial.Usuario;

import junit.framework.TestCase;

public class pruebasEndika extends TestCase {  
	private static final LinkedList<Jugador> lista1 = null;

	public static void main(String[] args){

	}

    		
	protected void setUp() throws Exception {
		//Anadimos dos jugadores, ya que la grafica nos impide intentar cargar con menos
		Usuario usuarioAux = new Usuario("Endika", null, null, false, 1, 1);
		Jugador jugadorAux = new Jugador (usuarioAux,null , "RED", false, 0);
		ListaJugadores.getListaJugadores().anadirJugador(jugadorAux);
		
		usuarioAux = new Usuario("Helen", null, null, false, 1, 1);
		jugadorAux = new Jugador (usuarioAux,null , "BLUE", false, 0);
		ListaJugadores.getListaJugadores().anadirJugador(jugadorAux);
		
		GestorPartida.getGestorPartidas().cargarPartidasGuardadas();
	}

	public void testCargarPartida() {
		System.out.println(ListaJugadores.getListaJugadores().getListaJ().size());
		GestorPartida.getGestorPartidas().cargarPartida();
		Jugador jugadorAux= ListaJugadores.getListaJugadores().buscarJugador("Endika");
		assertTrue(jugadorAux.getUsuario().getNombre().equals("Endika"));
	}
}