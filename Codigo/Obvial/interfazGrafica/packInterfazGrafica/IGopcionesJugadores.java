package packInterfazGrafica;

import javax.swing.*;

import org.obvial.obvial.ListaCasillas;
import org.obvial.obvial.ListaJugadores;
import java.awt.*;
import java.awt.event.*;

public class IGopcionesJugadores extends JDialog {
	private JPanel panel;
	private JCheckBox elegirJug3;
	private JCheckBox elegirJug4;
	private JLabel Jugador2;
	private JLabel Jugador1;
	private JTextField nombreJug1;
	private JTextField nombreJug2;
	private JTextField nombreJug3;
	private JTextField nombreJug4;
	private Choice colorJug1;
	private Choice colorJug3;
	private Choice colorJug4;
	private Choice colorJug2;
	private JPanel panel_1;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JPanel panel_2;
	//Lo usaremos en el metodo modificarChoice
	private String[] opcionesChoice = {"Rojo", "Verde", "Amarillo", "Azul"};
	//En el almacenaremos las selecciones que hayan realizado los jugadores
	private String[] seleccionJugadores = {"","","",""};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGopcionesJugadores dialog = new IGopcionesJugadores();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the dialog.
	 */
	public IGopcionesJugadores() {
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		getContentPane().add(getPanel_1(), BorderLayout.SOUTH);
		getContentPane().add(getPanel_2(), BorderLayout.NORTH);
	}

	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(4, 3, 15, 25));
			panel.add(getJugador1());
			panel.add(getNombreJug1());
			panel.add(getColorJug1());
			panel.add(getJugador2());
			panel.add(getNombreJug2());
			panel.add(getColorJug2());
			panel.add(getElegirJug3());
			panel.add(getNombreJug3());
			panel.add(getColorJug3());
			panel.add(getElegirJug4());
			panel.add(getNombreJug4());
			panel.add(getColorJug4());
		}
		return panel;
	}
	private JLabel getJugador1() {
		if (Jugador1 == null) {
			Jugador1 = new JLabel("Jugador 1");
			Jugador1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return Jugador1;
	}
	private JLabel getJugador2() {
		if (Jugador2 == null) {
			Jugador2 = new JLabel("Jugador 2");
			Jugador2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return Jugador2;
	}
	private JCheckBox getElegirJug3() {
		if (elegirJug3 == null) {
			elegirJug3 = new JCheckBox("Jugador 3");
			
			//Si seleccionamos el checkbox, habilitamos los componentes del jugador 3 y
			//habilitamos el checkbox del jugador 4, ya que si no hay un jugador 3 no tiene que
			//haber un jugador 4.
			elegirJug3.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evento) {
					if(evento.getStateChange() == 1){
						nombreJug3.setEnabled(true);
						nombreJug3.requestFocus();
						colorJug3.setEnabled(true);
						elegirJug4.setEnabled(true);
					}else{
						resetearJug3();
						elegirJug4.setEnabled(false);
						resetearJug4();
					}
				}
			});
			elegirJug3.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return elegirJug3;
	}
	private JCheckBox getElegirJug4() {
		if (elegirJug4 == null) {
			elegirJug4 = new JCheckBox("Jugador 4");
			elegirJug4.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evento) {
					if(evento.getStateChange() == 1){
						nombreJug4.setEnabled(true);
						nombreJug4.requestFocus();
						colorJug4.setEnabled(true);
					}else{
						resetearJug4();
					}
				}
			});
			elegirJug4.setEnabled(false);
			elegirJug4.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return elegirJug4;
	}
	/**
	 * Devuelve los valores a su estado inicial
	 */	
	private void resetearJug3(){
		nombreJug3.setEnabled(false);
		nombreJug3.setText("Jugador 3");
		colorJug3.setEnabled(false);
		seleccionJugadores[2] = "";
		modificarChoice(colorJug1, seleccionJugadores[2], 0);
		modificarChoice(colorJug2, seleccionJugadores[2], 1);
	}
	private void resetearJug4(){
		nombreJug4.setEnabled(false);
		nombreJug4.setText("Jugador 4");
		colorJug4.setEnabled(false);
		seleccionJugadores[3] = "";
		modificarChoice(colorJug1, seleccionJugadores[3], 0);
		modificarChoice(colorJug2, seleccionJugadores[3], 1);
		modificarChoice(colorJug3, seleccionJugadores[3], 2);
	}
	
	
	private JTextField getNombreJug1() {
		if (nombreJug1 == null) {
			nombreJug1 = new JTextField();
			nombreJug1.setColumns(10);
			nombreJug1.setText("Jugador 1");
		}
		return nombreJug1;
	}
	private JTextField getNombreJug2() {
		if (nombreJug2 == null) {
			nombreJug2 = new JTextField();
			nombreJug2.setColumns(10);
			nombreJug2.setText("Jugador 2");
		}
		return nombreJug2;
	}
	private JTextField getNombreJug3() {
		if (nombreJug3 == null) {
			nombreJug3 = new JTextField();
			nombreJug3.setColumns(10);
			nombreJug3.setText("Jugador 3");
			nombreJug3.setEnabled(false);
		}
		return nombreJug3;
	}
	private JTextField getNombreJug4() {
		if (nombreJug4 == null) {
			nombreJug4 = new JTextField();
			nombreJug4.setColumns(10);
			nombreJug4.setText("Jugador 4");
			nombreJug4.setEnabled(false);
		}
		return nombreJug4;
	}
	
	
	private Choice getColorJug1() {
		if (colorJug1 == null) {
			colorJug1 = new Choice();
			colorJug1.addItem("Rojo");
			colorJug1.addItem("Verde");
			colorJug1.addItem("Amarillo");
			colorJug1.addItem("Azul");
			colorJug1.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evento) {
						int op = colorJug1.getSelectedIndex();
						String color = colorJug1.getItem(op);
						seleccionJugadores[0] = color;
						modificarChoice(colorJug2, color, 1);
						modificarChoice(colorJug3, color, 2);
						modificarChoice(colorJug4, color, 3);
				}
			});	
		}
		return colorJug1;
	}
	private Choice getColorJug2() {
		if (colorJug2 == null) {
			colorJug2 = new Choice();
			colorJug2.addItem("Rojo");
			colorJug2.addItem("Verde");
			colorJug2.addItem("Amarillo");
			colorJug2.addItem("Azul");
			colorJug2.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evento) {
						int op = colorJug2.getSelectedIndex();
						String color = colorJug2.getItem(op);
						seleccionJugadores[1] = color;
						modificarChoice(colorJug1, color, 0);
						modificarChoice(colorJug3, color, 2);
						modificarChoice(colorJug4, color, 3);
				}
			});
		}
		return colorJug2;
	}
	private Choice getColorJug3() {
		if (colorJug3 == null) {
			colorJug3 = new Choice();
			colorJug3.addItem("Rojo");
			colorJug3.addItem("Verde");
			colorJug3.addItem("Amarillo");
			colorJug3.addItem("Azul");
			colorJug3.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evento) {
						int op = colorJug3.getSelectedIndex();
						String color = colorJug3.getItem(op);
						seleccionJugadores[2] = color;
						modificarChoice(colorJug1, color, 0);
						modificarChoice(colorJug2, color, 1);
						modificarChoice(colorJug4, color, 3);
				}
			});
			colorJug3.setEnabled(false);
		}
		return colorJug3;
	}
	private Choice getColorJug4() {
		if (colorJug4 == null) {
			colorJug4 = new Choice();
			colorJug4.addItem("Rojo");
			colorJug4.addItem("Verde");
			colorJug4.addItem("Amarillo");
			colorJug4.addItem("Azul");
			colorJug4.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evento) {
						int op = colorJug4.getSelectedIndex();
						String color = colorJug4.getItem(op);
						seleccionJugadores[3] = color;
						modificarChoice(colorJug1, color, 0);
						modificarChoice(colorJug2, color, 1);
						modificarChoice(colorJug3, color, 2);
				}
			});
			colorJug4.setEnabled(false);
		}
		return colorJug4;
	}
	
	
	private void modificarChoice(Choice pColorJug, String pColor, int pNumJug){
		int cont = 0;
		
		//Eliminamos todos los opciones del choice
		pColorJug.removeAll();
		
		//Lo volvemos a rellenar con todos los colores menos el pasado por parametro en pColor,
		//que es el que acaba de ser seleccionado.
		while(cont < opcionesChoice.length){
			if(!opcionesChoice[cont].equals(pColor)){
				pColorJug.addItem(opcionesChoice[cont]);
			}
			cont++;
		}
		
		cont = 0;
		
		//Como lo hemos rellenado con todos menos con el que acaba de ser seleccionado, debemos eliminar
		//los colores que se han seleccionado en otras ocasiones.
		while(cont < seleccionJugadores.length){
			if(!seleccionJugadores[cont].isEmpty()){
				if(this.esta(pColorJug, seleccionJugadores[cont]) && cont != pNumJug){
					pColorJug.remove(seleccionJugadores[cont]);
				}
			}
			cont++;
		}
		
		//Como lo hemos reiniciado, tenemos que dejar marcada la seleccion que habia hecho el jugador
		if(!seleccionJugadores[pNumJug].isEmpty()){
			pColorJug.select(seleccionJugadores[pNumJug]);
		}
	}
	/**
	 * Comprueba si el color esta en el choice
	 */
	private boolean esta(Choice pColorJug, String pColor){
		boolean res = false;
		int cont = 0;
		while(res == false && cont < pColorJug.getItemCount()){
			if(pColorJug.getItem(cont).equals(pColor)){
				res = true;
			}
			cont++;
		}
		return res;
	}

	
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getBtnAceptar());
			panel_1.add(getBtnCancelar());
		}
		return panel_1;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					ListaJugadores.getListaJugadores().vaciarLista();
					//ListaCasillas.getListaCasillas().reiniciarCasillas();
					//recogerJugadores();
				}
			});
		}
		return btnAceptar;
	}
	private void recogerJugadores(){
//		//Si jugador 3 y jugador 4 no estan seleccionados
//		if(!elegirJug3.isSelected() && !elegirJug4.isSelected()){
//			
//			//Comprobamos que hayan seleccionado un color
//			if(!seleccionJugadores[0].isEmpty() && !seleccionJugadores[1].isEmpty()){
//				
//				//Comprobamos que tengan nombre
//				if(!nombreJug1.getText().isEmpty() && !nombreJug2.getText().isEmpty()){
//					ListaJugadores.getListaJugadores().anadirJugador(nombreJug1.getText(), seleccionJugadores[0]);
//					ListaJugadores.getListaJugadores().anadirJugador(nombreJug2.getText(), seleccionJugadores[1]);
//					dispose();
//				}else{
//					
//					//Si no tienen nombre, se avisa
//					JOptionPane.showMessageDialog(null, "Ha algún jugador le falta el nombre");
//					
//				}
//			}else{
//				
//				//Si no tienen color, se avisa
//				JOptionPane.showMessageDialog(null, "Ha algún jugador le falta elegir el color de la ficha");
//				
//			}
//		}else{
//			
//			//Si el jugador 3 esta seleccionado, pero el 4 no
//			if(elegirJug3.isSelected() && !elegirJug4.isSelected()){
//				
//				//Comprobamos que hayan seleccionado un color
//				if(!seleccionJugadores[0].isEmpty() && !seleccionJugadores[1].isEmpty() && !seleccionJugadores[2].isEmpty()){
//					
//					//Comprobamos que tengan nombre
//					if(!nombreJug1.getText().equals(null) && !nombreJug2.getText().equals(null) && !nombreJug3.getText().equals(null)){
//						ListaJugadores.getListaJugadores().anadirJugador(nombreJug1.getText(), seleccionJugadores[0]);
//						ListaJugadores.getListaJugadores().anadirJugador(nombreJug2.getText(), seleccionJugadores[1]);
//						ListaJugadores.getListaJugadores().anadirJugador(nombreJug3.getText(), seleccionJugadores[2]);
//						dispose();
//					}else{
//						
//						//Si no tienen nombre, se avisa
//						JOptionPane.showMessageDialog(null, "Ha algún jugador le falta el nombre");
//						
//					}
//				}else{
//					
//					//Si no tienen color, se avisa
//					JOptionPane.showMessageDialog(null, "Ha algún jugador le falta elegir el color de la ficha");
//					
//				}
//			}else{
//				
//				//Si todo estan seleccionados, comprobamos que hayan seleccionado un color
//				if(!seleccionJugadores[0].isEmpty() && !seleccionJugadores[1].isEmpty() && !seleccionJugadores[2].isEmpty() && !seleccionJugadores[3].isEmpty()){
//					
//					//Comprobamos que hayan seleccionado un color
//					if(!nombreJug1.getText().equals(null) && !nombreJug2.getText().equals(null) && !nombreJug3.getText().equals(null) && !nombreJug4.getText().equals(null)){
//						ListaJugadores.getListaJugadores().anadirJugador(nombreJug1.getText(), seleccionJugadores[0]);
//						ListaJugadores.getListaJugadores().anadirJugador(nombreJug2.getText(), seleccionJugadores[1]);
//						ListaJugadores.getListaJugadores().anadirJugador(nombreJug3.getText(), seleccionJugadores[2]);
//						ListaJugadores.getListaJugadores().anadirJugador(nombreJug4.getText(), seleccionJugadores[3]);
//						dispose();
//					}else{
//						
//						//Si no tienen nombre, se avisa
//						JOptionPane.showMessageDialog(null, "Ha algún jugador le falta el nombre");
//						
//					}
//				}else{
//					
//					//Si no tienen color, se avisa
//					JOptionPane.showMessageDialog(null, "Ha algún jugador le falta elegir el color de la ficha");
//					
//				}
//			}
//		}
	}
	
	
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}

	
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			flowLayout.setVgap(15);
		}
		return panel_2;
	}
}
