/**Clase creada por Endika*/
package org.obvial.obvial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestorUsuarios {
/**Atributos*/
	private LinkedList<Partida> miListaUsuarios;
	private static GestorUsuarios miGestorUsuarios;
	//Atributo de Sara
	private LinkedList<Usuario> listaUsuarios;

	

/**METODO CREADO POR ENDIKA*/
	
/**Método constructor de la clase*/
	private GestorUsuarios(){
		miListaUsuarios=new LinkedList<Partida>();
		//Modificacion Sara
		listaUsuarios= new LinkedList<Usuario>();
	}
	
	public static GestorUsuarios getGestorUsuarios(){
		if (miGestorUsuarios==null){
			miGestorUsuarios= new GestorUsuarios();
		}
		return miGestorUsuarios;
	}

/**METODO CREADO POR ENDIKA*/
	
/**Método que obtiene la lista de partidas*/
	public LinkedList<Partida> getLista(){
		return miListaUsuarios;
	}

/**METODO CREADO POR ENDIKA*/
	
/**Método iterador de la lista*/
	private Iterator getIterador(){
		return miListaUsuarios.iterator();
	}
	
	// Sara --> actualizarUsuario(Usuario elUsuario,boolean rdo)
	public void actualizarUsuario(Usuario elUsuario,boolean rdo){
		elUsuario.incrementarPreguntasTotales();
		if(rdo){
			elUsuario.incrementarPreguntasAcertadas();
		}
	}
		
	/**METODO CREADO POR HELEN
	 * Pre:recibe un usuario 
	 * Pos:devuelve true si ambos usuarios son iguales y False en caso contrario
	*/
	public boolean esIgual(Usuario pUserActual, Usuario pUserGuardado) {
		boolean es= pUserActual.equalsUsuario(pUserGuardado);
		return es;
	}
    /**METODO CREADO POR HELEN
     * metodo para validar correo electronio*/
    public boolean coprobarEmail(String pCorreo) {
        boolean correcto;
    	Pattern pat = null;
        Matcher mat = null;        
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(pCorreo);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            correcto=true;
        }else{
            correcto=false;
        }
        return correcto;
    }
    /**METODO CREADO POR HELEN
     * comprueba si el usuario ya existe en nuestra base de datos
     * devuelve False: si el usuario que esta introduciendo ya existe en nuestra BD
     * */
    public boolean comprobarUsuario (String pUsuario){
		boolean distinto = true;
		String sql= "SELECT count(*) as Cantidad FROM usuarios WHERE nombre='"+pUsuario+"';";
		try {
			ResultSet resultado = GestorBD.getInstance().consulta(sql);
			resultado.next();//siempre va a haber un resultado
			if (resultado.getInt("Cantidad")==1){//el numbre de usuario es unico asi q solo puede haber uno o ninguno
				distinto=false;
			}
			GestorBD.getInstance().cerrarConsulta(resultado);	
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return distinto;	
    }
	
	
	/**
	 * Metodo creado por Jon Ander que consulta en la base de datos si un Usuario/Administrador existe para el logeo en el sistema
	 * 
	 * @param pUsuario
	 * @param pPassword
	 * @param pAdministrador
	 * @return
	 */
	public boolean logeoUsuario(String pUsuario, String pPassword, boolean pAdministrador){
		boolean existe=false;
		String sql =new String();
		if (pAdministrador==true){//si el metodo se ejecuta para logar un administrador
			sql= "Select count(*) as numUsu from usuarios where nombre='"+ pUsuario +"' and passw ='"+ pPassword +"' and administrador=1;";
		}
		else{//si el metodo se ejecuta para logear a un usuario
			sql= "Select count(*) as numUsu from usuarios where nombre='"+ pUsuario +"' and passw ='"+ pPassword +"';";
		}
		try {
			ResultSet resultado = GestorBD.getInstance().consulta(sql);
			resultado.next();//siempre va a haber un reasultado al menos ya que es un count y siempre devolvera una linea con un valor
			if (resultado.getInt("numUsu")==1){//si existe un usuario con ese id y ese password y si es admin en caso necesario el count sera 1 ya que no se puede repetir esto nunca ya que el nombre de usuario es unico
				existe=true;
			}
			GestorBD.getInstance().cerrarConsulta(resultado);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return existe;//si no existe un usuario con las caracteristicas que le facilitan al metodo devolvera un false de lo contrario un true
	}
	
	//METODOS DAVID
	public Usuario cargarDatosUsuario(String pNombre){
		ResultSet resultado = null;
		Usuario pUsuario = null;
		String sql =new String();
		sql= "Select * from usuarios where nombre='"+ pNombre +"';";
		try {
			resultado = GestorBD.getInstance().consulta(sql);
			resultado.next();//siempre va a haber un reasultado al menos ya que es un count y siempre devolvera una linea con un valor			
			String nombre= resultado.getString("nombre");
			int numPregAcert= resultado.getInt("numPregAcert");
			int numPregTotal= resultado.getInt("numPregTotal");
			pUsuario = new Usuario(nombre,null,null,false,numPregAcert,numPregTotal);
			GestorBD.getInstance().cerrarConsulta(resultado);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return pUsuario;
	}
	
    /**METODO CREADO POR HELEN
     * inserta un nuevo usuario en la BD
     * */
    public void registrarUsuario (String pNombre,String pEmail,String pPassword){
    	String sql="INSERT INTO usuarios VALUES('"+pNombre+"','"+pPassword+"','"+pEmail+"',0,0,0)";
    	try {
    		GestorBD.getInstance().insertar(sql);
    	}catch (SQLException e) {
			System.out.println(e.toString());
		}
    }
}
