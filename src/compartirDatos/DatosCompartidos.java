package compartirDatos;

import controlador.JuegoController;
import controlador.LoginController;
import controlador.MaxScoreController;

/*
 * CODIGO DE CLASE 1RO DAW PROGRAMACION
 */
public class DatosCompartidos {
	private JuegoController cdc1;
	private MaxScoreController cdc2;
	private LoginController cdc3;
	
	public JuegoController getCdc1() {
		return cdc1;
	}
	
	
	
	public void setCdc1(JuegoController cdc1) {
		this.cdc1 = cdc1;
	}
	public MaxScoreController getCdc2() {
		return cdc2;
	}
	public void setCdc2(MaxScoreController cdc2) {
		this.cdc2 = cdc2;
	}
	public LoginController getCdc3() {
		return cdc3;
	}
	public void setCdc3(LoginController cdc3) {
		this.cdc3 = cdc3;
	}
	
	
}
