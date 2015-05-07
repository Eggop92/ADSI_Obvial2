package org.obvial.obvial;

import java.util.ArrayList;
import java.util.Iterator;

//Aqui almacenamos y gestionamos las cartas de una misma tematica.

public class ListaCartas {

	private ArrayList<Carta> listaC;
	
	public ListaCartas (){
		listaC=new ArrayList<Carta>();
	}
	
	public void anadirCarta(Carta pC){
		if(!this.esta(pC)){
			listaC.add(pC);
		}
	}
	
	public void eliminarCarta(int pC){
		
		Carta carta = this.buscarCarta(pC);
		
		if (carta != null){
			listaC.remove(carta);
			}
	}
	
	public Carta buscarCarta(int pC){
		Iterator<Carta>itr= listaC.iterator();
		boolean encontrada=false;
		Carta nC=null;
		
		//Si no se encuentra la carta buscada, se devuelve una carta usada
		Carta ret = new Carta(0, "", "", "", "", "", "");
		ret.usarCarta();
		
		while (itr.hasNext() && !encontrada){
			nC=itr.next();
			if(nC.esEsaCarta(pC)){
				encontrada=true;
				ret = nC;
			}
		}
		return ret;
	}
	
	public boolean esta(Carta pC){
		return listaC.contains(pC);
	}
	
	public void reiniciarLista(){
		Iterator<Carta>itr= listaC.iterator();
		Carta nC=null;
		while (itr.hasNext()){
			nC=itr.next();
			nC.reiniciarCarta();
		}
	}
	
	public int tamanio(){
		return listaC.size();
	}
	
}
