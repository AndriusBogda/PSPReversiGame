package com.reversi.Control;

import com.reversi.Model.GameRules;
import com.reversi.Model.Utils.Color;
import com.reversi.Model.Utils.DiskFactory;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;

public class GameObserver {
    private GameRules Rules;
    @FXML
    public Rectangle backGround;
    @FXML
    public GridPane GridPane;
    @FXML
    public ImageView x3y4;
    @FXML
    public ImageView x4y4;
    @FXML
    public ImageView x3y3;
    @FXML
    public ImageView x4y3;

    @FXML
    public void initialize(){
        Rules = new GameRules();
        x3y3.setImage(DiskFactory.getBlackDisk());
        x3y4.setImage(DiskFactory.getWhiteDisk());
        x4y3.setImage(DiskFactory.getWhiteDisk());
        x4y4.setImage(DiskFactory.getBlackDisk());
    }

    private ImageView selectedGrid;

    private void endTurn(){
        Rules.endTurn();

        ArrayList<ImageView> grid = new ArrayList();
        for (var item:
                GridPane.getChildren()) {
            if (item instanceof ImageView){
                grid.add((ImageView)item);
            }
        }

        for (var array:
                Rules.getMatrix()) {
            for (var disk:
                 array) {
                if (disk != null){
                    for (var gridDisk:
                         grid) {
                        if (gridDisk != null &&
                                disk.X.compareTo(gridDisk.getX()) == 0 && disk.Y.compareTo(gridDisk.getY()) == 0){
                            if (disk.Color == Color.White){
                                gridDisk.setImage(DiskFactory.getWhiteDisk());
                            } else {
                                gridDisk.setImage(DiskFactory.getBlackDisk());
                            }
                        }
                    }
                }
            }
        }
    }

    public void listenForSelection(MouseEvent event) {
        if (Rules.GameIsRunning){
            Object source = event.getSource();
            if (source instanceof ImageView clickedImageView &&
                    clickedImageView != selectedGrid) {
                selectedGrid = clickedImageView;
                try{
                    selectedGrid.setImage(Rules.playDisk(selectedGrid));
                    endTurn();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        } else {
            //some game over img over the screen, mby timer, then off the app
        }
    }
}
