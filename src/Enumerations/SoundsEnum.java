package Enumerations;

/*Esse enumerdaor foi criado para padronizar e deixar os caminhos dos sons do jogo 
 * em uum local só, facilitando a alteração se precisar.*/

public enum SoundsEnum {
	CLIQUE_BOTAO("botao-clique.mp3"),
	CLIQUE_BOTAO_VOLTAR("botao-clique-voltar.mp3"),
	CLIQUE_BOTAO_DIFICULDADE("botao-clique-dificuldade.mp3"),
	MUSICA_MENU("menu-musica.mp3"),
	DANO_JOGADOR("jogador-sofrendo-dano.mp3"),
	DANO_INIMIGO("inimigo-sofrendo-dano.mp3"),
	RESPOSTA_CORRETA("resposta-correta.mp3"),
	RESPOSTA_ERRADA("resposta-errada.mp3");
	
	String som;
	
	//método que retorna o caminho relativo do arquivo 
	public String getSom() {
		return "/assets/sounds/"+this.som;
	}
	
	SoundsEnum(String string) {
		som = string;
	}
}
