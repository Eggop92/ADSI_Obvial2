

import java.sql.SQLException;

import org.obvial.obvial.GestorBD;
import org.obvial.obvial.GestorRespuestas;

import junit.framework.TestCase;

public class pruebasAaron extends TestCase {

	
	public pruebasAaron(String name) {
		
		int acertadaOno = 1;
		String nombreUsuario = "Ianire";
		int idenPregunta = 3;
		
		try{
			GestorBD.getInstance().insertar("insert into respuestas (NombreU, idPregunta, Fecha, Acertada) value ("+nombreUsuario+","+idenPregunta+",'2013-01-22', "+acertadaOno+");");
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
		
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testCalcularPregTotales() throws SQLException {
		assertEquals(1, GestorRespuestas.getGestorRespuestas().calcularPregTotales("ciencia", "Ianire"));
	}

	public void testCalcularFalladas() throws SQLException {
		assertEquals(1, GestorRespuestas.getGestorRespuestas().calcularFalladas("ciencia", "Ianire"));
	}

	public void testCalcularPuntosTotales() throws SQLException {
		assertEquals(5, GestorRespuestas.getGestorRespuestas().calcularPuntosTotales("ciencia", "Ianire"));
	}

	public void testCalcularPuntosPerdidos() throws SQLException {
		assertEquals(5, GestorRespuestas.getGestorRespuestas().calcularPuntosPerdidos("ciencia", "Ianire"));
	}

}
