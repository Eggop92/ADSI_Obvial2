package packInterfazGrafica;

import java.awt.*;
import javax.swing.*;

public class PanelTablero extends JPanel {
	
	 private Image imagen;
	 
	 public PanelTablero() {
	 	initialize();
	 }
	 private void initialize() {
	 	setLayout(null);
	 }
	 
	 public PanelTablero(String nombreImagen) {
		 if (nombreImagen != null) {
			 imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
		 }
	 }
	     
	 public PanelTablero(Image imagenInicial) {
		 if (imagenInicial != null) {
			 imagen = imagenInicial;
		 }
	 }
	 
	 public void setImagen(String nombreImagen) {
		 if (nombreImagen != null) {
			 imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
		 } else {
			 imagen = null;
		 }
		 repaint();
	 }
	 
	 public void setImagen(Image nuevaImagen) {
		 imagen = nuevaImagen;
		 repaint();
	 }
	 
	 @Override
	 public void paint(Graphics g) {
		 if (imagen != null) {
			 g.drawImage(imagen, 0, 0, getWidth(), getHeight(),this);
			 setOpaque(false);
		 } else {
			 setOpaque(true);
		 }
		 super.paint(g);
	 }
}
