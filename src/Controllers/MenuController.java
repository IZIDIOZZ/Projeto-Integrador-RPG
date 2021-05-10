package Controllers;

import java.io.IOException;

import Classes.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MenuController  {
	
	

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
    	new Helper().NavegarEntrePagina(event,"../Views/DificuldadeView.fxml");
    }
    
    @FXML
    public void CreditoView(ActionEvent event) throws IOException{
    	new Helper().NavegarEntrePagina(event,"../Views/CreditosView.fxml");
    }
    
    @FXML
    public void SairRPG() throws IOException{
    	System.exit(0);
    }
    
    @FXML
    public void CliqueBotao(ActionEvent event) throws IOException {
    		
    }
    
    @FXML
    public void RegrasView(ActionEvent event) throws IOException {
    	new Helper().NavegarEntrePagina(event,"../Views/RegrasView.fxml");
    }
    
    @FXML
    public void BatalhaView(ActionEvent event) throws IOException {
    	new Helper().NavegarEntrePagina(event,"../Views/BatalhaView.fxml");
    }
}
