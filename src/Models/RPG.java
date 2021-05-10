package Models;

import java.util.List;

public class RPG {
	private List<Pergunta> perguntas;
	private List<Capitulo> capitulos;
	private List<Personagem> inimigos;
	private ConfiguracaoJogo configuracaoJogo;
	
	public ConfiguracaoJogo getConfiguracaoJogo() {return configuracaoJogo;}
	public void setConfiguracaoJogo(ConfiguracaoJogo configuracaoJogo) {this.configuracaoJogo = configuracaoJogo;}
	
	public List<Personagem> getInimigos() { return inimigos; }
	public void setInimigos(List<Personagem> inimigos) { this.inimigos = inimigos; }
	
	public List<Capitulo> getCapitulos() { return capitulos; }
	public void setCapitulos(List<Capitulo> capitulos) { this.capitulos = capitulos; }
	
	public List<Pergunta> getPerguntas() { return perguntas; }
	public void setPerguntas(List<Pergunta> perguntas) { this.perguntas = perguntas; } 
	
}


