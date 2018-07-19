package strudel.view;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import strudel.logic.Strudel;
import strudel.logic.StrudelComment;
import strudel.logic.StrudelLogic;

public class CommentRenderNode extends MainWindowRenderNode {
    
    StrudelLogic logic = StrudelLogic.getInstance();
    
    public CommentRenderNode(Pane pane, StrudelComment strudel, int y) {
        super(pane, strudel, y);
    }    
    
    public void upVote(StrudelComment strudel) {
        if (!strudel.hasVoted()) {
            voteNumber.setText(Integer.toString(strudel.getVotes()+1));
            logic.upVoteComment(strudel.getCommentID());
            downBtn.setImage(new Image("asset/img/downVoteHollow.png"));
            upBtn.setImage(new Image("asset/img/upVoteHalf.png"));
            strudel.setVoted(true);
        }
    }
    
    public void downVote(StrudelComment strudel) {
        if (!strudel.hasVoted()) {
            voteNumber.setText(Integer.toString(strudel.getVotes()-1));
            logic.downVoteComment(strudel.getCommentID());
            downBtn.setImage(new Image("asset/img/downVoteHalf.png"));
            upBtn.setImage(new Image("asset/img/upVoteHollow.png"));
            strudel.setVoted(true);
        }
    }
    
    @Override
    public void comment(Strudel strudel) {
        
    }
    
    @Override
    public void setComment() {
        
    }
    
}
