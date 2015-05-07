package org.obvial.obvial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;


public class GestorRespuestas {//Sara
	private LinkedList<Respuesta> listaRespuestas;
	private static GestorRespuestas miGestorRespuestas;

	private GestorRespuestas(){
		listaRespuestas= new LinkedList<Respuesta>();

	}

	public static GestorRespuestas getGestorRespuestas(){
		if (miGestorRespuestas==null){
			miGestorRespuestas= new GestorRespuestas();
		}
		return miGestorRespuestas;
	}
	
	// Sara --> crearRespuesta(boolean solucion,Usuario elUsuario, Pregunta laPregunta)
	//he quitado el parametro fecha: no me lo pasan,lo creo yo en el metodo
public void crearRespuesta(boolean solucion,Usuario elUsuario, Pregunta laPregunta){
		
		Calendar fecha2 = Calendar.getInstance();
		Date fecha = fecha2.getTime();
		
		int dia = fecha2.get(Calendar.DAY_OF_MONTH);
		int mes = fecha2.get(Calendar.MONTH);
		int año = fecha2.get(Calendar.YEAR);
		int hora = fecha2.get(Calendar.HOUR_OF_DAY);
		int minuto = fecha2.get(Calendar.MINUTE);
		int segundo = fecha2.get(Calendar.SECOND);
		String laFecha = año+"-"+mes+"-"+dia+" "+hora+":"+minuto+":"+segundo;
		
		Respuesta r =new Respuesta(solucion,elUsuario,laPregunta,fecha);
		listaRespuestas.add(r);
		String nombreUsuario=elUsuario.getNombre();
		int idPregunta=laPregunta.getId();
		try{
			GestorBD.getInstance().insertar("insert into respuestas values ('"+nombreUsuario+"',"+idPregunta+",'"+laFecha+"',"+solucion+")");
		}
		catch(SQLException ex){
			
			System.out.println(ex.toString());
		}
	}

	
	//Aaron
	
	public int calcularPregTotales(){
		//TODO: Rellenar
		return 0;
	}
	
	public int calcularFalladas(){
	//TODO: Rellenar
	return 0;
	}
	
	public int puntosTotales(){
		//TODO: Rellenar
		return 0;
		}
	
	public int calcularPuntosPerdidos(){
		//TODO: Rellenar
		return 0;
		}
	
	public LinkedList<Respuesta> getListaRespuestas(){
		return listaRespuestas;
	}
	
	//Parte de Aaron Ojembarrena
 	
	
	public int calcularPregTotales(String categoria, String nombreUsuario) throws SQLException{
		ResultSet devolver = null;
		int rdo=0;
		try{
			devolver  = GestorBD.getInstance().consulta("select count(*) from categoria, pregcat, respuestas where categoria.idCategoria=pregcat.idCategoria and pregcat.idPregunta=respuestas.idPregunta and categoria.nombre="+categoria+" and respuestas.NombreU="+nombreUsuario+"");
			rdo=devolver.getInt(1);
			GestorBD.getInstance().cerrarConsulta(devolver);
		}
		catch(SQLException ex){
			System.out.println("ex.toString()");
		}
		
	return rdo;
	}
	
	public int calcularFalladas(String categoria, String nombreUsuario) throws SQLException{
		
		ResultSet devolver = null;
		int rdo=0;
		try{
			devolver = GestorBD.getInstance().consulta("select count(*) from categoria, pregcat, respuestas where categoria.idCategoria=pregcat.idCategoria and pregcat.idPregunta=respuestas.idPregunta and categoria.nombre="+categoria+" and respuestas.NombreU="+nombreUsuario+" and respuestas.Acertada=1");
			rdo=devolver.getInt(1);
			GestorBD.getInstance().cerrarConsulta(devolver);
		}
		catch(SQLException ex){
			System.out.println("ex.toString()");
		}
		
	return rdo;
	}
	
	public int calcularPuntosTotales(String categoria, String nombreUsuario) throws SQLException{
		
		int totales = calcularPregTotales(categoria, nombreUsuario);
		
		
		return totales;
		}
	
	public int calcularPuntosPerdidos(String categoria, String nombreUsuario) throws SQLException{
	 
		int pPerdidos = calcularFalladas(categoria, nombreUsuario)*5;
		
		return pPerdidos;
		}
}
