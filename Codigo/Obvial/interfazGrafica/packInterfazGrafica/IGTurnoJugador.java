//Clase Modificada por Jon Ander Basandose en la original

package packInterfazGrafica;

import java.awt.*;
import javax.swing.*;

import org.obvial.obvial.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
/**
 * Clase modificada Basada en la interface Original, Se ha modificado la forma de visualizar las opciones del turno del jugadors ya no sale en la ventana
 * la parte central de la ventana de juego
 * 
 * @author Jon Ander
 *
 */
public class IGTurnoJugador extends JDialog{
	private JPanel centro;
	private JButton btnTirada;
	private JButton btnPasar;
	private JButton btnRetencion;
	private JButton btnGuardar;
	private JButton btnFinalizar;
	private JPanel norte;
	private JPanel sur;
	private JPanel oeste;
	private JPanel este;
	private static Jugador jugActual;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGTurnoJugador dialog = new IGTurnoJugador(jugActual);
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
	public IGTurnoJugador(Jugador pJugador) {
		setTitle(pJugador.getUsuario().getNombre());
		jugActual = pJugador;
		initialize();
	}
	private void initialize() {
		
		setResizable(false);
		setUndecorated(true);
		setBounds(910, 60, 350, 446);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getCentro(), BorderLayout.CENTER);
		getContentPane().add(getNorte(), BorderLayout.NORTH);
		getContentPane().add(getSur(), BorderLayout.SOUTH);
		getContentPane().add(getOeste(), BorderLayout.WEST);
		getContentPane().add(getEste(), BorderLayout.EAST);
		
