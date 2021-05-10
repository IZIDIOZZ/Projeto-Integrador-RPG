package Classes;
import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Helper {

	public void NavegarEntrePagina(ActionEvent event,String destino) throws IOException {
    	Scene pagina = new Scene(FXMLLoader.load(getClass().getResource(destino)));
    	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	app_stage.setScene(pagina);
    	app_stage.show();
    }
	public static void PintaBotao(Label btn,Color cor) {
		btn.setBackground(new Background(new BackgroundFill(cor, new CornerRadii(0), Insets.EMPTY)));
	}
	public static void ResetaCoresBotao(List<Label> listaButoes) {
		for(Label btn: listaButoes) { PintaBotao(btn, Color.rgb(219, 219, 219));}
	}
	
	public void LerEmIntervaloDeTempo(Label label,String texto) throws InterruptedException {
		String juncaoTexto = new String();
		for(int i = 0; i< texto.length(); i++) {
			juncaoTexto += texto.charAt(i);
			Thread.sleep(100);
			label.setText(juncaoTexto);
		}
		Thread.currentThread().interrupt();	
	}
}
