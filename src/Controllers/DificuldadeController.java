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
    
    /*Esse método é chamado sempre um dos 3 botoes de dificuldade é pressionado.
     * Quando isso ocorre, o botão pressionado retorna o valor da dificuldade clicada
     * e atualiza o valor no arquivo JSON.*/
    @FXML
    public void alteraDificuldadeJogo(MouseEvent event) throws IOException {
    	Helper.Reproduzir(SoundsEnum.CLIQUE_BOTAO_DIFICULDADE);
    	
    	//converte explicitamente o parâmetro do evento uma Label para ser acessível
    	Label btn = (Label) event.getSource(); 	
    	
    	/*Chama o método de configuração de dificuldade que atualiza o
    	JSON com uma dificuldade(String) passada.*/
    	ConfiguracaoJogo.ConfiguraDificuldade(btn.getAccessibleText());
    	
    	//Reseta as Labels com as cores padrões
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
    	
     //Instancia uma lista de botões quando é iniciado a View
     listaBotoes = new ArrayList<Label>(){{add(btnDificuldadeFacil);}
    									  {add(btnDificuldadeMedio);}
    									  {add(btnDificuldadeDificil);}};
    									  
     //Pinta o botão com a dificuldade selecionada 									  
     PintaBotaoSelecionado();									 
  	 Helper.SetaVersaoJogo(lblVersaoJogo); 	
    }
    
    //Esse método pinta a label que tiver o valor igual a dificuldade que está no JSON
    public void PintaBotaoSelecionado() throws IOException {
    	
    	/*Cria um objeto do Tipo RPG que é um modelo e 
    	 * passa para ele um objeto convertido através do JSON */
    	RPG base = ConfiguracaoJogo.ConfiguraJogo();
    	
    	//Acessa a dificuldade do Objeto e passa para uma variável String
    	String config = base.getConfiguracaoJogo().getDificuldadeJogo();
    	
    	/*Percorre a lista de botões e verifica se o AcessibleText (propriedade do botão) 
    	 * é igual ao valor da dificuldade do JSON */
    	for(Label lbl: listaBotoes) {
    		if(lbl.getAccessibleText().equals(config)) {
    			lbl.setStyle("-fx-background-color: #7AC043;");
    		}
    	}
    }
}
