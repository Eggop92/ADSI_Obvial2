package org.obvial.obvial;

import java.util.Observable;
import java.util.Random;

//Aqui almacenamos y gestionamos las tematicas de las preguntas.
//En cada una de las posiciones del array tenemos un arraylist
//con las preguntas de esa tematica.

public class Listas extends Observable{
	
	private static Listas misListas=new Listas();
	//Posicion 0 = Curiosidades
	//Posicion 1 = Series
	//Posicion 2 = Peliculas
	//Posicion 3 = Deportes
	//Posicion 4 = Videojuegos
	
	private ListaCartas[] listasDeCartas = new ListaCartas[5];
	
	//Constructora
	private Listas (){	
	}
	
	public static Listas getMisListas(){
		return misListas;
	}
	
	public void anadirListas(int pNum,ListaCartas pLis){
		if ((pNum <= 4)){
		listasDeCartas[pNum]=pLis;
		}
		else{
			System.out.println("El numero de lista introducido no es correcto.");
		}
	}

	//Escoge de manera aleatoria una carta del tema que le pasamos por parametro
	public Carta buscarCartaTema(int pTema){
		Carta carta = null;
		Random r= new Random();
		int tirada; 
		boolean escogida = false;
		int cont = 1;
		while (!escogida){
			tirada= r.nextInt(this.listasDeCartas[pTema].tamanio())+1;
			carta=this.listasDeCartas[pTema].buscarCarta(tirada);
			if(!carta.haSidoUsada()){
				escogida = true;
				carta.usarCarta();
			}
			
			cont ++;
			
			if(cont == this.listasDeCartas[pTema].tamanio()){
				this.listasDeCartas[pTema].reiniciarLista();
			}
		}
		setChanged();
		notifyObservers(carta);
		return carta;
	}
	
	//Pone el atributo usado de las cartas a false
	//para poder iniciar una nueva partida
	public void reiniciarListas(){
		int i=0;
		while (i<=4){
			listasDeCartas[i].reiniciarLista();
			i++;
		}
	}
	
	//Para pruebas
	public ListaCartas[] getListasDeCartas(){
		return this.listasDeCartas;
	}
}
