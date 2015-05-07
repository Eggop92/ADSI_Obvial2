//Prueba de Egoitz
package org.obvial.obvial;

import java.util.ArrayList;
import java.util.Iterator;
/** mod en local **/

public class Casilla {
	
	private int numero;
	private ArrayList <Jugador> jugadoresEnCasilla;
	private int clase;
	private int posX;
	private int posY;
	
	public Casilla(int pNumero, int pClase, int pPosX, int pPosY){
		numero = pNumero;
		jugadoresEnCasilla = new ArrayList <Jugador> ();
		clase = pClase;
		posX = pPosX;
		posY = pPosY;
	}
	
	public int getClase(){
		return this.clase;
	}
	
	public int getNumero(){
		return this.numero;
	}
	
	//GetPosX y GetPosY los usamos para poder ubicar 
	//los labels con las imagenes de las fichas en el tablero
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}
	
	public void anadirJugadorACasilla(Jugador pJ){
		if (!esta (pJ)){
			jugadoresEnCasilla.add(pJ);
		}else{
			System.out.println("No se puede anadir una ficha que ya esta en la casilla");
		}
		
	}
	
	public void eliminarJugadorDeCasilla(Jugador pJ){
		if (esta(pJ)){
			jugadoresEnCasilla.remove(pJ);
		}
	}
	
	public Iterator <Jugador> getIterador(){
		return jugadoresEnCasilla.iterator();
	}
	
	//Comprueba si el jugador esta en la casilla comparando el color de la ficha
	public boolean esta(Jugador pJ){
		boolean res = false;
		Iterator <Jugador> itr = this.getIterador();
		Jugador aux = null;
		while (itr.hasNext() && res == false){
			aux = itr.next();
			if (aux.getColor() == pJ.getColor()){
				res = true;
			}
		}
		return res;
	}
	
	//Para las pruebas
	public int tamano(){
		return jugadoresEnCasilla.size();
	}

}
