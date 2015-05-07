//Clase Creada por Jon Ander y David

package packInterfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import org.obvial.obvial.GestorUsuarios;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JPasswordField;

/***
 * 
 * @author Jon Ander y David
 *
 */
public class IGLogeoJugador extends JFrame {


	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textContraseña;
	private boolean logeoCorrecto;
	static String jugador= null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGLogeoJugador frame = new IGLogeoJugador(jugador);
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
	public IGLogeoJugador(final String pJugador) {
		setResizable(false);
		jugador=pJugador;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		final JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setBounds(74, 197, 299, 14);
		contentPane.add(lblError);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Modificado por Jon Ander y David
				logeoCorrecto = GestorUsuarios.getGestorUsuarios().logeoUsuario(textNombre.getText(),textContraseña.getText(),false);
				if (logeoCorrecto){
					if(pJugador!=null){		
						IGConfigurarJugadoresPartida.cambiarLabel(textNombre.getText(), jugador);
						IGConfigurarJugadoresPartida.bloquearAcciones(jugador);
						IGConfigurarJugadoresPartida.IntroducirJugadorEnLista(textNombre.getText());
					}
					else{
						//Llamda para el metodo de Aaron de estadisticas
						IGEstadisticas estadisticas = new IGEstadisticas(textNombre.getText());
						estadisticas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						estadisticas.setVisible(true);
					}
				dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "No se ha introducido correctamente el Usuario o la contraseña.\nIntentelo de nuevo", "Usuario Inexistente", JOptionPane.ERROR_MESSAGE);
				}
				//Fin de modificacion por Jon Ander y David
			}
		});
		btnAceptar.setBounds(111, 224, 83, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(237, 224, 89, 23);
		contentPane.add(btnCancelar);
		
		textNombre = new JTextField();
		textNombre.setBounds(173, 30, 137, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
				
		textContraseña = new JPasswordField();
		textContraseña.setBounds(173, 61, 137, 20);
		contentPane.add(textContraseña);
		textContraseña.setColumns(10);
		
		JTextArea txtrRecuperar = new JTextArea();
		txtrRecuperar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblError.setText("Metodo no implementado");
			}
		});
		txtrRecuperar.setForeground(Color.BLUE);
		txtrRecuperar.setBackground(SystemColor.control);
		txtrRecuperar.setText("Recuperar contrase\u00F1a");
		txtrRecuperar.setBounds(139, 164, 171, 22);
		contentPane.add(txtrRecuperar);
		
		JTextArea txtrRegistrarse = new JTextArea();
		txtrRegistrarse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IGRegistrarJugador registrar= new IGRegistrarJugador();
				registrar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//HAce que no se cierre todo al salir solo esta ventana
				registrar.setVisible(true);
			}
		});
		txtrRegistrarse.setBackground(SystemColor.control);
		txtrRegistrarse.setForeground(Color.BLUE);
		txtrRegistrarse.setText("Registrarse");
		txtrRegistrarse.setBounds(164, 131, 98, 22);
		contentPane.add(txtrRegistrarse);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombre.setBounds(74, 32, 76, 14);
		contentPane.add(lblNombre);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblContrasea.setBounds(74, 64, 89, 14);
		contentPane.add(lblContrasea);
	}
}
