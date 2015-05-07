package org.obvial.obvial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;


//import java.util.ArrayList;
/**
 * @author egoitz
 *
 * Clase GestorPreguntas. MAE. Gestiona las preguntas y sus listas.
 */
public class GestorPreguntas {

	private static GestorPreguntas miGestorPreguntas;
	
	/**
	 * Método constructor
	 */
	private void GestorPreguntas(){}
	
	/**
	 * Hecho:Egoitz
	 * Método de acceso a la MAE.
	 * @return GestorPreguntas
	 */
	public static GestorPreguntas getGestorPreguntas(){
		if (miGestorPreguntas == null){
			miGestorPreguntas = new GestorPreguntas();
		}
		return miGestorPreguntas;
	}
	
	//**************************
	//    A RELLENAR
	//**************************
	// Sara --> obtenerPreguntaCorrespondiente(LinkedList<Pregunta> LP)
	public LinkedList<String> obtenerPreguntaCorrespondiente(LinkedList<Pregunta> LP){
		LinkedList<String> datos = new LinkedList<String>();
		if(!LP.isEmpty()){
		boolean encontrado=false;
		Pregunta unaPregunta=null;
		Iterator<Pregunta> itr = LP.iterator();
		while (itr.hasNext()&& !encontrado){
			unaPregunta= itr.next();
			if(!unaPregunta.compruebaRealizada()){
				encontrado=true;
			}
		}
		if (!encontrado){
			//poner todas a false antes de volver a buscar la pregunta
			Iterator<Pregunta> itr2 = LP.iterator();
			while (itr2.hasNext()){
				unaPregunta= itr2.next();
				unaPregunta.setPreguntada(false);
			}
		
	
			Iterator<Pregunta> itr3 = LP.iterator();
			while (itr3.hasNext()&& !encontrado){
				unaPregunta= itr3.next();
				if(!unaPregunta.compruebaRealizada()){
					encontrado=true;
				}
			}
		}
				String idPregunta= unaPregunta.getId()+"";
				String textoPregunta=unaPregunta.getTextoPregunta();
				LinkedList<String> respuestas=unaPregunta.getListaRespuestas();
				String rA= respuestas.get(0);
				String rB= respuestas.get(1);
				String rC= respuestas.get(2);
				String rD= respuestas.get(3);
				datos.add(textoPregunta);
				datos.add(rA);
				datos.add(rB);
				datos.add(rC);
				datos.add(rD);
				datos.add(idPregunta);
				unaPregunta.setPreguntada(true);
				unaPregunta.incrementarVecesPreguntada();
		}
				
		return datos;
	}
	
//	//Hecho por Sara
//	//ObtenerInfoPregunta(ListaPreguntas LP, Pregunta laPregunta)
//	//[LP estaba como ListaPreguntas y lo he cambiado a LinkedList]
//	//[He tenido que añadir que este método recibe como parámetro respuestaSeleccionada]
//	public boolean ObtenerInfoPregunta(LinkedList<Pregunta> LP, Pregunta laPregunta,String respuestaSeleccionada){
//		boolean encontrado=false;
//		Pregunta unaPregunta=null;
//		Iterator<Pregunta> itr = LP.iterator();
//		while (itr.hasNext()&& !encontrado){
//			unaPregunta= itr.next();
//			if(unaPregunta.equals(laPregunta)){
//				encontrado=true;
//			}
//			else{
//				itr.next();
//			}
//		}
//		boolean sol=unaPregunta.comprobarRespuestas(respuestaSeleccionada);
//		if(sol){
//			unaPregunta.incrementarVecesAcertada();
//		}
//		return sol;
//	}
	
	// Sara --> ObtenerInfoPregunta(Pregunta laPregunta,String respuestaSeleccionada)
	//[He tenido que cambiar que este método recibe como parámetro respuestaSeleccionada y no recibe como parametro la LP]
	public boolean ObtenerInfoPregunta(Pregunta laPregunta,String respuestaSeleccionada){
		boolean sol=laPregunta.comprobarRespuestas(respuestaSeleccionada);
		if(sol){
			laPregunta.incrementarVecesAcertada();
		}
		return sol;
	}	
	
	//NUEVO --> Sara --> buscarPregunta(LinkedList<Pregunta>listaPreguntas, int idPregunta)
		public Pregunta buscarPregunta(LinkedList<Pregunta>listaPreguntas, int idPregunta){
			boolean encontrado= false;
			Pregunta unaPreg=null;
			Iterator<Pregunta> itr = listaPreguntas.iterator();
			while (itr.hasNext()&&!encontrado){
				unaPreg = itr.next();
				if(unaPreg.getId()==idPregunta){
					encontrado= true;
				}
			}
			return unaPreg;
		}
	
	public boolean eliminarPreguntas(LinkedList<Pregunta> LP, Categoria laCategoria){
		//TODO: Rellenar
		return false;
	}
	
