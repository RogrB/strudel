package strudel.view;

import java.util.ArrayList;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import strudel.logic.Strudel;
import strudel.logic.StrudelLogic;

public class CommentWindow {
    
    ArrayList<StrudelRenderNode> renderNodes = new ArrayList();
    TextArea textArea = new TextArea();
    StrudelLogic logic = StrudelLogic.getInstance();
    ViewController view = ViewController.getInstance();
    private int height;
    private int width;
    private String color;
    private final int id;
    
    public CommentWindow(int id) {
        this.id = id;
    }
    
    public void setView(ViewController view) {
        this.view = view;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public Pane init() {
        Pane root = new Pane();
        root.setPrefSize(width, height);
        Pane content = setContent();
        ScrollPane scrollPane = setScrollPane();
        Pane footer = setFooter();
        root.getChildren().addAll(scrollPane, footer);
        scrollPane.setContent(content);
        return root;
    }
    
    public Pane setContent() {
        Pane content = new Pane();
        content.setTranslateX(0);
        content.setTranslateY(31);
        content.prefHeight(Control.USE_COMPUTED_SIZE);
        content.maxHeight(Double.POSITIVE_INFINITY);
        content.setStyle("-fx-background-color: #FFFFFF;");
        populateStrudels(content);        
        return content;
    }       
    
    public Pane populateStrudels(Pane pane) {
        ArrayList<Strudel> strudels = logic.getComments(id);
        int y = -33;
        for (Strudel strudel : strudels) {
            renderNodes.add(new CommentRenderNode(pane, strudel, y));
            y += strudel.getHeight() + 3;
        }
        return pane;
    }
    
    public Pane setFooter() {
        Pane footer = new Pane();
        footer.setPrefWidth(width+15);
        footer.setPrefHeight(30);
        footer.setStyle("-fx-background-color: #FFFFFF;");
        
        Text send = new Text("Send");
        send.setFont(send.getFont().font(20));
        Pane sendPane = new Pane();
        sendPane.setPrefWidth(50);
        sendPane.setOnMouseClicked(event-> post());
        sendPane.getChildren().add(send);
        sendPane.setTranslateX(width-60);
        sendPane.setTranslateY(height-30);
        
        Pane textBoxPane = setTextBox();
        footer.getChildren().addAll(sendPane, textBoxPane);
        
        return footer;
    }
    
    public Pane setTextBox() {
        Pane content = new Pane();
        content.setPrefWidth(width);
        content.setPrefHeight(30);
        content.setTranslateY(height-30);
        textArea.setPromptText("Strudel back here..");
        textArea.setPrefSize(width, 30);
        content.getChildren().add(textArea);
        return content;
    }    
    
    public ScrollPane setScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPannable(false);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setTranslateX(0);
        scrollPane.setTranslateY(31);
        scrollPane.setPrefWidth(view.getWidth()+15);
        scrollPane.setPrefHeight(view.getHeight()-50);
        scrollPane.toBack();
        return scrollPane;        
    }        
    
    public void post() {
        String message = textArea.getText().replaceAll("\n", System.getProperty("line.separator"));
        logic.post(message, color);
        textArea.clear();
        view.resetScene();
    }
    
}
