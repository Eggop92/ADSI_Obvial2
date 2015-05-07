package org.obvial.obvial;

public class CasillaRetencion extends Casilla{
	
	private int turnosRetencion;
	
	public CasillaRetencion(int pNumero, int pRet, int pClase, int pPosX, int pPosY){
		super (pNumero, pClase, pPosX, pPosY);
		turnosRetencion = pRet;
	}
	
	public int getTurnosRetencion(){
		return turnosRetencion;
	}
	

}
