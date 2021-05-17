package Models;

/*Esse é o modelo base dos parâmetros que estão no arquivo Json,
 * este modelo é usado para transformar os dados de texto do 
 * json em um objeto para assim o manipular.
 * 
 * Esse modelo chama  diversos outros modelos 
 * que representam diferentes tipos de dados que existem no json.
 */
import java.util.List;
public class RPG {
	
	private List<Pergunta> perguntas;
	
	private List<Personagem> inimigos;
	
	private ConfiguracaoJogo configuracaoJogo;
	
	public ConfiguracaoJogo getConfiguracaoJogo() {return configuracaoJogo;}
	public void setConfiguracaoJogo(ConfiguracaoJogo configuracaoJogo) {this.configuracaoJogo = configuracaoJogo;}
	
	public List<Personagem> getInimigos() { return inimigos; }
	public void setInimigos(List<Personagem> inimigos) { this.inimigos = inimigos; }
	
	public List<Pergunta> getPerguntas() { return perguntas; }
	public void setPerguntas(List<Pergunta> perguntas) { this.perguntas = perguntas; } 
	
}


