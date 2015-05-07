//Clase Modificada por Jon Ander Basandose en la original

package packInterfazGrafica;

import java.awt.EventQueue;
import javax.swing.*;
import org.obvial.obvial.Carta;
import org.obvial.obvial.Categoria;
import org.obvial.obvial.GestorCategorias;
import org.obvial.obvial.GestorPreguntas;
import org.obvial.obvial.GestorRespuestas;
import org.obvial.obvial.GestorUsuarios;
import org.obvial.obvial.Pregunta;
import org.obvial.obvial.Usuario;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
/**
 * Clase modificada Basada en la interface Original, Se ha modificado la forma de visualizar la pregunta ya no sale en la ventana
 * la parte derecha de la ventana de juego
 * 
 * @author Jon Ander
 *
 */
public class IGPregunta extends JDialog {

	private JPanel panel;
	private JTextField txtTempo;
	private JTextArea txtPregunta;
	private ButtonGroup grupoDeBotones = new ButtonGroup();
	private JRadioButton rdbtnRespuestaA;
	private JRadioButton rdbtnRespuestaB;
	private JRadioButton rdbtnRespuestaC;
	private JRadioButton rdbtnRespuestaD;
	private JButton btnAceptarRespuesta;
	private int n=0;
	private Timer t;
	//private static Carta cartaActual = new Carta(0, "", "", "", "", "", "");
	//private boolean respuesta = false;
	private static Categoria laCategoria;
	private static Usuario elUsuario;
	private Pregunta laPregunta;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGPregunta dialog = new IGPregunta(laCategoria,elUsuario);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public IGPregunta(Categoria pLaCategoria,Usuario pElUsuario) {
		//cartaActual = pCarta;
		laCategoria=pLaCategoria;
		elUsuario=pElUsuario;
		initialize();
	}
	private void initialize() {
		//Con esto quitamos los bordes de la ventana
		setUndecorated(true);
		setBounds(898, 47, 488, 426);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		t.start();
		//Centrar en la pantalla
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = this.getSize();
		if (windowSize.height>screenSize.height) {
		windowSize.height= screenSize.height;
		}
		if (windowSize.width>screenSize.width){
		windowSize.width= screenSize.width;
		}
		setLocation((screenSize.width-windowSize.width)/2, 
		(screenSize.height-windowSize.height)/2);
	}
	
	
	private JPanel getPanel() {
		LinkedList<String> datos = GestorCategorias.getGestorCategorias().obtenerPreguntasCategoria(laCategoria);
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(7, 1, 0, 0));
			panel.setVisible(true);
			panel.setSize(500, 200);
			panel.add(getTxtTempo());
			panel.add(getTxtPregunta());
			//getTxtPregunta().setText(cartaActual.getPregunta());
			getTxtPregunta().setText(datos.get(0));
			panel.add(getRdbtnRespuestaA());
			//getRdbtnRespuestaA().setText(cartaActual.getResp1());
			getRdbtnRespuestaA().setText(datos.get(1));
			panel.add(getRdbtnRespuestaB());
			//getRdbtnRespuestaB().setText(cartaActual.getResp2());
			getRdbtnRespuestaA().setText(datos.get(2));
			panel.add(getRdbtnRespuestaC());
			//getRdbtnRespuestaC().setText(cartaActual.getResp3());
			getRdbtnRespuestaA().setText(datos.get(3));
			panel.add(getRdbtnRespuestaD());
			//getRdbtnRespuestaD().setText(cartaActual.getResp4());
			getRdbtnRespuestaA().setText(datos.get(4));
			panel.add(getBtnAceptarRespuesta());
			grupoDeBotones.add(rdbtnRespuestaA);
			grupoDeBotones.add(rdbtnRespuestaB);
			grupoDeBotones.add(rdbtnRespuestaC);
			grupoDeBotones.add(rdbtnRespuestaD);
			
