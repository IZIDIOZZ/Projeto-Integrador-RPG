package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Classes.Helper;
import Enumerations.SoundsEnum;
import Models.ConfiguracaoJogo;
import Models.RPG;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DificuldadeController {

    @FXML
    private Pane pnlGeneral;

    @FXML
    private Label lblVersaoJogo;

    @FXML
    private Label btnDificuldadeFacil;

    @FXML
    private Label btnDificuldadeMedio;

    @FXML
    private Label btnDificuldadeDificil;
    List<Label> listaBotoes;
    
    /*Esse m�todo � chamado sempre um dos 3 botoes de dificuldade � pressionado.
     * Quando isso ocorre, o bot�o pressionado retorna o valor da dificuldade clicada
     * e atualiza o valor no arquivo JSON.*/
    @FXML
    public void alteraDificuldadeJogo(MouseEvent event) throws IOException {
    	Helper.Reproduzir(SoundsEnum.CLIQUE_BOTAO_DIFICULDADE);
    	
    	//converte explicitamente o par�metro do evento uma Label para ser acess�vel
    	Label btn = (Label) event.getSource(); 	
    	
    	/*Chama o m�todo de configura��o de dificuldade que atualiza o
    	JSON com uma dificuldade(String) passada.*/
    	ConfiguracaoJogo.ConfiguraDificuldade(btn.getAccessibleText());
    	
    	//Reseta as Labels com as cores padr�es
    	listaBotoes.forEach(x -> x.setStyle("-fx-background-color: #C8C9CB;"));
    	
    	//Pinta a Label selecionada
    	PintaBotaoSelecionado();
    }
    
    @FXML
    public void voltarMenuView(ActionEvent event) throws Exception {   	
    	Helper.Reproduzir(SoundsEnum.CLIQUE_BOTAO_VOLTAR);
    	new Helper().NavegarEntrePagina(event, "../Views/MenuView.fxml");
    }
    
    @SuppressWarnings("serial")
	@FXML
    public void initialize() throws Exception {   	
    	
     //Instancia uma lista de bot�es quando � iniciado a View
     listaBotoes = new ArrayList<Label>(){{add(btnDificuldadeFacil);}
    									  {add(btnDificuldadeMedio);}
    									  {add(btnDificuldadeDificil);}};
    									  
     //Pinta o bot�o com a dificuldade selecionada 									  
     PintaBotaoSelecionado();									 
  	 Helper.SetaVersaoJogo(lblVersaoJogo); 	
    }
    
    //Esse m�todo pinta a label que tiver o valor igual a dificuldade que est� no JSON
    public void PintaBotaoSelecionado() throws IOException {
    	
    	/*Cria um objeto do Tipo RPG que � um modelo e 
    	 * passa para ele um objeto convertido atrav�s do JSON */
    	RPG base = ConfiguracaoJogo.ConfiguraJogo();
    	
    	//Acessa a dificuldade do Objeto e passa para uma vari�vel String
    	String config = base.getConfiguracaoJogo().getDificuldadeJogo();
    	
    	/*Percorre a lista de bot�es e verifica se o AcessibleText (propriedade do bot�o) 
    	 * � igual ao valor da dificuldade do JSON */
    	for(Label lbl: listaBotoes) {
    		if(lbl.getAccessibleText().equals(config)) {
    			lbl.setStyle("-fx-background-color: #7AC043;");
    		}
    	}
    }
}
