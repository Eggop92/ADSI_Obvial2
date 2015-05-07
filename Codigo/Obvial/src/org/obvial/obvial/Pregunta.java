package org.obvial.obvial;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Clase del modelo: Pregunta
 * @author egoitz
 */
public class Pregunta {
	
	private String textoPregunta;
	private LinkedList<String> listaRespuestas;
	private String respuestaCorrecta;
	private int idPregunta;
	private int vecesPreguntado;
	private int vecesAcertada;
	private boolean preguntada;
	
	/**
	 * Hecho: Egoitz
	 * Método constructor.
	 * @param datos necesarios para la pregunta
	 */
	public Pregunta(String pTextoPregunta, LinkedList<String> pListaRespuestas, String pRespuestaCorrecta, int pVecesAcertada, int pVecesPreguntada) {
		textoPregunta = pTextoPregunta;
		listaRespuestas = pListaRespuestas;
		respuestaCorrecta = pRespuestaCorrecta;
		vecesPreguntado = pVecesPreguntada;
		vecesAcertada = pVecesAcertada;
		preguntada = false;
	}
	
	public Pregunta(int pIdPreg, String pTextoPregunta, LinkedList<String> pListaRespuestas, String pRespuestaCorrecta, int pVecesAcertada, int pVecesPreguntada) {
		  idPregunta = pIdPreg;
		  textoPregunta = pTextoPregunta;
		  listaRespuestas = pListaRespuestas;
		  respuestaCorrecta = pRespuestaCorrecta;
		  vecesPreguntado = pVecesPreguntada;
		  vecesAcertada = pVecesAcertada;
		  preguntada = false;
		 }
	
	
	// Sara --> comprobarRespuestas(String Respuesta)
	public boolean comprobarRespuestas(String respuesta){
		if(respuesta==respuestaCorrecta){
			return true;
		}
		else{
			return false;
		}
	}
	
	// Sara --> incrementarVecesAcertada() 
	public void incrementarVecesAcertada(){
		vecesAcertada=vecesAcertada + 1;
		try{
			GestorBD.getInstance().actualizar("update pregunta set VecesAcertada=VecesAcertada+1 where idPregunta="+idPregunta);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
	}
	
	// Sara --> compruebaRealizada()
	public boolean compruebaRealizada(){
		return preguntada;
	}	
	
	
	/**
	 * Hecho: Egoitz
	 * @return
	 */
	public String getTextoPregunta(){
		return textoPregunta;
	}
	
	// Sara --> getListaRespuestas()
	//[ponia ArrayList y es LinkedList] 
	public LinkedList<String> getListaRespuestas(){
		return listaRespuestas;
	}
	
	// Sara --> incrementarVecesPreguntada()
	public void incrementarVecesPreguntada(){
		vecesPreguntado=vecesPreguntado + 1;
		try{
		GestorBD.getInstance().actualizar("update pregunta set VecesPreguntada=VecesPreguntada+1 where idPregunta="+idPregunta);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
	}

	/**
	 * Hecho: Egoitz
	 * Decide si el titulo de la pregunta coincide con el introducido por el parametro
	 * @param nombrePregunta
	 * @return true si coinciden, false en caso contrario
	 */
	public boolean comparar(String nombrePregunta) {
		boolean rdo = false;
		if (nombrePregunta.equals(textoPregunta)){
			rdo=true;
		}
		return rdo;
	}

	/**
	 * Hecho: Egoitz
	 * @return
	 */
	public int getId() {
		return idPregunta;
	}
	
	// Sara --> setPreguntada(boolean b)
	//he tendiodo que crear este metodo para poder cambiar el atributo preguntada
	//se usa en el metodo obtenerPreguntaCorrespondiente de GestorPreguntas
	public void setPreguntada(boolean b) {
		preguntada=b;
	}
	
	/**
	 * Hecho:Egoitz
	 * Este metodo devuelve la respuesta correcta
	 * @return
	 */
	public String getRespuestaCorrecta(){
		return respuestaCorrecta;
	}

	/**
	 * Hecho: Egoitz
	 * Modifica los datos de la pregunta y actualizad en la Base de Datos
	 * datosMod: 1-Titulo 2/5-Resp 6-RespCorrecta
	 * @param datosMod
	 * @return
	 */
	public boolean modificarDatos(LinkedList<String> datosMod) {
		boolean rdo=false;
		if(datosMod !=null && !datosMod.isEmpty() && datosMod.size()== 6){
			String titulo, resp1, resp2, resp3, resp4, respCorr, sql;
			titulo=datosMod.get(0);
			resp1=datosMod.get(1);
			resp2=datosMod.get(2);
			resp3=datosMod.get(3);
			resp4=datosMod.get(4);
			respCorr=datosMod.get(5);
			sql= "update pregunta set texto='"+titulo+"', resp1='"+resp1+"', resp2='"+resp2+"', resp3='"+resp3+"', resp4='"+resp4+"', respCorrecta='"+respCorr+"' where idPregunta='"+idPregunta+"';";
			try{
				GestorBD.getInstance().actualizar(sql);
				textoPregunta=titulo;
				listaRespuestas= new LinkedList<String>();
				listaRespuestas.add(resp1);
				listaRespuestas.add(resp2);
				listaRespuestas.add(resp3);
				listaRespuestas.add(resp4);
				respuestaCorrecta=respCorr;
				rdo=true;
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
		}
		return rdo;
	}
	
	/**
	 * Clase creada por Jon Ander para añadir un ID a la pregunta
	 * 
	 * @param pId
	 */
	public void setId(int pId){
		this.idPregunta=pId;
	}
		
	@Override
	/**
	 * Hecho por eclipse
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pregunta))
			return false;
		Pregunta other = (Pregunta) obj;
		if (idPregunta != other.idPregunta)
			return false;
		return true;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////
////METODOS PARA PRUEBAS [NO están puestos en el proyecto]--> SARA///////////////////////

public int getVecesAcertada() {
return vecesAcertada;
}
public int getVecesPreguntada() {
return vecesPreguntado;
}
////////////////////////////////////////////////////////////////////////////////////////////////

}
