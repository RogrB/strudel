package strudel.view;

import io.StrudelData;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import strudel.logic.Strudel;
import strudel.logic.StrudelComment;
import strudel.logic.StrudelLogic;

public class CommentRenderNode extends MainWindowRenderNode {
    
    StrudelLogic logic = StrudelLogic.getInstance();
    StrudelComment strudel;
    
    public CommentRenderNode(Pane pane, StrudelComment strudel, int y) {
        super(pane, strudel, y);
        this.strudel = strudel;
        compareComments();
    }
    
    public void compareComments() {
        ArrayList<StrudelData> strudelData = logic.getStrudelData();
        for (StrudelData data : strudelData) {
            if (data.getCommentID() == strudel.getCommentID()) {
                strudel.setVoted(true);
                strudel.setVoteDirection(data.getVote());
            }
        }
    }
    
    public void upVoteComment(StrudelComment strudel) {
        if (!strudel.hasVoted()) {
            voteNumber.setText(Integer.toString(strudel.getVotes()+1));
            logic.upVoteComment(strudel.getID(), strudel.getCommentID());
            downBtn.setImage(new Image("asset/img/downVoteHollow.png"));
            upBtn.setImage(new Image("asset/img/upVoteHalf.png"));
            strudel.setVoted(true);
        }
    }
    
    public void downVoteComment(StrudelComment strudel) {
        if (!strudel.hasVoted()) {
            voteNumber.setText(Integer.toString(strudel.getVotes()-1));
            logic.downVoteComment(strudel.getID(), strudel.getCommentID());
            downBtn.setImage(new Image("asset/img/downVoteHalf.png"));
            upBtn.setImage(new Image("asset/img/upVoteHollow.png"));
            strudel.setVoted(true);
        }
    }
    
    @Override
    public void setUpVote() {
        setUpButton();
        Pane upVotePane = new Pane();
        upVotePane.getChildren().add(upBtn);
        upVotePane.setOnMouseClicked(event -> {
            upVoteComment(this.strudel);
            System.out.println("hallo");
                });
        upVotePane.setTranslateX(320);
        upVotePane.setTranslateY(y+15);
        upVotePane.setPrefSize(30, 30);
        parent.getChildren().add(upVotePane);
    }
    
    @Override
    public void setDownVote() {
        setDownButton();
        Pane downVotePane = new Pane();
        downVotePane.getChildren().add(downBtn);
        downVotePane.setOnMouseClicked(event -> downVoteComment(this.strudel));
        downVotePane.setTranslateX(320);
        downVotePane.setTranslateY(y+65);
        downVotePane.setPrefSize(30, 30);
        parent.getChildren().add(downVotePane);
    }    
    
    @Override
    public void comment(Strudel strudel) {
        
    }
    
    @Override
    public void setComment() {
        
    }
    
}
