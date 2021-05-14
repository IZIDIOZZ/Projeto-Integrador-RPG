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
    
    @FXML
    public void alteraDificuldadeJogo(MouseEvent event) throws IOException {
    	Helper.Reproduzir(SoundsEnum.CLIQUE_BOTAO_DIFICULDADE);
    	Label btn = (Label) event.getSource(); 	
    	ConfiguracaoJogo.ConfiguraDificuldade(btn.getAccessibleText());
    	listaBotoes.forEach(x -> x.setStyle("-fx-background-color: #C8C9CB;"));
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
    	listaBotoes = new ArrayList<Label>(){{add(btnDificuldadeFacil);}
    										 {add(btnDificuldadeMedio);}
    										 {add(btnDificuldadeDificil);}};
     PintaBotaoSelecionado();									 
    	
    }
    public void PintaBotaoSelecionado() throws IOException {
    	RPG base = ConfiguracaoJogo.ConfiguraJogo();
    	String config = base.getConfiguracaoJogo().getDificuldadeJogo();
    	
    	for(Label lbl: listaBotoes) {
    		if(lbl.getAccessibleText().equals(config)) {
    			lbl.setStyle("-fx-background-color: #7AC043;");
    		}
    	}
    }
}
