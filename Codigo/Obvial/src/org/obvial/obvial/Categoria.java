package org.obvial.obvial;

//import implementacion.GestorBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


/**
 * Clase Categoria. TAD. Clase del modelo.
 * @author egoitz
 */
public class Categoria {

	private int idCategoria;
	private String nombre;
	private LinkedList<Pregunta> listaPreguntas;
	
	public Categoria(int pIdCat, String pNombre){
		idCategoria=pIdCat;
		nombre=pNombre;
		listaPreguntas=new LinkedList<Pregunta>();
		//Al crear la categoría se cargan todas las preguntas que pertenezcan
		cargarpreguntas();
	}
	
	/**
	 * Hecho: Egoitz
	 */
	private void cargarpreguntas() {
		// TODO Auto-generated method stub
		String consulta;
		int idPreg, acert, pregun;
		String texto, resp1, resp2, resp3, resp4, respCorrecta;
		Pregunta nPreg;
		LinkedList<String> respuestas;
		consulta ="select  idPregunta, texto, resp1, resp2, resp3, resp4, respCorrecta, vecesPreguntada, vecesAcertada from pregunta natural join pregcat where idCategoria='"+idCategoria+"';";
		try{
			ResultSet rdo = GestorBD.getInstance().consulta(consulta);
			while(rdo.next()==true) {
				idPreg =rdo.getInt("idPregunta");
				texto = rdo.getString("texto");
				respCorrecta = rdo.getString("respCorrecta");
				resp1 = rdo.getString("resp1");
				resp2 = rdo.getString("resp2");
				resp3 = rdo.getString("resp3");
				resp4 = rdo.getString("resp4");
				acert = rdo.getInt("vecesAcertada");
				pregun = rdo.getInt("vecesPreguntada");
				
				respuestas = new LinkedList<String>();
				respuestas.add(resp1);
				respuestas.add(resp2);
				respuestas.add(resp3);
				respuestas.add(resp4);
				
				nPreg = new Pregunta(texto, respuestas, respCorrecta, acert, pregun);
				listaPreguntas.add(nPreg);
			} 
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		
	}

	/**
	 * Hecho: Egoitz
	 * @return
	 */
	public LinkedList<Pregunta> getListaPreguntas(){
		return listaPreguntas;
	}
	
	/**
	 * Hecho:Egoitz
	 * @return
	 */
	public String obtenerNombre(){
		return nombre;
	}
	
	/**
	 * Hecho: Egoitz
	 * @return el numero de preguntas en la categoria
	 */
	public int numeroPreguntas(){
		int rdo = listaPreguntas.size();
		return rdo;
	}
	
	public void cambiarNombre(String nuevoNombre){
		//TODO: RELLENAR
	}

	/**
	 * Hecho: Egoitz
	 * @param nomCat
	 * @return
	 */
	public boolean comparar(String nomCat) {
		boolean rdo;
		if(nomCat.equals(nombre)){
			rdo=true;
		}else{
			rdo=false;
		}
		return rdo;
	}

	public void actualizarPreguntas(LinkedList<Pregunta> lPreguntas) {
		listaPreguntas = lPreguntas;
	}

	/**
	 * Hecho: Egoitz
	 * @return
	 */
	public int getId() {
		return idCategoria;
	}

	/**
	 * Hecho:Egoitz
	 * Quita la pregunta de la lista de preguntas de la categoria, pero no elimina la pregunta de la Base de datos
	 * @param preg
	 * @return
	 */
	public boolean quitarPregunta(Pregunta preg) {
		boolean rdo;
		rdo=GestorPreguntas.getGestorPreguntas().quitarPregunta(listaPreguntas, preg);
		return rdo;
	}

	/**
	 * 
	 * Metodo Hecho por Jon Ander Fontán
	 * @param preg
	 * @return
	 */
	public boolean anadirPregunta(Pregunta preg) {
		boolean rdo;
		rdo=GestorPreguntas.getGestorPreguntas().AnadirPregunta(listaPreguntas, preg);
		return rdo;
	}
	/**
	 * elimina la pregunta de la BD y de los objetos.
	 * @param preg
	 */
	public void eliminarPregunta(Pregunta preg) {
		listaPreguntas = GestorPreguntas.getGestorPreguntas().eliminarPregunta(listaPreguntas, preg);
		
	}


	/**
	 * Generado por eclipse
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Categoria)) {
			return false;
		}
		Categoria other = (Categoria) obj;
		if (idCategoria != other.idCategoria) {
			return false;
		}
		return true;
	}

}
