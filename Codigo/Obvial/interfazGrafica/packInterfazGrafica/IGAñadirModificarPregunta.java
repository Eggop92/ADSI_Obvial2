//Clase creada por Jon Ander Fontán

package packInterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;

import org.obvial.obvial.*;

/**
 * Ventana que se muestra para añadir una pregunta o para modificar una pregunta.
 * @author Jon Ander
 *
 */
public class IGAñadirModificarPregunta extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextArea textArea;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	private Pregunta preg;
	private NombreYCategoria noYCa;
	private JComboBox<String> comboBox;
	private LinkedList<NombreYCategoria> nomYCat;
	private ButtonGroup grupoDeBotones= new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGAñadirModificarPregunta frame = new IGAñadirModificarPregunta(null, null);
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
	//public IGAñadirModificarPregunta(String textoPregunta)
	public IGAñadirModificarPregunta(Pregunta pPreg, NombreYCategoria nombYCate) {
		
		this.preg=pPreg;
		//System.out.println(pPreg.getId());
		noYCa=nombYCate;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTextoDeLa = new JLabel("Texto de la pregunta");
		lblTextoDeLa.setBounds(10, 11, 176, 14);
		contentPane.add(lblTextoDeLa);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 33, 414, 61);
		contentPane.add(textArea);
		
		JLabel lblRespuesta = new JLabel("Respuesta 1");
		lblRespuesta.setBounds(10, 105, 67, 14);
		contentPane.add(lblRespuesta);
		
		textField = new JTextField();
		textField.setBounds(10, 130, 241, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblRespuesta_1 = new JLabel("Respuesta 2");
		lblRespuesta_1.setBounds(10, 161, 67, 14);
		contentPane.add(lblRespuesta_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 186, 241, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblRespuesta_2 = new JLabel("Respuesta 3");
		lblRespuesta_2.setBounds(10, 217, 67, 14);
		contentPane.add(lblRespuesta_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 242, 241, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblRespuesta_3 = new JLabel("Respuesta 4");
		lblRespuesta_3.setBounds(10, 273, 67, 14);
		contentPane.add(lblRespuesta_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 298, 241, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		radioButton1 = new JRadioButton("");
		radioButton2 = new JRadioButton("");
		radioButton3 = new JRadioButton("");
		radioButton4 = new JRadioButton("");
		

		grupoDeBotones.add(radioButton1);
		grupoDeBotones.add(radioButton2);
		grupoDeBotones.add(radioButton3);
		grupoDeBotones.add(radioButton4);

		
		radioButton1.setBounds(345, 127, 21, 23);
		contentPane.add(radioButton1);
		radioButton2.setBounds(345, 185, 21, 23);
		contentPane.add(radioButton2);
		radioButton3.setBounds(345, 241, 21, 23);
		contentPane.add(radioButton3);
		radioButton4.setBounds(345, 297, 21, 23);
		contentPane.add(radioButton4);
		
		JLabel lblRespuestaCorrecta = new JLabel("Respuesta Correcta");
		lblRespuestaCorrecta.setBounds(307, 105, 96, 14);
		contentPane.add(lblRespuestaCorrecta);
		
		JLabel lblCategoriaALa = new JLabel("Categoria a la que pertenece");
		lblCategoriaALa.setBounds(10, 329, 140, 14);
		contentPane.add(lblCategoriaALa);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 354, 140, 20);
		contentPane.add(comboBox);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(97, 403, 89, 23);
		contentPane.add(btnAceptar);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Modificado por Egoitz
				if(preg!=null){//Si se ha pasado una pregunta como parametro(Modificar)
					Categoria nCat, vCat;
					LinkedList<String> datosMod;
					
					vCat= noYCa.getCat();
					
					String nomSelec=(String) comboBox.getSelectedItem();
					NombreYCategoria nYC=null;
					boolean enco =false;
					Iterator<NombreYCategoria> itrC = nomYCat.iterator();
					while(itrC.hasNext()&& !enco){
						nYC=itrC.next();
						enco=nYC.equals(nomSelec);
					}
					nCat=nYC.getCat();
					if(!textArea.getText().equals("") && !textField.getText().equals("") && !textField_1.getText().equals("") && !textField_2.getText().equals("") && !textField_3.getText().equals("")){
						//si se ha seleccionado una respuesta correcta
						if(grupoDeBotones.getSelection()!=null){
							//si se ha seleccionado una categoria
							//if (comboBox.getSelectedIndex()!=0){
								datosMod=new LinkedList<String>();
								datosMod.add(textArea.getText());
								datosMod.add(textField.getText());
								datosMod.add(textField_1.getText());
								datosMod.add(textField_2.getText());
								datosMod.add(textField_3.getText());
								if (radioButton1.isSelected()){datosMod.add(textField.getText());}
								else if(radioButton2.isSelected()){datosMod.add(textField_1.getText());}
								else if(radioButton3.isSelected()){datosMod.add(textField_2.getText());}
								else {datosMod.add(textField_3.getText());}
											
								GestorCategorias.getGestorCategorias().modificarPregunta(preg, datosMod, nCat, vCat);
								dispose();
							//}
							//else{//Si no se ha seleccionado una categoria
							//	JOptionPane.showMessageDialog(null, "No has seleccionado una categoria", "Error seleccion categoria", JOptionPane.ERROR_MESSAGE);
							//}
						}
						else{ //si no se ha seleccionado una respuesta correcta
							JOptionPane.showMessageDialog(null, "No has seleccionado una respuesta correcta", "Error seleccion respuesta correcta", JOptionPane.ERROR_MESSAGE);
						}
					}
					else{ //si no se han rellenado todos los campos
						JOptionPane.showMessageDialog(null, "No has rellenado todos los datos en lo referente a pregunta", "Error insercion de datos", JOptionPane.ERROR_MESSAGE);
					}
				}
				//Fin Modificacion Egoitz
				
				else{//(if pregunta==null)Si no se pasa una pregunta como parametro(Añadir)
					LinkedList<String> datosNuevaPregunta = new LinkedList<String>();
					//Si todos los campos estan rellenos
					if(!textArea.getText().equals("") && !textField.getText().equals("") && !textField_1.getText().equals("") && !textField_2.getText().equals("") && !textField_3.getText().equals("")){
						//si se ha seleccionado una respuesta correcta
						if(grupoDeBotones.getSelection()!=null){
							//si se ha seleccionado una categoria
							if (comboBox.getSelectedIndex()!=0){
								//TODO Añadir una pregunta a lacategoria correspondiente
								datosNuevaPregunta.add(textField.getText());
								datosNuevaPregunta.add(textField_1.getText());
								datosNuevaPregunta.add(textField_2.getText());
								datosNuevaPregunta.add(textField_3.getText());
								String respuestaCorrecta = null;
								if (radioButton1.isSelected()){
									respuestaCorrecta=textField.getText();
								}
								else if(radioButton2.isSelected()){
									respuestaCorrecta=textField_1.getText();									
								}
								else if(radioButton3.isSelected()){
									respuestaCorrecta=textField_2.getText();									
								}
								else if(radioButton4.isSelected()){
									respuestaCorrecta=textField_3.getText();
								}
								Pregunta nuevaPregunta = GestorPreguntas.getGestorPreguntas().crearPregunta(textArea.getText(), datosNuevaPregunta, respuestaCorrecta,0,0);
								String nombreCategoria = (String) comboBox.getSelectedItem();
								
								boolean sePuede = GestorCategorias.getGestorCategorias().añadirPreguntaACateroria(nuevaPregunta,GestorCategorias.getGestorCategorias().buscarCategoria(nombreCategoria));
								if (!sePuede){
									JOptionPane.showMessageDialog(null, "La pregunta que estas añadiendo ya existe en la base de datos", "Error al insertar Pregunta", JOptionPane.ERROR_MESSAGE);
								}
								else{
									JOptionPane.showMessageDialog(null, "Pregunta añadida correctamente", "Añadido Correctamente", JOptionPane.INFORMATION_MESSAGE);
								}
								
								dispose();//Cierro la ventana de añadir pregunta
							}
							else{//Si no se ha seleccionado una categoria
								JOptionPane.showMessageDialog(null, "No has seleccionado una categoria", "Error seleccion categoria", JOptionPane.ERROR_MESSAGE);
							}
						}
						else{ //si no se ha seleccionado una respuesta correcta
							JOptionPane.showMessageDialog(null, "No has seleccionado una respuesta correcta", "Error seleccion respuesta correcta", JOptionPane.ERROR_MESSAGE);
						}
					}
					else{ //si no se han rellenado todos los campos
						JOptionPane.showMessageDialog(null, "No has rellenado todos los datos en lo referente a pregunta", "Error insercion de datos", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(232, 403, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			}
		});
		
		//Modificado Por Egoitz
		//Rellenamos el comboBox de las categorias
		nomYCat = GestorCategorias.getGestorCategorias().cargarNombresYCategorias();
		if(nomYCat!=null && !nomYCat.isEmpty()){
			Iterator<NombreYCategoria> itr = nomYCat.iterator();
			String nombre;
			//
			if(nombYCate!=null){
				comboBox.addItem(nombYCate.getNom());
			}else{comboBox.addItem("");}
			while(itr.hasNext()){
				nombre = itr.next().getNom();
				if(( nombYCate!=null && !nombre.equals(nombYCate.getNom()))||nombYCate==null){
					comboBox.addItem(nombre);
				}
			}
		}		
		if(preg!=null){
			//Es modificar
			//Rellenamos los campos de la ventana con los datos de la pregunta
			 LinkedList<String> dtos =GestorPreguntas.getGestorPreguntas().cargarDatosPregunta(preg);
			 textArea.setText(dtos.get(0));
			 textField.setText(dtos.get(1));
			 textField_1.setText(dtos.get(2));
			 textField_2.setText(dtos.get(3));
			 textField_3.setText(dtos.get(4));
			 //MArcamos el radioButton que corresponde a la solucion
			 String respCorr = dtos.get(5);
			 if(respCorr.equals(dtos.get(1))){radioButton1.setSelected(true);}
			 else if(respCorr.equals(dtos.get(2))){radioButton2.setSelected(true);}
			 else if(respCorr.equals(dtos.get(3))){radioButton3.setSelected(true);}
			 else {radioButton4.setSelected(true);}
		}
		//Fin Modificado Por Egoitz
	}
}
