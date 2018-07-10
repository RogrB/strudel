package strudel.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import io.io;
import java.util.ArrayList;
import javafx.scene.control.Control;
import strudel.logic.Strudel;
import strudel.logic.StrudelLogic;

public class MainWindow {
    
    Pane root;
    ViewController view = ViewController.getInstance();
    io io = new io();
    StrudelLogic logic = StrudelLogic.getInstance();
    ArrayList<StrudelRenderNode> renderNodes = new ArrayList();
    
    public Pane init() {
        root = new Pane();
        root.setPrefSize(view.getWidth(), view.getHeight());
        
        Pane header = setHeader();
        Pane content = setContent();
        ScrollPane sp = setScrollPane();
        Pane footer = setFooter();
        Pane addBtn = addBtn();
        
        root.getChildren().addAll(header, footer, sp, addBtn);
        sp.setContent(content);
        
        return root;
    }
    
    public Pane setHeader() {
        Pane header = new Pane();
        header.setPrefWidth(view.getWidth()+15);
        header.setPrefHeight(30);
        header.setStyle("-fx-background-color: #FFFFFF;");
        ImageView logo = new ImageView(new Image("asset/img/strudel.png"));
        Pane logoPane = new Pane();
        logoPane.setOnMouseClicked(event -> view.resetScene());
        logoPane.getChildren().add(logo);
        logo.setX((view.getWidth()/2)-35);
        Text karma = new Text("My Karma");
        karma.setFont(karma.getFont().font(10));
        karma.setX(view.getWidth()-50);
        karma.setY(25);
        Text karmaNbr = new Text(Integer.toString(io.getKarma()));
        karmaNbr.setX(view.getWidth()-40);
        karmaNbr.setY(15);
        karmaNbr.setFont(karmaNbr.getFont().font(18));
        header.getChildren().addAll(logoPane, karma, karmaNbr);
        return header;
    }   
    
    public Pane setFooter() {
        Pane footer = new Pane();
        footer.setPrefWidth(view.getWidth()+15);
        footer.setPrefHeight(30);
        footer.setTranslateY(view.getHeight()-18);
        footer.setStyle("-fx-background-color: #FFFFFF;");
        
        ImageView clock = new ImageView(new Image("asset/img/clock_highlight.png"));
        clock.setFitWidth(20);
        clock.setFitHeight(20);
        clock.setX(55);
        clock.setY(5);
        Pane newPane = new Pane();
        newPane.setOnMouseClicked(event -> view.sortNew());
        newPane.getChildren().add(clock);
        newPane.setPrefWidth(view.getWidth()/3);
        newPane.setTranslateX(1);
        
        ImageView comment = new ImageView(new Image("asset/img/comment_regular.png"));
        comment.setFitWidth(20);
        comment.setFitHeight(20);
        comment.setX(55);
        comment.setY(5);
        Pane commentPane = new Pane();
        commentPane.setOnMouseClicked(event -> view.sortComment());
        commentPane.getChildren().add(comment);
        commentPane.setPrefWidth(view.getWidth()/3);
        commentPane.setTranslateX(120);
        
        ImageView votes = new ImageView(new Image("asset/img/votes_regular.png"));
        votes.setFitWidth(20);
        votes.setFitHeight(20);
        votes.setX(55);
        votes.setY(5);
        Pane votesPane = new Pane();
        votesPane.setOnMouseClicked(event -> view.sortVotes());
        votesPane.getChildren().add(votes);
        votesPane.setPrefWidth(view.getWidth()/3);
        votesPane.setTranslateX(240);
        
        footer.getChildren().addAll(newPane, commentPane, votesPane);
        return footer;
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
    
    public ScrollPane setScrollPane() {
        ScrollPane sp = new ScrollPane();
        sp.setPannable(false);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setTranslateX(0);
        sp.setTranslateY(31);
        sp.setPrefWidth(view.getWidth()+15);
        sp.setPrefHeight(view.getHeight()-50);
        sp.toBack();
        return sp;        
    }    
    
    public Pane addBtn() {
        Pane addBtn = new Pane();
        addBtn.setTranslateX((view.getWidth()/2)-20);
        addBtn.setTranslateY(view.getHeight()-80);
        addBtn.setOnMouseClicked(event -> view.newStrudel());
        Image addImg = new Image("asset/img/add.png");
        ImageView addView = new ImageView();
        addView.setImage(addImg);
        addBtn.getChildren().add(addView);
        return addBtn;
    }   
    
    public Pane populateStrudels(Pane pane) {
        ArrayList<Strudel> strudels = logic.readStrudels();
        int nextHeight = -33;
        for (Strudel s : strudels) {
            renderNodes.add(new StrudelRenderNode(pane, s, nextHeight));
            nextHeight += s.getHeight() + 3;
        }        
        return pane;
    }
        
}
