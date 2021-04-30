import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Models.Capitulo;
import Models.Configuracao;
import Models.Pergunta;
import Models.Personagem;
import Models.Resposta;

enum Dificuldades{
	FACIL("facil"),
	MEDIO("medio"),
	DIFICIL("dificil");
	
	private String escolhaDificuldade;
	public String getEscolhaDificuldade() {
		return escolhaDificuldade;
	}
	private Dificuldades(String escolhaDificuldade) {
		this.escolhaDificuldade = escolhaDificuldade;
	}
}
public class ProjetoRPG {
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("- MENU PRINCIPAL -");
		System.out.println("===================");
		System.out.println("sonho_de_dev.java");
		System.out.println("=======OPÇÕES=======");
		System.out.println("1: NOVO JOGO");
		System.out.println("2: REGRAS");
		System.out.println("3: CRÉDITOS");
		System.out.println("4: DIFICULDADE");
		System.out.println("5: SAIR");
		System.out.println("===================");
		System.out.println("Digite o número da opção: ");
		
		switch (new Scanner(System.in).nextInt()){
			case 1:
				System.out.println("Opção NOVO JOGO selecionada!");
				System.out.println("Tenha um bom jogo :)");
				IniciaJogo();
				
				break;
				
			case 2:
				System.out.println("Opção REGRAS selecionada!");
				System.out.println("=======REGRAS=======");
				System.out.println("1 - Você deverá acertar a resposta das perguntas para causar dano no inimigo.");
				System.out.println("2 - Caso você erre a resposta, sofrerá dano.");
				System.out.println("3 - Você deverá fazer a barra de vida do inimigo chegar a zero para ganhar a batalha.");
				System.out.println("4 - Se sua barra de vida chegar a zero primeiro, você perderá a batalha.");
				System.out.println("5 - Faça boas escolhas, pois elas determinam seu progresso no jogo.");
				System.out.println("6 - Divirta-se! :)");
				break;
				
			case 3:
				System.out.println("Opção CRÉDITOS selecionada!");
				System.out.println("=======CRÉDITOS=======");
				System.out.println("Este jogo foi desenvolvido pelo grupo 4 do Projeto Integrador");
				System.out.println("=====INTEGRANTES=====");
				System.out.println("Benjamin Nicolini");
				System.out.println("Daniel Izidio");
				System.out.println("Danilo Lima");
				System.out.println("Davi Yuri");
				System.out.println("Gustavo Souza");
				System.out.println("Gustavo Vieira");
				System.out.println("=====================");
				break;
			
			case 4:
				
				int escolha = 0;
				do {
					System.out.println("Opção DIFICULDADE selecionada!");
					System.out.println("=======DIFICULDADE=======");
					
					for(Dificuldades dif: Dificuldades.values() ) {
						System.out.println((dif.ordinal()+1)+" -"+dif.getEscolhaDificuldade());
					}
					
					System.out.println("==========================");
					System.out.println("Selecione a dificuldade do jogo: ");
					escolha  = new Scanner(System.in).nextInt();
				}while(!EscolhaDificuldadeSelecionadaExiste(escolha));
				
				String dificuldade = Dificuldades.values()[escolha-1].getEscolhaDificuldade();	
				
				ConfiguraDificuldade(dificuldade,ConfiguraJogo());
				break;
			case 5:
				System.out.println("Opção SAIR selecionada!");
				System.out.println("O jogo será encerrado.");
				System.exit(0);
				break;
				
		}		
	}
	public static boolean EscolhaDificuldadeSelecionadaExiste(int indexEscolhaDificuldade) {
		boolean escolhaExiste = false;
		for(Dificuldades dif: Dificuldades.values() ) {
			if(indexEscolhaDificuldade == (dif.ordinal()+1)) {
				escolhaExiste = true; 
				break;
			} 
		}
		return escolhaExiste;
	}
	public static void ConfiguraDificuldade(String dificuldadeJogo,Configuracao config) throws IOException {
		
		//Chama o método que lê o arquivo json e passa pra uma String
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		config.setDificuldadeJogo(dificuldadeJogo);
		String novoTexto = gson.toJson(config, Configuracao.class);

		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("./src/config.json"), StandardCharsets.UTF_8);
		out.write(novoTexto);
		out.close();
	}
	//método responsável pelo início do jogo
	public static void IniciaJogo() throws IOException {
		//Cria um objeto do tipo Configuracao que será usado para gerenciar todo o jogo
		Configuracao base = new Configuracao();
		
		//Chama o método que preenche o objeto com os dados do json
		base = ConfiguraJogo();
		System.out.println("Caro jogador, insira seu Nome");
		
		//Cria um novo personagem para o jogador
		Personagem jogador = new Personagem(new Scanner(System.in).next(), 100, 25);	
		
		/*Pega as perguntas que tiverem o mesmo nível de 
		dificuldade da propriedade de dificuldade do Json*/
		List<Pergunta> perguntas =  PegaPerguntasConfiguracao(base);
		
		List<Personagem> inimigos = PegaInimigosConfiguracao(base);
	
		for(Capitulo cap: base.getCapitulos()) {
			
			jogador = Batalhar(inimigos.get(0),jogador,perguntas);
			
			if(jogador.getVida() <= 0) 
		    {
				System.out.println("Você perdeu!");
				break;
			}else {
				System.out.println("Você ganhou esse turno parabéns");
			}
		}
	}
	
	public static Personagem Batalhar(Personagem inimigo,Personagem jogador, List<Pergunta>perguntas) {		
			int i = perguntas.size();
		for(Pergunta perg: perguntas) {
				if(inimigo.getVida() <=0 || jogador.getVida() <=0 || i == 0) break;
				Resposta respostaCorreta = new Resposta();
				System.out.println(perg.getEnunciado());
				
				for(Resposta resp : perg.getRespostas()){
					System.out.println(resp.getAlternativa()+") "+resp.getTextoResposta());
					if(resp.isRespostaCorreta()) {
						respostaCorreta = resp;
					}				
				}
				if(RespostaCorreta(new Scanner(System.in).next(),respostaCorreta)){
					inimigo.SofrerDano(jogador);
				}else {
					jogador.SofrerDano(inimigo);
				}
				System.out.println("vida do jogador - "+jogador.getVida());
				System.out.println("vida do inimigo - "+inimigo.getVida());
				i--;
			}
			return jogador;
	}
	//método que verifica se a resposta está correta
	public static boolean RespostaCorreta(String alternativaCorreta, Resposta resposta){ 
		boolean ehCorreta = false;
		if((Character.toUpperCase(alternativaCorreta.charAt(0)) == Character.toUpperCase(resposta.getAlternativa()) 
	        || (alternativaCorreta.toUpperCase().equals(resposta.getTextoResposta().toUpperCase())))) {
			ehCorreta = true;
		}
		return ehCorreta;
	}
	
	public static List<Personagem> PegaInimigosConfiguracao(Configuracao base){

		//o código resumido em uma linha 
		List<Personagem> inimigos = new ArrayList<Personagem>();
		
		//Percorre o as perguntas para verificar a propriedade de dificuldade delas
		for(Personagem inim: base.getInimigos()) {
			
			//verifica se a dificuldade da pergutna é igual a dificuldade geral do arquivo Json
			if(inim.getNivel().equals(base.getDificuldadeJogo())) {
				inimigos.add(inim);
			}
		}
		return inimigos;
	}
	
	/*Este método verifica a dificuldade do jogo definida no Json e 
	pega somente as perguntas que estejam com este nivel de dificuldade*/
	public static List<Pergunta> PegaPerguntasConfiguracao(Configuracao base) {

		//o código resumido em uma linha 
		List<Pergunta> perguntasRetorno = new ArrayList<Pergunta>();
		
		//Percorre o as perguntas para verificar a propriedade de dificuldade delas
		for(Pergunta perg: base.getPerguntas()) {
			
			//verifica se a dificuldade da pergutna é igual a dificuldade geral do arquivo Json
			if(perg.getDificuldadePergunta().equals(base.getDificuldadeJogo())) {
				perguntasRetorno.add(perg);
			}
		}
		return perguntasRetorno;
	}
	
	/*método responsável por fazer a configuração geral do RPG, ele retorna um 
	Objeto do tipo Configuracao com todas as propriedades de configuração que estão no Json*/
	public static Configuracao ConfiguraJogo() throws IOException {
		
		//Chama o método que lê o arquivo json e passa pra uma String
		String json = LerArquivoJson("./src/config.json","UTF-8");
		
		//Converte a String em um objeto Json com base na Classe Configuracao
		Configuracao config = new Gson().fromJson(json, Configuracao.class);
		
		//retorna o objeto
		return config;
	}
	
	//Este método faz a leitura do arquivo Json através de um caminho e do tipo de codificação do arquivo
	public static String LerArquivoJson(String caminho, String codificacao) throws IOException {
		
		//passa todas as linhas lidas para uma lista de Strings 
		List<String> linhas = Files.readAllLines(Paths.get(caminho),Charset.forName(codificacao));
		
		//variável para receber o retorno que será o aqruivo completo lido
		String retorno = new String();
		
		//iteramos a lista e passamos cada linha para a variável retorno
		for(String e : linhas) { retorno += e + "\n";}
		return retorno;
	}
	
	//código que limpa a tela do console
	public static void  LimparTela(){
		try {
			
			/*chama a classe ProcessBuilder que é responsável por executar comandos
			  através de uma aplicação, neste caso estamos usando o cmd*/
		  new ProcessBuilder("cmd.exe","/c","cls").inheritIO().start().waitFor();
		
		} catch (Exception e) {
			System.out.println("Não foi possível limpar a tela");
		}
	}
}
