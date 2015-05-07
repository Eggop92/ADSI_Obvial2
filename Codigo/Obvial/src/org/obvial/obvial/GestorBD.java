/**Clase creada por Endika*/
package org.obvial.obvial;
import java.sql.*;

public class GestorBD {
	/**variables*/
	    private String usuario;
	    private String clave;
	    private String url;
	    private String driverClassName;
	    private Connection conn = null;
	    private Statement sentencia;
	    public static GestorBD conex;
	  
	 
	/**CONSTRUCTORES*/
	    /**Constructor que crea la conexion sin parametros con unos definidos en la clase (meter los datos correspondientes)*/
	    private GestorBD() {
	    	/**conexion MySQL*/
	    	this.usuario = "adsi"; //usuario creado en MySQL y con permiso en la Base de
	  	    this.clave = "adsi"; //Password de mysql del usuario
	  	    this.url = "jdbc:mysql://localhost:3306/practica_adsi"; //Forma de "encontrar" la Base de Datos en
	  	    this.driverClassName = "com.mysql.jdbc.Driver"; //driver para trabajar con MySQL   	
	    	
	        try {
				AbrirConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    public static GestorBD getInstance() {
	        if(conex == null) {
	          
	              conex = new GestorBD();
	           }
	        return conex;
	     }
	
	    //El método que nos permite abrir la conexión
	 
	    private void AbrirConexion() throws SQLException {
	    	
	        try {
	        	
	        		        		//en MySQL con usuario y clave
	        		Class.forName(this.driverClassName).newInstance();
	            this.conn = DriverManager.getConnection(this.url, this.usuario, this.clave);
	        		        	
	 
	        } catch (Exception err) {
	            System.out.println("Error " + err.getMessage());
	        }
	    }
	    
	    
	        
	 	   	 
	    //Cerrar la conexion
	 
	    public void CerrarConexion() throws SQLException {
	        this.conn.close();
	    }
	 
	/**Métodos para trabajar con la Base de Datos. En este caso he puesto cuatro métodos
	  y los cuatro trabajan con el objeto ResulSet. Hay más opciones, las podéis consultar
	  en la documentación de java.sql y usarlas en función de vuestras necesidades
	 **/
	    public ResultSet consulta(String consulta) throws SQLException {
	        this.sentencia = (Statement) conn.createStatement();
	        return this.sentencia.executeQuery(consulta);
	    }
	    
	    public void cerrarConsulta (ResultSet resul)throws SQLException {
	    	resul.close();
	    }
	 
	    public void actualizar(String actualiza) throws SQLException {
	        this.sentencia = (Statement) conn.createStatement();
	        sentencia.executeUpdate(actualiza);
	    }
	 
	    public void borrar(String borra) throws SQLException {
	        this.sentencia = (Statement) conn.createStatement();
	        sentencia.executeUpdate(borra);
	    }
	 
	    public int insertar(String inserta) throws SQLException {
	        Statement st = (Statement) this.conn.createStatement();
	        return st.executeUpdate(inserta);
	    }
		

}
