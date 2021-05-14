package Classes;
import java.io.IOException;
import java.util.List;

import Enumerations.SoundsEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Helper {

	public void NavegarEntrePagina(ActionEvent event,String destino) throws IOException {
    	Scene pagina = new Scene(FXMLLoader.load(getClass().getResource(destino)));
    	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	app_stage.setScene(pagina);
    	app_stage.show();
    }
	
	public void ExecutaSom(SoundsEnum caminho, boolean deveSerLoop, Double volume) {
		 AudioClip audio = new AudioClip(getClass().getResource(caminho.getSom()).toExternalForm());
	     if(deveSerLoop) audio.setCycleCount(javafx.scene.media.AudioClip.INDEFINITE);
	     audio.setVolume(volume);
	     audio.play();
	}
	 
	public static void SomDeClique() {
	   	new Helper().ExecutaSom(SoundsEnum.CLIQUE_BOTAO,false,1D);
	}
	
	public static void Reproduzir(SoundsEnum som) {
	   	new Helper().ExecutaSom(som,false,1D);
	}
	
	public static void PintaBotao(Label btn,Color cor) {
		btn.setBackground(new Background(new BackgroundFill(cor, new CornerRadii(0), Insets.EMPTY)));
	}
	
	public static void ResetaCoresBotao(List<Label> listaButoes) {
		for(Label btn: listaButoes) { PintaBotao(btn, Color.rgb(219, 219, 219));}
	}
}
