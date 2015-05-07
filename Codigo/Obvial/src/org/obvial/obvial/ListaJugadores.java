package org.obvial.obvial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
/**
* MODIFICADO POR BOOLEAN
*
*/
public class ListaJugadores extends Observable{
	
	private static ListaJugadores miListaJugadores = new ListaJugadores();
	private LinkedList<Jugador> listaJ;
	
	private ListaJugadores(){
		listaJ = new LinkedList <Jugador> ();
	}
	
	public static ListaJugadores getListaJugadores(){
		return miListaJugadores;
	}
	
	public void anadirJugador(Jugador pJugador){
		listaJ.add(pJugador);
	}
	
	public void anadirJugador(Usuario pUsuario,Casilla pCasilla, boolean pLeToca, String pColor, int pTunosBloqueados){
		
		if (listaJ.size() == 4){
			System.out.println("No se pueden anadir mas jugadores");
		}else{
			if(listaJ.size() < 4){
				//Usuario ,Casilla pCAsilla, String pColorJugador,boolean pLeToca, 
				Jugador j = new Jugador (pUsuario,pCasilla , pColor, pLeToca, pTunosBloqueados);
				listaJ.add(j);
				setChanged();
				notifyObservers(j);
				//como el tablero no esta inicializado da error
				//ListaCasillas.getListaCasillas().anadirJugadorACasilla(j, j.getPosicion());
			}
		}
	}
	
	public Iterator <Jugador> getIterador(){
		return listaJ.iterator();
	}
	
	public Jugador buscarJugador (String pId){
		Iterator <Jugador> itrJ = this.getIterador();
	
		boolean enc = false;
		Jugador res = null;
		
		while (itrJ.hasNext() && enc == false){
			res = itrJ.next();
			if (res.compararId(pId)){
				enc = true;
			}
		}
		
		if ( enc == false){
			res = null;
		}
		
		return res;
	}
	
	public boolean algunoHaTerminado(){
		Iterator<Jugador> itrJ = this.getIterador();
		
		boolean ret = false;
		Jugador unJugador = null;
		
		while(itrJ.hasNext() && ret == false){
			unJugador = itrJ.next();
			if(unJugador.haFinalizado() == true){
				ret = true;
			}
		}
		
		return ret;
	}
	
	public void vaciarLista(){
		listaJ = new LinkedList<Jugador>();
	}
	

	public LinkedList <Jugador> getListaJ(){
		return listaJ;
	}

}
