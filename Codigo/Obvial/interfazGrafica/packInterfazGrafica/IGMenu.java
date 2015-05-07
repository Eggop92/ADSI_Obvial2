//Clase hecha por Jon Ander Fontán

package packInterfazGrafica;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.SystemColor;

/**
 * Ventana principal del juego que muestra un menu con las tistintas posibilidades de cara al jugador.
 * @author Jon Ander
 *
 */
public class IGMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGMenu frame = new IGMenu();
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
	public IGMenu() {
		setResizable(false);
		setTitle("Obvial 2.0");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 308, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.setBounds(67, 11, 156, 45);
		contentPane.add(btnJugar);
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IGConfigurarJugadoresPartida partida = new IGConfigurarJugadoresPartida();
				partida.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//HAce que no se cierre todo al salir solo esta ventana
				//principal.setModal(true);
				partida.setVisible(true);
				//dispose();
			}
		});
	
		JButton btnVerEstadisticas = new JButton("Ver Estadisticas");
		btnVerEstadisticas.setBounds(67, 85, 156, 45);
		contentPane.add(btnVerEstadisticas);
		btnVerEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//Modificado por Aaron Ojembarrena	
				//En el boton estadisticas
				//LLamar a ventana Estadisticas
				IGLogeoJugador nuevoLogeo= new IGLogeoJugador(null);
				nuevoLogeo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				nuevoLogeo.setVisible(true);
			//Fin modificacion Aaron Ojembarrena		
			}
		});
	
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(67, 160, 156, 45);
		contentPane.add(btnRegistrarse);
		//Modificado por Helen
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IGRegistrarJugador registrar = new IGRegistrarJugador();
				//Hace que al cerrarse la ventana de registrar no se cierren las demas
				registrar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				registrar.setVisible(true);
				//dispose(); //es para cerrar la ventana actual
			}
		});
		//Fin de modificacion de Helen
		
		JButton btnVerRanking = new JButton("Ver Ranking");
		btnVerRanking.setBounds(67, 234, 156, 45);
		contentPane.add(btnVerRanking);
		
		
		JButton btnAdministrar = new JButton("Administrar");
		btnAdministrar.setBounds(67, 305, 156, 45);
		contentPane.add(btnAdministrar);
		btnAdministrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IGLogeoAdmin logeo = new IGLogeoAdmin();
				//administrar.setModal(true);
				logeo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//HAce que no se cierre todo al salir solo esta ventana
				logeo.setVisible(true);
			}
		});

	}
}
