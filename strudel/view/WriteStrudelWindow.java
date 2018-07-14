package strudel.view;

import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import strudel.logic.StrudelLogic;

public class WriteStrudelWindow {
    
    TextArea textArea = new TextArea();
    StrudelLogic logic = StrudelLogic.getInstance();
    ViewController view;
    private int height;
    private int width;
    private String color;
    
    public WriteStrudelWindow(int width, int height) {
        view = ViewController.getInstance();
        this.width = width;
        this.height = height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public Pane init() {
        this.color = logic.getRandomColor();
        Pane root = new Pane();
        root.setPrefSize(width, height);
        Pane header = setWriteHeader();
        Pane content = setWriteTextBox();
        root.getChildren().addAll(header, content);        
        return root;
    }
    
    public Pane setWriteHeader() {
        Pane header = new Pane();
        header.setPrefWidth(width+15);
        header.setPrefHeight(30);
        header.setStyle("-fx-background-color: #FFFFFF;");
        ImageView arrow = new ImageView(new Image("asset/img/arrow.png"));
        arrow.setX(5);
        arrow.setY(5);
        Pane arrowPane = new Pane();
        arrowPane.setOnMouseClicked(event-> view.resetScene());
        arrowPane.setPrefWidth(50);
        arrowPane.getChildren().add(arrow);
        
        Text send = new Text("Send");
        send.setFont(send.getFont().font(20));
        Pane sendPane = new Pane();
        sendPane.setPrefWidth(50);
        sendPane.setOnMouseClicked(event-> post());
        sendPane.getChildren().add(send);
        sendPane.setTranslateX(width-60);
        sendPane.setTranslateY(20);
        
        
        header.getChildren().addAll(arrowPane, sendPane);
        
        return header;
    }
    
    public Pane setWriteTextBox() {
        String color = logic.getRandomColor();
        Pane content = new Pane();
        content.setPrefWidth(width);
        content.setPrefHeight(height-29);
        content.setTranslateY(31);
        content.setStyle("-fx-background-color: " + color);
        textArea.setPromptText("Share something with the world");
        textArea.setPrefSize(width, height-29);
        textArea.setStyle("-fx-control-inner-background:" + color + "; -fx-font-family: Consolas; -fx-text-fill: #FFFFFF; ");
        content.getChildren().add(textArea);
        return content;
    }    
    
    public void post() {
        String message = textArea.getText().replaceAll("\n", System.getProperty("line.separator"));
        logic.post(message, color);
        logic.upDateStrudelsFromDB();
        textArea.clear();
        view.resetScene();
    }    
    
}
