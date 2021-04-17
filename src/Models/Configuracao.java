package Models;

import java.util.List;

public class Configuracao {
	private List<Pergunta> perguntas;
	
	private String inimigos;
	private String capitulos;
	private String dificuldadeJogo;
	
	public String getInimigos() {
		return inimigos;
	}
	public void setInimigos(String inimigos) {
		this.inimigos = inimigos;
	}
	public String getCapitulos() {
		return capitulos;
	}
	public void setCapitulos(String capitulos) {
		this.capitulos = capitulos;
	}
	public String getDificuldadeJogo() {
		return dificuldadeJogo;
	}
	public void setDificuldadeJogo(String dificuldadeJogo) {
		this.dificuldadeJogo = dificuldadeJogo;
	}
	
	public List<Pergunta> pegaPerguntas() {
		return perguntas;
	}
	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	} 
}

