import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import org.obvial.obvial.Categoria;
import org.obvial.obvial.GestorBD;
import org.obvial.obvial.GestorCategorias;
import org.obvial.obvial.GestorPreguntas;
import org.obvial.obvial.GestorUsuarios;
import org.obvial.obvial.Pregunta;



import junit.framework.TestCase;

public class pruebasJonAnder extends TestCase {
	
	LinkedList<String> listaRespuestas = new LinkedList<String>();
	Pregunta nuevaPregunta= null;
	Categoria categoria = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		listaRespuestas.add("Amarillo");
		listaRespuestas.add("Verde");
		listaRespuestas.add("Rojo");
		listaRespuestas.add("Azul");
		nuevaPregunta = GestorPreguntas.getGestorPreguntas().crearPregunta("De que color es un platano recien cojido", listaRespuestas, "Verde", 0, 0);
		categoria = GestorCategorias.getGestorCategorias().buscarCategoria("Cine");

	}
	
	public void testañadirIdAPregunta(){//Metodo que se encuentra en la clase Pregunta	
		//En este metodo se prueba si un ID se añade correctamente a una pregunta
		assertEquals(0,nuevaPregunta.getId());//Comprobamos el ID antes de añadir
		GestorPreguntas.getGestorPreguntas().añadirIdAPregunta(nuevaPregunta,99);//añadimos el ID
		assertEquals(99,nuevaPregunta.getId());//Comprobamos el ID despues de añadir
	}
	
	public void testanadirPregunta(){//Metodo que se encuentra en la clase Categoria
		//En este metodo se prueba si una pregunta se añade a una categoria
		assertEquals(categoria.numeroPreguntas(),3);//COmprobamos la cantidad de preguntas en la categoria antes de añadir
		categoria.anadirPregunta(nuevaPregunta);//Añadimos una pregunta a la lista de preguntas de la categoria
		assertEquals(categoria.numeroPreguntas(),4);//COmprobamos la cantidad de preguntas en la categoria despues de añadir
		categoria.eliminarPregunta(nuevaPregunta);//Eliminamos la pregunta de la lista ya que era de prueba
	}
	
	public void testobtenerIdCategoria(){//Metodo que se encuentra en la clase GestorCategoria
		//Metodo que prueba que un ID de una categoria se obtiene correctamente
		assertEquals(GestorCategorias.getGestorCategorias().obtenerIdCategoria(categoria),1);//COmprobamos que el ID de la categoria que le hemos pasado se corresponte (La categoria Cien tiene ID 1)
	}
	
	public void testAñadirPreguntaALaBD(){//Metodo que se encuentra en la clase GestorPreguntas
		ResultSet resultado =null;
		String sql="SELECT count(*) as veces FROM pregunta WHERE texto='"+ nuevaPregunta.getTextoPregunta() +"';";
		try {
			//Comprobamos si la pregunta existe en la base de datos
			resultado = GestorBD.getInstance().consulta(sql);
			resultado.next();
			assertEquals(0,resultado.getInt("veces"));
			GestorBD.getInstance().cerrarConsulta(resultado);
			
			//Insertamos que la pregunta a la base de datos y comprobamos que lo ha hecho correctamente
			GestorPreguntas.getGestorPreguntas().anadirPreguntaALaBD(nuevaPregunta);
			resultado = GestorBD.getInstance().consulta(sql);
			resultado.next();
			assertEquals(1,resultado.getInt("veces"));
			GestorBD.getInstance().cerrarConsulta(resultado);
			
			//Eliminamos la pregunta de la base de datos al tratarse de una pregunta de prueba.
			sql = "DELETE FROM pregunta WHERE texto='"+ nuevaPregunta.getTextoPregunta() +"';";
			GestorBD.getInstance().borrar(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		GestorPreguntas.getGestorPreguntas().anadirPreguntaALaBD(nuevaPregunta);
	}
	
	public void testAñadirPreguntaACategoria(){//Metodo que se encuentra en la clase GestorCategorias
	//Intento de añadir una pregunta a la BD
		int numeroPreguntasenCategoriaAntesDeAñadir = categoria.numeroPreguntas();
		ResultSet resultado = null;
		String sql="SELECT count(*) as veces FROM pregunta WHERE texto='"+ nuevaPregunta.getTextoPregunta() +"';";
		
		try {
			//Consulto que realmente la pregunta no existe en la base de datos
			resultado = GestorBD.getInstance().consulta(sql);
			resultado.next();
			assertEquals(0,resultado.getInt("veces"));
			GestorBD.getInstance().cerrarConsulta(resultado);
			
			//Añado la pregunta a la bese de datos
			boolean sePuede = GestorCategorias.getGestorCategorias().añadirPreguntaACateroria(nuevaPregunta,categoria);
			
			//Compruebo que la pregunta esta en la base de datos
			resultado = GestorBD.getInstance().consulta(sql);
			resultado.next();
			assertEquals(1,resultado.getInt("veces"));
			GestorBD.getInstance().cerrarConsulta(resultado);
			
			//Compruebo que hay una pregunta mas de las que habia al principio en la categoria que tiene que asignarse la pregunta
			assertEquals(numeroPreguntasenCategoriaAntesDeAñadir + 1,categoria.numeroPreguntas());
				
	
	//Intento de añadir una pregunta que ya existe en la BD
			numeroPreguntasenCategoriaAntesDeAñadir = categoria.numeroPreguntas();
			sql="SELECT count(*) as veces FROM pregunta WHERE texto='"+ nuevaPregunta.getTextoPregunta() +"';";
			//Compruebo que la pregunta esta en la base de datos
			resultado = GestorBD.getInstance().consulta(sql);
			resultado.next();
			assertEquals(1,resultado.getInt("veces"));
			GestorBD.getInstance().cerrarConsulta(resultado);
			
			//Intento insertar la pregunta por segunda vez
			sePuede = GestorCategorias.getGestorCategorias().añadirPreguntaACateroria(nuevaPregunta,categoria);
			
			//Compruebo que la pregunta no se ha insertado en la base de datos
			resultado = GestorBD.getInstance().consulta(sql);
			resultado.next();
			assertEquals(1,resultado.getInt("veces"));
			GestorBD.getInstance().cerrarConsulta(resultado);
			
			//Compruebo que el numero de preguntas en la cateria no ha variado
			assertEquals(numeroPreguntasenCategoriaAntesDeAñadir,categoria.numeroPreguntas());
			
		
		//Elimino la pregunta de prueba de la BD
			sql = "DELETE FROM pregunta WHERE texto='"+ nuevaPregunta.getTextoPregunta() +"';";
			GestorBD.getInstance().borrar(sql);
//			GestorBD.getInstance().cerrarConsulta(resultado);


		} catch (SQLException e) {
			System.out.println(e.toString());
		}

	}
	
	//Metodo que prueba los logeos correctos del sistema
	public void testLogeoUsuario(){
	//Logeos Correctos
		//Logeo de un usuario con permisos como administrador
		assertTrue(GestorUsuarios.getGestorUsuarios().logeoUsuario("Admin","Admin",true));
		//Logeo de usuario con permisos logeandose como jugador
		assertTrue(GestorUsuarios.getGestorUsuarios().logeoUsuario("Admin","Admin",false));
		//Logeo de usuarios sin permisos logeandose como jugadores
		assertTrue(GestorUsuarios.getGestorUsuarios().logeoUsuario("Sara","araS",false));
		assertTrue(GestorUsuarios.getGestorUsuarios().logeoUsuario("Helen","neleH",false));
		assertTrue(GestorUsuarios.getGestorUsuarios().logeoUsuario("David","divaD",false));
		assertTrue(GestorUsuarios.getGestorUsuarios().logeoUsuario("Egoitz","ztiogE",false));
	
	//Logeos Incorrectos
		//Logeo de un usuario sin permisos como administrador
		assertFalse(GestorUsuarios.getGestorUsuarios().logeoUsuario("Sara","araS",true));
		//Logeo de un usuario que no existe logeandose como jugador
		assertFalse(GestorUsuarios.getGestorUsuarios().logeoUsuario("Pepe","Pepe",false));
		//logeo de un usuario que no existe logeandose como administrador
		assertFalse(GestorUsuarios.getGestorUsuarios().logeoUsuario("Pepe","Pepe",true));

	}

	protected void tearDown() throws Exception {
		super.tearDown();
		nuevaPregunta= null;
		categoria = null;
	}

}