			int idPregunta = Integer.parseInt(datos.get(5));
			laPregunta=GestorCategorias.getGestorCategorias().obtenerPregunta(idPregunta, laCategoria);
	
		}
		return panel;
	}
	private JTextField getTxtTempo() {
		if (txtTempo == null) {
			txtTempo = new JTextField();
			txtTempo.setHorizontalAlignment(SwingConstants.CENTER);
			txtTempo.setFont(new Font("Lucida Grande", Font.BOLD, 50));
			txtTempo.setColumns(10);
			txtTempo.setEditable(false);
			ActionListener accion = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(n<30){
						String seg = String.valueOf(n);
						txtTempo.setText(seg);
						n=n+1;
					}else{
						t.stop();
						btnAceptarRespuesta.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Se te ha acabado el tiempo", "Fin de tiempo", JOptionPane.ERROR_MESSAGE);
						n=0;
						//respuesta = false;
						dispose();
					}
				}
			};
			t = new Timer(1000, accion);
		}
		return txtTempo;
	}
	private JTextArea getTxtPregunta() {
		if (txtPregunta == null) {
			txtPregunta = new JTextArea();
			txtPregunta.setEditable(false);
			txtPregunta.setLineWrap(true);
		}
		return txtPregunta;
	}
	private JRadioButton getRdbtnRespuestaA(){
		if (rdbtnRespuestaA == null) {
			rdbtnRespuestaA = new JRadioButton();
		}
		return rdbtnRespuestaA;
	}
	private JRadioButton getRdbtnRespuestaB() {
		if (rdbtnRespuestaB == null) {
			rdbtnRespuestaB = new JRadioButton();
		}
		return rdbtnRespuestaB;
	}
	private JRadioButton getRdbtnRespuestaC() {
		if (rdbtnRespuestaC == null) {
			rdbtnRespuestaC = new JRadioButton();
		}
		return rdbtnRespuestaC;
	}
	private JRadioButton getRdbtnRespuestaD() {
		if (rdbtnRespuestaD == null) {
			rdbtnRespuestaD = new JRadioButton();
		}
		return rdbtnRespuestaD;
	}
	private JButton getBtnAceptarRespuesta() {
		if (btnAceptarRespuesta == null) {
			btnAceptarRespuesta = new JButton("Aceptar");
			btnAceptarRespuesta.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					boolean result = false;
					
					if(grupoDeBotones.getSelection() == null){
						JOptionPane.showMessageDialog(null, "No has seleccionado ninguna opción", "Atención", JOptionPane.WARNING_MESSAGE);
					}else{
						if(rdbtnRespuestaA.isSelected()){
							//result = cartaActual.esCorrecta("A");
							result=GestorPreguntas.getGestorPreguntas().ObtenerInfoPregunta(laPregunta, rdbtnRespuestaA.getText());
						}else{
							if(rdbtnRespuestaB.isSelected()){
								//result = cartaActual.esCorrecta("B");
								result=GestorPreguntas.getGestorPreguntas().ObtenerInfoPregunta(laPregunta, rdbtnRespuestaB.getText());

							}else{
								if(rdbtnRespuestaC.isSelected()){
									//result = cartaActual.esCorrecta("C");
									result=GestorPreguntas.getGestorPreguntas().ObtenerInfoPregunta(laPregunta, rdbtnRespuestaB.getText());

								}else{
									if(rdbtnRespuestaD.isSelected()){
										//result = cartaActual.esCorrecta("D");
										result=GestorPreguntas.getGestorPreguntas().ObtenerInfoPregunta(laPregunta, rdbtnRespuestaC.getText());

									}
								}
							}
						}
						
						if(result){
							t.stop();
							JOptionPane.showMessageDialog(null, "¡¡Enhorabuena, has acertado!!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
							//respuesta = true;
							dispose();
						}else{
							t.stop();
							JOptionPane.showMessageDialog(null, "Oooooh, has fallado...", "Resultado", JOptionPane.INFORMATION_MESSAGE);
							//respuesta = false;
							dispose();
						}
						GestorRespuestas.getGestorRespuestas().crearRespuesta(result, elUsuario, laPregunta);
						GestorUsuarios.getGestorUsuarios().actualizarUsuario(elUsuario, result);
					}
				}
			});
		}
		return btnAceptarRespuesta;
	}


}
