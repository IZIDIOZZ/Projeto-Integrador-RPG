package Models;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//Modelo que representa as perguntas que existem no JSON
public class Pergunta {
	private int id;
	private String enunciado;
	private List<Resposta> respostas; 
	private String dificuldadePergunta;
	private boolean jaFoiFeita = false;
	
	public boolean jaFoiFeita() { return jaFoiFeita; }
	public void setJaFoiFeita(boolean jaFoiFeita) { this.jaFoiFeita = jaFoiFeita; }
	
	public int getId() { return id;}
	public void setId(int id) { this.id = id;}
	
	public String getDificuldadePergunta() {
		return dificuldadePergunta;
	}
	public void setDificuldadePergunta(String dificuldadePergunta) {
		this.dificuldadePergunta = dificuldadePergunta;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public List<Resposta> getRespostas() {
		return respostas;
	}
	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}
	
	/*Este m�todo verifica a dificuldade do jogo definida no Json e 
	pega somente as perguntas que estejam com este nivel de dificuldade*/
	public static List<Pergunta> BuscaPerguntasComBaseDificuldadeGeral(RPG base) {
		try {
			
			//Passa a configura��o geral do jogo pro objeto de configura��o
			ConfiguracaoJogo configJogo = base.getConfiguracaoJogo();
			
			//RANDOMIZA os elementos na lista de Perguntas
			Collections.shuffle(base.getPerguntas());	
			
			//LAMBDA para pegar as perguntas com base na dificuldade geral do jogo no JSON
			return base.getPerguntas()
					  .stream()
					  .filter(x->x.getDificuldadePergunta().equals(configJogo.getDificuldadeJogo()))
					  .limit(configJogo.getQuantidadeMaximaPerguntas())
					  .collect(Collectors.toList());
		} catch (Exception e) {
			throw e;
		}
	}
	
	//m�todo para retornar as perguntas que n�o foram feitas ainda
	public static List<Pergunta> RetornaPerguntasQueNaoForamFeitas(List<Pergunta>perguntas) {	
		
		//LAMBDA para pegar somente as perguntas que n�o foram feitas
		return perguntas.stream()
						.filter(pergunta->pergunta.jaFoiFeita() == false)
						.collect(Collectors.toList());
	}
}
