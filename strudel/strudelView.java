package strudel;

import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class strudelView {
    
    private final int width = 360;
    private int height = 640;
    public Pane root = new Pane();
    
    strudelLogic logic = new strudelLogic();
    
    public Parent initScene() {
        root.setPrefSize(width, height);
        
        Pane header = new Pane();
        header.setPrefWidth(width+15);
        header.setPrefHeight(30);
        header.setStyle("-fx-background-color: #F01010;");
        
        root.setStyle("-fx-background-color: #FFFFFF;");
        Pane content = new Pane();
        content.setTranslateX(0);
        content.setTranslateY(31);
        content.setPrefSize(width+30, height-51);
        content.setStyle("-fx-background-color: #FFFFFF;");
        populateStrudels(content);
        
        Text test = new Text("ASDF");
        test.setFill(Color.BLACK);
        test.setX(20);
        test.setY(50);
        test.setFont(test.getFont().font(20)); 
        test.toFront();
        root.getChildren().add(test);
        
        Pane footer = new Pane();
        footer.setPrefWidth(width+15);
        footer.setPrefHeight(30);
        footer.setTranslateY(height-18);
        footer.setStyle("-fx-background-color: #F0591E;");
        header.toFront();
        footer.toFront();
        
        root.getChildren().addAll(header, footer, content);
        
        return root;
    }
    
    public Pane populateStrudels(Pane pane) {
        ArrayList<Strudel> strudels = logic.getTest();
        int nextHeight = 0;
        for (Strudel s : strudels) {
            Pane p = new Pane();
            p.setTranslateX(0);
            p.setTranslateY(nextHeight);
            p.setPrefSize(width+30, s.getHeight());
            p.setStyle("-fx-background-color: " + s.getColor());
            setText(pane, s, nextHeight);
            pane.getChildren().add(p);
            
            nextHeight += s.getHeight();
        }        
        return pane;
    }
    
    public void setText(Pane pane, Strudel s, int height) {
        Text t = new Text(s.getMessage());
        t.setX(10);
        System.out.println(height);
        t.setY(height);
        t.setFill(Color.WHITE);
        pane.getChildren().add(t);
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getHeight() {
        return this.height;
    }
    
}
