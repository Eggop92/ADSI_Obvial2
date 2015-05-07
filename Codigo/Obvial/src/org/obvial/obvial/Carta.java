package org.obvial.obvial;
//HPaz Prueba Y mas Pruebas
//Laura aldatuta push probatzeko
//janirek aldatu du
public class Carta {
 /**modificiada en la ramaonline**/
	private int num;
	private String pregunta;
	private String respCorrecta;
	private boolean usada;
	private String resp1;
	private String resp2;
	private String resp3;
	private String resp4;
//nik aldatu dut Janire push egteko
//ostabere	
	public Carta(int pNum, String pPregunta, String pRespCorrecta, String pResp1, String pResp2, String pResp3, String pResp4){
		num=pNum;
		pregunta=pPregunta;
		respCorrecta= pRespCorrecta;
		usada=false;
		resp1=pResp1;
		resp2=pResp2;
		resp3=pResp3;
		resp4=pResp4;
	}
	
	public String getPregunta() {
		return pregunta;
	}

	public String getResp1() {
		return resp1;
	}

	public String getResp2() {
		return resp2;
	}

	public String getResp3() {
		return resp3;
	}

	public String getResp4() {
		return resp4;
	}

	public boolean esCorrecta(String pResp){
		return respCorrecta.equals(pResp);
	}
	
	public int getNum(){
		return num;
	}
	
	public void usarCarta(){
		usada=true;
	}
	
	public boolean haSidoUsada(){
		return usada;
	}
	
	public boolean esEsaCarta(int pNum){
		return this.num == pNum; 
	}
	
	public void reiniciarCarta(){
		this.usada=false;
	}
	
}
