package org.obvial.obvial;

/**
 * 
 * @author egoitz
 *
 */
public class PreguntaYTitulo {

	private Pregunta preg;
	private String titulo;
	
	
	public PreguntaYTitulo(Pregunta preg, String titulo) {
		this.preg = preg;
		this.titulo = titulo;
	}

	public Pregunta getPreg() {
		return preg;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public boolean equals(Object o){
		boolean rdo=false;
		if(o instanceof String){
			rdo=titulo.equals(o);
		}
		return rdo;
	}
	
}
