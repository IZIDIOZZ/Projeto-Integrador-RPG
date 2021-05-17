package Models;

//Modelo para a resposta no JSON
public class Resposta {
	private String textoResposta;
	private boolean respostaCorreta;
	private char alternativa;
	
	public String getTextoResposta() {
		return textoResposta;
	}
	public void setTextoResposta(String textoResposta) {
		this.textoResposta = textoResposta;
	}
	public boolean isRespostaCorreta() {
		return respostaCorreta;
	}
	public void setRespostaCorreta(boolean respostaCorreta) {
		this.respostaCorreta = respostaCorreta;
	}
	public char getAlternativa() {
		return alternativa;
	}
	public void setAlternativa(char alternativa) {
		this.alternativa = alternativa;
	}
}
