package Controllers;

import java.io.IOException;

import Classes.Helper;
import Enumerations.SoundsEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class MenuController  {
	
	
    @FXML
    private Pane pnlGeneral;

    @FXML
    private Button btnInicioJogo;
		
    @FXML
    private Button btnRegras;

    @FXML
    private Button btnCreditos;

    @FXML
    private Button btnDificuldade;

    @FXML
    private Button btnSair;

    @FXML
    private Label lblVersaoJogo;
    
    
    @FXML
    public void dificuldadeView(ActionEvent event) throws IOException{
    	Helper.Reproduzir(SoundsEnum.CLIQUE_BOTAO);
    	new Helper().NavegarEntrePagina(event,"../Views/DificuldadeView.fxml");
    }
    
    @FXML
    public void CreditoView(ActionEvent event) throws IOException{
    	Helper.Reproduzir(SoundsEnum.CLIQUE_BOTAO);
    	new Helper().NavegarEntrePagina(event,"../Views/CreditosView.fxml");
    }
    
    @FXML
    public void SairRPG() throws IOException{
    	Helper.Reproduzir(SoundsEnum.CLIQUE_BOTAO);
    	System.exit(0);
    }
   
    @FXML
    public void RegrasView(ActionEvent event) throws IOException {
    	Helper.Reproduzir(SoundsEnum.CLIQUE_BOTAO);
    	new Helper().NavegarEntrePagina(event,"../Views/RegrasView.fxml");
    }
    
    @FXML
    public void BatalhaView(ActionEvent event) throws IOException {
    	Helper.Reproduzir(SoundsEnum.CLIQUE_BOTAO);
    	new Helper().NavegarEntrePagina(event,"../Views/BatalhaView.fxml");
    }
}
