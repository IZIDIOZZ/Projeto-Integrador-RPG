package Classes;
import java.io.IOException;
import java.util.List;

import Enumerations.SoundsEnum;
import Models.ConfiguracaoJogo;
import Models.RPG;
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

/*Essa classe � usada para literalmente SALVAR o programa abstraindo
 *  aqui os m�todos gerais que ser�o usados por todo o projeto
 */
public class Helper {
	
	//M�todo respons�vel por Navegar entre as p�ginas
	public void NavegarEntrePagina(ActionEvent event,String destino) throws IOException {
    	Scene pagina = new Scene(FXMLLoader.load(getClass().getResource(destino)));
    	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	app_stage.setScene(pagina);
    	app_stage.show();
    }
	
	/*M�todo reposn�vel por executar sons com base no caminho passado
	 * recebe tamb�m um valor boleano que verifica se � loop ou n�o e o volume do som*/
	public void ExecutaSom(SoundsEnum caminho, boolean deveSerLoop, Double volume) {
		 //Cria um objeto do Tipo AudioClip e passa o caminho para ele
		 AudioClip audio = new AudioClip(getClass().getResource(caminho.getSom()).toExternalForm());
		 
		 //se o boleano for verdadeiro, define o som como infinito
	     if(deveSerLoop) audio.setCycleCount(javafx.scene.media.AudioClip.INDEFINITE);
	     
	     //define o volume do som e executa da play
	     audio.setVolume(volume);
	     audio.play();
	}
	
	//Usa o m�todo de executar Som num m�todo est�tico para ser chamado direto
	public static void Reproduzir(SoundsEnum som) {
	   	new Helper().ExecutaSom(som,false,1D);
	}
	
	public static void PintaBotao(Label btn,Color cor) {
		//Seta uma nova cor para o bot�o
		btn.setBackground(new Background(new BackgroundFill(cor, new CornerRadii(0), Insets.EMPTY)));
	}
	
	public static void ResetaCoresBotao(List<Label> listaButoes) {
		for(Label btn: listaButoes) { PintaBotao(btn, Color.rgb(219, 219, 219));}
	}
	
	//define a vers�o do Jogo na Label
	public static void SetaVersaoJogo(Label labelVersao) throws IOException {
		RPG versao = new RPG();
		
		//Pega a vers�o do jogo que est� no JSON
		versao = ConfiguracaoJogo.ConfiguraJogo();
		
		//passa para a label o valor
		labelVersao.setText(versao.getConfiguracaoJogo().getVersao());
	}

	
}
