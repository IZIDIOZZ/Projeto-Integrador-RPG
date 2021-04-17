package Models;
import java.util.List;

public class Pergunta {
	private String enunciado;
	private List<Resposta> respostas; 
	private String dificuldadePergunta;
	
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
}
