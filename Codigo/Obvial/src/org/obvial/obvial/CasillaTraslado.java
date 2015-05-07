package org.obvial.obvial;

public class CasillaTraslado extends Casilla{
	
	//Casilla a la que se desplaza
	private int casillaDesp;
	
	public CasillaTraslado(int pNumero, int pDesp, int pClase, int pPosX, int pPosY){
		super (pNumero, pClase, pPosX, pPosY);
		casillaDesp = pDesp;
	}
	
	public int getCasillaDesp(){
		return casillaDesp;
	}

}
