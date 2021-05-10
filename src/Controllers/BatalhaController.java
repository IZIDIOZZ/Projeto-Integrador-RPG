package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Classes.Helper;
import Models.Capitulo;
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
	    
	    RPG base = new RPG();
	    Personagem jogador = new Personagem();
	    List<Personagem> inimigos = new ArrayList<Personagem>();
	    List<Pergunta> perguntas = new ArrayList<Pergunta>();
	    List<Capitulo> capitulos = new ArrayList<Capitulo>();
	    List<Label> listabtn ;	
	    int quantidadeRepostasCorreta = 0 , 
	    		quantidadeRepostasErradas = 0,
	    		quantidadePerguntasFeitas = 0;
	    
		public void CarregaParciaisFinais(String cor) {
	   
	    	pnlParciaisFinal.setVisible(true);
	    	
	    	lblNomeJogadorFinal.setText(jogador.getNome());
	    	lblVidaFinalJogador.setText(String.valueOf(jogador.getVida()));
	    	
	    	lblNomeInimigoFinal.setText(inimigos.get(0).getNome());
	    	lblVidaFinalInimigo.setText(String.valueOf(inimigos.get(0).getVida()));
	    	
	    	lblQuantidadeRespostasCertas.setText(String.valueOf(quantidadeRepostasCorreta));
	    	lblQuantidadeRespostasErradas.setText(String.valueOf(quantidadeRepostasErradas));
	    	lblQuantidadePerguntasFeitas.setText(String.valueOf(quantidadePerguntasFeitas));
	    	for(Node con: pnlParciaisFinal.getChildren() ){
	    		con.setStyle("-fx-text-fill:"+cor);
	    	}
	    }
	    
	    @SuppressWarnings("serial")
		@FXML
	    public void initialize() throws IOException {
	    	base = new RPG();
	    	base = ConfiguracaoJogo.ConfiguraJogo();
	    	
	    	listabtn = new ArrayList<Label>(){{ add(btnRespostaA);}
											  { add(btnRespostaB);}
											  { add(btnRespostaC);}
											  { add(btnRespostaD);}};
											   
	    	perguntas =  Pergunta.BuscaPerguntasComBaseDificuldadeGeral(base);
			inimigos = Personagem.BuscaInimigosComBaseDificuldadeGeral(base);
	    	lblQuantidadePerguntasRestante.setText(String.valueOf(perguntas.size()));
	    	btnProximaPergunta.setDisable(true);
	    }
	      
	    
    public void ConfiguraBatalha() {
		perguntas = Pergunta.RetornaPerguntasQueNaoForamFeitas(perguntas);
//		inimigos = Personagem.RetornaInimigosQueNaoLutaram(inimigos);
		
		txtPergunta.setText(perguntas.get(0).getEnunciado()); 
							
		for(Resposta perg : perguntas.get(0).getRespostas()) {
			for(Label btn: listabtn) {
				if(Character.toUpperCase(btn.getAccessibleText().charAt(0)) == perg.getAlternativa()) {
					btn.setText(perg.getTextoResposta());
					break;
				}
			}
		}		
    }
    
    @FXML
    void ChamaProximaPergunta(MouseEvent event) {
    	perguntas = Pergunta.RetornaPerguntasQueNaoForamFeitas(perguntas);
    	if(perguntas.isEmpty()) {
    		VerificaSeUmPersonagemGanhou();
    		return;
    	}
    	
    	Helper.ResetaCoresBotao(listabtn);	
    	
    	ConfiguraBatalha();
    	
    	lblQuantidadePerguntasRestante.setText(String.valueOf(perguntas.size()));

    	btnProximaPergunta.setDisable(true);
    }
    
    public void DefineCorReposta(List<Label> listaBotoes,List<Pergunta> perguntas,Label btnPressionado, Resposta respostaSelecionada) {
    	if(respostaSelecionada.isRespostaCorreta()) {
    		Helper.PintaBotao(btnPressionado,Color.rgb(122, 192, 67)); 
    		return;
    	}
	    Helper.PintaBotao(btnPressionado,Color.rgb(218, 28, 56)); 
	    Resposta respostaCorreta = perguntas.get(0).getRespostas().stream().filter(x->x.isRespostaCorreta()).findFirst().get();
	    	
	    Label btnRespostaCorreta = listaBotoes.stream()
	    								.filter(x->x.getAccessibleText().charAt(0) == respostaCorreta.getAlternativa())
	    								.findFirst()
	    								.get();
	    	
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
    
    @FXML
    public void SelecionaResposta(MouseEvent event) {
    	 if(perguntas.isEmpty()) return;
    	 if(perguntas.get(0).jaFoiFeita()) return;
    	 
    	 Label btn = (Label) event.getSource();

    	 Resposta respostaSelecionada = perguntas.get(0).getRespostas()
			    			 		   .stream()
			    			           .filter(x->x.getAlternativa() == btn.getAccessibleText().charAt(0))
			    			           .findFirst()
			    			           .get();
    	 DefineCorReposta(listabtn,perguntas,btn,respostaSelecionada);
    	 
    	 if(respostaSelecionada.isRespostaCorreta()) {
    		 inimigos.get(0).SofrerDano(jogador);
    		 
    		 prgVidaInimigo.setProgress((double)(inimigos.get(0).getVida()/100F));
    		 
    		 lblNumeroVidaInimigo.setText(String.valueOf(inimigos.get(0).getVida()));
    		 
    		 inimigos.get(0).setJaLutou(inimigos.get(0).getVida() <= 0 ? true: false);
    		 
    		 quantidadeRepostasCorreta++;
    	 }else {
    		 jogador.SofrerDano(inimigos.get(0));
    		 
    		 prgVidaJogador.setProgress((double)(jogador.getVida()/100F));  
    		 
    		 lblNumeroVidaJogador.setText(String.valueOf(jogador.getVida()));
    		 
    		 quantidadeRepostasErradas++;
    	 }
    	 perguntas.get(0).setJaFoiFeita(true);
    	 quantidadePerguntasFeitas++;
    	 VerificaSeUmPersonagemGanhou();
    	 btnProximaPergunta.setDisable(false);
    }
    
    public void VerificaSeUmPersonagemGanhou() {
    	
    	if(jogador.getVida() > 0 && inimigos.get(0).getVida() > 0 && perguntas.size() > 1) return;
    	String corDoTexto = new String();
    	
    	if(jogador.getVida() < 0 || jogador.getVida() < inimigos.get(0).getVida()) {
    		pnlGameOver.setVisible(true);
    		corDoTexto = "#E22A38";
    	}
    	
    	if(inimigos.get(0).getVida() < 0 || inimigos.get(0).getVida() < jogador.getVida()) {
    		pnlVitoria.setVisible(true);
    		corDoTexto = "#7AC043";
    	}
    	
    	if(inimigos.get(0).getVida() == jogador.getVida()) {
    		pnlEmpate.setVisible(true);
    		corDoTexto = "#FDC132";
    	}
    	
    	CarregaParciaisFinais(corDoTexto);
    }
    
    @FXML
    public void MostrarGameOver(ActionEvent event) {
    	pnlGameOver.setVisible(true);
    }
    
    @FXML
    public void VoltarMenuInicial(ActionEvent event) throws IOException {
    	new Helper().NavegarEntrePagina(event,"../Views/MenuView.fxml");
    }
    
    @FXML
    public void IniciaJogoFechaResumoPrincipal(ActionEvent event) throws IOException {
    	
    	jogador = new Personagem(txtNomeJogador.getText(),
    			ConfiguracaoJogo.GeraNumeroAleatorioPorIntervalo(70, 125),
				ConfiguracaoJogo.GeraNumeroAleatorioPorIntervalo(10, 35));
    	
		lblNomeInimigo.setText(inimigos.get(0).getNome());
		prgVidaInimigo.setProgress(1);
		lblNumeroVidaInimigo.setText(String.valueOf(inimigos.get(0).getVida()));
		lblPoderAtaqueInimigo.setText(String.valueOf(inimigos.get(0).getPoderAtaque()));
		
		lblNomeJogador.setText(jogador.getNome());
		prgVidaJogador.setProgress(1);
		lblNumeroVidaJogador.setText(String.valueOf(jogador.getVida()));
		lblPoderAtaqueJogador.setText(String.valueOf(jogador.getPoderAtaque()));
		
		
		ConfiguraBatalha();
    	pnlIntroducaoJogo.setVisible(false);
    }
    
    @FXML
    void LiberaBotaoInicioJogo(KeyEvent event) {
    	btnIniciaJogo.setDisable(txtNomeJogador.getText().isEmpty()  ? true: false);
    }
}
