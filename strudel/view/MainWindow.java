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
        
        MainWindowHeader header = new MainWindowHeader();
        Pane headerPane = header.setHeader();
        Pane content = setContent();
        ScrollPane scrollPane = setScrollPane();
        MainWindowFooter footer = new MainWindowFooter();
        Pane footerPane = footer.setFooter();
        Pane addBtn = addBtn();
        
        root.getChildren().addAll(headerPane, footerPane, scrollPane, addBtn);
        scrollPane.setContent(content);
        
        return root;
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
        
}
