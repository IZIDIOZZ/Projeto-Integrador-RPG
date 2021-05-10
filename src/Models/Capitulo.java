package Models;

public class Capitulo {
	private int numero;	
	private String texto ;
	private boolean capituloFinal;
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public boolean isCapituloFinal() {
		return capituloFinal;
	}
	public void setCapituloFinal(boolean capituloFinal) {
		this.capituloFinal = capituloFinal;
	}
}
