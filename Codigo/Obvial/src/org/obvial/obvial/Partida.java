package org.obvial.obvial;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/***
 * 
 * @author Helen
 *
 */
public class Partida {
	
	private int idPartida;
	private Date fecha;
	private LinkedList<Jugador> listaJugadoresPartidaActual;
	
	
	public LinkedList<Jugador> obtenerListaJugadoresPartidaActual() {
		return listaJugadoresPartidaActual;
	}

	public Partida (int idP, LinkedList <Jugador> listaJPA ) {
		idPartida= idP;
		fecha=new Date();
		listaJugadoresPartidaActual=listaJPA;	
	}

	public int getIdPartida() {
		return idPartida;
	}

	public Date getFecha() {
		return fecha;
	}

}
