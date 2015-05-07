import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.obvial.obvial.Categoria;
import org.obvial.obvial.GestorBD;
import org.obvial.obvial.GestorCategorias;
import org.obvial.obvial.GestorPreguntas;
import org.obvial.obvial.GestorRespuestas;
import org.obvial.obvial.GestorUsuarios;
import org.obvial.obvial.Pregunta;
import org.obvial.obvial.Usuario;

import junit.framework.TestCase;

public class PruebasSara extends TestCase {
	
	
		Pregunta p1,p2;
		LinkedList<Pregunta> lp1,lp2;
		Categoria  c1,c4;
		Usuario u1;

		
		protected void setUp() throws Exception {
			
			LinkedList<String> lr1 = new LinkedList<String> ();
			lr1.add("Paco");
			lr1.add("Francisco");
			lr1.add("Indiana");
			lr1.add("Missisipi");
			p1 = new Pregunta(2,"Nombre del doctor Jones",lr1,"Indiana",0,0);
			LinkedList<String> lr2 = new LinkedList<String> ();
			lr2.add("Triston");
			lr2.add("Rison");
			lr2.add("Cabron");
			lr2.add("Chiston");
			p2 = new Pregunta(4,"Alicia en el pais de las maravillas: nombre del gato",lr2,"Rison",0,0);
			lp1 = new LinkedList<Pregunta>();
			lp1.add(p1);
			lp1.add(p2);
			lp2 = new LinkedList<Pregunta>();
			c1 = new Categoria(1,"Cine");
			c4 = new Categoria(4,"Manga");
			u1 = new Usuario("Sara","araS","sara@ehu.es",false,0,0);
			
		}
		
		protected void tearDown() throws Exception {
			
		}
		
		
		public void testObtenerPeguntasCategoria(){ 
			
			//Estan todas a false
			LinkedList<String> rdo1 = GestorCategorias.getGestorCategorias().obtenerPreguntasCategoria(c1);
			assertFalse(rdo1.isEmpty());
			assertEquals(rdo1.get(0),"Nombre del doctor Jones");
			assertEquals(rdo1.get(1),"Paco");
			assertEquals(rdo1.get(2),"Francisco");
			assertEquals(rdo1.get(3),"Indiana");
			assertEquals(rdo1.get(4),"Missisipi");
			assertEquals(rdo1.get(5),"2");
		
			//Estan a true y a false
			LinkedList<String> rdo2 = GestorCategorias.getGestorCategorias().obtenerPreguntasCategoria(c1);
			assertFalse(rdo2.isEmpty());
			assertEquals(rdo2.get(0),"Alicia en el pais de las maravillas: nombre del gato");
			assertEquals(rdo2.get(1),"Triston");
			assertEquals(rdo2.get(2),"Rison");
			assertEquals(rdo2.get(3),"Cabron");
			assertEquals(rdo2.get(4),"Chiston");
			assertEquals(rdo2.get(5),"4");
		
			//Estan todas a true
			LinkedList<String> rdo3 = GestorCategorias.getGestorCategorias().obtenerPreguntasCategoria(c1);
			assertFalse(rdo3.isEmpty());
			assertEquals(rdo3.get(0),"Nombre del doctor Jones");
			assertEquals(rdo3.get(1),"Paco");
			assertEquals(rdo3.get(2),"Francisco");
			assertEquals(rdo3.get(3),"Indiana");
			assertEquals(rdo3.get(4),"Missisipi");
			assertEquals(rdo3.get(5),"2");
		
			//La categoria no tiene preguntas
			LinkedList<String> rdo4 = GestorCategorias.getGestorCategorias().obtenerPreguntasCategoria(c4);
			assertTrue(rdo4.isEmpty());
			
		}
		
