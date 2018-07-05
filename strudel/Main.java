package strudel;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    private static strudelView view =  new strudelView();
      
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(view.initScene());
        view.setStage(primaryStage);
        primaryStage.setTitle("strudel");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> {
            System.out.println(java.lang.Thread.activeCount());
            Platform.exit();
            System.exit(0);});


        primaryStage.setScene(scene);
        
        primaryStage.show();        
        
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
}
