package strudel.view;

import io.ioController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class MainWindowHeader {
    
    ViewController view = ViewController.getInstance();
    ioController io = new ioController();
    
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
    
}
