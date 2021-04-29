package Models;

import java.util.List;

public class Configuracao {
	private List<Pergunta> perguntas;
	private List<Capitulo> capitulos;
	private List<Personagem> inimigos;
	private String dificuldadeJogo;
	
	public List<Personagem> getInimigos() { return inimigos; }
	public void setInimigos(List<Personagem> inimigos) { this.inimigos = inimigos; }
	
	public List<Capitulo> getCapitulos() { return capitulos; }
	public void setCapitulos(List<Capitulo> capitulos) { this.capitulos = capitulos; }
	
	public List<Pergunta> getPerguntas() { return perguntas; }
	public void setPerguntas(List<Pergunta> perguntas) { this.perguntas = perguntas; } 
	
	public String getDificuldadeJogo() { return dificuldadeJogo; }
	public void setDificuldadeJogo(String dificuldadeJogo) { this.dificuldadeJogo = dificuldadeJogo; }
	
}

