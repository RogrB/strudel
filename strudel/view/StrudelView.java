package strudel.view;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import strudel.logic.StrudelLogic;
import io.io;

public class StrudelView {
    
    private final int width = 360;
    private int height = 640;
    public Pane root = new Pane();

    StrudelLogic logic = StrudelLogic.getInstance();
    io io = new io();
    WriteStrudelWindow writeStrudel = new WriteStrudelWindow();
    Stage stage;
    
    private static final StrudelView instance = new StrudelView();
    
    private StrudelView(){ }

    public static StrudelView getInstance(){
        return instance;
    }        
    
    public Parent initScene() {
        MainWindow mainWindow = new MainWindow();
        root = mainWindow.init();
        writeStrudel.setView(this);
        writeStrudel.setHeight(height);
        writeStrudel.setWidth(width);
        
        return root;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void newStrudel() {
        root = writeStrudel.init();
        Scene scene = new Scene(root);
        stage.setScene(scene);       
        showStage();
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void showStage() {
        stage.show();
    }
    
    public void resetScene() {
        Scene scene = new Scene(initScene());
        stage.setScene(scene);
        showStage();
    }
    
    public void sortNew() {
        System.out.println("Sorting by new");
    }
    
    public void sortComment() {
        System.out.println("Sorting by comments");
    }
    
    public void sortVotes() {
        System.out.println("Sorting by votes");
    }
    
}
