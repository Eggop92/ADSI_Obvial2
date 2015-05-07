package org.obvial.obvial;

/**
 * 
 * @author egoitz
 *
 */
public class NombreYCategoria {

	private Categoria cat;
	private String nom;
	
	
	
	public NombreYCategoria(Categoria cat, String nom) {
		this.cat = cat;
		this.nom = nom;
	}
	
	public Categoria getCat() {
		return cat;
	}
	public String getNom() {
		return nom;
	}
	
	public boolean equals(Object o){
		boolean rdo=false;
		if (o instanceof String){
			rdo= nom.equals(o);
		}
		return rdo;
	}
}
