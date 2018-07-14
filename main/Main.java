package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import strudel.view.ViewController;

import static javafx.application.Application.launch;

public class Main extends Application {
    
    private static ViewController view = ViewController.getInstance();
      
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
