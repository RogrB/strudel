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
    ImageView sortByCommentsImage = new ImageView(new Image("asset/img/comment_regular.png"));
    ImageView sortByVotesImage = new ImageView(new Image("asset/img/votes_regular.png"));
    ImageView sortByNewestImage = new ImageView(new Image("asset/img/clock_highlight.png"));
    
    public Pane init() {
        root = new Pane();
        root.setPrefSize(view.getWidth(), view.getHeight());
        
        Pane header = setHeader();
        Pane content = setContent();
        ScrollPane scrollPane = setScrollPane();
        Pane footer = setFooter();
        Pane addBtn = addBtn();
        
        root.getChildren().addAll(header, footer, scrollPane, addBtn);
        scrollPane.setContent(content);
        
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
        
        sortByNewestImage.setFitWidth(20);
        sortByNewestImage.setFitHeight(20);
        sortByNewestImage.setX(55);
        sortByNewestImage.setY(5);
        Pane newPane = new Pane();
        newPane.setOnMouseClicked(event -> sortByNewest());
        newPane.getChildren().add(sortByNewestImage);
        newPane.setPrefWidth(view.getWidth()/3);
        newPane.setTranslateX(1);
        
        sortByCommentsImage.setFitWidth(20);
        sortByCommentsImage.setFitHeight(20);
        sortByCommentsImage.setX(55);
        sortByCommentsImage.setY(5);
        Pane commentPane = new Pane();
        commentPane.setOnMouseClicked(event -> sortByComments());
        commentPane.getChildren().add(sortByCommentsImage);
        commentPane.setPrefWidth(view.getWidth()/3);
        commentPane.setTranslateX(120);
        
        sortByVotesImage.setFitWidth(20);
        sortByVotesImage.setFitHeight(20);
        sortByVotesImage.setX(55);
        sortByVotesImage.setY(5);
        Pane votesPane = new Pane();
        votesPane.setOnMouseClicked(event -> sortByVotes());
        votesPane.getChildren().add(sortByVotesImage);
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
        ArrayList<Strudel> strudels = logic.getStrudels();
        int y = -33;
        for (Strudel strudel : strudels) {
            renderNodes.add(new StrudelRenderNode(pane, strudel, y));
            y += strudel.getHeight() + 3;
        }        
        return pane;
    }
    
    public void sortByComments() {
        sortByCommentsImage.setImage(new Image("asset/img/comment_highlight.png"));
        view.sortByComments();
        //sortByVotesImage.setImage(new Image("asset/img/votes_regular"));
        //sortByNewestImage.setImage(new Image("asset/img/clock_regular"));
    }
    
    public void sortByNewest() {
        //sortByNewestImage.setImage(new Image("asset/img/clock_highlight"));
        view.sortByNewest();
        //sortByVotesImage.setImage(new Image("asset/img/votes_regular"));
        //sortByCommentsImage.setImage(new Image("asset/img/comment_regular.png"));
    }
    
    public void sortByVotes() {
        sortByVotesImage.setImage(new Image("asset/img/votes_highlight"));
        view.sortByVotes();
        //sortByCommentsImage.setImage(new Image("asset/img/comment_regular.png"));
        //sortByNewestImage.setImage(new Image("asset/img/clock_regular"));
    }
        
}
