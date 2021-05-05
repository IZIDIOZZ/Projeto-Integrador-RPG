package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
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
	
	public Personagem(String nome, int vida,  int poderAtaque ){
		this.nome = nome;
		this.vida = vida;
		this.poderAtaque = poderAtaque;
	}
	
	public void SofrerDano(Personagem inimigo){
		this.vida -= inimigo.getPoderAtaque();
	}
	
	public void Batalhar(List<Personagem> inimigos, List<Pergunta>perguntas) {		
		inimigos = RetornaInimigosQueNaoLutaram(inimigos);
		perguntas = RetornaPerguntasQueNaoForamFeitas(perguntas);
		
		if(perguntas.isEmpty() || inimigos.isEmpty()) return;
		
		Personagem inimigo = inimigos.get(0);
		System.out.println("perguntas - "+perguntas.size());
		System.out.println("inimigos - "+inimigos.size());
		
		for(Pergunta pergunta: perguntas) {
			
				if(inimigo.getVida() <=0 || this.getVida() <=0) break;
				
				Resposta respostaCorreta = new Resposta();
				System.out.println(pergunta.getEnunciado());
				
				for(Resposta resp : pergunta.getRespostas()){
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
				
				pergunta.setJaFoiFeita(true);	
		}
		inimigo.setJaLutou(true);
	}
	
	public List<Personagem> RetornaInimigosQueNaoLutaram(List<Personagem> inimigos) {
		return inimigos.stream().filter(inimigo->inimigo.jaLutou == false).collect(Collectors.toList());
	}
	
	public List<Pergunta> RetornaPerguntasQueNaoForamFeitas(List<Pergunta>perguntas) {	
		return perguntas.stream().filter(pergunta->pergunta.jaFoiFeita() == false).collect(Collectors.toList());
	}
	public static List<Personagem> BuscaInimigosComBaseDificuldadeGeral(RPG base){

		//o código resumido em uma linha 
		List<Personagem> inimigos = new ArrayList<Personagem>();
		ConfiguracaoJogo configJogo = base.getConfiguracaoJogo();
		int contadorInimigos = 0;
		
		//RANDOMIZA os elementos na lista de inimigos
		Collections.shuffle(inimigos);
		
		//Percorre o as perguntas para verificar a propriedade de dificuldade delas
		for(Personagem inim: base.getInimigos()) {
			if(contadorInimigos > configJogo.getQuantidadeMaximaInimigos()) break;
			
			//verifica se a dificuldade do inimigo é igual a dificuldade geral do arquivo Json
			if(inim.getNivel().equals(configJogo.getDificuldadeJogo())) {
				inimigos.add(inim);
			}
			contadorInimigos++;
		}
		
		return inimigos;
	}
}
