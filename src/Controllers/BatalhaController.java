package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Classes.Helper;
import Enumerations.SoundsEnum;
import Models.ConfiguracaoJogo;
import Models.Pergunta;
import Models.Personagem;
import Models.RPG;
import Models.Resposta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BatalhaController {
	    @FXML
	    private Pane pnlGeneral;

	    @FXML
	    private Label lblVersaoJogo;

	    @FXML
	    private Label txtPergunta;

	    @FXML
	    private ProgressBar prgVidaJogador;

	    @FXML
	    private Label lblNomeJogador;

	    @FXML
	    private ProgressBar prgVidaInimigo;

	    @FXML
	    private Label lblNomeInimigo;

	    @FXML
	    private Label btnRespostaA;

	    @FXML
	    private Label btnRespostaB;

	    @FXML
	    private Label btnRespostaC;

	    @FXML
	    private Label btnRespostaD;

	    @FXML
	    private Label lblNumeroVidaJogador;

	    @FXML
	    private Label lblNumeroVidaInimigo;

	    @FXML
	    private Pane pnlMensagem;

	    @FXML
	    private Label txtMensagem;

	    @FXML
	    private Button btnFechar;

	    @FXML
	    private Pane pnlGameOver;

	    @FXML
	    private Label txtGameOver;

	    @FXML
	    private Button btnVoltarMenuInicial;

	    @FXML
	    private Label txtMensagemGameOver;

	    @FXML
	    private Pane pnlIntroducaoJogo;

	    @FXML
	    private Label lblTextoIntroducao;

	    @FXML
	    private Button btnIniciaJogo;

	    @FXML
	    private TextField txtNomeJogador;
	    
	    @FXML
	    private Label lblQuantidadePerguntasRestante;
	    
	    @FXML
	    private Button btnProximaPergunta;
	    
	    @FXML
	    private Button btnVoltarMenuInicialVitoria;
	    
	    @FXML
	    private Button btnVoltarMenuInicialEmpate;
	    
	    @FXML
	    private Pane pnlVitoria;
	    
	    @FXML
	    private Pane pnlEmpate;
	    
	    @FXML
	    private Label lblPoderAtaqueJogador;

	    @FXML
	    private Label lblPoderAtaqueInimigo;
	    
	    @FXML
	    private Label lblVidaFinalJogador;
	    
	    @FXML
	    private Label lblVidaFinalInimigo;
	    
	    @FXML
	    private Label lblQuantidadePerguntasFeitas;
	    
	    @FXML
	    private Label lblQuantidadeRespostasErradas;
	    
	    @FXML
	    private Label lblQuantidadeRespostasCertas;
	    
	    @FXML
	    private Label lblNomeJogadorFinal;
	    
	    @FXML
	    private Label lblNomeInimigoFinal;
	    
	    @FXML
	    private Pane pnlParciaisFinal;
	    
	    //parâmetros para controlar o estado da View
	    RPG base = new RPG();
	    
	    //Personagem do Jogador
	    Personagem jogador = new Personagem();
	    
	    List<Personagem> inimigos = new ArrayList<Personagem>();
	    List<Pergunta> perguntas = new ArrayList<Pergunta>();
	    List<Label> listabtn ;	
	    
	    //variáveis para conta a quantidade de repostas certas/erradas/total feito
	    int quantidadeRepostasCorreta = 0 , 
	    	quantidadeRepostasErradas = 0,
	    	quantidadePerguntasFeitas = 0;
	    
		
	    /*Esse método é chamado inicialmente e faz a carga 
	    de dados necessária nas variáveis para o jogo funcionar */
	    @SuppressWarnings("serial")
		@FXML
	    public void initialize() throws IOException {
	    	//Passa o valor do JSON para o Objeto principal
	    	base = new RPG();
	    	base = ConfiguracaoJogo.ConfiguraJogo();
	    	
	    	//Passa os botões para a lista de botões
	    	listabtn = new ArrayList<Label>(){{ add(btnRespostaA);}
											  { add(btnRespostaB);}
											  { add(btnRespostaC);}
											  { add(btnRespostaD);}};
			//Define a versão do jogo								  
			Helper.SetaVersaoJogo(lblVersaoJogo);	
			
			//Pega as perguntas e os inimigos com base na dificuldade do jogo
	    	perguntas =  Pergunta.BuscaPerguntasComBaseDificuldadeGeral(base);
			inimigos = Personagem.BuscaInimigosComBaseDificuldadeGeral(base);
			
			//passa a quantidade de perguntas para a Label que controla
	    	lblQuantidadePerguntasRestante.setText(String.valueOf(perguntas.size()));
	    	
	    	//desabilita o botao de próxima pergunta 
	    	btnProximaPergunta.setDisable(true);
	    }
	      
	//Esse método configura a visualização da próxima pergunta
    public void ConfiguraVisualizacaoPergunta() {
    	
    	//Passa para a próxima pergunta que não foi feita ainda
		perguntas = Pergunta.RetornaPerguntasQueNaoForamFeitas(perguntas);
	
		//Passa o enunciado para a label de pergunta
		txtPergunta.setText(perguntas.get(0).getEnunciado()); 
		
		
		//Faz um loop nas respostas da pergunta atual e passa para a lista de botões 
		for(Resposta perg : perguntas.get(0).getRespostas()) {
			
			/*Faz um loop nos botões e passa a o texto da resposta para o botão 
			 * correto com base na alternativa */
			for(Label btn: listabtn) {
				if(Character.toUpperCase(btn.getAccessibleText().charAt(0)) == perg.getAlternativa()) {
					btn.setText(perg.getTextoResposta());
					break;
				}
			}
		}		
    }
    
    //Método que chama a proxima pergunta e atualiza as informações visuais na tela
    @FXML
    void ChamaProximaPergunta(MouseEvent event) {
    	
    	//Pega as perguntas que ainda não foram feitas.
    	perguntas = Pergunta.RetornaPerguntasQueNaoForamFeitas(perguntas);
    	
    	//se não houver mais perguntas, chama o método para definir o vencedor
    	if(perguntas.isEmpty()) {
    		VerificaSeUmPersonagemGanhou();
    		return;
    	}
    	
    	//Reseta as informações visuais dos botões
    	Helper.ResetaCoresBotao(listabtn);	
    	
    	//Atualiza a visualização das perguntas e repostas
    	ConfiguraVisualizacaoPergunta();
    	
    	//Atualiza a quantidade de pergutna restante
    	lblQuantidadePerguntasRestante.setText(String.valueOf(perguntas.size()));

    	//Desabilita o botão de ir para a próxima pergunta
    	btnProximaPergunta.setDisable(true);
    }
    
    //Método responsável por pintar os botões com base em se a reposta está correta
    public void DefineCorReposta(List<Label> listaBotoes,List<Pergunta> perguntas,Label btnPressionado, Resposta respostaSelecionada) {
    	
    	//se a reposta está correta pinta o botão clicado de verde
    	if(respostaSelecionada.isRespostaCorreta()) {
    		Helper.PintaBotao(btnPressionado,Color.rgb(122, 192, 67)); 
    		return;
    	}
    	
    	/*senão , pinta o botão clicado de vermelho e 
    	 * pinta o botão com a resposta correta de verde
    	 */
	    Helper.PintaBotao(btnPressionado,Color.rgb(218, 28, 56)); 
	    
	    //Busca a resposta correta
	    Resposta respostaCorreta = perguntas.get(0).getRespostas()
	    						   .stream()
	    						   .filter(x->x.isRespostaCorreta())
	    						   .findFirst().get();
	    	
	    //Busca qual botão possui a resposta correta
	    Label btnRespostaCorreta = listaBotoes.stream()
	    						   .filter(x->x.getAccessibleText().charAt(0) == respostaCorreta.getAlternativa())
	    						   .findFirst()
	    						   .get();
	    
	    //Pinta o botão coma resposta correta de verde
	    Helper.PintaBotao(btnRespostaCorreta,Color.rgb(122, 192, 67)); 	    
    }
    
    
    @FXML
    void MostrarTelaMensagem(ActionEvent event) {
    	pnlMensagem.setVisible(true);
    }
    
    @FXML
    void FecharTelaMensagem(ActionEvent event) {
    	pnlMensagem.setVisible(false);
    }
    
    //Método responsável por verificar se a resposta selecionada está correta
    @FXML
    public void SelecionaResposta(MouseEvent event) {
    	 //se a lista de pergunta for vazia ou a ultima pergunta ja foi feita, então sai do método
    	 if(perguntas.isEmpty()) return;
    	 if(perguntas.get(0).jaFoiFeita()) return;
    	 
    	 //reproduz o som de Clique dio botão
    	 Helper.Reproduzir(SoundsEnum.CLIQUE_BOTAO);
    	 
    	 //Pega a fonte e converte para um Botão (no caso uma Label clicavel)
    	 Label btn = (Label) event.getSource();

    	 //pega a reposta Selecionada pelo usuário
    	 Resposta respostaSelecionada = perguntas.get(0).getRespostas()
			    			 		   .stream()
			    			           .filter(x->x.getAlternativa() == btn.getAccessibleText().charAt(0))
			    			           .findFirst()
			    			           .get();
    	 
    	 //define a cor da resposta com base em se é correta ou errada
    	 DefineCorReposta(listabtn,perguntas,btn,respostaSelecionada);
    	 
    	 /*se a resposta for correta, da dano no inimigo e atualiza as variáveis de contagem
    	  *se a resposta for errada, da dano no jogador e atualiza as variáveis de contagem 
    	  */
    	 if(respostaSelecionada.isRespostaCorreta()) {
    		 //inimigo sofre dano do jogador
    		 inimigos.get(0).SofrerDano(jogador);
    		 
    		 //atualiza as info do jogador
    		 AtualizaPersonagem(inimigos.get(0), 
    				            lblNumeroVidaInimigo,
    				 			prgVidaInimigo,
    				 			lblNomeInimigo,
    				 			lblPoderAtaqueInimigo);
    		 
    		 inimigos.get(0).setJaLutou(inimigos.get(0).getVida() <= 0 ? true: false);
    		 
    		//incrementa as respostas corretas
    		 quantidadeRepostasCorreta++;
    		 
    		 //reproduz o som de inimigo recebendo dano
    		 Helper.Reproduzir(SoundsEnum.DANO_INIMIGO);
    		 Helper.Reproduzir(SoundsEnum.RESPOSTA_CORRETA);
    		 
    	 }else {
    		 //jogador sofre dano do inimigo
    		 jogador.SofrerDano(inimigos.get(0));
    		 
    		//atualiza as info do inimigo
    		 AtualizaPersonagem(jogador, 
    				            lblNumeroVidaJogador,
			 			        prgVidaJogador, 
			 			        lblNomeJogador, 
			 			        lblPoderAtaqueJogador);
    		 
    		 //incrementa as respostas erradas
    		 quantidadeRepostasErradas++;
    		 
    		 //reproduz o som de jogador recebendo dano
    		 Helper.Reproduzir(SoundsEnum.DANO_JOGADOR);
    		 Helper.Reproduzir(SoundsEnum.RESPOSTA_ERRADA);
    	 }
    	 perguntas.get(0).setJaFoiFeita(true);
    	 quantidadePerguntasFeitas++;
    	 VerificaSeUmPersonagemGanhou();
    	 btnProximaPergunta.setDisable(false);
    }
    
    //método reponsável por verificar quem ganhou o jogo
    public void VerificaSeUmPersonagemGanhou() {
    	/*se a vida do jogador for maior que a vida do inimigo e ainda existir perguntas,
    	 * então sai do método para fazer mais perguntas até acaba-las
    	 */
    	if(jogador.getVida() > 0 && inimigos.get(0).getVida() > 0 && perguntas.size() > 1) return;
    	String corDoTexto = new String();
    	
    	/*se a vida do jogador for menor que 0 ou se for menor que a do inimigo,
    	 *  então mostra o painel de gameOver 
    	 */
    	if(jogador.getVida() < 0 || jogador.getVida() < inimigos.get(0).getVida()) {
    		pnlGameOver.setVisible(true);
    		
    		//seta a cor do texto para Vermelho
    		corDoTexto = "#E22A38";
    	}
    	
    	/*se a vida do inimigo for menor que 0 ou se for menor que a do jogador,
    	 *  então mostra o painel de Vitória 
    	 */
    	if(inimigos.get(0).getVida() < 0 || inimigos.get(0).getVida() < jogador.getVida()) {
    		pnlVitoria.setVisible(true);
    		
    		//seta a cor do texto para Verde
    		corDoTexto = "#7AC043";
    	}
    	/*Se a vidade de ambos forem iguais, então mostra o
    	 *  painel de empate (raro de ocorrer devido a randomicidade, mas está tratado)*/
    	if(inimigos.get(0).getVida() == jogador.getVida()) {
    		pnlEmpate.setVisible(true);
    		
    		//seta a cor do texto para amarelo
    		corDoTexto = "#FDC132";
    	}
    	
    	//Chama método para carregar as parciais finais do jogo e passa a cor para pintar o painel
    	CarregaParciaisFinais(corDoTexto);
    }
    
    //método que carrega o resultado final do jogo
    public void CarregaParciaisFinais(String cor) {
 	   
    	//define o painel de parciais como visivel
    	pnlParciaisFinal.setVisible(true);
    	
    	//passa as informações do jogador para as labels
    	lblNomeJogadorFinal.setText(jogador.getNome());
    	lblVidaFinalJogador.setText(String.valueOf(jogador.getVida()));
    	
    	//passa as informações do inimigo para as labels
    	lblNomeInimigoFinal.setText(inimigos.get(0).getNome());
    	lblVidaFinalInimigo.setText(String.valueOf(inimigos.get(0).getVida()));
    	
    	//preenche as informações de repostas corretas, erradas e totais feito
    	lblQuantidadeRespostasCertas.setText(String.valueOf(quantidadeRepostasCorreta));
    	lblQuantidadeRespostasErradas.setText(String.valueOf(quantidadeRepostasErradas));
    	lblQuantidadePerguntasFeitas.setText(String.valueOf(quantidadePerguntasFeitas));
    	
    	//pinta todos os filhos do painel da cor passada
    	for(Node con: pnlParciaisFinal.getChildren() ){
    		con.setStyle("-fx-text-fill:"+cor);
    	}
    }
    
    //mostra o painel de Game Over
    @FXML
    public void MostrarGameOver(ActionEvent event) {
    	pnlGameOver.setVisible(true);
    }
    
    @FXML
    public void VoltarMenuInicial(ActionEvent event) throws IOException {
    	new Helper().NavegarEntrePagina(event,"../Views/MenuView.fxml");
    }
    
    //Método que inicia o jogo e pega o nome do usu
    @FXML
    public void IniciaJogoFechaResumoPrincipal(ActionEvent event) throws IOException {
    	Helper.Reproduzir(SoundsEnum.CLIQUE_BOTAO);
    	
    	//Cria o jogador passando um nome, uma vida aleatória e um poder aleatório 
    	jogador = new Personagem(txtNomeJogador.getText(),
    			ConfiguracaoJogo.GeraNumeroAleatorioPorIntervalo(70, 125),
				ConfiguracaoJogo.GeraNumeroAleatorioPorIntervalo(10, 35));
    	
    	//passa os valores do inimigo para os componentes da View
		AtualizaPersonagem(inimigos.get(0), 
				           lblNumeroVidaInimigo,
				 		   prgVidaInimigo,
				 		   lblNomeInimigo,
				 		   lblPoderAtaqueInimigo);
		
		//passa os valores do jogador para os componentes da View
		AtualizaPersonagem(jogador, 
						   lblNumeroVidaJogador,
					       prgVidaJogador, 
					       lblNomeJogador, 
					       lblPoderAtaqueJogador);
		
		//Configura uma nova Pergunta
		ConfiguraVisualizacaoPergunta();
		
		//dexia invisivel o painel de introdução
    	pnlIntroducaoJogo.setVisible(false);
    }
    
    //método responsável por atualizar as informações do personagem
    void AtualizaPersonagem(Personagem personagem,
    						Label textoVida, 
    						ProgressBar barraVida, 
    						Label nomePersonagem,
    						Label poderAtaque
    						) {
    	//Altera o nome do personagem com base no nome do personagem
    	nomePersonagem.setText(personagem.getNome());
    	
    	//define o progesso da barra de progresso com base no valor da vida / vida base (valor total)
    	barraVida.setProgress((double)(personagem.getVida()/(double)personagem.getVidaBase()));
    	
    	//passa o valor numerico para a vida do personagem para a Label
		textoVida.setText(String.valueOf(personagem.getVida()));
		
		//passa o poder de ataque do personagem para a Label de poder de ataque
		poderAtaque.setText(String.valueOf(personagem.getPoderAtaque()));		
    }
    
    /*Habilita o botão de inicio de jogo somente quando 
    houver algum texto digitado na entrada de texto do nome na introdução*/
    @FXML
    void LiberaBotaoInicioJogo(KeyEvent event) {
    	btnIniciaJogo.setDisable(txtNomeJogador.getText().isEmpty()  ? true: false);
    }
}
