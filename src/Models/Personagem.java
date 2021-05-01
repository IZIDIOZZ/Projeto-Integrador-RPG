package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Personagem {
	private int id;
	private int vida;
	private String nome;
	private int poderAtaque;
	private List<Fala> falas;
	private String nivel;
	
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
	
	public Personagem(String nome, int vida,  int poderAtaque ){
		this.nome = nome;
		this.vida=vida;
		this.poderAtaque = poderAtaque;
	}
	
	public void SofrerDano(Personagem inimigo){
		this.vida -= inimigo.getPoderAtaque();
	}
	
	public void Batalhar(Personagem inimigo, List<Pergunta>perguntas) {		
		int i = perguntas.size();
		for(Pergunta perg: perguntas) {
				if(inimigo.getVida() <=0 || this.getVida() <=0 || i == 0) break;
				Resposta respostaCorreta = new Resposta();
				System.out.println(perg.getEnunciado());
				
				for(Resposta resp : perg.getRespostas()){
					System.out.println(resp.getAlternativa()+") "+resp.getTextoResposta());
					if(resp.isRespostaCorreta()) {
						respostaCorreta = resp;
					}				
				}
				
				if(respostaCorreta.RespostaCorreta(new Scanner(System.in).next(),respostaCorreta)){
					inimigo.SofrerDano(this);
				}else {
					this.SofrerDano(inimigo);
				}
				System.out.println("vida do jogador - "+this.getVida());
				System.out.println("vida do inimigo - "+inimigo.getVida());
				i--;
		}
	}
	
	public static List<Personagem> BuscaInimigosComBaseDificuldadeGeral(RPG base){

		//o código resumido em uma linha 
		List<Personagem> inimigos = new ArrayList<Personagem>();
		ConfiguracaoJogo configJogo = base.getConfiguracaoJogo();
		int contadorInimigos = 0;
		//Percorre o as perguntas para verificar a propriedade de dificuldade delas
		for(Personagem inim: base.getInimigos()) {
			//verifica se a dificuldade da pergutna é igual a dificuldade geral do arquivo Json
			if(inim.getNivel().equals(configJogo.getDificuldadeJogo()) 
				&& contadorInimigos <= configJogo.getQuantidadeMaximaInimigos()) {
				inimigos.add(inim);
			}
			contadorInimigos++;
		}
		//RANDOMIZA os elementos na lista de inimigos
		Collections.shuffle(inimigos);
		return inimigos;
	}
}
