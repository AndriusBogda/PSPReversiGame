package com.reversi.Model.Utils;

import com.reversi.Main;
import javafx.scene.image.Image;

public class DiskConfigurations {
    public static Image getWhiteDisk(){
        return new Image(Main.class.getResource("Utils/White.jpg").getPath().substring(1));
    }
    public static Image getBlackDisk(){
        return new Image(Main.class.getResource("Utils/black.jpg").getPath().substring(1));
    }
}