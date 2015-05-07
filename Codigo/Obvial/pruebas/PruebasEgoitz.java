import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.obvial.obvial.*;

import junit.framework.TestCase;


public class PruebasEgoitz extends TestCase {

	//Atributos para el test de la clase Pregunta
	private Pregunta preg;
	private String aux; //contiene la resp correcta
	//Atributos para el test de la clase GestorPreguntas
	private LinkedList<Pregunta> LP;
	
	
	//SETUP'S
	protected void setUpPregunta() throws Exception {
		super.setUp();
		String sqlupdate="update pregunta set idPregunta='2'," +
				" texto='Nombre del doctor Jones'," +
				" resp1='Paco'," +
				" resp2='Francisco'," +
				" resp3='Indiana'," +
				" resp4='Missisipi'," +
				" respCorrecta='Indiana' where idPregunta=2";
		try{
			GestorBD.getInstance().actualizar(sqlupdate);
			LinkedList<String> listaRespuestas = new LinkedList<String>();
			aux="Indiana";
			listaRespuestas.add("Paco");
			listaRespuestas.add("Francisco");
			listaRespuestas.add(aux);
			listaRespuestas.add("Missisipi");
			preg= new Pregunta(2, "Nombre del doctor Jones", listaRespuestas, "Indiana", 0, 0);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
	}

	protected void tearDownPregunta() throws Exception {
		super.tearDown();
	}

	protected void setUpGestorPreguntas() throws Exception {
		super.setUp();
		LP= new LinkedList<Pregunta>();
		LinkedList<String> listaRespuestas = new LinkedList<String>();
		aux="Indiana";
		listaRespuestas.add("Paco");
		listaRespuestas.add("Francisco");
		listaRespuestas.add(aux);
		listaRespuestas.add("Missisipi");
		preg= new Pregunta(2, "Nombre del doctor Jones", listaRespuestas, "Indiana", 0, 0);
		
		String sql1 = "insert into pregunta (idPregunta, texto, resp1, resp2, resp3, resp4, respCorrecta) values " +
				"('2','Nombre del doctor Jones','Paco','Francisco','Indiana','Missisipi','Indiana');";
		String sql2 = "insert into pregunta (idPregunta, texto, resp1, resp2, resp3, resp4, respCorrecta) values " +
				"('3','Sobrenatural: Apellido de Sam y Dean','Winchester','Beretta','Kate','Colt','Winchester');";
		try {
			GestorBD.getInstance().actualizar("delete from pregunta where idPregunta='2'");
			GestorBD.getInstance().insertar(sql1);
			//
			GestorBD.getInstance().insertar("insert into pregCat (idPregunta, idCategoria) values ('2','1')");
		}
		catch(SQLException ex){
			System.out.println("Ya existe la pregunta de Iniana Jones. No es error.");
			//System.out.println(ex.toString());
		}
		try {
			GestorBD.getInstance().actualizar("delete from pregunta where idPregunta='3'");
			GestorBD.getInstance().insertar(sql2);
			GestorBD.getInstance().insertar("insert into pregCat (idPregunta, idCategoria) values ('3','3')");
		}
		catch(SQLException ex){
			System.out.println("Ya existe la pregunta de Sobrenatural. No es error.");
			//System.out.println(ex.toString());
		}
	}
	
	//Test de la clase Pregunta:
	
	public void testPregunta() throws Exception{
		setUpPregunta();
		assertEquals("Indiana", preg.getRespuestaCorrecta());
		assertEquals(aux,preg.getListaRespuestas().get(2));
		assertEquals("Missisipi",preg.getListaRespuestas().get(3));
		assertEquals(2, preg.getId());
	}

	public void testGetTextoPregunta() throws Exception{
		setUpPregunta();
		assertEquals("Nombre del doctor Jones", preg.getTextoPregunta());
	}

	public void testComparar() throws Exception{
		setUpPregunta();
		assertEquals(true, preg.comparar("Nombre del doctor Jones"));
		assertEquals(false, preg.comparar(""));
		assertEquals(false, preg.comparar("Nombre del doctor JONES"));
		assertEquals(false, preg.comparar(aux));
	}

	public void testGetId() throws Exception{
		setUpPregunta();
		assertEquals(2, preg.getId());
	}

	public void testGetRespuestaCorrecta() throws Exception{
		setUpPregunta();
		assertEquals(aux, preg.getRespuestaCorrecta());
		assertEquals("Indiana", preg.getRespuestaCorrecta());
		assertEquals(preg.getListaRespuestas().get(2), preg.getRespuestaCorrecta());
	}

	public void testModificarDatos() throws Exception{
		setUpPregunta();
		LinkedList<String> datosMod = new LinkedList<String>();
		//0-Titulo 1/4-Resp 5-RespCorrecta
		datosMod.add("Nombre del doctor Jones, que dispone de 4 peliculas");
		datosMod.add("Paco");
		assertEquals(false, preg.modificarDatos(null));
		datosMod.add(aux);
		datosMod.add("Francisco");
		assertEquals(false, preg.modificarDatos(datosMod));
		datosMod.add("Missisipi");
		datosMod.add(aux);
		assertEquals(true, preg.modificarDatos(datosMod));
		
		assertEquals("Indiana", preg.getRespuestaCorrecta());
		assertEquals("Francisco",preg.getListaRespuestas().get(2));
		assertEquals("Missisipi",preg.getListaRespuestas().get(3));
		assertEquals(2, preg.getId());
		String sql = "select texto, resp1, VecesAcertada from pregunta where idPregunta='2';";
		try{
			ResultSet rdo= GestorBD.getInstance().consulta(sql);
			if (rdo.next()){
				assertEquals("Nombre del doctor Jones, que dispone de 4 peliculas", rdo.getNString("texto"));
				assertEquals("Paco", rdo.getNString("resp1"));
				assertNotNull(rdo.getInt("VecesAcertada"));
			}else{
				fail("La pregunta no se ha encontrado en la BD");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		tearDownCategoria();
	}

	
	//Pruebas para la clase GestorPreguntas
	
	public void testAnadirPregunta() throws Exception{
		setUpGestorPreguntas();
		assertEquals(LP.size(),0);
		//public boolean AnadirPregunta(LinkedList<Pregunta> LP, Pregunta laPregunta)
		assertFalse(GestorPreguntas.getGestorPreguntas().AnadirPregunta(LP, null));
		assertEquals(LP.size(),0);
		assertFalse(GestorPreguntas.getGestorPreguntas().AnadirPregunta(null, null));
		assertEquals(LP.size(),0);
		assertFalse(GestorPreguntas.getGestorPreguntas().AnadirPregunta(null, preg));
		assertEquals(LP.size(),0);
		assertTrue(GestorPreguntas.getGestorPreguntas().AnadirPregunta(LP, preg));
		assertEquals(LP.size(),1);
		LinkedList<String> listaRespuestas = new LinkedList<String>();
		aux="Winchester";
		listaRespuestas.add("Winchester");
		listaRespuestas.add("Beretta");
		listaRespuestas.add("Kate");
		listaRespuestas.add("Colt");
		preg= new Pregunta(3, "Sobrenatural: Apellido de Sam y Dean", listaRespuestas, "Winchester", 0, 0);
		assertTrue(GestorPreguntas.getGestorPreguntas().AnadirPregunta(LP, preg));
		assertEquals(LP.size(),2);
		assertFalse(GestorPreguntas.getGestorPreguntas().AnadirPregunta(LP, null));
		assertEquals(LP.size(),2);
		assertFalse(GestorPreguntas.getGestorPreguntas().AnadirPregunta(null, null));
		assertEquals(LP.size(),2);
		assertFalse(GestorPreguntas.getGestorPreguntas().AnadirPregunta(null, preg));
		assertEquals(LP.size(),2);
	}
	
	public void testObtenerTextoPreguntas() throws Exception {
		setUpGestorPreguntas();
		LinkedList<PreguntaYTitulo> rdo;
		//public LinkedList<PreguntaYTitulo> ObtenerTextoPreguntas(LinkedList<Pregunta> lPreg)
		//Probamos a obtener de un objeto nulo.
		rdo=GestorPreguntas.getGestorPreguntas().ObtenerTextoPreguntas(null);
		assertNull(rdo);
		//Probabos de obtener de un objeto vacio
		rdo=GestorPreguntas.getGestorPreguntas().ObtenerTextoPreguntas(LP);
		assertNull(rdo);
		//Probamos obtener de un objeto con un unico contenido
		LP.add(preg);
		rdo=GestorPreguntas.getGestorPreguntas().ObtenerTextoPreguntas(LP);
		assertNotNull(rdo);
		assertEquals(rdo.size(),1);
		assertEquals(rdo.get(0).getPreg(),preg);
		//Probamos a obtener de un objeto con dos contenidos.
		LinkedList<String> listaRespuestas = new LinkedList<String>();
		aux="Winchester";
		listaRespuestas.add("Winchester");
		listaRespuestas.add("Beretta");
		listaRespuestas.add("Kate");
		listaRespuestas.add("Colt");
		preg= new Pregunta(3, "Sobrenatural: Apellido de Sam y Dean", listaRespuestas, "Winchester", 0, 0);
		LP.add(preg);
		rdo=GestorPreguntas.getGestorPreguntas().ObtenerTextoPreguntas(LP);
		assertNotNull(rdo);
		assertEquals(rdo.size(),2);
		assertEquals(rdo.get(1).getPreg(),preg);
		assertEquals(rdo.get(0).getTitulo(), "Nombre del doctor Jones");
	}
	
	public void testEliminarPregunta() throws Exception {
		setUpGestorPreguntas();
		//public LinkedList<Pregunta> eliminarPregunta(LinkedList<Pregunta> lPreg, Pregunta preg)
		LinkedList<Pregunta> rdo;
		LP.add(preg);
		LinkedList<String> listaRespuestas = new LinkedList<String>();
		aux="Winchester";
		listaRespuestas.add("Winchester");
		listaRespuestas.add("Beretta");
		listaRespuestas.add("Kate");
		listaRespuestas.add("Colt");
		Pregunta preg2= new Pregunta(3, "Sobrenatural: Apellido de Sam y Dean", listaRespuestas, "Winchester", 0, 0);
		LP.add(preg2);
		assertEquals(LP.size(),2);
		String sql = "select count(*) as count from pregunta;";
		ResultSet rdoConsulta;
		try {
			rdoConsulta = GestorBD.getInstance().consulta(sql);
			if(rdoConsulta.next()){
				assertEquals(rdoConsulta.getInt("count"),4);
			}else{
				fail("Fallo al seleccionar consulta (1)");
			}
			GestorBD.getInstance().cerrarConsulta(rdoConsulta);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		
		//Pregunta null -> no hace nada
		assertEquals(GestorPreguntas.getGestorPreguntas().eliminarPregunta(LP, null),LP);
		//Eliminamos con 2 objetos
		rdo = GestorPreguntas.getGestorPreguntas().eliminarPregunta(LP, preg);
		assertNotNull(rdo);
		assertEquals(rdo.size(),1);
		assertEquals(LP.size(),1);
		try {
			rdoConsulta = GestorBD.getInstance().consulta(sql);
			if(rdoConsulta.next()){
				assertEquals(rdoConsulta.getInt("count"),3);
			}else{
				fail("Fallo al seleccionar consulta (2)");
			}
			GestorBD.getInstance().cerrarConsulta(rdoConsulta);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		
		//Eliminamos dos veces el mismo objeto -> no ocurre nada
		rdo = GestorPreguntas.getGestorPreguntas().eliminarPregunta(LP, preg);
		assertEquals(rdo,LP);
		//Eliminamos con 1 objeto
		rdo = GestorPreguntas.getGestorPreguntas().eliminarPregunta(LP, preg2);
		assertNotNull(rdo);
		assertEquals(rdo.size(),0);
		assertEquals(LP.size(),0);
		try {
			rdoConsulta = GestorBD.getInstance().consulta(sql);
			if(rdoConsulta.next()){
				assertEquals(rdoConsulta.getInt("count"),2);
			}else{
				fail("Fallo al seleccionar consulta (3)");
			}
			GestorBD.getInstance().cerrarConsulta(rdoConsulta);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		
		//Eliminamos con ningun objeto
		rdo = GestorPreguntas.getGestorPreguntas().eliminarPregunta(LP, preg2);
		assertNotNull(rdo);
		assertEquals(rdo.size(),0);
		assertEquals(LP.size(),0);
		//Pasamos parametros null
		assertNull(GestorPreguntas.getGestorPreguntas().eliminarPregunta(null, preg2));
		assertNull(GestorPreguntas.getGestorPreguntas().eliminarPregunta(null, null));
		tearDownCategoria();
	}

	public void testCargarDatosPregunta() throws Exception {
		setUpGestorPreguntas();
		//public LinkedList<String> cargarDatosPregunta(Pregunta preg)
		LinkedList<String> rdo;
		//Pasamos un null
		rdo = GestorPreguntas.getGestorPreguntas().cargarDatosPregunta(null);
		assertNull(rdo);
		//Pasamos una pregunta
		rdo = GestorPreguntas.getGestorPreguntas().cargarDatosPregunta(preg);
		assertEquals(rdo.size(),6);
		assertEquals(rdo.get(0),"Nombre del doctor Jones");
		assertEquals(rdo.get(1), "Paco");
		assertEquals(rdo.get(2), "Francisco");
		assertEquals(rdo.get(3), "Indiana");
		assertEquals(rdo.get(4), "Missisipi");
		assertEquals(rdo.get(5), "Indiana");
		tearDownCategoria();
	}
	
	public void testQuitarPregunta() throws Exception {
		//public boolean quitarPregunta(LinkedList<Pregunta> listaPreguntas, Pregunta preg)
		//elimina de la lista, no de la BD
		setUpGestorPreguntas();
		LinkedList<Pregunta> rdo;
		LP.add(preg);
		LinkedList<String> listaRespuestas = new LinkedList<String>();
		aux="Winchester";
		listaRespuestas.add("Winchester");
		listaRespuestas.add("Beretta");
		listaRespuestas.add("Kate");
		listaRespuestas.add("Colt");
		Pregunta preg2= new Pregunta(3, "Sobrenatural: Apellido de Sam y Dean", listaRespuestas, "Winchester", 0, 0);
		LP.add(preg2);
		assertEquals(LP.size(),2);
		// null -> no hace nada
		assertFalse(GestorPreguntas.getGestorPreguntas().quitarPregunta(LP, null));
		assertFalse(GestorPreguntas.getGestorPreguntas().quitarPregunta(null, preg));
		assertFalse(GestorPreguntas.getGestorPreguntas().quitarPregunta(null, null));
		//Eliminamos con 2 objetos
		assertTrue(GestorPreguntas.getGestorPreguntas().quitarPregunta(LP, preg));
		//Eliminamos un objeto que no esta (dos veces el mismo objeto)
		assertFalse(GestorPreguntas.getGestorPreguntas().quitarPregunta(LP, preg));
		//Eliminamos con 1 objeto
		assertTrue(GestorPreguntas.getGestorPreguntas().quitarPregunta(LP, preg2));
		//Eliminamos con 0 objetos
		assertFalse(GestorPreguntas.getGestorPreguntas().quitarPregunta(LP, preg));
		tearDownCategoria();
	}
	
	public void testModificarDatosPreg() throws Exception {
		setUpGestorPreguntas();
		//public boolean modificarDatosPreg(Pregunta preg, LinkedList<String> datosMod)
		LinkedList<String> datosMod = new LinkedList<String>();
		//datosMod null
		assertFalse(GestorPreguntas.getGestorPreguntas().modificarDatosPreg(preg, null));
		//DatosMod vacio
		assertFalse(GestorPreguntas.getGestorPreguntas().modificarDatosPreg(preg, datosMod));
		//Pregunta null
		assertFalse(GestorPreguntas.getGestorPreguntas().modificarDatosPreg(null, datosMod));
		//DatosMod correcto
		//'Nombre del doctor Jones','Paco','Francisco','Indiana','Missisipi','Indiana'
		datosMod.add("Nombre del doctor Jones, que dispone de 4 peliculas");
		datosMod.add("Paco");
		datosMod.add("Francisco");
		datosMod.add("Indiana");
		datosMod.add("Missisipi");
		datosMod.add("Indiana");
		assertTrue(GestorPreguntas.getGestorPreguntas().modificarDatosPreg(preg, datosMod));
		String sql = "select texto, resp1, respCorrecta, VecesAcertada from pregunta where idPregunta='2';";
		try{
			ResultSet rdo= GestorBD.getInstance().consulta(sql);
			if (rdo.next()){
				assertEquals("Nombre del doctor Jones, que dispone de 4 peliculas", rdo.getNString("texto"));
				assertEquals("Paco", rdo.getNString("resp1"));
				assertEquals("Indiana", rdo.getNString("respCorrecta"));
				assertNotNull(rdo.getInt("VecesAcertada"));
			}else{
				fail("La pregunta no se ha encontrado en la BD");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}catch(SQLException ex){
			System.out.println(ex.toString());
		}
		//DatosMod con mas
		datosMod = new LinkedList<String>();
		datosMod.add("Nombre del doctor Jones, protagonista de 4 peliculas");
		datosMod.add("Paco");
		datosMod.add("Francisco");
		datosMod.add("Indiana");
		datosMod.add("Missisipi");
		datosMod.add("Indiana");
		datosMod.add("asdfghjk");
		assertFalse(GestorPreguntas.getGestorPreguntas().modificarDatosPreg(preg, datosMod));
		try{
			ResultSet rdo= GestorBD.getInstance().consulta(sql);
			if (rdo.next()){
				assertEquals("Nombre del doctor Jones, que dispone de 4 peliculas", rdo.getNString("texto"));
				assertEquals("Paco", rdo.getNString("resp1"));
				assertEquals("Indiana", rdo.getNString("respCorrecta"));
				assertNotNull(rdo.getInt("VecesAcertada"));
			}else{
				fail("La pregunta no se ha encontrado en la BD");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}catch(SQLException ex){
			System.out.println(ex.toString());
		}
	}
	
	
	//Categoria
	Categoria catCine, catVideojuegos, catManga;
	protected void setUpCategoria() throws Exception {
		super.setUp();
		/*try{
			ResultSet rdo;
			GestorBD.getInstance().actualizar("update categoria set nombre='cine' where idCategoria='1';");
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}*/
		catCine=new Categoria(1, "Cine"); //tiene 2 preguntas
		catVideojuegos=new Categoria(2, "Videojuegos"); //Tiene 1 pregunta
		catManga=new Categoria (4,"Manga"); // tiene 0 preguntas
	}
	
	protected void tearDownCategoria() throws Exception {
		super.tearDown();
		try{
			GestorBD.getInstance().actualizar("delete from pregunta where idPregunta='2'");
			GestorBD.getInstance().insertar("insert into pregunta (idPregunta, texto, resp1, resp2, resp3, resp4, respCorrecta) values"+
			"('2','Nombre del doctor Jones','Paco','Francisco','Indiana','Missisipi','Indiana');");
			GestorBD.getInstance().insertar("insert into pregCat (idPregunta, idCategoria) values ('2','1')");
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		try{
			GestorBD.getInstance().actualizar("delete from pregunta where idPregunta='4'");
			GestorBD.getInstance().insertar("insert into pregunta (idPregunta, texto, resp1, resp2, resp3, resp4, respCorrecta) values"+
			"('4','Alicia en el pais de las maravillas: nombre del gato','Triston','Rison','Cabron','Chiston','Rison');");
			GestorBD.getInstance().insertar("insert into pregCat (idPregunta, idCategoria) values ('4','1')");
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		try{
			GestorBD.getInstance().actualizar("delete from pregunta where idPregunta='3'");
			GestorBD.getInstance().insertar("insert into pregunta (idPregunta, texto, resp1, resp2, resp3, resp4, respCorrecta) values"+
			"('3','Sobrenatural: Apellido de Sam y Dean','Winchester','Beretta','Kate','Colt','Winchester');");
			GestorBD.getInstance().insertar("insert into pregCat (idPregunta, idCategoria) values ('3','3')");
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		try{
			GestorBD.getInstance().actualizar("delete from pregunta where idPregunta='1'");
			GestorBD.getInstance().insertar("insert into pregunta (idPregunta, texto, resp1, resp2, resp3, resp4, respCorrecta) values"+
			"('1', 'Pokemon: Nombre de la rata amarilla', 'Charmander','Pikachu','Squirtel','Ekans','Pikachu');");
			GestorBD.getInstance().insertar("insert into pregCat (idPregunta, idCategoria) values ('1','2')");
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
	}
	
	public void testGetListaPreguntas() throws Exception{
		setUpCategoria();
		//Categoria con 2 preguntas
		assertNotNull(catCine.getListaPreguntas());
		assertEquals(catCine.getListaPreguntas().size(),2);
		//Categoria con 1 pregunta
		assertNotNull(catVideojuegos.getListaPreguntas());
		assertEquals(catVideojuegos.getListaPreguntas().size(),1);
		//Categoria sin preguntas
		assertNotNull(catManga.getListaPreguntas());
		assertEquals(catManga.getListaPreguntas().size(),0);
		//public LinkedList<Pregunta> getListaPreguntas()
		tearDownCategoria();
	}
	
	public void testObtenerNombre() throws Exception{
		setUpCategoria();
		assertEquals(catCine.obtenerNombre(),"Cine");
		//public String obtenerNombre()
	}
	
	public void testNumeroPreguntas()throws Exception{
		//fail("No implementado");
		setUpCategoria();
		assertEquals(catCine.numeroPreguntas(),2);
		assertEquals(catVideojuegos.numeroPreguntas(),1);
		assertEquals(catManga.numeroPreguntas(),0);
		//public int numeroPreguntas()
	}
	
	/*public void testCambiarNombre()throws Exception{
		//fail("No implementado");
		setUpCategoria();
		catCine.cambiarNombre("Cine2");
		try{
			ResultSet rdo;
			rdo = GestorBD.getInstance().consulta("select nombre from categoria where idCategoria='"+catCine.getId()+"';");
			if(rdo.next()){
				assertEquals(rdo.getString("nombre"), "Cine2");
			}
			else{
				fail("No se ha encontrado el registro en la BD");
			}
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		//public void cambiarNombre(String nuevoNombre)
	}*/
	
	public void testCompararCategoria()throws Exception{
		setUpCategoria();
		assertTrue(catCine.comparar("Cine"));
		assertFalse(catCine.comparar("Quimica"));
		//public boolean comparar(String nomCat)
	}
	
	public void testActualizarPreguntas()throws Exception{
		//fail("No implementado");
		setUpCategoria();
		assertEquals(catCine.numeroPreguntas(),2);
		LP=new LinkedList<Pregunta>();
		catCine.actualizarPreguntas(LP);
		assertEquals(catCine.numeroPreguntas(),0);
		//public void actualizarPreguntas(LinkedList<Pregunta> lPreguntas)
		tearDownCategoria();
	}
	public void testGetIdCategoria()throws Exception{
		//fail("No implementado");
		setUpCategoria();
		assertEquals(catCine.getId(),1);
		assertEquals(catVideojuegos.getId(),2);
		assertEquals(catManga.getId(),4);
		//public int getId() 
	}
	
	public void testQuitarPreguntaCategoria()throws Exception{
		setUpCategoria();
		//Eliminar un null -> false
		assertFalse(catCine.quitarPregunta(null));
		//Eliminar Pregunta que no es de la categoria -> false
		//('1', 'Pokemon: Nombre de la rata amarilla', 'Charmander','Pikachu','Squirtel','Ekans','Pikachu')
		LinkedList<String> listaRespuestas=new LinkedList<String>();
		listaRespuestas.add("Charmander");
		listaRespuestas.add("Pikachu");
		listaRespuestas.add("Squirtel");
		listaRespuestas.add("Ekans");
		Pregunta preg2= new Pregunta(1, "Pokemon: Nombre de la rata amarilla", listaRespuestas, "Pikachu", 0, 0);
		assertFalse(catCine.quitarPregunta(preg2));
		
		listaRespuestas.add("Paco");
		listaRespuestas.add("Francisco");
		listaRespuestas.add(aux);
		listaRespuestas.add("Missisipi");
		Pregunta preg3= new Pregunta(2, "Nombre del doctor Jones, que dispone de 4 peliculas", listaRespuestas, "Indiana", 0, 0);
		//Eliminar de cat con 2 preguntas
		assertEquals(catCine.numeroPreguntas(),2);
		assertTrue(catCine.quitarPregunta(preg3));
		assertEquals(catCine.numeroPreguntas(),1);
		//eliminar de cat con 1 preg
		assertEquals(catVideojuegos.numeroPreguntas(),1);
		assertTrue(catVideojuegos.quitarPregunta(preg2));
		assertEquals(catVideojuegos.numeroPreguntas(),0);
		//eliminar de cat con 0 preguntas
		assertFalse(catVideojuegos.quitarPregunta(preg2));
		assertEquals(catVideojuegos.numeroPreguntas(),0);
		tearDownCategoria();
		//public boolean quitarPregunta(Pregunta preg)
	}
	
	public void testAnadirPreguntaCategoria()throws Exception{
		setUpCategoria();
		assertEquals(catCine.numeroPreguntas(),2);
		assertFalse(catCine.anadirPregunta(null));
		assertEquals(catCine.numeroPreguntas(),2);
		LinkedList<String> listaRespuestas = new LinkedList<String>();
		listaRespuestas.add("Charmander");
		listaRespuestas.add("Pikachu");
		listaRespuestas.add("Squirtel");
		listaRespuestas.add("Ekans");
		Pregunta preg2= new Pregunta(1, "Pokemon: Nombre de la rata amarilla", listaRespuestas, "Pikachu", 0, 0);
		assertTrue(catCine.anadirPregunta(preg2));
		assertEquals(catCine.numeroPreguntas(),3);
		//public boolean anadirPregunta(Pregunta preg)
		tearDownCategoria();
	}
	
	public void testEliminarPreguntaCategoria()throws Exception{
		//fail("No implementado");
		setUpCategoria();
		LinkedList<String> listaRespuestas=new LinkedList<String>();
		listaRespuestas.add("Paco");
		listaRespuestas.add("Francisco");
		listaRespuestas.add(aux);
		listaRespuestas.add("Missisipi");
		Pregunta preg3= new Pregunta(2, "Nombre del doctor Jones, que dispone de 4 peliculas", listaRespuestas, "Indiana", 0, 0);
		//Eliminar null ->no hace nada
		assertEquals(catCine.numeroPreguntas(),2);
		catCine.eliminarPregunta(null);
		assertEquals(catCine.numeroPreguntas(),2);
		//Eliminar en lista con 2 objetos
		assertEquals(catCine.numeroPreguntas(),2);
		catCine.eliminarPregunta(preg3);
		assertEquals(catCine.numeroPreguntas(),1);
		//Eliminar en lista con 1 objeto
		//('4','Alicia en el pais de las maravillas: nombre del gato','Triston','Rison','Cabron','Chiston','Rison')
		listaRespuestas=new LinkedList<String>();
		listaRespuestas.add("Triston");
		listaRespuestas.add("Rison");
		listaRespuestas.add("Cabron");
		listaRespuestas.add("Chiston");
		Pregunta preg2= new Pregunta(4, "Alicia en el pais de las maravillas: nombre del gato", listaRespuestas, "Rison", 0, 0);
		assertEquals(catCine.numeroPreguntas(),1);
		catCine.eliminarPregunta(preg2);
		assertEquals(catCine.numeroPreguntas(),0);
		//Eliminar en lista con 0 objetos
		assertEquals(catCine.numeroPreguntas(),0);
		catCine.eliminarPregunta(preg2);
		assertEquals(catCine.numeroPreguntas(),0);
		//public void eliminarPregunta(Pregunta preg)
		tearDownCategoria();
	}
	
	
	//GestorCategoria
	protected void setUpGestorCategoria() throws Exception {
		super.setUp();
		GestorCategorias.getGestorCategorias().eliminarGestor();
		catCine=new Categoria(1, "Cine"); //tiene 2 preguntas
		catVideojuegos=new Categoria(2, "Videojuegos"); //Tiene 1 pregunta
		catManga=new Categoria (4,"Manga"); // tiene 0 preguntas
	}
	
	public void testCargarNombresYCategorias()throws Exception{
		//fail("no implementado");
		setUpGestorCategoria();
		//public LinkedList<NombreYCategoria> cargarNombresYCategorias()
		LinkedList<NombreYCategoria> rdo;
		rdo= GestorCategorias.getGestorCategorias().cargarNombresYCategorias();
		assertNotNull(rdo);
		assertEquals(rdo.size(),4);
	}
	
	public void testComprobarSiSePuedeEliminar()throws Exception {
		//fail("no implementado");
		setUpGestorCategoria();
		//public boolean comprobarSiSePuedeEliminar(Categoria cat)
		//Ejemplo con 2 preguntas
		assertTrue(GestorCategorias.getGestorCategorias().comprobarSiSePuedeEliminar(catCine));
		//Ejemplo con 1 pregunta
		assertFalse(GestorCategorias.getGestorCategorias().comprobarSiSePuedeEliminar(catVideojuegos));
		//Ejemplo con 0 preguntas
		assertFalse(GestorCategorias.getGestorCategorias().comprobarSiSePuedeEliminar(catManga));
		//Ejemplo con parametro null -> false
		assertFalse(GestorCategorias.getGestorCategorias().comprobarSiSePuedeEliminar(null));
	}
	
	public void testCargarPreguntas()throws Exception {
		//fail("no implementado");
		setUpGestorCategoria();
		//public LinkedList<PreguntaYTitulo> cargarPreguntas(Categoria cat)
		LinkedList<PreguntaYTitulo> rdo;
		//Ejemplo con 2 preguntas
		rdo = GestorCategorias.getGestorCategorias().cargarPreguntas(catCine);
		assertNotNull(rdo);
		assertEquals(rdo.size(),2);
		//Ejemplo con 1 pregunta
		rdo = GestorCategorias.getGestorCategorias().cargarPreguntas(catVideojuegos);
		assertNotNull(rdo);
		assertEquals(rdo.size(),1);
		//Ejemplo con 0 preguntas -> devuelve null
		rdo = GestorCategorias.getGestorCategorias().cargarPreguntas(catManga);
		assertNull(rdo);
		//Ejemplo con param null -> devuelve null
		rdo = GestorCategorias.getGestorCategorias().cargarPreguntas(null);
		assertNull(rdo);
		tearDownCategoria();
	}
	
	public void testBuscarCategoria()throws Exception {
		//fail("no implementado");
		setUpGestorCategoria();
		//public Categoria buscarCategoria(String nomCat)
		//La categoria existe y la devuelve
		Categoria rdo;
		rdo=GestorCategorias.getGestorCategorias().buscarCategoria("Cine");
		assertNotNull(rdo);
		assertTrue(catCine.equals(rdo));
		//La categoria no existe -> devuelve null
		assertNull(GestorCategorias.getGestorCategorias().buscarCategoria("Quimica"));
		tearDownCategoria();
	}
	
	public void testBorrarPreguntaDeCategoria()throws Exception {
		//fail("no implementado");
		setUpGestorCategoria();
		//public void borrarPreguntaDeCategoria(Pregunta preg, Categoria cat)
		LinkedList<String> listaRespuestas=new LinkedList<String>();
		listaRespuestas.add("Paco");
		listaRespuestas.add("Francisco");
		listaRespuestas.add(aux);
		listaRespuestas.add("Missisipi");
		Pregunta pregIndi= new Pregunta(2, "Nombre del doctor Jones, que dispone de 4 peliculas", listaRespuestas, "Indiana", 0, 0);
		listaRespuestas=new LinkedList<String>();
		listaRespuestas.add("Triston");
		listaRespuestas.add("Rison");
		listaRespuestas.add("Cabron");
		listaRespuestas.add("Chiston");
		Pregunta pregAlicia= new Pregunta(4, "Alicia en el pais de las maravillas: nombre del gato", listaRespuestas, "Rison", 0, 0);
		listaRespuestas = new LinkedList<String>();
		listaRespuestas.add("Charmander");
		listaRespuestas.add("Pikachu");
		listaRespuestas.add("Squirtel");
		listaRespuestas.add("Ekans");
		Pregunta pregPika= new Pregunta(1, "Pokemon: Nombre de la rata amarilla", listaRespuestas, "Pikachu", 0, 0);
		ResultSet rdo;
		//Borrar categoria null
		GestorCategorias.getGestorCategorias().borrarPreguntaDeCategoria(null, null);
		GestorCategorias.getGestorCategorias().borrarPreguntaDeCategoria(pregPika, null);
		//Borrar de 2 preguntas
			//Borrar pregunta null
		assertEquals(catCine.numeroPreguntas(),2);
		GestorCategorias.getGestorCategorias().borrarPreguntaDeCategoria(null, catCine);
		assertEquals(catCine.numeroPreguntas(),2);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),2);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
			//Borrar pregunta que no esta
		assertEquals(catCine.numeroPreguntas(),2);
		GestorCategorias.getGestorCategorias().borrarPreguntaDeCategoria(pregPika, catCine);
		assertEquals(catCine.numeroPreguntas(),2);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),2);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
			//Borrar pregunta que esta
		assertEquals(catCine.numeroPreguntas(),2);
		GestorCategorias.getGestorCategorias().borrarPreguntaDeCategoria(pregAlicia, catCine);
		assertEquals(catCine.numeroPreguntas(),1);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),1);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		//Borrar de 1 pregunta -> en ningun caso debe de dejar
			//Borrar pregunta null
		assertEquals(catCine.numeroPreguntas(),1);
		GestorCategorias.getGestorCategorias().borrarPreguntaDeCategoria(null, catCine);
		assertEquals(catCine.numeroPreguntas(),1);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),1);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
			//Borrar pregunta que no esta
		assertEquals(catCine.numeroPreguntas(),1);
		GestorCategorias.getGestorCategorias().borrarPreguntaDeCategoria(pregPika, catCine);
		assertEquals(catCine.numeroPreguntas(),1);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),1);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
			//Borrar pregunta que esta
		assertEquals(catCine.numeroPreguntas(),1);
		GestorCategorias.getGestorCategorias().borrarPreguntaDeCategoria(pregIndi, catCine);
		assertEquals(catCine.numeroPreguntas(),1);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),1);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		//Borrar de 0 preguntas
			//Borrar pregunta null
		assertEquals(catManga.numeroPreguntas(),0);
		GestorCategorias.getGestorCategorias().borrarPreguntaDeCategoria(null, catManga);
		assertEquals(catManga.numeroPreguntas(),0);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='4';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),0);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
			//Borrar pregunta que no esta
		assertEquals(catManga.numeroPreguntas(),0);
		GestorCategorias.getGestorCategorias().borrarPreguntaDeCategoria(pregIndi, catManga);
		assertEquals(catManga.numeroPreguntas(),0);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='4';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),0);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		
		tearDownCategoria();
	}
	
	public void testModificarPregunta()throws Exception {
		//fail("no implementado");
		setUpGestorCategoria();
		LinkedList<String> listaRespuestas=new LinkedList<String>();
		listaRespuestas.add("Paco");
		listaRespuestas.add("Francisco");
		listaRespuestas.add(aux);
		listaRespuestas.add("Missisipi");
		Pregunta pregIndi= new Pregunta(2, "Nombre del doctor Jones, que dispone de 4 peliculas", listaRespuestas, "Indiana", 0, 0);
		listaRespuestas=new LinkedList<String>();
		listaRespuestas.add("Triston");
		listaRespuestas.add("Rison");
		listaRespuestas.add("Cabron");
		listaRespuestas.add("Chiston");
		Pregunta pregAlicia= new Pregunta(4, "Alicia en el pais de las maravillas: nombre del gato", listaRespuestas, "Rison", 0, 0);
		listaRespuestas = new LinkedList<String>();
		listaRespuestas.add("Charmander");
		listaRespuestas.add("Pikachu");
		listaRespuestas.add("Squirtel");
		listaRespuestas.add("Ekans");
		Pregunta pregPika= new Pregunta(1, "Pokemon: Nombre de la rata amarilla", listaRespuestas, "Pikachu", 0, 0);
		LinkedList<String> datosMod = new LinkedList<String>();
		ResultSet rdo;
		//public boolean modificarPregunta(Pregunta preg, LinkedList<String> datosMod, Categoria nCat, Categoria vCat)
		
		//Pregunta null -> no hace nada
		assertFalse(GestorCategorias.getGestorCategorias().modificarPregunta(null, datosMod, catManga, catCine));
		assertEquals(catCine.numeroPreguntas(),2);
		assertEquals(catManga.numeroPreguntas(),0);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),2);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		//datosMod null -> no hace nada
		assertFalse(GestorCategorias.getGestorCategorias().modificarPregunta(pregAlicia, null, catManga, catCine));
		assertEquals(catCine.numeroPreguntas(),2);
		assertEquals(catManga.numeroPreguntas(),0);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),2);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		//datosMod no completo ->no hace nada
		assertFalse(GestorCategorias.getGestorCategorias().modificarPregunta(pregAlicia, datosMod, catManga, catCine));
		assertEquals(catCine.numeroPreguntas(),2);
		assertEquals(catManga.numeroPreguntas(),0);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),2);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		//nCat null -> no hace nada
		//('4','Alicia en el pais de las maravillas: nombre del gato','Triston','Rison','Cabron','Chiston','Rison')
		datosMod.add("Alicia en el pais de las maravillas: nombre del gato rosa");
		datosMod.add("Triston :(");
		datosMod.add("Rison");
		datosMod.add("Cabron");
		datosMod.add("Chiston xD");
		datosMod.add("Rison");
		assertFalse(GestorCategorias.getGestorCategorias().modificarPregunta(pregAlicia, datosMod, null, catCine));
		assertEquals(catCine.numeroPreguntas(),2);
		assertEquals(catManga.numeroPreguntas(),0);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),2);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		//vCat null -> no hace nada
		assertFalse(GestorCategorias.getGestorCategorias().modificarPregunta(pregAlicia, datosMod, catManga, null));
		assertEquals(catCine.numeroPreguntas(),2);
		assertEquals(catManga.numeroPreguntas(),0);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),2);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		//La pregunta no pertenece a vCat -> no hace nada
		assertFalse(GestorCategorias.getGestorCategorias().modificarPregunta(pregPika, datosMod, catManga, catCine));
		assertEquals(catCine.numeroPreguntas(),2);
		assertEquals(catManga.numeroPreguntas(),0);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),2);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		//vCat = ncat -> se modifican los datos
		assertTrue(GestorCategorias.getGestorCategorias().modificarPregunta(pregAlicia, datosMod, catCine, catCine));
		assertEquals(catCine.numeroPreguntas(),2);
		assertEquals(catManga.numeroPreguntas(),0);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),2);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
			
			rdo=GestorBD.getInstance().consulta("select texto, resp1, VecesAcertada from pregunta where idPregunta='4';");
			if(rdo.next()){
				assertEquals(rdo.getString("texto"),"Alicia en el pais de las maravillas: nombre del gato rosa");
				assertEquals(rdo.getString("resp1"),"Triston :(");
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		//vCat != nCat -> se cambia la categoria
		assertTrue(GestorCategorias.getGestorCategorias().modificarPregunta(pregAlicia, datosMod, catVideojuegos, catCine));
		assertEquals(catCine.numeroPreguntas(),1);
		assertEquals(catVideojuegos.numeroPreguntas(),2);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),1);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
			
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='2';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),2);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
			rdo=GestorBD.getInstance().consulta("select texto, resp1, VecesAcertada from pregunta where idPregunta='4';");
			if(rdo.next()){
				assertEquals(rdo.getString("texto"),"Alicia en el pais de las maravillas: nombre del gato rosa");
				assertEquals(rdo.getString("resp1"),"Triston :(");
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		//vCat = nCat vCat solo 1 preg-> se modifican los datos
		assertTrue(GestorCategorias.getGestorCategorias().modificarPregunta(pregIndi, datosMod, catCine, catCine));
		assertEquals(catCine.numeroPreguntas(),1);
		//assertEquals(catManga.numeroPreguntas(),0);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),1);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
			
			rdo=GestorBD.getInstance().consulta("select texto, resp1, VecesAcertada from pregunta where idPregunta='2';");
			if(rdo.next()){
				assertEquals(rdo.getString("texto"),"Alicia en el pais de las maravillas: nombre del gato rosa");
				assertEquals(rdo.getString("resp1"),"Triston :(");
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		
		
		//vCat != nCat vCat no permite eliminar preguntas de su lista -> no hace nada
		assertFalse(GestorCategorias.getGestorCategorias().modificarPregunta(pregIndi, datosMod, catVideojuegos, catCine));
		assertEquals(catCine.numeroPreguntas(),1);
		assertEquals(catVideojuegos.numeroPreguntas(),2);
		try{
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='1';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),1);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
			
			rdo=GestorBD.getInstance().consulta("select count(*) as count from (categoria natural join pregcat) natural join pregunta where idCategoria='2';");
			if(rdo.next()){
				assertEquals(rdo.getInt("count"),2);
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
			
			rdo=GestorBD.getInstance().consulta("select texto, resp1, VecesAcertada from pregunta where idPregunta='2';");
			if(rdo.next()){
				assertEquals(rdo.getString("texto"),"Alicia en el pais de las maravillas: nombre del gato rosa");
				assertEquals(rdo.getString("resp1"),"Triston :(");
			}
			else{
				fail("No se ha realizado la consulta correctamente.");
			}
			GestorBD.getInstance().cerrarConsulta(rdo);
		}
		catch(SQLException ex){
			System.out.println(ex.toString());
		}
		tearDownCategoria();
	}
	
	
	
	
}
