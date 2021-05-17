package Models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//Modelo da configura��o do jogo que es�ta no JSON
public class ConfiguracaoJogo {

	private String dificuldadeJogo;
	private int quantidadeMaximaPerguntas;
	private int quantidadeMaximaInimigos;
	private String versao;
	
	public String getVersao() { return versao; }
	public void setVersao(String versao) { this.versao = versao;}
	
	public String getDificuldadeJogo() {
		return dificuldadeJogo;
	}
	public void setDificuldadeJogo(String dificuldadeJogo) {
		this.dificuldadeJogo = dificuldadeJogo;
	}
	public int getQuantidadeMaximaPerguntas() {
		return quantidadeMaximaPerguntas;
	}
	public void setQuantidadeMaximaPerguntas(int quantidadeMaximaPerguntas) {
		this.quantidadeMaximaPerguntas = quantidadeMaximaPerguntas;
	}
	public int getQuantidadeMaximaInimigos() {
		return quantidadeMaximaInimigos;
	}
	public void setQuantidadeMaximaInimigos(int quantidadeMaximaInimigos) {
		this.quantidadeMaximaInimigos = quantidadeMaximaInimigos;
	}
	
	
	/*m�todo respons�vel por fazer a configura��o geral do RPG, ele retorna um 
	Objeto do tipo Configuracao com todas as propriedades de configura��o que est�o no Json*/
	public static RPG ConfiguraJogo() throws IOException {
		
		//Chama o m�todo que l� o arquivo json e passa pra uma String
		String json = LerArquivoJson("./src/config.json","UTF-8");
		
		//Converte a String em um objeto Json com base na Classe Configuracao
		RPG config = new Gson().fromJson(json, RPG.class);
		
		//retorna o objeto
		return config;
	}
	
	/*M�todo respons�vel pela altera��o da dificuldade geral do jogo direto no JSON.
	 *A altera��o acontece alterando primeiro o objeto que representa o JSON e depois 
	 *� sobrescrito todo o arquivo JSON com base nesse objeto alterado.
	 */
	public static void ConfiguraDificuldade(String dificuldadeJogo) throws IOException {
		
		//Chama o m�todo que l� o arquivo json e passa pra uma String
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		RPG config = ConfiguraJogo();
		
		//Altera a propriedade de dificuldade do objeto que representa o JSON
		config.getConfiguracaoJogo().setDificuldadeJogo(dificuldadeJogo);
		
		//Escreve o objeto em forma de texto no arquivo JSON
		EscreverArquivoJson(gson.toJson(config, RPG.class),"./src/config.json");
	}
	
	//M�todo respons�vel por alterar o arquivo Json passando um novo texto
	public static void EscreverArquivoJson(String novoTexto,String caminho) throws IOException {
		
		//Cria um objeto de escrita para acessar e alterar o aqruivo JSON 
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(caminho), StandardCharsets.UTF_8);
		
		//Altera o Arquivo substituindo o valor atual do aqruivo pela String passada
		out.write(novoTexto);
		
		//Fecha o Stream
		out.close();
	}
	
	//Este m�todo faz a leitura do arquivo Json atrav�s de um caminho e do tipo de codifica��o do arquivo
	public static String LerArquivoJson(String caminho, String codificacao) throws IOException {
		
		//passa todas as linhas lidas para uma lista de Strings 
		List<String> linhas = Files.readAllLines(Paths.get(caminho),Charset.forName(codificacao));
		
		//vari�vel para receber o retorno que ser� o aqruivo completo lido
		String retorno = new String();
		
		//iteramos a lista e passamos cada linha para a vari�vel retorno
		for(String e : linhas) { retorno += e + "\n";}
		return retorno;
	}
	
	//M�todo usado para gerar numeros aleat�rios entre um intervalo dado
	public static int GeraNumeroAleatorioPorIntervalo(int valorInicial, int valorFinal) {
			return new Random().nextInt(valorFinal-valorInicial) + valorInicial;
	}
}
