package strudel;

import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import io.io;

public class strudelView {
    
    private final int width = 360;
    private int height = 640;
    public Pane root = new Pane();
    Image upVoteImg = new Image("asset/img/upVoteFull.png");
    Image downVoteImg = new Image("asset/img/downVoteFull.png");
    strudelLogic logic = new strudelLogic();
    io io = new io();
    Stage stage;
    TextArea ta = new TextArea();
    
    public Parent initScene() {
        root = new Pane();
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
        logic.makeTest();
        ArrayList<Strudel> strudels = logic.getStrudels();
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
        footer.setStyle("-fx-background-color: #FFFFFF;");
        
        ImageView clock = new ImageView(new Image("asset/img/clock_highlight.png"));
        clock.setFitWidth(20);
        clock.setFitHeight(20);
        clock.setX(50);
        clock.setY(5);
        Pane newPane = new Pane();
        newPane.setOnMouseClicked(event -> sortNew());
        newPane.getChildren().add(clock);
        newPane.setPrefWidth(width/3);
        newPane.setTranslateX(1);
        
        ImageView comment = new ImageView(new Image("asset/img/comment_regular.png"));
        comment.setFitWidth(20);
        comment.setFitHeight(20);
        comment.setX(50);
        comment.setY(5);
        Pane commentPane = new Pane();
        commentPane.setOnMouseClicked(event -> sortComment());
        commentPane.getChildren().add(comment);
        commentPane.setPrefWidth(width/3);
        commentPane.setTranslateX(120);
        
        ImageView votes = new ImageView(new Image("asset/img/votes_regular.png"));
        votes.setFitWidth(20);
        votes.setFitHeight(20);
        votes.setX(50);
        votes.setY(5);
        Pane votesPane = new Pane();
        votesPane.setOnMouseClicked(event -> sortVotes());
        votesPane.getChildren().add(votes);
        votesPane.setPrefWidth(width/3);
        votesPane.setTranslateX(240);
        
        footer.getChildren().addAll(newPane, commentPane, votesPane);
        return footer;
    }
    
    public Pane setHeader() {
        Pane header = new Pane();
        header.setPrefWidth(width+15);
        header.setPrefHeight(30);
        header.setStyle("-fx-background-color: #FFFFFF;");
        ImageView logo = new ImageView(new Image("asset/img/strudel.png"));
        Pane logoPane = new Pane();
        logoPane.setOnMouseClicked(event -> reSetScene());
        logoPane.getChildren().add(logo);
        logo.setX((width/2)-35);
        Text karma = new Text("My Karma");
        karma.setFont(karma.getFont().font(10));
        karma.setX(width-50);
        karma.setY(25);
        Text karmaNbr = new Text(Integer.toString(io.getKarma()));
        karmaNbr.setX(width-40);
        karmaNbr.setY(15);
        karmaNbr.setFont(karmaNbr.getFont().font(18));
        header.getChildren().addAll(logoPane, karma, karmaNbr);
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
        root = new Pane();
        root.setPrefSize(width, height);
        root.getChildren().removeAll();
        Scene scene = new Scene(root);
        Pane header = setWriteHeader();
        Pane content = setWriteTextBox();
        root.getChildren().addAll(header, content);
        stage.setScene(scene);        
        showStage();
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void showStage() {
        stage.show();
    }
    
    public void reSetScene() {
        System.out.println("reset scene");
        Scene scene = new Scene(initScene());
        stage.setScene(scene);
        showStage();
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
        arrowPane.setOnMouseClicked(event-> reSetScene());
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
        content.setStyle("-fx-background-color: #" + color);
        ta.setPromptText("Share something with the world");
        ta.setPrefSize(width, height-29);
        ta.setStyle("-fx-control-inner-background:#" + color + "; -fx-font-family: Consolas; -fx-text-fill: #FFFFFF; ");
        content.getChildren().add(ta);
        return content;
    }
    
    public void sortNew() {
        System.out.println("Sorting by new");
    }
    
    public void sortComment() {
        System.out.println("Sorting by comments");
    }
    
    public void sortVotes() {
        System.out.println("Soring by votes");
    }
    
    public void post() {
        System.out.println("posting");
        String message = ta.getText().replaceAll("\n", System.getProperty("line.separator"));
        logic.post(message);
        reSetScene();
    }
    
}
