package Models;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Personagem {
	private int id;
	private int vida;
	private String nome;
	private int poderAtaque;
	private List<Fala> falas;
	private String nivel;
	private boolean jaLutou = false;
	
	public boolean jaLutou() { return jaLutou; }

	public void setJaLutou(boolean jaLutou) { this.jaLutou = jaLutou; }

	public int getId() {return id;}
	
	public List<Fala> getFalas() {return falas;}
	public void setFalas(List<Fala> falas) { this.falas = falas;}
	
	public int getVida() {return vida;}
	public void setVida(int vida) {this.vida = vida;}
	
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	
	public int getPoderAtaque() {return poderAtaque;}
	public void setPoderAtaque(int poderAtaque) {this.poderAtaque = poderAtaque;}
	
	public String getNivel() {return nivel;}
	public void setNivel(String nivel) {this.nivel = nivel;}
	
	public Personagem() {}
	public Personagem(String nome, int vida,  int poderAtaque ){
		this.nome = nome;
		this.vida = vida;
		this.poderAtaque = poderAtaque;
	}
	
	public void SofrerDano(Personagem inimigo){
		if(this.vida < inimigo.poderAtaque) {
			this.vida = 0;
			return;
		}
		this.vida -= inimigo.getPoderAtaque();
	}
	
	public static List<Personagem> RetornaInimigosQueNaoLutaram(List<Personagem> inimigos) {
		return inimigos.stream().filter(inimigo->inimigo.jaLutou == false).collect(Collectors.toList());
	}
	
	public static List<Personagem> BuscaInimigosComBaseDificuldadeGeral(RPG base){
		try{
			ConfiguracaoJogo configJogo = base.getConfiguracaoJogo();
			//RANDOMIZA os elementos na lista de inimigos
			Collections.shuffle(base.getInimigos());
			
			return base.getInimigos()
					   .stream()
					   .filter(x->x.getNivel().equals(configJogo.getDificuldadeJogo()))
					   .limit(configJogo.getQuantidadeMaximaInimigos())
					   .collect(Collectors.toList());
			
		} catch (Exception e) {
			throw e;
		}
	}
}
