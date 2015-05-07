package org.obvial.obvial;

import java.util.Date;


public class Respuesta {//Sara
	private boolean acertada;
	private Usuario usuario;
	private Pregunta pregunta;
	private Date fecha;

	public Respuesta(boolean pAcertada, Usuario pUsuario, Pregunta pPregunta, Date pFecha) {
		acertada=pAcertada;
		usuario=pUsuario;
		pregunta=pPregunta;
		fecha=pFecha;
	}
}
