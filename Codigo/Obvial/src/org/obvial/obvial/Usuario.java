package org.obvial.obvial;

import java.sql.SQLException;

public class Usuario {
	
	private String nombre;
	private String password;
	private String email;
	private boolean administrador;
	private int numPregAcer;
	private int numPregTotal;

	/**METODO CREADO POR HELEN*/
	public Usuario(String pNombre, String pPassword, String pEmail, boolean pAdministrador, int pNumPregAcer, int pNumPregTotal) {
		nombre=pNombre;
		password=pPassword;
		email=pEmail;
		administrador=pAdministrador;
		numPregAcer=pNumPregAcer;
		numPregTotal=pNumPregTotal;
	}
	
	/**METODOS CREADOS POR SARA*/
	public String getNombre() {
		return nombre;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public boolean isAdministrador() {
		return administrador;
	}
	public int getNumPregAcer() {
		return numPregAcer;
	}
	public int getNumPregTotal() {
		return numPregTotal;
	}
	
	

	/**METODO CREADO POR SARA*/
	public void incrementarPreguntasAcertadas() {
		numPregAcer=numPregAcer + 1;
		try{
			GestorBD.getInstance().actualizar("update usuarios set numPregAcert=numPregAcert+1 where nombre='"+nombre+"';");
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
	}
	
	// Sara --> incrementarPreguntasTotales()
	public void incrementarPreguntasTotales() {
		numPregTotal=numPregTotal + 1;
		try{
			GestorBD.getInstance().actualizar("update usuarios set numPregTotal=numPregTotal+1 where nombre='"+nombre+"';");
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		
	}
	
	/** CREADO POR HELEN
	 * con esto puedo comparar 2 objetos de usuario pero solo comparo el nombre 
	 * se usa en gestorUsuarios
	 */
	public boolean equalsUsuario(Object obj) {
		
		boolean es=false;
		if(obj instanceof Usuario){
			if (nombre.equals(((Usuario) obj).getNombre())){
				es=true;
			}	
		}
		return es;
		
	}
}