	/**
	 *Hecho por Jon Ander Fontán 
	 * @return
	 */
	public int anadirPreguntaALaBD(Pregunta pPregunta){
		int rdo=0;
		String sql="Select Max(idPregunta) as numMaxIdPregunta from pregunta;";
		ResultSet resultado = null;
		
		try{
			resultado = GestorBD.getInstance().consulta(sql);
			resultado.next();
			rdo = resultado.getInt("numMaxIdPregunta");
			rdo++;
			GestorBD.getInstance().cerrarConsulta(resultado);
			LinkedList<String> respuestas= pPregunta.getListaRespuestas();

			
			sql="Insert into pregunta VALUES("+ rdo +",'"+ pPregunta.getTextoPregunta() +"','"+ respuestas.removeFirst() +"','"+ respuestas.removeFirst() +"','"+ respuestas.removeFirst() +"','"+ respuestas.removeFirst() +"','"+ pPregunta.getRespuestaCorrecta() +"',0,0);";
			int resultadoInsercion2 = GestorBD.getInstance().insertar(sql);
			
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		return rdo;
		
	}
	
	
	/**
	 * Hecho: Egoitz
	 * añade la pregunta a la lista, pero no en la base de datos
	 * @param LP
	 * @param laPregunta
	 * @return
	 */
	public boolean AnadirPregunta(LinkedList<Pregunta> LP, Pregunta laPregunta){
		//TODO: Rellenar
		boolean rdo=false;
		if(LP!=null && laPregunta!=null){
			rdo= LP.add(laPregunta);
		}
		return rdo;
	}
	

	/**
	 * Hecho: Egoitz
	 * este método devuelve los titulos de las preguntas de la lista
	 * @param lPreg
	 * @return LinkedList<String> con los titulos de las preguntas (null si la lista esta vacia)
	 */
	public LinkedList<PreguntaYTitulo> ObtenerTextoPreguntas(LinkedList<Pregunta> lPreg) {
		LinkedList<PreguntaYTitulo> rdo = null;
		if( lPreg!=null && !lPreg.isEmpty()){
			rdo = new LinkedList<PreguntaYTitulo>();
			String tituloPreg;
			Pregunta unaPreg;
			PreguntaYTitulo pregYTit;
			Iterator<Pregunta> itr = lPreg.iterator();
			while (itr.hasNext()){
				unaPreg = itr.next();
				tituloPreg= unaPreg.getTextoPregunta();
				pregYTit = new PreguntaYTitulo(unaPreg, tituloPreg);
				rdo.add(pregYTit);
			}
		}
		return rdo;
	}

	/**
	 * Hecho: Egoitz
	 * Elimina la pregunta de la lista y devuelve esta actualizada. Elimina de la base de datos.
	 * @param lPreg
	 * @param nombrePregunta
	 * @return la lista de preguntas actualizada.
	 */
	public LinkedList<Pregunta> eliminarPregunta(LinkedList<Pregunta> lPreg, Pregunta preg) {
		//Pregunta ePreg = buscarPregunta(lPreg, nombrePregunta);
		if (preg!=null && lPreg!=null && !lPreg.isEmpty()){
			int idPreg = preg.getId();
			//Con esta sentencia se elimina la pregunta y por como esta diseñada la base de datos
			//todo lo relacionado con la pregunta tambien sera eliminado (estadisticas y la relacion con la categoria)
			String sql = "delete from pregunta where idPregunta='"+idPreg+"';";
			try{
				GestorBD.getInstance().actualizar(sql);
				lPreg.remove(preg);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
		}
		return lPreg;
	}

	/**
	 * Hecho: Egoitz
	 * Busca en la lista lPreg la pregunta con el titulo nombrePregunta
	 * @param lPreg
	 * @param nombrePregunta
	 * @return
	 */
	private Pregunta buscarPregunta(LinkedList<Pregunta> lPreg, String nombrePregunta) {
		// TODO Auto-generated method stub
		Pregunta rdo = null;
		boolean enc = false;
		if (!lPreg.isEmpty()){
			Iterator<Pregunta> itr = lPreg.iterator();
			while (itr.hasNext() && !enc){
				rdo= itr.next();
				enc = rdo.comparar(nombrePregunta);
			}
		}
		if(!enc){
			rdo=null;
		}
		return null;
	}
	
	/**
	 * Hecho: Egoitz
	 * Este metodo devuelve los datos de la pregunta preg en la categoria cat
	 * devuelve en el LinkedList: 1-Titulo 2/5-Resp 6-RespCorrecta
	 * @param cat
	 * @param preg
	 * @return
	 */
	public LinkedList<String> cargarDatosPregunta(Pregunta preg){
		LinkedList<String> rdo = null;
		if(preg!=null){
			rdo=new LinkedList<String>();
			rdo.add(preg.getTextoPregunta());
			LinkedList<String> resp = preg.getListaRespuestas();
			Iterator<String> itr = resp.iterator();
			while (itr.hasNext()){
				rdo.add(itr.next());
			}
			rdo.add(preg.getRespuestaCorrecta());
		}
		return rdo;
	}

	public boolean quitarPregunta(LinkedList<Pregunta> listaPreguntas, Pregunta preg) {

		boolean rdo=false;
		if(listaPreguntas!=null && !listaPreguntas.isEmpty() && preg!=null){
			rdo= listaPreguntas.remove(preg);
		}
		return rdo;
	}

	public boolean modificarDatosPreg(Pregunta preg, LinkedList<String> datosMod) {
		boolean rdo=false;
		if(preg!=null && datosMod!=null && datosMod.size()==6){
			rdo=preg.modificarDatos(datosMod);
		}
		return rdo;
	}

	public Pregunta crearPregunta(String text, LinkedList<String> datosNuevaPregunta, String respuestaCorrecta, int i, int j) {
		Pregunta preg = new Pregunta(text,datosNuevaPregunta,respuestaCorrecta, i,j);;
		return preg;
	}
	
	/**
	 * Clase creada por Jon Ander para añadir un ID a una pregunta pregunta
	 * 
	 * @param pId
	 */
	public void añadirIdAPregunta(Pregunta pPregunta,int pId){
		pPregunta.setId(pId);
	}
	

}
