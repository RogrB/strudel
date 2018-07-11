package strudel.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import strudel.logic.Strudel;
import strudel.logic.StrudelLogic;

public class StrudelRenderNode {
    
    private Pane root = new Pane();
    Pane parent;
    Strudel strudel;
    int height;
    int comments;
    private final ViewController view = ViewController.getInstance();
    private final StrudelLogic logic = StrudelLogic.getInstance();
    Text message = new Text();
    Text timeText = new Text();
    Text voteNumber = new Text();
    Text commentText = new Text();
    ImageView upBtn = new ImageView();
    ImageView downBtn = new ImageView();
    
    public StrudelRenderNode(Pane pane, Strudel strudel, int height) {
        this.parent = pane;
        this.strudel = strudel;
        this.height = height;
        pane.getChildren().add(root);
        root.setTranslateX(0);
        root.setTranslateY(height+3);
        root.setPrefSize(view.getWidth()+30, strudel.getHeight());
        root.setStyle("-fx-background-color: " + strudel.getColor());   
        root.setOnMouseClicked(event -> comment());
        
        init();
    }
    
    public void init() {
        setText();
        setVotes();
        setTime();
        setUpVote();
        setDownVote();
        this.comments = logic.countComments(strudel.getID());
        if (comments > 0) {
            setComment();
        }
    }
    

    public void setText() {
        message.setText(strudel.getMessage());
        message.setX(10);
        message.setY(height+60);
        message.setFill(Color.WHITE);
        parent.getChildren().add(message);
    }
    
    public void setVotes() {
        voteNumber.setText(Integer.toString(strudel.getVotes()));
        voteNumber.setX(view.getWidth()-30);
        voteNumber.setY(height+62);
        voteNumber.setFill(Color.WHITE);
        voteNumber.setFont(voteNumber.getFont().font(20));
        parent.getChildren().add(voteNumber);
    }
    
    public void setTime() {
        timeText.setText(logic.getTime(strudel));
        timeText.setX(20);
        timeText.setY(height+20);
        timeText.setFill(Color.WHITE);
        timeText.setFont(timeText.getFont().font(10));
        parent.getChildren().add(timeText);        
    }

    public void setUpVote() {
        upBtn.setImage(new Image("asset/img/upVoteFull.png"));
        Pane upVotePane = new Pane();
        upVotePane.getChildren().add(upBtn);
        upVotePane.setOnMouseClicked(event -> upVote(strudel));
        upVotePane.setTranslateX(320);
        upVotePane.setTranslateY(height+15);
        upVotePane.setPrefSize(30, 30);
        parent.getChildren().add(upVotePane);
    }
    
    public void setDownVote() {
        downBtn.setImage(new Image("asset/img/downVoteFull.png"));
        Pane downVotePane = new Pane();
        downVotePane.getChildren().add(downBtn);
        downVotePane.setOnMouseClicked(event -> downVote(strudel));
        downVotePane.setTranslateX(320);
        downVotePane.setTranslateY(height+65);
        downVotePane.setPrefSize(30, 30);
        parent.getChildren().add(downVotePane);
    }
    
    public void upVote(Strudel strudel) {
        if (!strudel.hasVoted()) {
            voteNumber.setText(Integer.toString(strudel.getVotes()+1));
            logic.upVote(strudel.getID());
            downBtn.setImage(new Image("asset/img/downVoteHollow.png"));
            upBtn.setImage(new Image("asset/img/upVoteHalf.png"));
            strudel.setVoted(true);
        }
    }
    
    public void downVote(Strudel strudel) {
        if (!strudel.hasVoted()) {
            voteNumber.setText(Integer.toString(strudel.getVotes()-1));
            logic.downVote(strudel.getID());
            downBtn.setImage(new Image("asset/img/downVoteHalf.png"));
            upBtn.setImage(new Image("asset/img/upVoteHollow.png"));
            strudel.setVoted(true);
        }
    }
    
    public void setComment() {
        Pane commentPane = new Pane();
        commentPane.setTranslateX(10);
        commentPane.setTranslateY(height+strudel.getHeight()-20); 
        commentPane.setOnMouseClicked(event -> comment());
        
        Text commentCount = new Text(Integer.toString(this.comments));
        commentCount.setX(20);
        commentCount.setY(height+strudel.getHeight()-55);
        commentCount.setFill(Color.WHITE);
        commentCount.setFont(commentCount.getFont().font(12));
        
        ImageView commentImg = new ImageView();
        commentImg.setImage(new Image("asset/img/commentCounter.png"));
        commentImg.setFitHeight(15);
        commentImg.setFitWidth(15);
        
        commentPane.getChildren().addAll(commentImg, commentCount);
        parent.getChildren().add(commentPane);
    }   
    
    public void comment() {
        System.out.println("comment view");
    }
    
}
