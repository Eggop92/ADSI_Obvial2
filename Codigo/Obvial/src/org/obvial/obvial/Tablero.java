package org.obvial.obvial;

import java.util.Iterator;

// Esta clase nos sirve para hacer uso del modelo sin interfaz
// gráfica, es decir, de manera secuencial

public class Tablero {
	
	private static Tablero miTablero = new Tablero();
	private boolean enEjecucion = false;
	
	private Tablero(){}
	
	public static Tablero getTablero(){
		return miTablero;
	}
	
	public boolean getEnEjecucion(){
		return this.enEjecucion;
	}
	
	public static void main(String[] args) {
			
	}
	
	public void jugar(){
		
		this.enEjecucion = true;
		
		Jugador jugadorActual;
		
		//Iniciamos el iterador
		Iterator<Jugador> itrJug = ListaJugadores.getListaJugadores().getIterador();
		
		//Mientras no haya finalizado ningun jugador, seguimos
		while(ListaJugadores.getListaJugadores().algunoHaTerminado() == false){
			
			//Mientras turno sea true, sera el turno del jugador actual
			//Al cambiar a false, el turno pasara al siguiente
			Boolean turno = true;
			
			//Si estabamos en el ultimo reiniciamos el iterador
			//para volver al primero
			if(!itrJug.hasNext()){
				itrJug = ListaJugadores.getListaJugadores().getIterador();
			}
			
			//Cogemos un jugador
			jugadorActual = itrJug.next();
			
			//Si el jugador estuviese retenido en alguna casilla, no entraria en el while,
			//por lo que pasaria el turno al siguiente jugador.
			//Si el jugador esta en la ultima casilla, tampoco entraría en el while, ya que
			//tiene un tratamiento distinto.
			while(jugadorActual.getRetencion()== 0 && turno == true && jugadorActual.getPosicion().getNumero() != 63){
				
				//Tiramos el dado
				int tirada = Dado.getDado().getTirada();
				Visualizacion.getVisualizacion().visualizarTirada(jugadorActual.getUsuario().getNombre(), tirada);
				
				//Avanzamos hasta la casilla que nos corresponda
				jugadorActual.avanzar(tirada);
				Visualizacion.getVisualizacion().visualizarAvance(jugadorActual);
				
				//Obtenemos la posicion en la que se encuentra el jugador
				int posicion = jugadorActual.getPosicion().getNumero();
				
				//Obtenemos el tipo de casilla en la que se encuentra
				int tipoCasilla = ListaCasillas.getListaCasillas().getTipoCasilla(posicion);
				
				switch(tipoCasilla){
				
				//Casilla de pregunta
				case 1: turno = this.ejecutarCasillaNormal(turno);
						break;
						
				//Casilla de retencion
				case 2: turno = this.ejecutarCasillaRetencion(turno, jugadorActual, posicion);
						break;
						
				//Casilla de traslado
				case 3: this.ejecutarCasillaTraslado(turno, jugadorActual, posicion);
						break;
				}
				
			}
			
			if(turno && jugadorActual.getRetencion() != 0 && jugadorActual.getPosicion().getNumero() != 63){
				jugadorActual.actualizarRet();
				Visualizacion.getVisualizacion().visualizarRetencionPendiente(jugadorActual);
				turno = false;
			}
			
			//Al llegar a la ultima casilla se le hace al jugador una pregunta
			//de cada tema. Si falla alguna, pasa el turno al siguiente jugador.
			//Si en 3 turnos no acierta todas, vuelve a empezar
			if(jugadorActual.getPosicion().getNumero() == 63){
				if(jugadorActual.getRetencion() < 3){
					jugadorActual.actualizarRet();
				}
				
				if(jugadorActual.getRetencion() != 0){
					
					//Hacer preguntas, una de cada tipo
					//Si falla alguna nos salimos
					Boolean fallo = false;
					int cont = 0;
					int aciertos = 0;
					while(fallo == false && aciertos < 5){
						
						Carta unaCarta = Listas.getMisListas().buscarCartaTema(cont);
						
						while(unaCarta ==  null){
							unaCarta = Listas.getMisListas().buscarCartaTema(cont);
							if(unaCarta == null){
								Listas.getMisListas().reiniciarListas();
							}
						}
						
						//Visualizar carta
						//Recoger respuesta
						Boolean resp = Visualizacion.getVisualizacion().visualizarCartayRecogerRespuesta(unaCarta);
						
						if(resp){
							
							//Si acierta -> acierto+1
							aciertos++;
						}else{
							
							//Si falla -> fallo = true
							fallo = true;
						}
						
						cont++;
					}
					
					if(!fallo){
						jugadorActual.ganar();
						Visualizacion.getVisualizacion().visualizarHaGanado(jugadorActual);
						this.enEjecucion = false;
					}
					
				}else{
					
					//El jugador vuelve a empezar
					jugadorActual.moverACasilla(1);
					
				}
			}
		}
		
	}
	public boolean ejecutarCasillaNormal(boolean pTurno){
		
		//Obtenemos el tema de la carta que vamos a mostrar
		int tema = ListaCasillas.getListaCasillas().getTema();
		
		//Cogemos una carta de ese tema
		Carta unaCarta = Listas.getMisListas().buscarCartaTema(tema);
		
		//Mostramos la carta y recogemos su solución
		Boolean solucion = Visualizacion.getVisualizacion().visualizarCartayRecogerRespuesta(unaCarta);
	
		if(solucion){
			
			//Ha acertado
			Visualizacion.getVisualizacion().visualizarEnhorabuena();
			
		}else{
			
			//Ha fallado
			Visualizacion.getVisualizacion().visualizarConsuelo();
			pTurno = false;

		}
		return pTurno;
	}
	public boolean ejecutarCasillaRetencion(boolean pTurno, Jugador pJugador, int pPos){
		
		//Buscamos la casilla para saber los turnos de retencion
		CasillaRetencion unaCasilla = ListaCasillas.getListaCasillas().buscarCasillaRetencion(pPos);
		
		//Mostramos los turnos que va a estar retenido
		Visualizacion.getVisualizacion().visualizarTurnosDeRetencion(pJugador, unaCasilla.getTurnosRetencion());
		
		//Aplicamos al jugador los turnos de retencion
		pJugador.retener(unaCasilla.getTurnosRetencion());
		
		pTurno = false;
		
		return pTurno;
	}
	public boolean ejecutarCasillaTraslado(boolean pTurno, Jugador pJugador, int pPos){
		
		//Buscamos la casilla para saber a que casilla va a ser trasladado el jugador
		CasillaTraslado casillaTraslado = ListaCasillas.getListaCasillas().buscarCasillaTraslado(pPos);
		
		//Movemos al jugador de casilla
		pJugador.moverACasilla(casillaTraslado.getCasillaDesp());
		
		//Mostramos a que casilla ha sido movido
		Visualizacion.getVisualizacion().visualizarTraslado(pJugador);
		
		if(casillaTraslado.getNumero() == 58){
			pTurno = false;
		}
		return pTurno;
	}
}
