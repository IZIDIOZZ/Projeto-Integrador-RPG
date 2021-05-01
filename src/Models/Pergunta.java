package Models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pergunta {
	private int id;
	private String enunciado;
	private List<Resposta> respostas; 
	private String dificuldadePergunta;
	
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
	
	/*Este método verifica a dificuldade do jogo definida no Json e 
	pega somente as perguntas que estejam com este nivel de dificuldade*/
	public static List<Pergunta> BuscaPerguntasComBaseDificuldadeGeral(RPG base) {

		//o código resumido em uma linha 
		List<Pergunta> perguntasRetorno = new ArrayList<Pergunta>();
		ConfiguracaoJogo configJogo = base.getConfiguracaoJogo();
		int contaPerguntas = 0;
		//Percorre o as perguntas para verificar a propriedade de dificuldade delas
		for(Pergunta perg: base.getPerguntas()) {
			System.out.println(configJogo.getDificuldadeJogo());
			//verifica se a dificuldade da pergutna é igual a dificuldade geral do arquivo Json
			if(perg.getDificuldadePergunta().equals(configJogo.getDificuldadeJogo())
				&& contaPerguntas <= configJogo.getQuantidadeMaximaPerguntas()) {
				perguntasRetorno.add(perg);
			}
			contaPerguntas++;
		}
		//RANDOMIZA os elementos na lista de Perguntas
		Collections.shuffle(perguntasRetorno);	
		return perguntasRetorno;
	}
}
