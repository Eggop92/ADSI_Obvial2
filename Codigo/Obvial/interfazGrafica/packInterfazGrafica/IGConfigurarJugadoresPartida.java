package packInterfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import org.obvial.obvial.GestorPartida;
import org.obvial.obvial.GestorUsuarios;
import org.obvial.obvial.ListaJugadores;
import org.obvial.obvial.Usuario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class IGConfigurarJugadoresPartida extends JFrame {
	

	//////////////////////////////////////////////////////////////
	private JPanel contentPane;
	//Texto donde se pondra el nombre de los jugadores registrados, por defecto Jugador 1, Jugador 2...
	private static JLabel lblJ1;
	private static JLabel lblJ2;
	private static JLabel lblJ3;
	private static JLabel lblJ4;
	//Boton para logearse con cada jugador, una vez logeado correctamente se bloqueara
	private static JButton btnLogeoJ1;
	private static JButton btnLogeoJ2;
	private static JButton btnLogeoJ3;
	private static JButton btnLogeoJ4;
	//Botones con los colores de los jugadores, pinchando en ellos cambian
	private static JTextField colorJ1;
	private static JTextField colorJ2;
	private static JTextField colorJ3;
	private static JTextField colorJ4;
	//Label con el nombre del color para daltonicos, cambia tambien a la vez que los colores
	private final JLabel labelColor1;
	private final JLabel labelColor2;
	private final JLabel labelColor3;
	private final JLabel labelColor4;
	//Label para mostrar posibles errores
	final JLabel lblMensajeError;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGConfigurarJugadoresPartida frame = new IGConfigurarJugadoresPartida();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IGConfigurarJugadoresPartida() {
		setResizable(false);
		
		
		//Cuando se abre la configuracion de jugadores es para jugar una nueva partida o cargar
		//Reseteamos la lista de jugadores para introducir 
		ListaJugadores.getListaJugadores().vaciarLista();
		//Cargamos todas las partidas para las opciones cargar y guardar que lo necesitaran
		GestorPartida.getGestorPartidas().cargarPartidasGuardadas();
		
		setTitle("Configurar Jugadores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//label para mostrar los errores en rojo, se ira modificando dependiendo el error
		lblMensajeError = new JLabel("");
		lblMensajeError.setForeground(Color.RED);
		lblMensajeError.setBounds(46, 256, 362, 14);
		contentPane.add(lblMensajeError);
		

		
		//////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////
		
		//Jugador1
		
		lblJ1 = new JLabel("Jugador 1");
		lblJ1.setBackground(Color.WHITE);
		lblJ1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJ1.setBounds(46, 68, 72, 14);
		contentPane.add(lblJ1);
		
		labelColor1 = new JLabel("Rojo");
		labelColor1.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelColor1.setBackground(Color.WHITE);
		labelColor1.setBounds(288, 69, 72, 14);
		contentPane.add(labelColor1);
		
		//Accion cuando se pincha logeo en el primer jugador
		btnLogeoJ1 = new JButton("Logearse");
		btnLogeoJ1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				//Eliminamos el label de error
				EliminarError();
				IGLogeoJugador nuevoLogeo= new IGLogeoJugador("J1");
				nuevoLogeo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				nuevoLogeo.setVisible(true);
			}
		});
		btnLogeoJ1.setBounds(141, 65, 89, 23);
		contentPane.add(btnLogeoJ1);
		
		
		colorJ1 = new JTextField();
		colorJ1.setEditable(false);
		//Accion cuando se pincha en los cuadros para que cambie el color
		colorJ1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (colorJ1.isEnabled()){	
					cambiarColor(colorJ1, labelColor1);
				}
			}
		});
		colorJ1.setBackground(Color.RED);
		colorJ1.setBounds(258, 66, 20, 20);
		contentPane.add(colorJ1);
		colorJ1.setColumns(10);
		
		/////////////////////////////////////////////////
		////////////////////////////////////////////////
		
		//Jugador2
		
		lblJ2 = new JLabel("Jugador 2");
		lblJ2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJ2.setBounds(46, 113, 72, 14);
		contentPane.add(lblJ2);
		
		labelColor2 = new JLabel("Amarillo");
		labelColor2.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelColor2.setBackground(Color.WHITE);
		labelColor2.setBounds(288, 114, 72, 14);
		contentPane.add(labelColor2);
		
		
		btnLogeoJ2 = new JButton("Logearse");
		//Accion cuando se pincha logeo en el primer jugador
		btnLogeoJ2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Eliminamos el label de error
				EliminarError();
				//Si en la lista todavia no hay jugadores, el jugador no esta logeado
				if (ListaJugadores.getListaJugadores().getListaJ().size()<1){
					lblMensajeError.setText("Error: Primero debe logearse el jugador 1");
				}
				//Si el color esta repetido no se puede realizar el logeo
				else if(ColorRepetido(2)){
					lblMensajeError.setText("Error: El color elejido ya ha sido escogido");
				}
				//Si todo esta correcto se logea
				else{
					IGLogeoJugador nuevoLogeo= new IGLogeoJugador("J2");
					nuevoLogeo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					nuevoLogeo.setVisible(true);
				}
			}
		});
		btnLogeoJ2.setBounds(141, 110, 89, 23);
		contentPane.add(btnLogeoJ2);
		
		
		colorJ2 = new JTextField();
		colorJ2.setEditable(false);
		//Accion cuando se pincha en los cuadros para que cambie el color
		colorJ2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (colorJ2.isEnabled()){	
					cambiarColor(colorJ2, labelColor2);
				}
			}
		});
		colorJ2.setBackground(Color.YELLOW);
		colorJ2.setBounds(258, 111, 20, 20);
		contentPane.add(colorJ2);
		colorJ2.setColumns(10);
		
		/////////////////////////////////////////////////
		////////////////////////////////////////////////
		
		//Jugador3
		
		lblJ3 = new JLabel("Jugador 3");
		lblJ3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJ3.setBounds(46, 157, 72, 14);
		contentPane.add(lblJ3);
		
		labelColor3 = new JLabel("Verde");
		labelColor3.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelColor3.setBackground(Color.WHITE);
		labelColor3.setBounds(288, 158, 72, 14);
		contentPane.add(labelColor3);
		
		
		btnLogeoJ3 = new JButton("Logearse");
		//Accion cuando se pincha logeo en el primer jugador
		btnLogeoJ3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Eliminamos el label de error
				EliminarError();
				//Si la lista es menor que 2, el jugador 2 no esta todavia logeado
				if (ListaJugadores.getListaJugadores().getListaJ().size()<2){
					lblMensajeError.setText("Error: Primero deben logearse los jugadores 1 y 2");
				}
				//Si el color esta repetido no se puede logear
				else if(ColorRepetido(3)){
					lblMensajeError.setText("Error: El color elejido ya ha sido escogido");
				}
				//Si todo es correcto se procede con el logeo
				else{
					IGLogeoJugador nuevoLogeo= new IGLogeoJugador("J3");
					nuevoLogeo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					nuevoLogeo.setVisible(true);
				}
			}
		});
		btnLogeoJ3.setBounds(141, 154, 89, 23);
		contentPane.add(btnLogeoJ3);
		

		colorJ3 = new JTextField();
		colorJ3.setEditable(false);
		//Cuando se pincha el recuadro se cambia el color
		colorJ3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (colorJ3.isEnabled()){	
					cambiarColor(colorJ3, labelColor3);
				}
			}
		});
		colorJ3.setColumns(10);
		colorJ3.setBackground(Color.GREEN);
		colorJ3.setBounds(258, 155, 20, 20);
		contentPane.add(colorJ3);
		
		//////////////////////////////////////////////////
		/////////////////////////////////////////////////
		
		//Jugador4
		
		lblJ4 = new JLabel("Jugador 4");
		lblJ4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJ4.setBounds(46, 200, 72, 14);
		contentPane.add(lblJ4);
		
		labelColor4 = new JLabel("Azul");
		labelColor4.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelColor4.setBackground(Color.WHITE);
		labelColor4.setBounds(288, 201, 72, 14);
		contentPane.add(labelColor4);
		
		
		btnLogeoJ4 = new JButton("Logearse");
		//Accion cuando se pincha logeo en el primer jugador
		btnLogeoJ4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Eliminamos el label de error
				EliminarError();
				//Si la lista es menor que 3, el jugador 3 todavia no se ha logeado
				if (ListaJugadores.getListaJugadores().getListaJ().size()<3){
					lblMensajeError.setText("Error: Primero deben logearse los jugadores 1, 2 y 3");
				}
				//Si el color esta repetido no se puede logear
				else if(ColorRepetido(4)){
					lblMensajeError.setText("Error: El color elejido ya ha sido escogido");
				}
				//Si todo es correcto se procede con el logeo
				else{
					IGLogeoJugador nuevoLogeo= new IGLogeoJugador("J4");
					nuevoLogeo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					nuevoLogeo.setVisible(true);
				}
			}
		});
		btnLogeoJ4.setBounds(141, 197, 89, 23);
		contentPane.add(btnLogeoJ4);
		
		
		colorJ4 = new JTextField();
		colorJ4.setEditable(false);
		//Cuando se pincha el recuadro de color cambia su color
		colorJ4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (colorJ4.isEnabled()){	
					cambiarColor(colorJ4, labelColor4);
				}
			}
		});
		colorJ4.setColumns(10);
		colorJ4.setBackground(Color.BLUE);
		colorJ4.setBounds(258, 198, 20, 20);
		contentPane.add(colorJ4);
		
		////////////////////////////////////////////////////////
		
		//Boton Nueva partida
		
		JButton btnNueva = new JButton("Nueva");
		btnNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Eliminamos el label de error
				EliminarError();
				//Si en la lista no hay al menos dos jugadores no se puede jugar
				if (ListaJugadores.getListaJugadores().getListaJ().size()<2){
					lblMensajeError.setText("Error: Al menos deben logearse dos jugadores");
				}
				else{
				//Si todo es correcto se comienza la nueva partida
					IGPrincipal principal= new IGPrincipal();
					principal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					principal.setVisible(true);
					dispose();
				}
			}
		});
		btnNueva.setBounds(30, 305, 109, 23);
		contentPane.add(btnNueva);
		
		////////////////////////////////////////////////////////
		
		//Boton Cargar Patida
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Eliminamos el label de error
				EliminarError();
				if (ListaJugadores.getListaJugadores().getListaJ().size()<2){
					lblMensajeError.setText("Error: Al menos deben logearse dos jugadores");
				}
				else{
					boolean cargadoCorrecto = GestorPartida.getGestorPartidas().cargarPartida();
					if (cargadoCorrecto){
						//Si todo es correcto se retoma la partida, el metodo cargar
						//se habra encargado de haber introducido los datos de los jugadores
						IGPrincipal principal= new IGPrincipal();
						principal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						principal.setVisible(true);
						dispose();
					}
				}
			}
		});
		btnCargar.setBounds(159, 305, 109, 23);
		contentPane.add(btnCargar);
		
		////////////////////////////////////////////////////////
		
		//Boton Cancelar para volver al menu anterior
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//volver al menu principal
				dispose();
			}
		});
		btnCancelar.setBounds(335, 305, 89, 23);
		contentPane.add(btnCancelar);		
	}
	
	

	////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////
	
	//Metodo para cambiar las label de jugadores con los nombres logeados
	//Este metodo sirve para aclarar y ahorrar instrucciones
	//Se pasa por parametro el jugador al que hay que cambiar la label
	public static void cambiarLabel(String pNombreJugador, String pJugador){
		if (pJugador.equals("J1")){
			lblJ1.setText(pNombreJugador);
		}
		else if (pJugador.equals("J2")){
			lblJ2.setText(pNombreJugador);
		}
		else if (pJugador.equals("J3")){
			lblJ3.setText(pNombreJugador);
		}
		else {
			lblJ4.setText(pNombreJugador);
		}
		
	}
	
	//Metodo para bloquear las acciones de cambiar color y logear jugador una vez se ha logeado una posicion
	//Este metodo recibe el jugador al que hay que bloquear los botones y lo hace, sirve para aclarar y ahorrar codigo
	public static void bloquearAcciones(String jugador){
		if (jugador.equals("J1")){
			btnLogeoJ1.setEnabled(false);
			colorJ1.setEnabled(false);
		}
		else if (jugador.equals("J2")){
			btnLogeoJ2.setEnabled(false);
			colorJ2.setEnabled(false);
		}
		else if (jugador.equals("J3")){
			btnLogeoJ3.setEnabled(false);
			colorJ3.setEnabled(false);
		}
		else if (jugador.equals("J4")){
			btnLogeoJ4.setEnabled(false);
			colorJ4.setEnabled(false);
		}
	}
	
	

	//Metodo para cambiar los textos de color para que los jugadores puedan elejir su color
	//Dependiendo del color que tiene actualmente pasa al siguiente y modifica el texto adjunto
	private void cambiarColor(JTextField color, JLabel texto){
		if (color.getBackground().equals(Color.RED)){
			color.setBackground(Color.YELLOW);
			texto.setText("Amarillo");
		}
		else if (color.getBackground().equals(Color.YELLOW)){
			color.setBackground(Color.GREEN);
			texto.setText("Verde");
		}
		else if (color.getBackground().equals(Color.GREEN)){
			color.setBackground(Color.BLUE);
			texto.setText("Azul");
		}
		else {
			color.setBackground(Color.RED);
			texto.setText("Rojo");
		}
	}
	
	
	//Comprobar que no se intenta logear con un color repetido, con el 1 nunca se hace ya que no hay nadie logeado
	private boolean ColorRepetido(int Jugador){
		boolean aux = false;
		if (Jugador==2){
			//Comprovamos su color con el jugador 1 ya que es el unico logeado
			if (colorJ2.getBackground().equals(colorJ1.getBackground())){
				aux=true;
			}
		}
		else if (Jugador==3){
			//Comprovamos su color con el jugador 1 y 2 ya que es el unico logeado
			if (colorJ3.getBackground().equals(colorJ1.getBackground()) ||colorJ3.getBackground().equals(colorJ2.getBackground())){
				aux=true;
			}
			
		}
		else{
			//Comprovamos su color con el jugador 1, 2 y 3 ya que es el unico logeado
			if (colorJ4.getBackground().equals(colorJ1.getBackground()) ||colorJ4.getBackground().equals(colorJ2.getBackground()) || colorJ4.getBackground().equals(colorJ3.getBackground())){
				aux=true;
			}
		}
		return aux;		
	}
	
	
	//Metodo para introducir los jugadores que se han ido logeando correctamente
	public static void IntroducirJugadorEnLista(String pUsuario) {
		//Cargamos el usuario desde la base de datos llamando a un metodo del gestor
		Usuario usuarioAux = GestorUsuarios.getGestorUsuarios().cargarDatosUsuario(pUsuario);
		String color;
		//Conseguimos el color del jugador a añadir a la lista
		if (ListaJugadores.getListaJugadores().getListaJ().size()<1){
			color=colorJ1.getBackground().toString();
		}
		else if (ListaJugadores.getListaJugadores().getListaJ().size()<2){
			color=colorJ2.getBackground().toString();
		}
		else if (ListaJugadores.getListaJugadores().getListaJ().size()<3){
			color=colorJ3.getBackground().toString();		
		}
		else {
			color=colorJ4.getBackground().toString();	
		}
		//Añadimos el jugador a la lista, los parametros casilla y si le toca los dejamos a null y false
		//Cuando el juego se inicie este se encargara de asignar los turnos, la casilla inicial, etc...
		ListaJugadores.getListaJugadores().anadirJugador(usuarioAux, null, false, color, 0);
	}
	
	//Metodo para resetear el campo de error
	private void EliminarError()
	{
		lblMensajeError.setText("");
	}
	


	
}
