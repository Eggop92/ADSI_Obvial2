//Clase Modificada por Jon Ander Basandose en la original

package packInterfazGrafica;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.obvial.obvial.*;
import java.awt.event.*;
import java.util.*;

/**
 * Clase modificada basandose en la original. Se ha eliminado el menu dado que ya no es necesario y se ha establecido un tamaño de ventana
 * en el que el tablero se visualice correctamente.
 * 
 * @author Jon Ander
 *
 */
public class IGPrincipal extends JFrame implements Observer{

	private static IGPrincipal miPrincipal = new IGPrincipal();
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menuJuego;
	private JMenuItem mntmNuevoJuego;
	private JMenuItem mntmSalir;
	private JMenu mnOpciones;
	private JMenuItem mntmConfigurarJugadores;
	private PanelTablero panel_1;
	private JPanel panel;
	private JLabel lblJug1;
	private JLabel lblJug2;
	private JLabel lblJug3;
	private JLabel lblJug4;
	private final IGopcionesJugadores opciones = new IGopcionesJugadores();
	public static boolean enEjecucion = false;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGPrincipal frame = new IGPrincipal();
					frame.setVisible(true);
					Inicializar.getInicializar().InicializarCartas();
					Inicializar.getInicializar().InicializarCasillas();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public IGPrincipal() {
		initialize();
	}
	public static IGPrincipal getPrincipal(){
		return miPrincipal;
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 785);
		Inicializar.getInicializar().InicializarCartas();
		Inicializar.getInicializar().InicializarCasillas();
		//setExtendedState(MAXIMIZED_BOTH);
		ListaCasillas.getListaCasillas().addObserver(this);
		ListaJugadores.getListaJugadores().addObserver(this);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel_1(), BorderLayout.WEST);
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}
	
	
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMenuJuego());
			menuBar.add(getMnOpciones());
		}
		return menuBar;
	}
	private JMenu getMenuJuego() {
		if (menuJuego == null) {
			menuJuego = new JMenu("Juego");
			menuJuego.add(getMntmNuevoJuego());
			menuJuego.add(getMntmSalir());
		}
		return menuJuego;
	}
	private JMenuItem getMntmNuevoJuego() {
		if (mntmNuevoJuego == null) {
			mntmNuevoJuego = new JMenuItem("Nuevo Juego");
			mntmNuevoJuego.setEnabled(false);
			mntmNuevoJuego.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(!enEjecucion){
						mntmNuevoJuego.setEnabled(false);
						jugar();
					}
				}
			});
		}
		return mntmNuevoJuego;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}
		return mntmSalir;
	}
	private JMenu getMnOpciones() {
		if (mnOpciones == null) {
			mnOpciones = new JMenu("Opciones");
			mnOpciones.add(getMntmConfigurarJugadores());
		}
		return mnOpciones;
	}
	private JMenuItem getMntmConfigurarJugadores() {
		if (mntmConfigurarJugadores == null) {
			mntmConfigurarJugadores = new JMenuItem("Configurar Jugadores");
			mntmConfigurarJugadores.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarOpciones();
				}
			});
		}
		return mntmConfigurarJugadores;
	}
	private void mostrarOpciones(){
		opciones.setModal(true);
		opciones.setVisible(true);
		opciones.requestFocus();
		
	}
	
	
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new PanelTablero();
			panel_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					System.out.print(arg0.getX()+" ");
					System.out.println(arg0.getY());
				}
			});
			panel_1.setImagen("imagenes/tablero.jpg");
			Dimension dimension = new Dimension(896, 812);
			panel_1.setPreferredSize(dimension);
			panel_1.add(getLblJug1());
			panel_1.add(getLblJug2());
			panel_1.add(getLblJug3());
			panel_1.add(getLblJug4());
		}
		return panel_1;
	}	
	private JLabel getLblJug1(){
		if (lblJug1 == null) {
			lblJug1 = new JLabel("");
			lblJug1.setSize(71, 71);
		}
		return lblJug1;
	}
	private JLabel getLblJug2(){
		if (lblJug2 == null) {
			lblJug2 = new JLabel("");
			lblJug2.setSize(71, 71);
		}
		return lblJug2;
	}
	private JLabel getLblJug3(){
		if (lblJug3 == null) {
			lblJug3 = new JLabel("");
			lblJug3.setSize(71, 71);
		}
		return lblJug3;
	}
	private JLabel getLblJug4(){
		if (lblJug4 == null) {
			lblJug4 = new JLabel("");
			lblJug4.setSize(71, 71);
		}
		return lblJug4;
	}

	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(7, 1, 0, 0));
			panel.setVisible(false);
			panel.setSize(500, 200);
		}
		return panel;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Casilla){
			moverJugadoresACasilla((Casilla)arg);
		}
		
		if(arg instanceof Jugador){
			mntmNuevoJuego.setEnabled(true);
			asignarLabelAJugador((Jugador)arg);
		}
	}
	private void moverJugadoresACasilla(Casilla pCasilla){
		Iterator<Jugador> itr = pCasilla.getIterador();
		Jugador unJugador = null;
		int posX = pCasilla.getPosX();
		int posY = pCasilla.getPosY();
		
		while(itr.hasNext()){
			unJugador = itr.next();
			String color = unJugador.getColor();
			switch(color){
			case "RED": lblJug1.setLocation(posX, posY);
			break;
			case "GREEN": lblJug2.setLocation(posX, posY);
			break;
			case "BLUE": lblJug3.setLocation(posX, posY);
			break;
			case "YELLOW": lblJug4.setLocation(posX, posY);
			break;
			}
		}
	}
	private void asignarLabelAJugador(Jugador pJugador){
		
		String color = pJugador.getColor();
		switch(color){
		case "RED": lblJug1.setIcon(elegirImagenFicha(color));
		break;
		case "GREEN": lblJug2.setIcon(elegirImagenFicha(color));
		break;
		case "BLUE": lblJug3.setIcon(elegirImagenFicha(color));
		break;
		case "YELLOW": lblJug4.setIcon(elegirImagenFicha(color));
		}
	}
	private ImageIcon elegirImagenFicha(String pColor){
		ImageIcon imagen = new ImageIcon(getClass().getResource("imagenes/fichas/error.png")); 
		
		if (pColor.equals("RED")){
			imagen = new ImageIcon(getClass().getResource("imagenes/fichas/Rojo.png"));
		}
		else if (pColor.equals("YELLOW")){
			imagen = new ImageIcon(getClass().getResource("imagenes/fichas/Amarillo.png"));
		}
		else if (pColor.equals("GREEN")){
			imagen = new ImageIcon(getClass().getResource("imagenes/fichas/Verde.png"));
		}
		else if (pColor.equals("BLUE")){
			imagen = new ImageIcon(getClass().getResource("imagenes/fichas/Azul.png"));
		}
//		switch(pColor){
//			case "Rojo": imagen = new ImageIcon(getClass().getResource("imagenes/fichas/Rojo.png"));
//			break;
//			case 2: imagen = new ImageIcon(getClass().getResource("imagenes/fichas/Azul.png"));
//			break;
//			case 10: imagen = new ImageIcon(getClass().getResource("imagenes/fichas/Verde.png"));
//			break;
//			case 20: imagen = new ImageIcon(getClass().getResource("imagenes/fichas/Amarillo.png"));
//			break;
//		}
		return imagen;
	}
	
	
	private void jugar(){
		
		enEjecucion = true;
		
		Jugador jugadorActual;
		
		//Iniciamos el iterador
		Iterator<Jugador> itrJug = ListaJugadores.getListaJugadores().getIterador();
		
		//Mientras no haya finalizado ningun jugador, seguimos
		while(ListaJugadores.getListaJugadores().algunoHaTerminado() == false){
			
			//Si estabamos en el ultimo reiniciamos el iterador
			//para volver al primero
			if(!itrJug.hasNext()){
				itrJug = ListaJugadores.getListaJugadores().getIterador();
			}
			
			//Cogemos un jugador
			jugadorActual = itrJug.next();
			
			//Mostramos la ventana del turno
			IGTurnoJugador turnoJugador = new IGTurnoJugador(jugadorActual);
			turnoJugador.setModal(true);
			turnoJugador.setVisible(true);
		}
		enEjecucion = false;
	}
}
