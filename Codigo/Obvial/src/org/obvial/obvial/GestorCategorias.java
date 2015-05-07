package org.obvial.obvial;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;


/**
 * Clase MAE. Gestor de Categorias. Clase del controlador.
 * @author Egoitz
 */

public class GestorCategorias {

	private LinkedList<Categoria> listaCategorias;
	private static GestorCategorias miGestorCategorias;
	
	/**
	 * Hecho: Egoitz
	 * Método constructor. Carga todas las categorías de la BD
	 */
	private GestorCategorias(){
		listaCategorias= new LinkedList<Categoria>();
		//Al crear esta instancia se recogen todas las categorias de la BD
		cargarCategorias();
	}
	
	/**
	 * Hecho: Egoitz
	 * Método de acceso al gestor.
	 * @return el gestor de categorias
	 */
	public static GestorCategorias getGestorCategorias(){
		if (miGestorCategorias==null){
			miGestorCategorias= new GestorCategorias();
		}
		return miGestorCategorias;
	}
	
	//NUEVO --> Sara --> obtenerPregunta(int idPregunta, Categoria laCategoria)
		public Pregunta obtenerPregunta(int idPregunta, Categoria laCategoria){
			LinkedList<Pregunta> lPreg= laCategoria.getListaPreguntas();
			Pregunta preg = GestorPreguntas.getGestorPreguntas().buscarPregunta(lPreg,idPregunta);
			return preg;
		}
	
	// Sara --> obtenerPreguntasCategoria(Categoria laCategoria) 
	public LinkedList<String> obtenerPreguntasCategoria(Categoria laCategoria){
		LinkedList<Pregunta> listaPreguntas=laCategoria.getListaPreguntas();
		LinkedList<String> datos=GestorPreguntas.getGestorPreguntas().obtenerPreguntaCorrespondiente(listaPreguntas);
		return datos;
	}
	
//	// Sara --> obtenerInfoPreguntas(Categoria laCategoria, Pregunta laPregunta, String respuesta)
//	public boolean obtenerInfoPreguntas(Categoria laCategoria, Pregunta laPregunta, String respuesta){
//		LinkedList<Pregunta> listaPreguntas=laCategoria.getListaPreguntas();
//		boolean sol =GestorPreguntas.getGestorPregruntas().ObtenerInfoPregunta(listaPreguntas, laPregunta,respuesta);
//		return sol;
//	}
	
	public LinkedList<NombreYCategoria> cargarNombresYCategorias(){
		LinkedList<NombreYCategoria> rdo = new LinkedList<NombreYCategoria>();
		Categoria cat;
		String nom;
		NombreYCategoria nomYCat;
		Iterator<Categoria> itr = listaCategorias.iterator();
		while (itr.hasNext()){
			cat= itr.next();
			nom= cat.obtenerNombre();
			nomYCat= new NombreYCategoria(cat, nom);
			rdo.add(nomYCat);
		}
		return rdo;
	}
	
	public boolean eliminar(Categoria laCategoria){
		//TODO: RELLENAR
		return false;
	}
	
	/**
	 * Hecho: Egoitz
	 * Obtiene los nombres de las categorias.
	 * @return
	 */
	/*public LinkedList<String> obtenerNombres(){
		LinkedList<String> rdo = new LinkedList<String>();
		Categoria cat;
		String nom;
		Iterator<Categoria> itr = listaCategorias.iterator();
		while (itr.hasNext()){
			cat= itr.next();
			nom= cat.obtenerNombre();
			rdo.add(nom);
		}
		return rdo;
	}0*/
	
