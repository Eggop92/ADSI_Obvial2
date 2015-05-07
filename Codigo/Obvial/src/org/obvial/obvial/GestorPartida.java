package org.obvial.obvial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;


public class GestorPartida {

	private LinkedList<Partida> listaPartidasGuardadas;
	//private LinkedList<Jugador> listaJugadoresQuePulsanGuardar;//guarda la lista de jugadores con todos sus datos en la partida actual
	private static GestorPartida miGestorPartidas;

/**METODO CREADO POR ENDIKA*/

/**Método constructor de la clase*/
	private GestorPartida(){
		listaPartidasGuardadas=new LinkedList<Partida>();
	}
	
	public static GestorPartida getGestorPartidas(){
		if (miGestorPartidas==null){
			miGestorPartidas= new GestorPartida();
		}
		return miGestorPartidas;
	}


	/**METODO CREADO POR HELEN*/

	public LinkedList<Partida> getListaPartidasGuardadas() {
		return listaPartidasGuardadas;
	}

/**METODO CREADO POR HELEN*/
	
	public Iterator <Partida> getIteradorPartidasGuardadas(){
		return listaPartidasGuardadas.iterator();
	}

	public void cargarPartidasGuardadas(){
		
		LinkedList<Jugador> listaJ= new LinkedList<Jugador>();
		boolean turno, admin;
		int idPar, max;
		try{
			String sqlpartida="SELECT count( * ) AS n FROM partida";
			ResultSet resNum = GestorBD.getInstance().consulta(sqlpartida);
			resNum.next();
			max=resNum.getInt("n");
		
			GestorBD.getInstance().cerrarConsulta(resNum);
			idPar=1;
			while(idPar <= max){
				listaJ= new LinkedList<Jugador>();
				ResultSet res2 = GestorBD.getInstance().consulta("SELECT * FROM infopartidas WHERE idPartida="+idPar+";");
				while(res2.next()){
					//res2.getInt(1);idPartida
					ResultSet resUsu = GestorBD.getInstance().consulta("SELECT * FROM usuarios WHERE nombre = '"+res2.getString(2)+"';");
					resUsu.next();
					if (resUsu.getInt(5)==1){
						admin=true;
					}else{
						admin=false;
					}
					Usuario usu3 = new Usuario(resUsu.getString(1),resUsu.getString(2),resUsu.getString(3),admin,resUsu.getInt(5),resUsu.getInt(6));
					GestorBD.getInstance().cerrarConsulta(resUsu);
					Casilla casi3= ListaCasillas.getListaCasillas().buscarCasilla(res2.getInt(3));
					if (res2.getInt(4)==0){
						turno=false;
					}else{
						turno=true;
					}
					Jugador j4=new Jugador(usu3,casi3,res2.getString(5),turno,res2.getInt(6));
					listaJ.add(j4);			
				}
				
				Partida p1=new Partida(idPar,listaJ);
				listaPartidasGuardadas.add(p1);
				idPar++;
				GestorBD.getInstance().cerrarConsulta(res2);
				
		}
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
/**METODO CREADO POR HELEN*/
	/**guardarPartidaActual
	 * pre: recibe la lista de jugadores de la interfaz(la partida q esta en curso)
	 * pos:guarda la partida en la BD
	 */
	public void guardarPartidaActual(LinkedList<Jugador> listaJugadoresQuePulsanGuardar) {
	
		Iterator <Partida> itrP= getIteradorPartidasGuardadas();//Iterador para recorrer las partidas guardadas
		Partida partidaAux = null;//inicializo  a null y luego Guardo la partida actual
		boolean partidasIguales=false;
		boolean jugador=false;//se cambia a true si consigue un jugador igual en una lista de jugadores
		boolean listaJugadoresIgual =true;
		LinkedList<Jugador> listaDeJugadoresEnPartidaAux;
		Jugador InfUserParaComparar;
		int nuevoIdP = 0; 
		int turno;
		
		while (itrP.hasNext()&& !partidasIguales){//miro todas las partidas
			partidaAux=itrP.next();//guardo la partida actual
			listaDeJugadoresEnPartidaAux=partidaAux.obtenerListaJugadoresPartidaActual();//guardo la lista de jugadores de esa partida guardada
			nuevoIdP=partidaAux.getIdPartida();
			//Comparo el tamaño de las dos listas de jugadores ya q si es diferente directamente paso a otra partida
			if (listaJugadoresQuePulsanGuardar.size() == listaDeJugadoresEnPartidaAux.size()){
				//me creo 2 iteradores para recorrer ambas listas si son iguales
				Iterator <Jugador> itrListaActual = listaJugadoresQuePulsanGuardar.iterator() ;//Iterator <Jugador> itrListaGuardada = listaJugadoresQuePulsanGuardar.iterator() ;
				Iterator <Jugador>itrListaGuardada=listaDeJugadoresEnPartidaAux.iterator();
				while (itrListaGuardada.hasNext()&& listaJugadoresIgual){ //bucle de las lista de jugadores actual
					InfUserParaComparar=itrListaActual.next();
					itrListaGuardada=listaDeJugadoresEnPartidaAux.iterator();//vuelvo a inicializar la lista de guardados
					while(itrListaGuardada.hasNext() && !jugador){//compara cada jugador guardado con el jugador actual
						jugador=itrListaGuardada.next().equalsJugador(InfUserParaComparar);//true si son iguales	
					}
					if (!jugador){
						listaJugadoresIgual=false;
					}
				}				
			}if(listaJugadoresIgual){
				partidasIguales=true;
			}
		}
		try{
			Jugador j;
			int cont=0;
			Date fecha=new Date();
		if (!partidasIguales){//aumento el id de la partida en caso de q no este guardada
			nuevoIdP= nuevoIdP++;//tomo en cuenta q las partidas estan guardadas en orden. es decir id 1, id 2...
			GestorBD.getInstance().insertar("INSERT INTO partida values ("+nuevoIdP+","+fecha+");");
			Iterator <Jugador> itrListaActual=listaJugadoresQuePulsanGuardar.iterator();
			
			while (itrListaActual.hasNext()){
				cont++;
				j=itrListaActual.next();
				if (j.isLeToca()){ turno=1;
				}else{ turno=0;}
				GestorBD.getInstance().insertar("INSERT INTO infopartidas"+
				" values ("+nuevoIdP+",'"+j.getUsuario().getNombre()+"',"+ j.getCasilla().getNumero()+","
				+turno+",'"+j.getColorJugador()+"',"+j.getRetencion()+","+cont+");");
			}
			
		}else{
			nuevoIdP= partidaAux.getIdPartida();
			Iterator <Jugador> itrListaActual=listaJugadoresQuePulsanGuardar.iterator();
			while (itrListaActual.hasNext()){
				cont++;
				j=itrListaActual.next();
				if (j.isLeToca()){
					turno=1;
				}else{
					turno=0;
				}
				GestorBD.getInstance().actualizar("UPDATE infopartidas SET numeroC="+j.getCasilla().getNumero()+
						", LeToca="+turno+", Color= '"+j.getColorJugador()+"' , TurnosBloqueado="+j.getRetencion()+
						", Posicion="+cont+" WHERE idPartida="+nuevoIdP+" AND nombreU='"+j.getUsuario().getNombre()+"';"); 
			}
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}		
	
	}

/**METODO CREADO POR HELEN
	
	public Iterator <Partida> getIteradorPartidas(){
		return listaPartidasGuardadas.iterator();
	}*/


/**METODO CREADO POR ENDIKA
 * @return */
	
	public boolean cargarPartida() {
			
			Iterator <Partida> itPar= this.getIteradorPartidasGuardadas(); /**Iterador para recorrer las partidas guardadas*/
			boolean enc= false; /**Atributo para ir comprobando los nombre de los jugadores*/
			boolean fin= false; /**Atributo para salir del primer bucle*/
			Partida parAux = null; /**Variable para cojer una partida*/
			LinkedList<Jugador> listaJugadoresPartidaGuardada= null; /**Lista auxiliar para ir comprobando los nombres de los jug (lista 1)*/

			while (itPar.hasNext() && !fin){/**recorrer todas las partidas*/
				parAux=itPar.next(); /**Cojemos una partida*/
				listaJugadoresPartidaGuardada=parAux.obtenerListaJugadoresPartidaActual();

				if(ListaJugadores.getListaJugadores().getListaJ().size()==listaJugadoresPartidaGuardada.size()){ /**Comparamos el tamaño de la dos listas*/
					/**Dos iteradores para comprobar si los nombres que nos dan, se corresponden con los jugadores que han iniciado sesion*/
					Iterator<Jugador> auxListaGuardada = parAux.obtenerListaJugadoresPartidaActual().iterator();
					Iterator<Jugador> auxListaActual = ListaJugadores.getListaJugadores().getIterador();
					
					/**Comparo si la lista esta desordenada*/
					//Por cada nombre de la lista guardada
					while (auxListaGuardada.hasNext()){
						String nom1= auxListaGuardada.next().getUsuario().getNombre();
						//Por cada nombre de la lista actual
						while(auxListaActual.hasNext() && !enc){
							String nom2= auxListaActual.next().getUsuario().getNombre();
							if (nom1.equals(nom2)){
								//Vamos controlando que coincidan los nombres
								enc=true;
							}
						}
					}
					if(auxListaGuardada.next()==null && enc){
						//Hemos terminado de recorrer la lista, el resultado de enc nos dira si existe partida
						fin=true;
					}
				}
			}

			/**Si nos salimos por que los jugadores son distintos, nos sale un error, por el contrario cojemos los
			 * datos que coinciden con los que queriamos cargar y los cargamos en la listaJugadores*/
			
			if(enc){
				Iterator<Jugador> auxListaGuardada = parAux.obtenerListaJugadoresPartidaActual().iterator();
				ListaJugadores.getListaJugadores().vaciarLista();
				while (auxListaGuardada.hasNext()){
					ListaJugadores.getListaJugadores().anadirJugador(auxListaGuardada.next());
				}

			}
			else{
				/**Error de que los usuarios no tienen ninguna partida guardada*/
				JOptionPane.showMessageDialog(null, "Los usuarios introducidos no tienen ninguna partida guardada con anterioridad", "Error Jugadores", JOptionPane.ERROR_MESSAGE);
			}
			return enc;
	}
}