		public void testObtenerPeguntaCorrespondiente(){ //no se si es necesaria esta prueba o con obtenerPreguntasCategoria ya vale
			
			//también compruebo que se incrementan las veces preguntada
			
			//Estan todas a false
				int vecesPreguntadaAntes1=p1.getVecesPreguntada();
			LinkedList<String> rdo1 = GestorPreguntas.getGestorPreguntas().obtenerPreguntaCorrespondiente(lp1);		
				int vecesPreguntadaDespues1=p1.getVecesPreguntada();
				assertTrue(vecesPreguntadaAntes1+1==vecesPreguntadaDespues1);
			assertFalse(rdo1.isEmpty());
			assertEquals(rdo1.get(0),"Nombre del doctor Jones");
			assertEquals(rdo1.get(1),"Paco");
			assertEquals(rdo1.get(2),"Francisco");
			assertEquals(rdo1.get(3),"Indiana");
			assertEquals(rdo1.get(4),"Missisipi");
			assertEquals(rdo1.get(5),"2");
		
			//Estan a true y a false
				int vecesPreguntadaAntes2=p2.getVecesPreguntada();
			LinkedList<String> rdo2 = GestorPreguntas.getGestorPreguntas().obtenerPreguntaCorrespondiente(lp1);		
				int vecesPreguntadaDespues2=p2.getVecesPreguntada();
				assertTrue(vecesPreguntadaAntes2+1==vecesPreguntadaDespues2);
			assertFalse(rdo2.isEmpty());
			assertEquals(rdo2.get(0),"Alicia en el pais de las maravillas: nombre del gato");
			assertEquals(rdo2.get(1),"Triston");
			assertEquals(rdo2.get(2),"Rison");
			assertEquals(rdo2.get(3),"Cabron");
			assertEquals(rdo2.get(4),"Chiston");
			assertEquals(rdo2.get(5),"4");
		
			//Estan todas a true
				int vecesPreguntadaAntes3=p1.getVecesPreguntada();		
			LinkedList<String> rdo3 = GestorPreguntas.getGestorPreguntas().obtenerPreguntaCorrespondiente(lp1);		
				int vecesPreguntadaDespues3=p1.getVecesPreguntada();
				assertTrue(vecesPreguntadaAntes3+1==vecesPreguntadaDespues3);		
			assertFalse(rdo3.isEmpty());
			assertEquals(rdo3.get(0),"Nombre del doctor Jones");
			assertEquals(rdo3.get(1),"Paco");
			assertEquals(rdo3.get(2),"Francisco");
			assertEquals(rdo3.get(3),"Indiana");
			assertEquals(rdo3.get(4),"Missisipi");
			assertEquals(rdo3.get(5),"2");
		
			//La categoria no tiene preguntas
			LinkedList<String> rdo4 = GestorPreguntas.getGestorPreguntas().obtenerPreguntaCorrespondiente(lp2);
			assertTrue(rdo4.isEmpty());
			
		}
		
