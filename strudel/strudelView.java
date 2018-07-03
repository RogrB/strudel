package strudel;

import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class strudelView {
    
    private final int width = 360;
    private int height = 640;
    public Pane root = new Pane();
    Image upVoteImg = new Image("asset/img/upVoteFull.png");
    Image downVoteImg = new Image("asset/img/downVoteFull.png");
    strudelLogic logic = new strudelLogic();
    
    public Parent initScene() {
        root.setPrefSize(width, height);
        
        Pane header = setHeader();
        Pane content = setContent();
        ScrollPane sp = setScrollPane();
        Pane footer = setFooter();
        Pane addBtn = addBtn();
        
        root.getChildren().addAll(header, footer, sp, addBtn);
        sp.setContent(content);
        return root;
    }
    
    public Pane populateStrudels(Pane pane) {
        ArrayList<Strudel> strudels = logic.getTest();
        int nextHeight = -33;
        for (Strudel s : strudels) {
            Pane p = new Pane();
            pane.getChildren().add(p);
            p.setTranslateX(0);
            p.setTranslateY(nextHeight+3);
            p.setPrefSize(width+30, s.getHeight());
            p.setStyle("-fx-background-color: " + s.getColor());
            
            setText(pane, s, nextHeight);
            setVotes(pane, s, nextHeight);
            setTime(pane, s, nextHeight);
            setUpVote(pane, s, nextHeight);
            setDownVote(pane, s, nextHeight);
            
            nextHeight += s.getHeight() + 3;
        }        
        return pane;
    }
    
    public void setText(Pane pane, Strudel s, int height) {
        Text t = new Text(s.getMessage());
        t.setX(10);
        t.setY(height+60);
        t.setFill(Color.WHITE);
        pane.getChildren().add(t);
    }
    
    public void setVotes(Pane pane, Strudel s, int height) {
        Text t = new Text(Integer.toString(s.getVotes()));
        t.setX(width-30);
        t.setY(height+62);
        t.setFill(Color.WHITE);
        t.setFont(t.getFont().font(20));
        pane.getChildren().add(t);
    }
    
    public void setTime(Pane pane, Strudel s, int height) {
        Text t = new Text(logic.getTime(s));
        t.setX(20);
        t.setY(height+25);
        t.setFill(Color.WHITE);
        t.setFont(t.getFont().font(10));
        pane.getChildren().add(t);        
    }
    
    public void setUpVote(Pane pane, Strudel s, int height) {
        Pane upVote = new Pane();
        ImageView upBtn = new ImageView();
        upBtn.setImage(upVoteImg);
        upVote.getChildren().add(upBtn);
        upVote.setOnMouseClicked(event -> upVote(s.getID()));
        upVote.setTranslateX(320);
        upVote.setTranslateY(height+15);
        upVote.setPrefSize(30, 30);
        pane.getChildren().add(upVote);
    }
    
    public void setDownVote(Pane pane, Strudel s, int height) {
        Pane downVote = new Pane();
        ImageView downBtn = new ImageView();
        downBtn.setImage(downVoteImg);
        downVote.getChildren().add(downBtn);
        downVote.setOnMouseClicked(event -> downVote(s.getID()));
        downVote.setTranslateX(320);
        downVote.setTranslateY(height+65);
        downVote.setPrefSize(30, 30);
        pane.getChildren().add(downVote);
    }    
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void upVote(int id) {
        logic.upVote(id);
    }
    
    public void downVote(int id) {
        logic.downVote(id);
    }
    
    public ScrollPane setScrollPane() {
        ScrollPane sp = new ScrollPane();
        sp.setPannable(false);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setTranslateX(0);
        sp.setTranslateY(31);
        sp.setPrefWidth(width+15);
        sp.setPrefHeight(height-50);
        sp.toBack();
        return sp;        
    }
    
    public Pane setFooter() {
        Pane footer = new Pane();
        footer.setPrefWidth(width+15);
        footer.setPrefHeight(30);
        footer.setTranslateY(height-18);
        footer.setStyle("-fx-background-color: #F0591E;");
        return footer;
    }
    
    public Pane setHeader() {
        Pane header = new Pane();
        header.setPrefWidth(width+15);
        header.setPrefHeight(30);
        header.setStyle("-fx-background-color: #000000;");        
        return header;
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
    
    public Pane addBtn() {
        Pane addBtn = new Pane();
        addBtn.setTranslateX((width/2)-20);
        addBtn.setTranslateY(height-80);
        addBtn.setOnMouseClicked(event -> newStrudel());
        Image addImg = new Image("asset/img/add.png");
        ImageView addView = new ImageView();
        addView.setImage(addImg);
        addBtn.getChildren().add(addView);
        return addBtn;
    }
    
    public void newStrudel() {
        System.out.println("new view");
    }
    
}
