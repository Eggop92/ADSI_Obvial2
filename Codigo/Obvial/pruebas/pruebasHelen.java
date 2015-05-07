import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import org.obvial.obvial.Casilla;
import org.obvial.obvial.GestorBD;
import org.obvial.obvial.GestorPartida;
import org.obvial.obvial.GestorPreguntas;
import org.obvial.obvial.GestorUsuarios;
import org.obvial.obvial.Jugador;
import org.obvial.obvial.Partida;
import org.obvial.obvial.Usuario;
import junit.framework.TestCase;


public class pruebasHelen extends TestCase {
	
	private Usuario usu1 = new Usuario("Hanna","","hpaz@hotmail.com",false,0,0);
	private Usuario usu2 = new Usuario("Juan","","jaja@hotmail.com",false,2,1);
	
	
	private Casilla casi1=new Casilla(2,2,2,2);
	private Casilla casi2=new Casilla(3,3,3,3);
	
	private Jugador j1=new Jugador(usu1,casi1,"Rojo",false,0);
	private Jugador j2=new Jugador(usu1,casi2,"Verde",true,1);
	private Jugador j3=new Jugador(usu2,casi2,"Azul",false,0);
	
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	// GestorPartidas
	public void testGuardarPartidaActual()throws Exception {
		/**
		 * Pruebo: q se guarda correctamente la partida en la lista y en la BD
		 * se supone q la lista de partidas guardadas ya estara cargada de la BD
		 * */
		//Cargo la lista de partidas guardadas q debe cargarse al iniciar el juego
		LinkedList<Jugador> listaJ= new LinkedList<Jugador>();
		LinkedList<Jugador> listaAJ= new LinkedList<Jugador>();
		boolean turno;
		String sql="SELECT * FROM infopartidas ORDER BY idPartida";
		int idPar=0;
		
		
		try{
			ResultSet res2 = GestorBD.getInstance().consulta(sql);
			while(res2.next()){
				//res2.getInt(1);idPartida
				Usuario usu3 = new Usuario(res2.getString(2),"","",false,0,0);
				Casilla casi3=new Casilla(res2.getInt(3),0,0,0);
				if (res2.getInt(4)==0){
					turno=false;
				}else{
					turno=true;
				}
				Jugador j4=new Jugador(usu3,casi3,res2.getString(5),turno,res2.getInt(6));
				listaJ.add(j4);
				if (res2.getInt(1)>idPar){
					Partida p1=new Partida(idPar+1,listaJ);
					GestorPartida.getGestorPartidas().getListaPartidasGuardadas().add(p1);
					idPar++;
					System.out.println(GestorPartida.getGestorPartidas().getListaPartidasGuardadas().size());
				}
			}
			
			//en listaJ tendre la ultima lista q he añadido y el listaAJ una lista q no esta en la lista de jugadores
			listaAJ.add(j2);
			listaAJ.add(j3);	
			
			//los jugadores no tienen partida guardada
			ResultSet resulA = GestorBD.getInstance().consulta("SELECT count(*) AS np FROM partida");resulA.next();
			assertEquals(resulA.getInt("np"),3);// hay 3 partidas 
			System.out.println("debe ser  3 --> "+resulA.getInt("np"));
			assertEquals(GestorPartida.getGestorPartidas().getListaPartidasGuardadas().size(),3);
			System.out.println("debe ser  3 --> "+GestorPartida.getGestorPartidas().getListaPartidasGuardadas().size());
			
			
			GestorPartida.getGestorPartidas().guardarPartidaActual(listaAJ);
			ResultSet resulB = GestorBD.getInstance().consulta("SELECT count(*) AS np1 FROM partida");
			resulB.next();
			assertEquals(resulB.getInt("np1"),4);//se debe añadir la partida nueva asi q habran 4
			assertEquals(GestorPartida.getGestorPartidas().getListaPartidasGuardadas().size(),4);
			System.out.println("debe ser  4 --> "+GestorPartida.getGestorPartidas().getListaPartidasGuardadas().size());
			
			
			//Tiene una partida guardada, y la sustituyo
			GestorPartida.getGestorPartidas().guardarPartidaActual(listaJ);//deben seguir habiendo 4 partidas ya q se sustituira 
			ResultSet resulC = GestorBD.getInstance().consulta("SELECT count(*) AS np FROM partida");
			resulC.next();
			assertEquals(resulC.getInt("np2"),4);
			assertEquals(GestorPartida.getGestorPartidas().getListaPartidasGuardadas().size(),4);
			
			GestorBD.getInstance().cerrarConsulta(resulA);
			GestorBD.getInstance().cerrarConsulta(resulB);
			GestorBD.getInstance().cerrarConsulta(resulC);
			GestorBD.getInstance().cerrarConsulta(res2);
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		
	}
		
	
	//Jugador
	public void testEqualsJugador() throws Exception{
		// compara solo  de la partida
	
		//el jugador es igual true
		assertTrue(j1.equalsJugador(j2));
		//el jugador no es igual false
		assertFalse(j1.equalsJugador(j3));
	}
	
	// GestorUsuarios
	public void testEsIgual()throws Exception {
		//este metodo llama al equalsUsuarios por lo q la prueba seria parecida
		
		//es igual true
		assertTrue(GestorUsuarios.getGestorUsuarios().esIgual(usu1, usu1));
		
		// no es igual false
		assertFalse(GestorUsuarios.getGestorUsuarios().esIgual(usu1, usu2));
		
	}
	public void testCoprobarEmail()throws Exception {
		String mail1;
		//si el mail es erroneo
		mail1="correoErroneo";
		assertFalse(GestorUsuarios.getGestorUsuarios().coprobarEmail(mail1));
		System.out.println(mail1+" --> "+GestorUsuarios.getGestorUsuarios().coprobarEmail(mail1));
		
		mail1="correoIncompleto@";
		assertFalse(GestorUsuarios.getGestorUsuarios().coprobarEmail(mail1));
		System.out.println(mail1+" --> "+GestorUsuarios.getGestorUsuarios().coprobarEmail(mail1));
		
		mail1="122correo.es";
		assertFalse(GestorUsuarios.getGestorUsuarios().coprobarEmail(mail1));
		System.out.println(mail1+" --> "+GestorUsuarios.getGestorUsuarios().coprobarEmail(mail1));
		
		mail1="122correo@algo.es";
		assertFalse(GestorUsuarios.getGestorUsuarios().coprobarEmail(mail1));
		System.out.println(mail1+" --> "+GestorUsuarios.getGestorUsuarios().coprobarEmail(mail1));
		
		//si el mail es correcto
		mail1="correo_22@hotmail.es";
		assertTrue(GestorUsuarios.getGestorUsuarios().coprobarEmail(mail1));
		System.out.println(mail1+" --> "+GestorUsuarios.getGestorUsuarios().coprobarEmail(mail1));
		
		mail1="correo_22@gmail.es";
		assertTrue(GestorUsuarios.getGestorUsuarios().coprobarEmail(mail1));
		System.out.println(mail1+" --> "+GestorUsuarios.getGestorUsuarios().coprobarEmail(mail1));
		
		mail1="correo_22@yahoo.es";
		assertTrue(GestorUsuarios.getGestorUsuarios().coprobarEmail(mail1));
		System.out.println(mail1+" --> "+GestorUsuarios.getGestorUsuarios().coprobarEmail(mail1));
		
	}
	//comprueba en la bases de datos
	public void testComprobarUsuario() throws Exception {
		String usu1;
		//usuario existe
		usu1="Helen";
		assertFalse(GestorUsuarios.getGestorUsuarios().comprobarUsuario(usu1));
		System.out.println(usu1+" --> "+GestorUsuarios.getGestorUsuarios().comprobarUsuario(usu1));
		
		//usuario no existe 
		usu1="Andres";
		assertTrue(GestorUsuarios.getGestorUsuarios().comprobarUsuario(usu1));
		System.out.println(usu1+" --> "+GestorUsuarios.getGestorUsuarios().comprobarUsuario(usu1));
	}
	
	//para comprobarlo hay primero q mirar la base de datos porq si el nombre el primer equals no se cumple
	//y obviamente la segunda vez q se ejecute la prueba el usuario ya estara registrado
	public void testRegistrarUsuario () throws Exception{
		//Agrega en la base de datos 
		String sql="SELECT count(*) AS num FROM usuarios WHERE nombre='Oscar';";
		try{
			//NO esta el usuario num devolvera 1
			ResultSet res = GestorBD.getInstance().consulta(sql);
			res.next();
			assertEquals(res.getInt("num"),0);
			
			// Registro al usuario
			GestorUsuarios.getGestorUsuarios().registrarUsuario("Oscar", "os@hotmail.com", "oscar");
			ResultSet res1 = GestorBD.getInstance().consulta(sql);
			res.next();
			assertEquals(res1.getInt("num"),1);
			GestorBD.getInstance().cerrarConsulta(res1);
			
			//Borramos el usuario
			sql="DELETE FROM usuarios WHERE nombre='Oscar' AND passw='oscar'";
			GestorBD.getInstance().borrar(sql);
			
			
		}catch(SQLException e){
			System.out.println(e.getMessage());			
		}	
	}
	
	//Usuario
	//compara dos usuarios
	public void testEqualsUsuario() throws Exception{
		// compara solo el nombre y mail del usuario
		
		//es igual true
		assertTrue(usu1.equalsUsuario(usu1));
		
		// no es igual false
		assertFalse(usu1.equalsUsuario(usu2));
		
		//fail("");
	}
}
