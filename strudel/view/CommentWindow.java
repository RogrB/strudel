package strudel.view;

import java.util.ArrayList;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import strudel.logic.Strudel;
import strudel.logic.StrudelComment;
import strudel.logic.StrudelLogic;

public class CommentWindow {
    
    ArrayList<MainWindowRenderNode> renderNodes = new ArrayList();
    TextArea textArea = new TextArea();
    StrudelLogic logic = StrudelLogic.getInstance();
    ViewController view = ViewController.getInstance();
    private int height;
    private int width;
    private final Strudel strudel;
    
    public CommentWindow(Strudel strudel) {
        this.strudel = strudel;
    }
    
    public Pane init() {
        this.height = view.getHeight();
        this.width = view.getWidth();
        Pane root = new Pane();
        root.setPrefSize(width, height);
        Pane content = setContent();
        ScrollPane scrollPane = setScrollPane();
        Pane footer = setFooter();
        Pane header = setHeader();
        root.getChildren().addAll(scrollPane, footer, header);
        scrollPane.setContent(content);
        return root;
    }
    
    public Pane setContent() {
        Pane content = new Pane();
        content.setTranslateX(0);
        content.setTranslateY(31);
        content.prefHeight(Control.USE_COMPUTED_SIZE);
        content.maxHeight(Double.POSITIVE_INFINITY);
        populateStrudels(content);
        return content;
    }       
    
    public Pane populateStrudels(Pane pane) {
        ArrayList<StrudelComment> strudels = logic.getComments(strudel.getID());
        int y = -33;
        renderNodes.add(new MainWindowRenderNode(pane, strudel, y));
        y += strudel.getHeight()+2;
        for (StrudelComment strudelComment : strudels) {
            renderNodes.add(new CommentRenderNode(pane, strudelComment, y));
            y += strudelComment.getHeight() + 3;
        }
        return pane;
    }
    
    public Pane setFooter() {
        Pane footer = new Pane();
        footer.setPrefWidth(width-52);
        footer.setPrefHeight(30);
        footer.setTranslateX(1);
        footer.setTranslateY(height-40);
        footer.setStyle("-fx-background-color: #FFFFFF;");
        
        Text send = new Text("Send");
        send.setFont(send.getFont().font(20));
        Pane sendPane = new Pane();
        sendPane.setPrefWidth(50);
        sendPane.setOnMouseClicked(event-> post());
        sendPane.getChildren().add(send);
        sendPane.setTranslateX(width-52);
        sendPane.setTranslateY(20);
        
        Pane textBoxPane = setTextBox();
        footer.getChildren().addAll(sendPane, textBoxPane);
        
        return footer;
    }
    
    public Pane setTextBox() {
        Pane textBoxPane = new Pane();
        textBoxPane.setPrefHeight(30);
        textArea.setPromptText("Strudel back here..");
        textArea.setPrefSize(width-60, 30);
        textBoxPane.getChildren().add(textArea);
        return textBoxPane;
    }    
    
    public ScrollPane setScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPannable(false);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setTranslateX(0);
        scrollPane.setTranslateY(31);
        scrollPane.setPrefWidth(view.getWidth()+15);
        scrollPane.setPrefHeight(view.getHeight()-73);
        scrollPane.setStyle("-fx-background-color: " + strudel.getColor() + ";");
        scrollPane.toBack();
        return scrollPane;        
    }      
    
    public Pane setHeader() {
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
        
        header.getChildren().add(arrowPane);
        return header;
    }
    
    public void post() {
        String message = textArea.getText().replaceAll("\n", System.getProperty("line.separator"));
        logic.postComment(message, strudel.getColor(), strudel.getID());
        textArea.clear();
        view.resetScene();
    }
    
}
