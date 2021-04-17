package Models;

import java.util.List;

public class Personagem {
	private int vida;
	private String nome;
	private int poderAtaque;
	private List<Fala> falas;
	private String nivel;
	
	public void setFalas(List<Fala> falas) {
		this.falas = falas;
	}
	public int getVida() {
		return vida;
	}
	
	public void setVida(int vida) {
		this.vida = vida;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getPoderAtaque() {
		return poderAtaque;
	}
	public void setPoderAtaque(int poderAtaque) {
		this.poderAtaque = poderAtaque;
	}
	public String getNivel() {
		return nivel;
	}
	
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
}
