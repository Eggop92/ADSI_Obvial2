package packInterfazGrafica;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.obvial.obvial.Dado;
import org.obvial.obvial.Jugador;

import java.awt.event.*;

public class IGDado extends JDialog {

	private JPanel contentPane;
	private JLabel dado;
	private JButton btnTiraElDado;
	private int tirada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGDado frame = new IGDado();
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
	public IGDado() {
		initialize();
	}
	private void initialize() {
		setForeground(Color.WHITE);
		setTitle("Tirada");
		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 180, 180);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtnTiraElDado());
		
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

	private JLabel getLblNewLabel() {
		if (dado == null) {
			dado = new JLabel("");
			dado.setHorizontalAlignment(SwingConstants.CENTER);
			dado.setIcon(new ImageIcon(getClass().getResource("imagenes/dado/dados.gif")));
			dado.setBounds(0, 10, 174, 100);
			dado.setVisible(true);
		}
		return dado;
	}
	
	
	// ESTE MÉTODO ES PARA QUE CUANDO PULSES EL BOTON SALGA LA TIRADA DEL DADO.
	private JButton getBtnTiraElDado() {
		if (btnTiraElDado == null) {
			btnTiraElDado = new JButton("TIRA EL DADO");
			btnTiraElDado.setFont(new Font("Terminator Two", Font.BOLD, 10));
			btnTiraElDado.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					
					if (btnTiraElDado.isEnabled()){
						
					final int num=Dado.getDado().getTirada();
					switch (num){
					case 1:
						ImageIcon i =new ImageIcon(getClass().getResource("imagenes/dado/dados1.jpg"));
						dado.setIcon(i);
						break;
					case 2:
						ImageIcon i2 =new ImageIcon(getClass().getResource("imagenes/dado/dados2.jpg"));
						dado.setIcon(i2);
						break;
					case 3:
						ImageIcon i3 =new ImageIcon(getClass().getResource("imagenes/dado/dados3.jpg"));
						dado.setIcon(i3);
						break;
					case 4:
						ImageIcon i4 =new ImageIcon(getClass().getResource("imagenes/dado/dados4.jpg"));
						dado.setIcon(i4);
						break;
					case 5:
						ImageIcon i5 =new ImageIcon(getClass().getResource("imagenes/dado/dados5.jpg"));
						dado.setIcon(i5);
						break;
					case 6:
						ImageIcon i6 =new ImageIcon(getClass().getResource("imagenes/dado/dados6.jpg"));
						dado.setIcon(i6);
						break;
					}
					btnTiraElDado.setEnabled(false);
					tirada = num;
					
//					Espera 2 segundos antes de cerrar la ventana
					ActionListener temporizador = new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					};
					new Timer(2000, temporizador).start();
					
					}
				}
			});

			btnTiraElDado.setBounds(17, 110, 140, 23);
		}
		return btnTiraElDado;
	}
	
	public int getTirada(){
		return tirada;
	}
}
