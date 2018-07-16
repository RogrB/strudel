package strudel.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import io.ioReader;
import java.util.ArrayList;
import javafx.scene.control.Control;
import strudel.logic.Strudel;
import strudel.logic.StrudelLogic;

public class MainWindow {
    
    Pane root;
    ViewController view = ViewController.getInstance();
    ioReader io = new ioReader();
    StrudelLogic logic = StrudelLogic.getInstance();
    Pane content = new Pane();
    ArrayList<MainWindowRenderNode> renderNodes = new ArrayList();
    
    public Pane init() {
        root = new Pane();
        root.setPrefSize(view.getWidth(), view.getHeight());
        MainWindowHeader header = new MainWindowHeader();
        Pane headerPane = header.setHeader();
        content = setContent();
        ScrollPane scrollPane = setScrollPane();
        MainWindowFooter footer = new MainWindowFooter();
        Pane footerPane = footer.setFooter();
        Pane addBtn = addBtn();
        
        root.getChildren().addAll(headerPane, footerPane, scrollPane, addBtn);
        scrollPane.setContent(content);
        
        return root;
    }
    
    /*
    public void updateContent() {
        populateStrudels(content);
    }*/
    
    public Pane setContent() {
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
            renderNodes.add(new MainWindowRenderNode(pane, strudel, y));
            y += strudel.getHeight() + 3;
        }        
        return pane;
    }
        
}