		public void testIncrementarVecesPreguntada(){//no se si es necesaria esta prueba 		
			
			int vecesPreguntadaAntes1=p1.getVecesPreguntada();
			p1.incrementarVecesPreguntada();
			int vecesPreguntadaDespues1=p1.getVecesPreguntada();
			assertTrue(vecesPreguntadaAntes1+1==vecesPreguntadaDespues1);
		
			//con BD
			
			int numAntes1=0,numDespues1=0;
			
			try{
			ResultSet resultado1=GestorBD.getInstance().consulta("select VecesPreguntada from pregunta where idPregunta=2");
			if(resultado1.next()==true){
				numAntes1=resultado1.getInt("VecesPreguntada");
			}
			GestorBD.getInstance().cerrarConsulta(resultado1);

			
			   p1.incrementarVecesPreguntada();
			
			
			ResultSet resultado2=GestorBD.getInstance().consulta("select VecesPreguntada from pregunta where idPregunta=2");
			if(resultado2.next()==true){
				numDespues1=resultado2.getInt("VecesPreguntada");
			}
			GestorBD.getInstance().cerrarConsulta(resultado2);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
			assertTrue(numAntes1+1==numDespues1);
			
		}
			
		public void testObtenerInfoPregunta(){
			
			//La respuesta es correcta
				int vecesAcertadaAntes1=p1.getVecesAcertada();
			boolean rdo1 = GestorPreguntas.getGestorPreguntas().ObtenerInfoPregunta(p1, "Indiana");
				int vecesAcertadaDespues1=p1.getVecesAcertada();
				assertTrue(vecesAcertadaAntes1+1==vecesAcertadaDespues1);
			assertTrue(rdo1);
			
			//La respuesta no es correcta
				int vecesAcertadaAntes2=p1.getVecesAcertada();
			boolean rdo2 = GestorPreguntas.getGestorPreguntas().ObtenerInfoPregunta(p1, "Paco");
				int vecesAcertadaDespues2=p1.getVecesAcertada();
				assertTrue(vecesAcertadaAntes2==vecesAcertadaDespues2);		
			assertFalse(rdo2);
			
		}
		
		public void testComprobarRespuesta(){//no se si es necesaria esta prueba o con obtenerInfoPregunta ya vale
			
			//La respuesta es correcta
			boolean rdo1 = p1.comprobarRespuestas("Indiana");
			assertTrue(rdo1);
			
			//La respuesta no es correcta
			boolean rdo2 = p1.comprobarRespuestas("Paco");
			assertFalse(rdo2);
			
		}
		
		public void testIncrementarVecesAcertada(){//no se si es necesaria esta prueba 
		
			int vecesAcertadaAntes1=p1.getVecesAcertada();
			p1.incrementarVecesAcertada();
			int vecesAcertadaDespues1=p1.getVecesAcertada();
			assertTrue(vecesAcertadaAntes1+1==vecesAcertadaDespues1);
		
			//con BD
			
			int numAntes1=0,numDespues1=0;
			
			try{
			ResultSet resultado1=GestorBD.getInstance().consulta("select VecesAcertada from pregunta where idPregunta=2");
			if(resultado1.next()==true){
				numAntes1=resultado1.getInt("VecesAcertada");
			}
			GestorBD.getInstance().cerrarConsulta(resultado1);

			
			   p1.incrementarVecesAcertada();
			
			
			ResultSet resultado2=GestorBD.getInstance().consulta("select VecesAcertada from pregunta where idPregunta=2");
			if(resultado2.next()==true){
				numDespues1=resultado2.getInt("VecesAcertada");
			}
			GestorBD.getInstance().cerrarConsulta(resultado2);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
			assertTrue(numAntes1+1==numDespues1);
			
			
		}
		
		public void testCrearRespuesta(){
			
			//La respuesta es correcta
			int tamañoListaAntes1=GestorRespuestas.getGestorRespuestas().getListaRespuestas().size();
			GestorRespuestas.getGestorRespuestas().crearRespuesta(true, u1, p1);
			int tamañoListaDespues1=GestorRespuestas.getGestorRespuestas().getListaRespuestas().size();
			assertTrue(tamañoListaAntes1+1==tamañoListaDespues1);
			
			//---La respuesta no es correcta con BD
			int numAntes1=0,numDespues1=0;
			
			try{
			ResultSet resultado1=GestorBD.getInstance().consulta("select count(*) as c from respuestas");
			if(resultado1.next()==true){
				numAntes1=resultado1.getInt("c");
			}
			GestorBD.getInstance().cerrarConsulta(resultado1);

			
			   GestorRespuestas.getGestorRespuestas().crearRespuesta(false, u1, p2);
			   
			
			ResultSet resultado2=GestorBD.getInstance().consulta("select count(*) as c from respuestas");
			if(resultado2.next()==true){
				
				numDespues1=resultado2.getInt("c");
				
			}
			GestorBD.getInstance().cerrarConsulta(resultado2);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
			assertTrue(numAntes1+1==numDespues1);
		}

		public void testActualizarUsuario(){
		
			//El rdo es true
			int numAcerAntes1=u1.getNumPregAcer();
			int numTotalAntes1=u1.getNumPregTotal();
			GestorUsuarios.getGestorUsuarios().actualizarUsuario(u1, true);
			int numAcerDespues1=u1.getNumPregAcer();
			int numTotalDespues1=u1.getNumPregTotal();
			assertTrue(numAcerAntes1+1==numAcerDespues1);
			assertTrue(numTotalAntes1+1==numTotalDespues1);
		
			//El rdo es false
			int numAcerAntes2=u1.getNumPregAcer();
			int numTotalAntes2=u1.getNumPregTotal();
			GestorUsuarios.getGestorUsuarios().actualizarUsuario(u1, false);
			int numAcerDespues2=u1.getNumPregAcer();
			int numTotalDespues2=u1.getNumPregTotal();
			assertTrue(numAcerAntes2==numAcerDespues2);
			assertTrue(numTotalAntes2+1==numTotalDespues2);
		
		}
		
		public void textIncrementarPreguntasAcertadas(){//no se si es necesaria esta prueba o con actualizarUsuario ya vale
			
			int numAcerAntes1=u1.getNumPregAcer();
			u1.incrementarPreguntasAcertadas();
			int numAcerDespues1=u1.getNumPregAcer();
			assertTrue(numAcerAntes1+1==numAcerDespues1);
			
			//con BD
			
			int numAntes1=0,numDespues1=0;
			
			try{
			ResultSet resultado1=GestorBD.getInstance().consulta("select numPregAcert from usuarios where nombre='Sara'");
			if(resultado1.next()==true){
				numAntes1=resultado1.getInt("numPregAcert");
			}
			GestorBD.getInstance().cerrarConsulta(resultado1);

			
			   u1.incrementarPreguntasAcertadas();
			
			
			ResultSet resultado2=GestorBD.getInstance().consulta("select numPregAcert from usuarios where nombre='Sara'");
			if(resultado2.next()==true){
				numDespues1=resultado2.getInt("numPregAcert");
			}
			GestorBD.getInstance().cerrarConsulta(resultado2);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
			assertTrue(numAntes1+1==numDespues1);
			
			
		}
		
		public void testIncrementarPreguntasTotales(){//no se si es necesaria esta prueba o con actualizarUsuario ya vale
			
			int numTotalAntes1=u1.getNumPregTotal();
			u1.incrementarPreguntasTotales();
			int numTotalDespues1=u1.getNumPregTotal();
			assertTrue(numTotalAntes1+1==numTotalDespues1);
			
	//con BD
			
			int numAntes1=0,numDespues1=0;
			
			try{
			ResultSet resultado1=GestorBD.getInstance().consulta("select numPregTotal from usuarios where nombre='Sara'");
			if(resultado1.next()==true){
				numAntes1=resultado1.getInt("numPregTotal");
			}
			GestorBD.getInstance().cerrarConsulta(resultado1);

			
			   u1.incrementarPreguntasTotales();
			
			
			ResultSet resultado2=GestorBD.getInstance().consulta("select numPregTotal from usuarios where nombre='Sara'");
			if(resultado2.next()==true){
				numDespues1=resultado2.getInt("numPregTotal");
			}
			GestorBD.getInstance().cerrarConsulta(resultado2);
			}
			catch(SQLException ex){
				System.out.println(ex.toString());
			}
			assertTrue(numAntes1+1==numDespues1);
			
		}
		
		
}
