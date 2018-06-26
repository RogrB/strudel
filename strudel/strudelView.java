package strudel;

import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class strudelView {
    
    private final int width = 360;
    private int height = 640;
    public Pane root = new Pane();
    public ArrayList<Pane> studelPanes = new ArrayList<>();
    final Canvas canvas = new Canvas(width, height);
    
    strudelLogic logic = new strudelLogic();
    
    public Parent initScene() {
        root.setPrefSize(width, height);
        
        Pane header = new Pane();
        header.setPrefWidth(width+15);
        header.setPrefHeight(30);
        header.setStyle("-fx-background-color: #F01010;");
        
        root.setStyle("-fx-background-color: #FFFFFF;");
        populateStrudels(root);
        
        Pane footer = new Pane();
        footer.setPrefWidth(width+15);
        footer.setPrefHeight(30);
        footer.setTranslateY(height-18);
        footer.setStyle("-fx-background-color: #F0591E;");
        
        root.getChildren().addAll(header, footer);
        
        return root;
    }
    
    public Pane populateStrudels(Pane pane) {
        ArrayList<Strudel> strudels = logic.getTest();
        for (Strudel s : strudels) {
            
        }
        return pane;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getHeight() {
        return this.height;
    }
    
}
