package strudel.view;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import strudel.logic.Strudel;
import strudel.logic.StrudelLogic;

public class StrudelRenderNode {
    
    private ArrayList<Strudel> strudels;
    private Pane root = new Pane();
    private final ViewController view = ViewController.getInstance();
    private final StrudelLogic logic = StrudelLogic.getInstance();
    Text message = new Text();
    Text timeText = new Text();
    Pane upVotePane = new Pane();
    Pane downVotePane = new Pane();
    ImageView upBtn = new ImageView();
    ImageView downBtn = new ImageView();
    
    public StrudelRenderNode(Pane pane, Strudel strudel, int height) {
        pane.getChildren().add(root);
        root.setTranslateX(0);
        root.setTranslateY(height+3);
        root.setPrefSize(view.getWidth()+30, strudel.getHeight());
        root.setStyle("-fx-background-color: " + strudel.getColor());

        setText(pane, strudel, height);
        setVotes(pane, strudel, height);
        setTime(pane, strudel, height);
        setUpVote(pane, strudel, height);
        setDownVote(pane, strudel, height);        
    }
    

    public void setText(Pane pane, Strudel strudel, int height) {
        message.setText(strudel.getMessage());
        message.setX(10);
        message.setY(height+60);
        message.setFill(Color.WHITE);
        pane.getChildren().add(message);
    }
    
    public void setVotes(Pane pane, Strudel s, int height) {
        Text t = new Text(Integer.toString(s.getVotes()));
        t.setX(view.getWidth()-30);
        t.setY(height+62);
        t.setFill(Color.WHITE);
        t.setFont(t.getFont().font(20));
        pane.getChildren().add(t);
    }
    
    public void setTime(Pane pane, Strudel strudel, int height) {
        timeText.setText(logic.getTime(strudel));
        timeText.setX(20);
        timeText.setY(height+25);
        timeText.setFill(Color.WHITE);
        timeText.setFont(timeText.getFont().font(10));
        pane.getChildren().add(timeText);        
    }

    public void setUpVote(Pane pane, Strudel strudel, int height) {
        upBtn.setImage(new Image("asset/img/upVoteFull.png"));
        upVotePane.getChildren().add(upBtn);
        upVotePane.setOnMouseClicked(event -> upVote(strudel));
        upVotePane.setTranslateX(320);
        upVotePane.setTranslateY(height+15);
        upVotePane.setPrefSize(30, 30);
        pane.getChildren().add(upVotePane);
    }
    
    public void setDownVote(Pane pane, Strudel strudel, int height) {
        downBtn.setImage(new Image("asset/img/downVoteFull.png"));
        downVotePane.getChildren().add(downBtn);
        downVotePane.setOnMouseClicked(event -> downVote(strudel));
        downVotePane.setTranslateX(320);
        downVotePane.setTranslateY(height+65);
        downVotePane.setPrefSize(30, 30);
        pane.getChildren().add(downVotePane);
    }
    
    public void upVote(Strudel strudel) {
        if (strudel.getVoted()) {
            System.out.println("upvote " + strudel.getID());
            logic.upVote(strudel.getID());
            downBtn.setImage(new Image("asset/img/downVoteHollow.png"));
            upBtn.setImage(new Image("asset/img/upVoteHalf.png"));
            strudel.setVoted(true);
        }
    }
    
    public void downVote(Strudel strudel) {
        if (strudel.getVoted()) {
            logic.downVote(strudel.getID());
            downBtn.setImage(new Image("asset/img/downVoteHalf.png"));
            upBtn.setImage(new Image("asset/img/upVoteHollow.png"));
            strudel.setVoted(true);
        }
    }        
    
}
