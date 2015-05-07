
import java.awt.Color;

import org.obvial.obvial.GestorPartida;
import org.obvial.obvial.GestorUsuarios;
import org.obvial.obvial.ListaJugadores;
import org.obvial.obvial.Partida;
import org.obvial.obvial.Usuario;

import junit.framework.TestCase;

public class pruebasDavid extends TestCase {

	public void testCargarPartidas()
	{
		GestorPartida.getGestorPartidas().cargarPartidasGuardadas();
		System.out.println(GestorPartida.getGestorPartidas().getListaPartidasGuardadas().size());
		Partida partidaAux = GestorPartida.getGestorPartidas().getListaPartidasGuardadas().get(0);
		System.out.println(partidaAux.obtenerListaJugadoresPartidaActual().size()+"blobloblo");
		
		System.out.println(partidaAux.obtenerListaJugadoresPartidaActual().get(0).getUsuario().getNombre());
		System.out.println(partidaAux.obtenerListaJugadoresPartidaActual().get(1).getUsuario().getNombre());
		System.out.println(partidaAux.obtenerListaJugadoresPartidaActual().get(2).getUsuario().getNombre());
		
		partidaAux = GestorPartida.getGestorPartidas().getListaPartidasGuardadas().get(1);
		System.out.println(partidaAux.obtenerListaJugadoresPartidaActual().size());
		
		System.out.println(partidaAux.obtenerListaJugadoresPartidaActual().get(0).getUsuario().getNombre());
		System.out.println(partidaAux.obtenerListaJugadoresPartidaActual().get(1).getUsuario().getNombre());
		
		partidaAux = GestorPartida.getGestorPartidas().getListaPartidasGuardadas().get(2);
		System.out.println(partidaAux.obtenerListaJugadoresPartidaActual().size());
		
		System.out.println(partidaAux.obtenerListaJugadoresPartidaActual().get(0).getUsuario().getNombre());
		System.out.println(partidaAux.obtenerListaJugadoresPartidaActual().get(1).getUsuario().getNombre());
	}
	
	
	public void testCargarDatosUsuario()
	{
		//Pre: siempre se pedira un usuario existente ya que el metodo que lo ha llamado
		//	   lo ha comprobado anteriormente
		//Pre: EL usuario nunca será administrador ya que este metodo solo es llamado desde logeoJugador
		
		//Comprobar que cuando cargamos un usuario es el correcto
		//Los datos son comparados con los que tenemos en nuestra base de datos
		Usuario pUsuario;
		
		pUsuario= GestorUsuarios.getGestorUsuarios().cargarDatosUsuario("Aaron");
		assertFalse(pUsuario.getNombre()==null);
		assertEquals(pUsuario.getNombre(),"Aaron");
		assertEquals(pUsuario.getNumPregAcer(),1);
		assertEquals(pUsuario.getNumPregTotal(),1);
		
		pUsuario= GestorUsuarios.getGestorUsuarios().cargarDatosUsuario("David");
		assertFalse(pUsuario.getNombre()==null);
		assertEquals(pUsuario.getNombre(),"David");
		assertEquals(pUsuario.getNumPregAcer(),1);
		assertEquals(pUsuario.getNumPregTotal(),1);
		
		pUsuario= GestorUsuarios.getGestorUsuarios().cargarDatosUsuario("Egoitz");
		assertFalse(pUsuario.getNombre()==null);
		assertEquals(pUsuario.getNombre(),"Egoitz");
		assertEquals(pUsuario.getNumPregAcer(),0);
		assertEquals(pUsuario.getNumPregTotal(),0);
		
		pUsuario= GestorUsuarios.getGestorUsuarios().cargarDatosUsuario("Endika");
		assertFalse(pUsuario.getNombre()==null);
		assertEquals(pUsuario.getNombre(),"Endika");
		assertEquals(pUsuario.getNumPregAcer(),0);
		assertEquals(pUsuario.getNumPregTotal(),1);
	} 
	
