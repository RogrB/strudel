package strudel.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MainWindowFooter {
    
    ImageView sortByCommentsImage = new ImageView();
    ImageView sortByVotesImage = new ImageView();
    ImageView sortByNewestImage = new ImageView();
    ViewController view = ViewController.getInstance();
    
    public MainWindowFooter() {
        setSortByImages();
    }
    
    public void setSortByImages() {
        String sortBy = view.getSortBy();
        switch(sortBy) {
            case "comments":
                sortByCommentsImage.setImage(new Image("asset/img/comment_highlight.png"));            
                sortByVotesImage.setImage(new Image("asset/img/votes_regular.png"));
                sortByNewestImage.setImage(new Image("asset/img/clock_regular.png"));
                break;
            case "newest":
                sortByNewestImage.setImage(new Image("asset/img/clock_highlight.png"));            
                sortByVotesImage.setImage(new Image("asset/img/votes_regular.png"));
                sortByCommentsImage.setImage(new Image("asset/img/comment_regular.png"));     
                break;
            case "votes":
                sortByVotesImage.setImage(new Image("asset/img/votes_highlight.png"));            
                sortByCommentsImage.setImage(new Image("asset/img/comment_regular.png"));
                sortByNewestImage.setImage(new Image("asset/img/clock_regular.png"));            
                break;
        }     
    }
    
    public Pane setFooter() {
        Pane footer = new Pane();
        footer.setPrefWidth(view.getWidth()+15);
        footer.setPrefHeight(30);
        footer.setTranslateY(view.getHeight()-18);
        footer.setStyle("-fx-background-color: #FFFFFF;");
        
        Pane newPane = initSortByNewestImage();
        Pane commentPane = initSortByComments();
        Pane votesPane = initSortByVotes();
        
        footer.getChildren().addAll(newPane, commentPane, votesPane);
        return footer;
    }
    
    public Pane initSortByNewestImage() {
        Pane newPane = new Pane();
        sortByNewestImage.setFitWidth(20);
        sortByNewestImage.setFitHeight(20);
        sortByNewestImage.setX(55);
        sortByNewestImage.setY(5);
        newPane.setOnMouseClicked(event -> sortByNewest());
        newPane.getChildren().add(sortByNewestImage);
        newPane.setPrefWidth(view.getWidth()/3);
        newPane.setTranslateX(1); 
        return newPane;
    }
    
    public Pane initSortByComments() {
        Pane commentPane = new Pane();
        sortByCommentsImage.setFitWidth(20);
        sortByCommentsImage.setFitHeight(20);
        sortByCommentsImage.setX(55);
        sortByCommentsImage.setY(5);
        commentPane.setOnMouseClicked(event -> sortByComments());
        commentPane.getChildren().add(sortByCommentsImage);
        commentPane.setPrefWidth(view.getWidth()/3);
        commentPane.setTranslateX(120);     
        return commentPane;
    }
    
    public Pane initSortByVotes() {
        Pane votesPane = new Pane();
        sortByVotesImage.setFitWidth(20);
        sortByVotesImage.setFitHeight(20);
        sortByVotesImage.setX(55);
        sortByVotesImage.setY(5);
        votesPane.setOnMouseClicked(event -> sortByVotes());
        votesPane.getChildren().add(sortByVotesImage);
        votesPane.setPrefWidth(view.getWidth()/3);
        votesPane.setTranslateX(240);        
        return votesPane;
    }
    
    public void sortByComments() {
        view.setSortBy("comments");
        view.sortByComments();

    }
    
    public void sortByNewest() {
        view.setSortBy("newest");
        view.sortByNewest();

    }
    
    public void sortByVotes() {
        view.setSortBy("votes");
        view.sortByVotes();
    }
    
}
