package Models;

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
	
	//método que verifica se a resposta está correta
	public boolean RespostaCorreta(String alternativaCorreta, Resposta resposta){ 
		boolean ehCorreta = false;
		if((Character.toUpperCase(alternativaCorreta.charAt(0)) == Character.toUpperCase(resposta.getAlternativa()) 
		   || (alternativaCorreta.toUpperCase().equals(resposta.getTextoResposta().toUpperCase())))) {
		   ehCorreta = true;
		}
		return ehCorreta;
	}
}