	public void testAnadirJugador(){
		//Pre: nunca se van a poder añadir mas de 4 jugadores, la grafica no lo permite
		Usuario pUsuario;
		ListaJugadores.getListaJugadores().vaciarLista();
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().isEmpty());
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().size()==0);

		pUsuario= GestorUsuarios.getGestorUsuarios().cargarDatosUsuario("Aaron");
		ListaJugadores.getListaJugadores().anadirJugador(pUsuario, null, false, "RED", 0);
		
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().size()==1);
		assertFalse(ListaJugadores.getListaJugadores().getListaJ().isEmpty());
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getUsuario().getNombre().equals("Aaron"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getRetencion()==0);
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getColor().equals("RED"));
		
		pUsuario= GestorUsuarios.getGestorUsuarios().cargarDatosUsuario("David");
		ListaJugadores.getListaJugadores().anadirJugador(pUsuario, null, false, "BLUE", 0);
		
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().size()==2);
		assertFalse(ListaJugadores.getListaJugadores().getListaJ().isEmpty());
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getUsuario().getNombre().equals("Aaron"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getRetencion()==0);
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getColor().equals("RED"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(1).getUsuario().getNombre().equals("David"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(1).getRetencion()==0);
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(1).getColor().equals("BLUE"));
		
		pUsuario= GestorUsuarios.getGestorUsuarios().cargarDatosUsuario("Egoitz");
		ListaJugadores.getListaJugadores().anadirJugador(pUsuario, null, false, "YELLOW", 0);
		
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().size()==3);
		assertFalse(ListaJugadores.getListaJugadores().getListaJ().isEmpty());
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getUsuario().getNombre().equals("Aaron"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getRetencion()==0);
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getColor().equals("RED"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(1).getUsuario().getNombre().equals("David"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(1).getRetencion()==0);
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(1).getColor().equals("BLUE"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(2).getUsuario().getNombre().equals("Egoitz"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(2).getRetencion()==0);
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(2).getColor().equals("YELLOW"));
		
		pUsuario= GestorUsuarios.getGestorUsuarios().cargarDatosUsuario("Sara");
		ListaJugadores.getListaJugadores().anadirJugador(pUsuario, null, false, "GREEN", 0);
		
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().size()==4);
		assertFalse(ListaJugadores.getListaJugadores().getListaJ().isEmpty());
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getUsuario().getNombre().equals("Aaron"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getRetencion()==0);
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getColor().equals("RED"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(1).getUsuario().getNombre().equals("David"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(1).getRetencion()==0);
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(1).getColor().equals("BLUE"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(2).getUsuario().getNombre().equals("Egoitz"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(2).getRetencion()==0);
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(2).getColor().equals("YELLOW"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(3).getUsuario().getNombre().equals("Sara"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(3).getRetencion()==0);
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(3).getColor().equals("GREEN"));
		
		//No se pueden añadir mas, la lista no cambia
		pUsuario= GestorUsuarios.getGestorUsuarios().cargarDatosUsuario("Helen");
		ListaJugadores.getListaJugadores().anadirJugador(pUsuario, null, false, "PINK", 0);
		
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().size()==4);
		assertFalse(ListaJugadores.getListaJugadores().getListaJ().isEmpty());
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getUsuario().getNombre().equals("Aaron"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getRetencion()==0);
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(0).getColor().equals("RED"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(1).getUsuario().getNombre().equals("David"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(1).getRetencion()==0);
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(1).getColor().equals("BLUE"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(2).getUsuario().getNombre().equals("Egoitz"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(2).getRetencion()==0);
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(2).getColor().equals("YELLOW"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(3).getUsuario().getNombre().equals("Sara"));
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(3).getRetencion()==0);
		assertTrue(ListaJugadores.getListaJugadores().getListaJ().get(3).getColor().equals("GREEN"));
		
	}
}
