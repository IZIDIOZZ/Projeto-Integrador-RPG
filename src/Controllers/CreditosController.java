package Controllers;

import Classes.Helper;
import Enumerations.SoundsEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CreditosController {
	
	  @FXML
	  private Button btnVoltar;
	  
	  @FXML
	   private Label lblVersaoJogo;
	  
	  @FXML
	  public void voltarMenuView(ActionEvent event) throws Exception {
		  Helper.Reproduzir(SoundsEnum.CLIQUE_BOTAO_VOLTAR);
		  new Helper().NavegarEntrePagina(event, "../Views/MenuView.fxml");
	   }
	  @FXML
	  public void initialize() throws Exception {   
	   	Helper.SetaVersaoJogo(lblVersaoJogo);
	  }
}
