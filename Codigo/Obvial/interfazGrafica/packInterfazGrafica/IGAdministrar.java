//Clase Creada por Jon Ander Fontán

package packInterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;

import org.obvial.obvial.*;

import java.awt.Font;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Ventana de administracion del juego
 * @author Jon Ander
 *
 */
public class IGAdministrar extends JFrame {

	private JPanel contentPane;
	//Colocados como Atributos para acceso.
	private JList list;
	private JComboBox comboBoxCategoria;
	private LinkedList<NombreYCategoria> nomYCat;
	private LinkedList<PreguntaYTitulo> pregYTit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGAdministrar frame = new IGAdministrar();
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
	public IGAdministrar() {
		setResizable(false);
		setTitle("Administrar");
		setBounds(100, 100, 449, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Preguntas", null, panel, null);
		panel.setLayout(null);
		
		comboBoxCategoria = new JComboBox();
		//comboBoxCategoria.
		//Rellenar el combo box
		//Modificado Por Egoitz
		nomYCat = GestorCategorias.getGestorCategorias().cargarNombresYCategorias();
		if(nomYCat!=null && !nomYCat.isEmpty()){
			Iterator<NombreYCategoria> itr = nomYCat.iterator();
			String nombre;
			comboBoxCategoria.addItem("");
			while(itr.hasNext()){
				nombre = itr.next().getNom();
				comboBoxCategoria.addItem(nombre);
			}
		}
		//Fin modificacion Egoitz
		comboBoxCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//accion a hacer cuando se cambie el combobox de categoria (Cargar en la lista todas las perguntas)
				//Modificacion Egoitz
				String nomSelec=(String) comboBoxCategoria.getSelectedItem();
				Categoria cat=null;
				if(!nomSelec.equals("")){
					NombreYCategoria nYC=null;
					boolean enc=false;
					Iterator<NombreYCategoria> itr = nomYCat.iterator();
					while(itr.hasNext()&& !enc){
						nYC=itr.next();
						enc=nYC.equals(nomSelec);
					}
					cat=nYC.getCat();
				}
				DefaultListModel modelo = new DefaultListModel();
				if(cat!=null){
					pregYTit= GestorCategorias.getGestorCategorias().cargarPreguntas(cat);
					if(pregYTit!=null && !pregYTit.isEmpty()){
						Iterator<PreguntaYTitulo> itrP =pregYTit.iterator();
						String titulo;
						while(itrP.hasNext()){
							PreguntaYTitulo PYG=itrP.next();
							titulo=PYG.getTitulo();
							modelo.addElement(titulo);
						}
					}
				}
				list.setModel(modelo);
				//Fin Modificacion Egoitz
			}
		});
		comboBoxCategoria.setBounds(10, 29, 90, 20);
		panel.add(comboBoxCategoria);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 11, 90, 14);
		panel.add(lblCategoria);
		
		JLabel lblPregunta = new JLabel("Pregunta");
		lblPregunta.setBounds(10, 60, 46, 14);
		panel.add(lblPregunta);
		
		JButton btnA = new JButton("A\u00F1adir");
		btnA.setBounds(10, 369, 89, 23);
		panel.add(btnA);
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO
				IGAñadirModificarPregunta añadirModificarPregunta = new IGAñadirModificarPregunta(null, null);
				//administrar.setModal(true);
				añadirModificarPregunta.setTitle("Añadir Pregunta");
				añadirModificarPregunta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//HAce que no se cierre todo al salir solo esta ventana
				añadirModificarPregunta.setVisible(true);
			}
		});
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(109, 369, 89, 23);
		panel.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//obtener texto de obj seleccionado en la lista TODO
				//modificado por Egoitz
				String titSel = (String) list.getSelectedValuesList().get(0);
				if(titSel!=null){
					PreguntaYTitulo pYT=null;
					boolean enc=false;
					Iterator<PreguntaYTitulo> itr=pregYTit.iterator();
					while(itr.hasNext() && !enc){
						pYT=itr.next();
						enc=pYT.equals(titSel);
					}
					String nomSelec=(String) comboBoxCategoria.getSelectedItem();
					NombreYCategoria nYC=null;
					boolean enco =false;
					Iterator<NombreYCategoria> itrC = nomYCat.iterator();
					while(itrC.hasNext()&& !enco){
						nYC=itrC.next();
						enco=nYC.equals(nomSelec);
					}
					IGAñadirModificarPregunta añadirModificarPregunta = new IGAñadirModificarPregunta(pYT.getPreg(), nYC);
					añadirModificarPregunta.setTitle("Modificar Pregunta");
					añadirModificarPregunta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//HAce que no se cierre todo al salir solo esta ventana
					añadirModificarPregunta.setVisible(true);
				}
				
				/* OJO ES EL texto DE PREGUNTA*/
				//fin modificado por Egoitz
				//administrar.setModal(true);
				
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(208, 369, 89, 23);
		panel.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					//Mostrar aviso de si esta seguro de eliminar la pregunta
					
					//modificado por Egoitz
					String titSel = (String) list.getSelectedValuesList().get(0);
					if(titSel!=null){
						PreguntaYTitulo pYT=null;
						boolean enc=false;
						Iterator<PreguntaYTitulo> itr=pregYTit.iterator();
						while(itr.hasNext() && !enc){
							pYT=itr.next();
							enc=pYT.equals(titSel);
						}
						String nomSelec=(String) comboBoxCategoria.getSelectedItem();
						NombreYCategoria nYC=null;
						boolean enco =false;
						Iterator<NombreYCategoria> itrC = nomYCat.iterator();
						while(itrC.hasNext()&& !enco){
							nYC=itrC.next();
							enco=nYC.equals(nomSelec);
						}
					
						if(GestorCategorias.getGestorCategorias().comprobarSiSePuedeEliminar(nYC.getCat())){
							JOptionPane confirm = new JOptionPane();
							int rdo = confirm.showConfirmDialog(null, "¿Esta seguro que desea eliminar la pregunta?");
							if(rdo== confirm.YES_OPTION){
								GestorCategorias.getGestorCategorias().borrarPregunta(pYT.getPreg(), nYC.getCat());
							}
						}else{
							JOptionPane.showMessageDialog(null, "Solo hay una pregunta en la categoria y no se puede eliminar", "Error seleccion", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna pregunta", "Error seleccion", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		
		JButton btnEstadisticas = new JButton("Estadisticas");
		btnEstadisticas.setBounds(307, 369, 111, 23);
		panel.add(btnEstadisticas);
		btnEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Mostrar la ventana de estadisticas de la pregunta
			}
		});
		
		JLabel lblSeleccionaUnaCategoria = new JLabel("Selecciona una categoria para mostrar");
		lblSeleccionaUnaCategoria.setForeground(Color.RED);
		lblSeleccionaUnaCategoria.setBounds(169, 11, 229, 14);
		panel.add(lblSeleccionaUnaCategoria);
		
		JLabel lblLasPreguntasQue = new JLabel("las preguntas que esta contiene");
		lblLasPreguntasQue.setForeground(Color.RED);
		lblLasPreguntasQue.setBounds(169, 32, 190, 14);
		panel.add(lblLasPreguntasQue);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 85, 390, 242);
		panel.add(scrollPane);
		
		list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 13));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};//"asdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sd", "ee","sd", "ee","sd", "ee", "sdewe", "de", "e", "de", "d", "ed", "e", "e"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Categorias", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Casillas", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Jugadores", null, panel_3, null);
	}
}