		//Centrar en la pantalla
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		Dimension windowSize = this.getSize();
//		if (windowSize.height>screenSize.height) {
//		windowSize.height= screenSize.height;
//		}
//		if (windowSize.width>screenSize.width){
//		windowSize.width= screenSize.width;
//		}
//		setLocation((screenSize.width-windowSize.width)/2, 
//		(screenSize.height-windowSize.height)/2);
	}
	
	
	private JPanel getCentro() {
		if (centro == null) {
			centro = new JPanel();
			centro.setLayout(null);
			centro.add(getBtnTirada());
			centro.add(getBtnPasar());
			centro.add(getBtnRetencion());
			centro.add(getBtnGuardar());
			centro.add(getBtnFinalizar());
		}
		return centro;
	}
	private JButton getBtnTirada() {
		if (btnTirada == null) {
			btnTirada = new JButton("Tirar Dado");
			btnTirada.setBounds(60, 70, 200, 50);
			
			//Cuando el jugador este retenido y no este en la posicion 63, el boton estara deshabilitado 
			if(jugActual.getRetencion()>0 && jugActual.getPosicion().getNumero() != 63){
				btnTirada.setEnabled(false);
			}else{
				
				//Como en la ronda final estara retenido y en la posicion 63, iremos modificando el texto del boton,
				//ya que no tiene que tirar el dado
				if(jugActual.getRetencion()>0 && jugActual.getPosicion().getNumero() == 63){
					
					if(jugActual.getRetencion()== 2){
						btnTirada.setText("Intento 2");
					}else{
						btnTirada.setText("Intento 3");
					}
					
				}
				
			}
			
			btnTirada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(jugActual.getPosicion().getNumero() == 63){
						setVisible(false);
						rondaFinal();
					}else{
						IGDado dado = new IGDado();
						dado.setModal(true);
						dado.setVisible(true);
						jugActual.avanzar(dado.getTirada());
						//setVisible(false);
						jugar();
					}
				}
			});
		}
		return btnTirada;
	}
	private JButton getBtnPasar() {
		if (btnPasar == null) {
			btnPasar = new JButton("Pasar Turno");
			btnPasar.setBounds(60, 140, 200, 50);
			if(btnTirada.isEnabled()){
				btnPasar.setEnabled(false);
			}else{
				btnPasar.setEnabled(true);
				btnPasar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						jugActual.actualizarRet();
						dispose();
					}
				});
			}
		}
		return btnPasar;
	}
	private JButton getBtnRetencion() {
		if (btnRetencion == null) {
			btnRetencion = new JButton("Turnos Pendientes");
			btnRetencion.setBounds(60, 210, 200, 50);
			if(btnTirada.isEnabled()){
				btnRetencion.setEnabled(false);
			}else{
				btnRetencion.setEnabled(true);
				btnRetencion.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(jugActual.getRetencion() == 1){
							JOptionPane.showMessageDialog(null, "Te queda "+jugActual.getRetencion()+" turno de retención", jugActual.getUsuario().getNombre(), JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(null, "Te quedan "+jugActual.getRetencion()+" turnos de retención", jugActual.getUsuario().getNombre(), JOptionPane.INFORMATION_MESSAGE);
						}
						
					}
				});
			}
		}
		return btnRetencion;
	}
	private JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton("Guardar Partida");
			btnGuardar.setBounds(60, 280, 200, 50);
		}
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Modificado Helen
				try{
					GestorPartida.getGestorPartidas().guardarPartidaActual(ListaJugadores.getListaJugadores().getListaJ());
					JOptionPane.showMessageDialog(null, "La partida se ha guardado corectamente.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "La partida no ha podido guardarse.", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				//Fin de modificacion Helen
			}
		});
		return btnGuardar;
	}
	
	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Acabar Juego");
			btnFinalizar.setBounds(60, 350, 200, 50);
		}
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jugActual.ganar();
				dispose();
			}
		});
		return btnFinalizar;
	}
	
	
	private JPanel getNorte() {
		if (norte == null) {
			norte = new JPanel();
			FlowLayout flowLayout = (FlowLayout) norte.getLayout();
		}
		return norte;
	}
	private JPanel getSur() {
		if (sur == null) {
			sur = new JPanel();
			FlowLayout flowLayout = (FlowLayout) sur.getLayout();
		}
		return sur;
	}
	private JPanel getOeste() {
		if (oeste == null) {
			oeste = new JPanel();
			FlowLayout flowLayout = (FlowLayout) oeste.getLayout();
		}
		return oeste;
	}
	private JPanel getEste() {
		if (este == null) {
			este = new JPanel();
			FlowLayout flowLayout = (FlowLayout) este.getLayout();
		}
		return este;
	}
	
	
	private void jugar(){
			
		int posicion = jugActual.getPosicion().getNumero();
		int tipoCasilla = ListaCasillas.getListaCasillas().getTipoCasilla(posicion);
				
		switch(tipoCasilla){
			
		//Casilla de pregunta
		case 1: //Obtenemos el tema de la pregunta
				int tema = ListaCasillas.getListaCasillas().getTema();
						
				//Buscamos la carta de ese tema
				//Carta unaCarta = Listas.getMisListas().buscarCartaTema(tema);
				CasillaPregunta casilla = (CasillaPregunta) jugActual.getPosicion();
				IGPregunta pregunta = new IGPregunta(casilla.getCategoria(),jugActual.getUsuario());
				pregunta.setModal(true);
				pregunta.setVisible(true);				
					pregunta.dispose();
					//dispose();		
				break;
						
		//Casilla de retencion
		case 2: //Buscamos la casilla para saber los turnos de retencion
				CasillaRetencion casillaRetencion = ListaCasillas.getListaCasillas().buscarCasillaRetencion(posicion);
					
				if(jugActual.getPosicion().getNumero() != 63){
					JOptionPane.showMessageDialog(null, "La retención será de "+casillaRetencion.getTurnosRetencion()+" turno/s", "Retención", JOptionPane.INFORMATION_MESSAGE);
				}
		
				//Aplicamos al jugador los turnos de retencion
				jugActual.retener(casillaRetencion.getTurnosRetencion());
					
				if(jugActual.getPosicion().getNumero() == 63){
					JOptionPane.showMessageDialog(null, "¡¡¡Enhorabuena!!! Has llegado al final, ahora solo te queda contestar 5 preguntas seguidas bien para ganar.", "Final", JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(null, "Primera oportunidad", "Final", JOptionPane.INFORMATION_MESSAGE);
					rondaFinal();
				}else{
					dispose();
				}
					
				break;
						
		//Casilla de traslado
		case 3:	//Buscamos la casilla para saber a que casilla va a ser trasladado el jugador
				CasillaTraslado casillaTraslado = ListaCasillas.getListaCasillas().buscarCasillaTraslado(posicion);
					
				JOptionPane.showMessageDialog(null, jugActual.getUsuario().getNombre()+" saltará a la casilla "+casillaTraslado.getCasillaDesp(), "Traslado", JOptionPane.INFORMATION_MESSAGE);
					
				//Movemos al jugador de casilla
				jugActual.moverACasilla(casillaTraslado.getCasillaDesp());
						
				//Al ser la casilla 58 la muerte, nos lleva a la casilla 1 y se nos acaba el turno
				if(casillaTraslado.getNumero() == 58){
					dispose();
				}else{
					setVisible(true);
				}
				break;
		}
	}
	private void rondaFinal(){
		
		Boolean fallo = false;
		
		//Al llegar a la ultima casilla se le hace al jugador una pregunta
		//de cada tema. Si falla alguna, pasa el turno al siguiente jugador.
		//Si en 3 turnos no acierta todas, vuelve a empezar
		if(jugActual.getRetencion() <= 3){
			jugActual.actualizarRet();
		}
	
		if(jugActual.getRetencion() >= 0){
			
			//Hacer preguntas, una de cada tipo
			//Si falla alguna nos salimos
			int cont = 0;
			int aciertos = 0;
			while(fallo == false && aciertos < 5){
				
				Carta unaCarta = Listas.getMisListas().buscarCartaTema(cont);
					
				while(unaCarta ==  null){
					unaCarta = Listas.getMisListas().buscarCartaTema(cont);
					if(unaCarta == null){
						Listas.getMisListas().reiniciarListas();
					}
				}
				
				//Mostramos la pregunta
				CasillaPregunta casilla = (CasillaPregunta) jugActual.getPosicion();
				IGPregunta pregunta = new IGPregunta(casilla.getCategoria(),jugActual.getUsuario());
				pregunta.setModal(true);
				pregunta.setVisible(true);
					
					pregunta.dispose();
					
					//Si falla, debemos salir del bucle
				
				cont++;
					
			}
		}
		
		if(!fallo){
			jugActual.ganar();
			JOptionPane.showMessageDialog(null,"¡¡¡Enhorabuena "+jugActual.getUsuario().getNombre()+" , has ganado!!!", jugActual.getUsuario().getNombre(), JOptionPane.INFORMATION_MESSAGE);
			IGPrincipal.enEjecucion = false;				
		}else{
			if(jugActual.getRetencion() == 0){
				JOptionPane.showMessageDialog(null,"Lo siento "+jugActual.getUsuario().getNombre()+"... Se te han acabado los intentos, vuelves a empezar.", jugActual.getUsuario().getNombre(), JOptionPane.ERROR_MESSAGE);
				//El jugador vuelve a empezar
				jugActual.moverACasilla(1);
			}else{
				if(jugActual.getRetencion() == 1){
					JOptionPane.showMessageDialog(null,"Te queda "+jugActual.getRetencion()+" oportunidad.", jugActual.getUsuario().getNombre(), JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null,"Te quedan "+jugActual.getRetencion()+" oportunidades.", jugActual.getUsuario().getNombre(), JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		dispose();
		
	}
}
