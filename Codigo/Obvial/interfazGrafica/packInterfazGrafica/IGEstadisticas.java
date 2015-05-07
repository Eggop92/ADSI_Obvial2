package packInterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

import org.obvial.obvial.*;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

//Esta interfaz gráfica para consulta de Estadísticas ha sido realizada por Aaron Ojembarrena

public class IGEstadisticas extends JFrame { 

	private JPanel contentPane;
	private JTextField NombreUsuario;
	private JTextField numAcertadas;
	private JTextField numfalladas;
	private JTextField numTotales;
	private JTextField numPuntTotales;
	private JTextField numPuntPerd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGEstadisticas frame = new IGEstadisticas(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param usuario 
	 */
	@SuppressWarnings("null")
	public IGEstadisticas(String usuario) {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 27, 46, 14);
		contentPane.add(lblUsuario);
		
		NombreUsuario = new JTextField();
		NombreUsuario.setEditable(false);
		NombreUsuario.setBounds(78, 24, 86, 20);
		contentPane.add(NombreUsuario);
		NombreUsuario.setColumns(10);
		NombreUsuario.setText(usuario);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 64, 55, 14);
		contentPane.add(lblCategora);
		
		
	GestorRespuestas.getGestorRespuestas();
		
		final JComboBox comboBoxCategorias = new JComboBox();
		comboBoxCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					numAcertadas.setText(String.valueOf(GestorRespuestas.getGestorRespuestas().calcularPregTotales(String.valueOf(comboBoxCategorias.getSelectedItem()), NombreUsuario.getText())-GestorRespuestas.getGestorRespuestas().calcularFalladas(String.valueOf(comboBoxCategorias.getSelectedItem()), NombreUsuario.getText())));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					numfalladas.setText(String.valueOf(GestorRespuestas.getGestorRespuestas().calcularFalladas(String.valueOf(comboBoxCategorias.getSelectedItem()), NombreUsuario.getText())));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					numTotales.setText(String.valueOf(GestorRespuestas.getGestorRespuestas().calcularPregTotales(String.valueOf(comboBoxCategorias.getSelectedItem()), NombreUsuario.getText())));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					numPuntTotales.setText(String.valueOf(GestorRespuestas.getGestorRespuestas().calcularPuntosTotales(String.valueOf(comboBoxCategorias.getSelectedItem()), NombreUsuario.getText())));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					numPuntPerd.setText(String.valueOf(GestorRespuestas.getGestorRespuestas().calcularPuntosPerdidos(String.valueOf(comboBoxCategorias.getSelectedItem()), NombreUsuario.getText())));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		comboBoxCategorias.setBounds(78, 55, 86, 20);
		contentPane.add(comboBoxCategorias);
		
		
		
		/*Este método que esta implementado en GestorCategorias, deberia cargar los nombre de los tipos de categoria en el comboBox; pero por un error al acceder a la base de datos no lo hace.*/
		/*;
		LinkedList<NombreYCategoria> nomYCat = GestorCategorias.getGestorCategorias().cargarNombresYCategorias();
		
		if(nomYCat!=null && !nomYCat.isEmpty()){
			Iterator<NombreYCategoria> itr = nomYCat.iterator();
			String nombre;
			comboBoxCategorias.addItem("ahjsgdahj");
			while(itr.hasNext()){
				nombre = itr.next().getNom();
				comboBoxCategorias.addItem(nombre);
			}
			
		}
		*/
		
		
		JLabel IblAcertadas = new JLabel("Preguntas Acertadas");
		IblAcertadas.setBounds(0, 147, 106, 14);
		contentPane.add(IblAcertadas);
		
		
		
		numAcertadas = new JTextField("");
		numAcertadas.setBounds(10, 172, 86, 20);
		numAcertadas.setEditable(false);
		contentPane.add(numAcertadas);
		numAcertadas.setColumns(10);
		
		
		
		
		JLabel lblPreguntasFalladas = new JLabel("Preguntas Falladas");
		lblPreguntasFalladas.setBounds(118, 147, 96, 14);
		contentPane.add(lblPreguntasFalladas);
		
		numfalladas = new JTextField();
		numfalladas.setBounds(121, 172, 86, 20);
		numfalladas.setEditable(false);
		numfalladas.setColumns(10);
		contentPane.add(numfalladas);
		
		
		JLabel lblPreguntasTotales = new JLabel("Preguntas totales");
		lblPreguntasTotales.setBounds(224, 147, 100, 14);
		contentPane.add(lblPreguntasTotales);
		
		JLabel lblPuntosTotales = new JLabel("Puntos Totales");
		lblPuntosTotales.setBounds(334, 147, 96, 14);
		contentPane.add(lblPuntosTotales);
		
		JLabel lblPuntosPerdidos = new JLabel("Puntos Perdidos");
		lblPuntosPerdidos.setBounds(440, 147, 99, 14);
		contentPane.add(lblPuntosPerdidos);
		
		numTotales = new JTextField();
		numTotales.setEditable(false);
		numTotales.setColumns(10);
		numTotales.setBounds(218, 172, 96, 20);
		contentPane.add(numTotales);
		
		
		numPuntTotales = new JTextField();
		numPuntTotales.setEditable(false);
		numPuntTotales.setColumns(10);
		numPuntTotales.setBounds(328, 172, 96, 20);
		contentPane.add(numPuntTotales);
		
		
		numPuntPerd = new JTextField();
		numPuntPerd.setEditable(false);
		numPuntPerd.setColumns(10);
		numPuntPerd.setBounds(440, 172, 99, 20);
		contentPane.add(numPuntPerd);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(450, 235, 89, 23);
		contentPane.add(btnContinuar);
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

	}	
}



