package Controllers;

import Classes.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CreditosController {
	
	  @FXML
	  private Button btnVoltar;
	    
	  @FXML
	  public void voltarMenuView(ActionEvent event) throws Exception {
		  new Helper().NavegarEntrePagina(event, "../Views/MenuView.fxml");
	   }
	  
}
