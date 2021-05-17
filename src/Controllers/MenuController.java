package Controllers;

import java.io.IOException;
import Classes.Helper;
import Enumerations.SoundsEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class MenuController  {
	
	/*propriedades que representam os Nodes que existe no arquivo da View,
	 * atrav�s delas pode-se manipular os valores do componentes.
	 */
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
    
    
    //M�todos para acessar as op��es na View de Menu
    @FXML
    public void dificuldadeView(ActionEvent event) throws IOException{
    	
    	//reproduz o som de Clique usando o m�todo da classe Helper com o Enum de sons como par�metro.
    	Helper.Reproduzir(SoundsEnum.CLIQUE_BOTAO);
    	
    	//Navega para a View passada como par�metro
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
    
    /*O Initialize � um m�todo que � executado sempre ap�s o carregamento dos Nodes(componente)
	    Nesse caso logo ap�s da Label de vers�o ser carregada na View ser� passado o valor da vers�o
	    contido no arquivo json*/
    @FXML
    public void initialize() throws Exception {   
    	Helper.SetaVersaoJogo(lblVersaoJogo);
    }
}
