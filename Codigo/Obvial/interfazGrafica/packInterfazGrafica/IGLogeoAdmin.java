//Clase creada por Jon Ander

package packInterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import org.obvial.obvial.GestorUsuarios;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Ventana que muestra una interface para que el jugador se logee
 * @author Jon Ander
 *
 */
public class IGLogeoAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGLogeoAdmin frame = new IGLogeoAdmin();
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
	public IGLogeoAdmin() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 308, 204);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInrtoduceLosCredenciales = new JLabel("Inrtoduce los credenciales para logearte");
		lblInrtoduceLosCredenciales.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInrtoduceLosCredenciales.setBounds(10, 11, 291, 14);
		contentPane.add(lblInrtoduceLosCredenciales);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 51, 46, 14);
		contentPane.add(lblUsuario);
		
		textField = new JTextField();
		textField.setBounds(10, 76, 118, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(152, 51, 76, 14);
		contentPane.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(152, 76, 118, 20);
		contentPane.add(passwordField);
		
		JButton btnLogearse = new JButton("Login");
		btnLogearse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					boolean rdo;
					rdo = GestorUsuarios.getGestorUsuarios().logeoUsuario(textField.getText(),passwordField.getText(),true);
					if (rdo){
						IGAdministrar administrar = new IGAdministrar();
						//administrar.setModal(true);
						administrar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//HAce que no se cierre todo al salir solo esta ventana
						administrar.setVisible(true);
						//cerrar ventana de logeo
						dispose();
					}
				}
			});
		btnLogearse.setBounds(33, 127, 89, 23);
		contentPane.add(btnLogearse);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(159, 127, 89, 23);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//cerrar ventana de logeo
				dispose();
			}
		});
	}
}
