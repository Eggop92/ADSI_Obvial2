package org.obvial.obvial;
/**
* MODIFICADO POR BOOLEAN
*
*/

public class Jugador {
	
	private Usuario usuario;//private String nombre;
	private Casilla casilla;//private int posicion;
	//private int id;
	private String colorJugador;//private int color;
	private boolean leToca;
	private boolean haFinalizado;
	private int turnosBloqueados;//private int retencion;//para cuando caiga en una casilla de retencion
	//private int posicionEnLaTirada;//el tablero tiene un jugador actual asi q no es necesario ya q comenzamos por el q le toca
	
	
	public Jugador (Usuario pUsuario,Casilla pCAsilla, String pColorJugador,boolean pLeToca, int pTunosBloqueados){
		
		usuario=pUsuario;
		casilla=pCAsilla;
		colorJugador=pColorJugador;
		leToca=pLeToca;
		haFinalizado=false;
		turnosBloqueados=pTunosBloqueados;	
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public Casilla getCasilla() {
		return casilla;
	}
	public String getColorJugador() {
		return colorJugador;
	}
	public boolean isLeToca() {
		return leToca;
	}
	public boolean isHaFinalizado() {
		return haFinalizado;
	}
	
	public boolean compararId(String pId){
		
		return (usuario.getNombre().equals(pId) );
	}
	
	public String getId(){
		return usuario.getNombre();
	}
	
	public void retener(int pRet){
		turnosBloqueados = pRet;
	}
	
	public int getRetencion(){
		return turnosBloqueados;
	}
	
	public void actualizarRet(){
		if(this.turnosBloqueados > 0){
			turnosBloqueados --;
		}
	}
	
	public Casilla getPosicion(){
		return this.casilla;
	}
	
	public String getColor(){
		return this.colorJugador;
	}
	
	public boolean haFinalizado(){
		return this.haFinalizado;
	}
	
	public void ganar(){
		this.haFinalizado = true;
	}
	
	public void avanzar(int pCantidad){
		
		//Posicion en la que se encontraria el jugador si avanza
		//el numero de casillas pasado por parametro 
		int posicionFutura = this.casilla.getNumero()+pCantidad;
		
		if(posicionFutura > 63){
			//Calculas cuantas posiciones "rebotas"
			posicionFutura = posicionFutura - 63;
			//Calculas a que posicion iras mediante tus posiciones de "rebote"
			this.moverACasilla(63 - posicionFutura);
		}else{
			this.moverACasilla(posicionFutura);
		}
	}
	
	public void moverACasilla(int pNumCasilla){
		ListaCasillas.getListaCasillas().eliminarJugadorDeCasilla(casilla.getNumero(), this);
		this.casilla = ListaCasillas.getListaCasillas().buscarCasilla(pNumCasilla);
		ListaCasillas.getListaCasillas().anadirJugadorACasilla(this, casilla);
	}
	


	/** HPAZ
	 * compara de un jugador solo el usuario
	 */
	public boolean equalsJugador(Object obj) {
		
		boolean es=false;
		if(obj instanceof Jugador){
			if (usuario.equals(((Jugador) obj).getUsuario())){
				es=true;
			}
		}
		return es;
	}
	

}