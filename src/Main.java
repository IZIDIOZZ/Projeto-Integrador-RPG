import Classes.Helper;
import Enumerations.SoundsEnum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	
	/*Como desenvolvedores deixamos aqui nossa avers�o a inserir muitos coment�rios nesse projeto.
	 *Afinal acreditamos que um bom c�digo n�o precisa de coment�rios, se auto explica.
	 *Maaaas, por quest�es de nota vamos inserir.*/
	
    @Override
    public void start(Stage primaryStage) throws Exception{
    	
    	//inicia o jogo executando m�todo que ir� tocar o som do menu inicial
        new Helper().ExecutaSom(SoundsEnum.MUSICA_MENU,true,0.3D);
    	
        //Carrega a view inicial do Menu num Node Pai e define as configura��es de tamanho, titulo e icone.
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Views/MenuView.fxml"));
    	Parent root = loader.load();
    	primaryStage.setTitle("Dev-Quest");
        primaryStage.setScene(new Scene(root, 1028, 682));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/assets/icon-app-pixel.jpg"));
        
        //Exibe a View
        primaryStage.show();
    }
    
    public static void main(String[] args) {
    	//m�todo respons�vel por iniciar o Jogo
        launch(args);
    }
}
