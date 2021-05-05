package Models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
			//o c�digo resumido em uma linha 
			List<Pergunta> perguntasRetorno = new ArrayList<Pergunta>();
			ConfiguracaoJogo configJogo = base.getConfiguracaoJogo();
			int contaPerguntas = 0;
			
			//RANDOMIZA os elementos na lista de Perguntas
			Collections.shuffle(perguntasRetorno);	
			
			//Percorre o as perguntas para verificar a propriedade de dificuldade delas
			for(Pergunta perg: base.getPerguntas()) {
				if(contaPerguntas > configJogo.getQuantidadeMaximaPerguntas()) break;
				
				//verifica se a dificuldade da pergutna � igual a dificuldade geral do arquivo Json
				else if(perg.getDificuldadePergunta().equals(configJogo.getDificuldadeJogo())) {
					perguntasRetorno.add(perg);
				}
				contaPerguntas++;
			}
			return perguntasRetorno;
			
		} catch (Exception e) {
			throw e;
		}
	}
}
