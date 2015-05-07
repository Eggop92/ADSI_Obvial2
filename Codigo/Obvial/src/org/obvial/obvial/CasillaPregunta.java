package org.obvial.obvial;

public class CasillaPregunta extends Casilla{

	private Categoria categoria;
	
	public CasillaPregunta(int pNumero, int pClase, int pPosX, int pPosY, Categoria pCategoria) {
		super(pNumero, pClase, pPosX, pPosY);
		categoria=pCategoria;
		// TODO Auto-generated constructor stub
	}

	public Categoria getCategoria(){
		return this.categoria;
	}
	
	
	
}
