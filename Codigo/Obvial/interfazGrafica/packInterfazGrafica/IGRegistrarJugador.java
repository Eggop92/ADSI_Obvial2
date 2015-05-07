/**CLASE CREADA POR HELEN*/

package packInterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.obvial.obvial.GestorUsuarios;

import java.awt.Color;

public class IGRegistrarJugador extends JFrame {

	private JPanel contentPane;
	private JTextField usuario;
	private JTextField eMail;
	private JPasswordField pass;
	private JPasswordField pass_1;
	private JLabel lblErrorUsuario = new JLabel("Usuario ya existente, intenta con otro.");
	private JLabel lblErrorContrasena=new JLabel("Verificar que las contraseñas cioncidan.");
	private JLabel lblErrorEmail = new JLabel("Direccion de e-mail incorrecta.");

	/**
	 * se ejecuta solo si esta es la ventana principal de la aplicacion
	 * que no es nuestro caso, pero es necesario para comprobar q la ventana realiza lo que queremos
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGRegistrarJugador frame = new IGRegistrarJugador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Crea el panel sobre el cual estaran los botones y demas cosas
	public IGRegistrarJugador() {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**recibo la informacion de los JTextFiel y contraseñas
				 * Verifico que las contraseñas sean iguales
				 * y el e-mail correcto
				 * luego se lo paso a un metodo de algun gestor
				 * que compruebe los usuarios a ver si no hay ninguno igual*/

				boolean confirmar=true;
				if (!GestorUsuarios.getGestorUsuarios().comprobarUsuario(usuario.getText())){//compruebo usuario
					lblErrorUsuario.setVisible(true);
					confirmar=false;
				}else{
					lblErrorUsuario.setVisible(false);
				}
				if(!GestorUsuarios.getGestorUsuarios().coprobarEmail(eMail.getText())){//compruebo e-mail
					lblErrorEmail.setVisible(true);
					confirmar=false;
				}else{
					lblErrorEmail.setVisible(false);
				}
				if(!pass.getText().equals(pass_1.getText())){//compruebo contdraseñas
					lblErrorContrasena.setVisible(true);
					confirmar=false;
				}else{
					lblErrorContrasena.setVisible(false);
				}
				if(confirmar){
					GestorUsuarios.getGestorUsuarios().registrarUsuario(usuario.getText(), eMail.getText(), pass.getText());
					JOptionPane.showMessageDialog(null, "El Usuario se ha guardado correctamente.", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				
			}
		});
		btnAceptar.setBounds(47, 198, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(183, 198, 89, 23);
		contentPane.add(btnCancelar);
		//Usuario
		usuario = new JTextField();
		usuario.setBounds(156, 11, 150, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		//E-Mail
		eMail = new JTextField();
		eMail.setBackground(Color.WHITE);
		eMail.setBounds(156, 56, 150, 20);
		contentPane.add(eMail);
		eMail.setColumns(10);
		
		//Contraseña
		pass = new JPasswordField();
		pass.setBounds(156, 101, 150, 20);
		contentPane.add(pass);
		
		//Contraseña repetida
		pass_1 = new JPasswordField();
		pass_1.setBounds(156, 146, 150, 20);
		contentPane.add(pass_1);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(13, 11, 130, 20);
		contentPane.add(lblUsuario);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(13, 56, 130, 20);
		contentPane.add(lblEmail);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasea.setBounds(13, 101, 130, 20);
		contentPane.add(lblContrasea);
		
		JLabel lblVerificarcontrasea = new JLabel("Verificar Contrase\u00F1a:");
		lblVerificarcontrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVerificarcontrasea.setBounds(13, 146, 130, 20);
		contentPane.add(lblVerificarcontrasea);
		
		
		lblErrorUsuario.setForeground(Color.RED);
		lblErrorUsuario.setBounds(102, 30, 204, 20);
		contentPane.add(lblErrorUsuario);
		lblErrorUsuario.setVisible(false);
			
		
		lblErrorEmail.setForeground(Color.RED);
		lblErrorEmail.setBounds(114, 75, 192, 20);
		contentPane.add(lblErrorEmail);
		lblErrorEmail.setVisible(false);
		
		
		lblErrorContrasena.setForeground(Color.RED);
		lblErrorContrasena.setBounds(87, 125, 222, 20);
		contentPane.add(lblErrorContrasena);
		lblErrorContrasena.setVisible(false);
		
		
				
	}
}