	/**
	 * Hecho: Egoitz
	 * Este método carga de la base de datos todas las categorias disponibles a objetos.
	 */
	private void cargarCategorias() {
		String consulta;
		int idCat;
		String nomCat;
		Categoria nCat;
		consulta="select * from categoria;";
		try{
			ResultSet resultado = GestorBD.getInstance().consulta(consulta);
			while(resultado.next()==true) {
				idCat= resultado.getInt("idCategoria");
				nomCat= resultado.getString("nombre");
				nCat= new Categoria(idCat, nomCat);
				listaCategorias.add(nCat);
			} 
			GestorBD.getInstance().cerrarConsulta(resultado);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
	}

	/**
	 * Hecho: Egoitz
	 * Decide si la pregunta se puede eliminar una pregunta de la categoria
	 * @param nombre
	 * @return true si se puede eliminar, false en caso contrario
	 */
	public boolean comprobarSiSePuedeEliminar(Categoria cat){
		boolean rdo = false;
		if(cat!=null){
			//Categoria cat = buscarCategoria(nombre);
			if(cat.numeroPreguntas() > 1){
				rdo = true;
			}
		}
		return rdo;
	}
	
	/**
	 * Hecho: Egoitz
	 * Recoge los titulos de las preguntas de la categoria introducida
	 * @param nomCat
	 * @return devuelve los textos de las preguntas (null si la categoria no existe o no posee preguntas).
	 */
	public LinkedList<PreguntaYTitulo> cargarPreguntas(Categoria cat){
		LinkedList<PreguntaYTitulo> rdo = null;
		//Categoria eCat = buscarCategoria(nomCat);
		if(cat!=null){
			LinkedList<Pregunta> lPreg = cat.getListaPreguntas();
			rdo = GestorPreguntas.getGestorPreguntas().ObtenerTextoPreguntas(lPreg);
		}
		
		return rdo;
	}
	
	/**
	 * Hecho: Egoitz
	 * obtiene una categoria a partir del nombre
	 * @param nomCat
	 * @return
	 */
	//Modificado por Jon Ander (Public)
	public Categoria buscarCategoria(String nomCat) {
	//Fin modificacion Jon Ander
		Categoria unaCat = null;
		boolean enc = false;
		if(!listaCategorias.isEmpty()){
			Iterator<Categoria> itr = listaCategorias.iterator();
			while (itr.hasNext() && !enc){
				unaCat = itr.next();
				enc = unaCat.comparar(nomCat);
			}
		}
		if(!enc){
			unaCat=null;
		}
		return unaCat;
	}

	public boolean modificarCategoria(String nuevoNombre){
		//TODO: RELLENAR
		return false;
	}
	
	/**
	 * elimina la pregunta de la categoria indicada y la elimina de la BD.
	 * @param nombrePregunta
	 * @param nombreCategoria
	 */
	public void borrarPreguntaDeCategoria(Pregunta preg, Categoria cat){
		//Categoria eCat = buscarCategoria(nombreCategoria);
		LinkedList<Pregunta> rdo;
		if(cat!=null && comprobarSiSePuedeEliminar(cat)){
			LinkedList<Pregunta> lPreg = cat.getListaPreguntas();
			rdo = GestorPreguntas.getGestorPreguntas().eliminarPregunta(lPreg, preg);
			cat.actualizarPreguntas(rdo);
		}
	}
	
	public void borrarPregunta(Pregunta preg, Categoria cat) {
		cat.eliminarPregunta(preg);
		
	}
	
	/**
	 * Hecho: Egoitz
	 * Este metodo devuelve un true si la pregunta ha sido modificada y un false en caso contrario
	 * Necesita la pregunta a modificar y las cateorias, la nueva a la que se quiere mover
	 * y la vieja de donde proviene (si no se quiere mover la pregunta de categoria,
	 * estas categorias deberan de coincidir)
	 * @param preg
	 * @param nCat
	 * @param vCat
	 * @return
	 */
	public boolean modificarPregunta(Pregunta preg, LinkedList<String> datosMod, Categoria nCat, Categoria vCat){
		boolean modificado=false;
		if(preg!=null && datosMod!=null && !datosMod.isEmpty() &&nCat!=null && vCat!=null){
			if(nCat.equals(vCat)){
				modificado = GestorPreguntas.getGestorPreguntas().modificarDatosPreg(preg, datosMod);
			}else{
				if(comprobarSiSePuedeEliminar(vCat)){
					modificado = GestorPreguntas.getGestorPreguntas().modificarDatosPreg(preg, datosMod);
					if(modificado){
						modificado= cambiarPreguntaDeCategoria(preg, nCat, vCat);
					}
				}
			}
		}
		return modificado;
	}

	private boolean cambiarPreguntaDeCategoria(Pregunta preg, Categoria nCat, Categoria vCat) {
		int idPreg = preg.getId();
		int idCat = nCat.getId();
		boolean rdo=false;
		String sql="update pregcat set idCategoria='"+idCat+"' where idPregunta='"+idPreg+"' and idCategoria='"+vCat.getId()+"';";
		try{
			rdo=vCat.quitarPregunta(preg);
			if(rdo){
				GestorBD.getInstance().actualizar(sql);
				rdo= nCat.anadirPregunta(preg);
			}
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		return rdo;
	}
	
	
	/***
	 * Metodo realizado por Jon Ander Fontán
	 * 
	 * Metodo que añade una pregunta a una categoria en concreto. Lo añade en la estructura de el programa y en la base de datos
	 * @param pPregunta
	 * @param pCategoria
	 * @return 
	 */
	public boolean añadirPreguntaACateroria(Pregunta pPregunta, Categoria pCategoria){
		int idPregunta;
		String sql = "SELECT count(*) as veces FROM pregunta WHERE texto='"+ pPregunta.getTextoPregunta() +"';";
		ResultSet resultadoSelect =null;
		boolean sePuede = false;
			try{
				resultadoSelect = GestorBD.getInstance().consulta(sql);//consulto si la pregunta ya esta en la BD
				resultadoSelect.next();
				int veces=resultadoSelect.getInt("veces");
				GestorBD.getInstance().cerrarConsulta(resultadoSelect);
				if(veces==0){
					idPregunta = GestorPreguntas.getGestorPreguntas().anadirPreguntaALaBD(pPregunta);//añado la pregunta ala BD
					GestorPreguntas.getGestorPreguntas().añadirIdAPregunta(pPregunta,idPregunta);
					
					pCategoria.anadirPregunta(pPregunta);//Añado la pregunta a la categoria			
					sql = "Insert into pregcat VALUES("+ idPregunta +","+ obtenerIdCategoria(pCategoria) +");";
					int rdoInsercion = GestorBD.getInstance().insertar(sql);//Relaciono la pregunta con la categoria en la BD
					sePuede=true;
				}
			}
			catch(SQLException e){
				System.out.println(e.toString());
			}
		return sePuede;
	}

	
	/**
	 * Metodo realizado por Jon Ander Fontán
	 * 
	 * Metodo que devuelve el ID de la Categoria
	 */
	public int obtenerIdCategoria(Categoria pCategoria){
		return pCategoria.getId();
	}
	
	/**
	 * Hecho: Egoitz
	 * Para las pruebas, y así reiniciar el gestor
	 */
	public void eliminarGestor(){
		miGestorCategorias=null;
	}
}

