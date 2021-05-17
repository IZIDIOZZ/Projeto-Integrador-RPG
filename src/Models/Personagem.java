package Models;

/*este modelo � usado para criar os personagens (inimigo e jogador)
 * Cont�m alguns m�todos criados para possibilitar a batalha e 
 * a cria��o de um novo personagem a partir de um construtor.
*/
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Personagem {
	
	//Caracteristicas b�sicas de um Personagem
	private int id;
	private int vida;
	private int vidaBase;
	private String nome;
	private int poderAtaque;
	private String nivel;
	private boolean jaLutou = false;
	
	public boolean jaLutou() { return jaLutou; }

	public void setJaLutou(boolean jaLutou) { this.jaLutou = jaLutou; }

	public int getId() {return id;}
	
	public int getVida() {return vida;}
	public void setVida(int vida) {this.vida = vida;}
	
	public void setVidaBase(int vidaBase) { this.vidaBase = vidaBase;}
	public int getVidaBase() { return vidaBase;}
	
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	
	public int getPoderAtaque() {return poderAtaque;}
	public void setPoderAtaque(int poderAtaque) {this.poderAtaque = poderAtaque;}
	
	public String getNivel() {return nivel;}
	public void setNivel(String nivel) {this.nivel = nivel;}
	
	//Construtores
	public Personagem() {}
	
	public Personagem(String nome, int vida,  int poderAtaque ){
		this.nome = nome;
		this.vida = vida;
		this.poderAtaque = poderAtaque;
		this.vidaBase = vida;
	}
	
	//m�todo para fazer o personagem sofrer dano de outro personagem
	public void SofrerDano(Personagem inimigo){
		if(this.vida < inimigo.poderAtaque) {
			this.vida = 0;
			return;
		}
		this.vida -= inimigo.getPoderAtaque();
	}
	
	//esse m�todo retorna os inimigos de uma lista que n�a lutaram ainda
	public static List<Personagem> RetornaInimigosQueNaoLutaram(List<Personagem> inimigos) {
		
		//LAMBDA para retornar somente os inimigos que n�o lutaram.
		return inimigos.stream()
				.filter(inimigo->inimigo.jaLutou == false)
				.collect(Collectors.toList());
	}
	
	/*m�todo que busca os inimigos que est�o no JSON
	 * com base na dificuldade geral do jogo (que tamb�m est� no JSOn) 
	 */
	public static List<Personagem> BuscaInimigosComBaseDificuldadeGeral(RPG base){
		try{
			
			//Cria um objeto de configura��o do jogo e passa a configura��o do jogo para ele
			ConfiguracaoJogo configJogo = base.getConfiguracaoJogo();
			
			//RANDOMIZA os elementos na lista de inimigos
			Collections.shuffle(base.getInimigos());
			
			/*lAMBDA para retornar os inimigos que tem a mesma 
			 * dificuldade da configura��o geral do jogo
			 */
			return base.getInimigos()
					   .stream()
					   .filter(x->x.getNivel().equals(configJogo.getDificuldadeJogo()))
					   .limit(configJogo.getQuantidadeMaximaInimigos())
					   .collect(Collectors.toList());
			
		} catch (Exception e) {
			throw e;
		}
	}
}
