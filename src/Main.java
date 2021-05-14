import Classes.Helper;
import Enumerations.SoundsEnum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	
       new Helper().ExecutaSom(SoundsEnum.MUSICA_MENU,true,0.3D);
    	
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Views/MenuView.fxml"));
    	Parent root = loader.load();
    	primaryStage.setTitle("RPG");
        primaryStage.setScene(new Scene(root, 1028, 682));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/assets/icon-app-pixel.jpg"));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
