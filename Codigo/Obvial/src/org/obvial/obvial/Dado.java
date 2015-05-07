package org.obvial.obvial;

import java.util.Random;

public class Dado {

	private static Dado miDado= new Dado(); 
	
	private Dado(){		
	}
	
	public int getTirada(){
		Random r= new Random();
		int tirada= r.nextInt(6)+1;
		return tirada;
	}
	
	public static Dado getDado(){
		return miDado;
	}
}
