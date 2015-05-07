package org.obvial.obvial;

public class InfoUsuarioEnPartida {
	//private int idPartida;
	private Usuario usuario;
	private Casilla casilla;
	private int leToca;
	private String colorJugador;
	private int turnosBloqueados;
	private int posicionEnLaTirada;

/**METODOS CREADOS POR HELEN*/

	public InfoUsuarioEnPartida(/*int id,*/Usuario Usu,Casilla cas, int leT, String ColJ, int tB, int pT) {
		//idPartida= id;
		usuario=Usu;
		casilla=cas;
		leToca=leT;
		colorJugador=ColJ;
		turnosBloqueados=tB;
		posicionEnLaTirada=pT;
		
	}
	/*public int getIdPartida() {
		return idPartida;
	}*/

	public Usuario getUsuario() {
		return usuario;
	}

	public Casilla getCasilla() {
		return casilla;
	}

	public int getLeToca() {
		return leToca;
	}
	
	public String getColorJugador() {
		return colorJugador;
	}
	
	public int getTurnosBloqueados() {
		return turnosBloqueados;
	}
	
	public int getPosicionEnLaTirada() {
		return posicionEnLaTirada;
	}

	/** 
	 * compara solo el usuario de la partida
	 */
	public boolean equals(Object obj) {
		
		boolean es=false;
		if(obj instanceof InfoUsuarioEnPartida){
			if (usuario.equals(((InfoUsuarioEnPartida) obj).getUsuario())){
				es=true;
			}
		}
		return es;
	}
	
	
}
