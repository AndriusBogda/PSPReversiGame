package com.reversi.Model.PlayableObjects;

import javafx.scene.image.ImageView;

public class Disk {
    public Double X;
    public Double Y;
    public com.reversi.Model.Utils.Color Color;

    public Disk(ImageView img, com.reversi.Model.Utils.Color color){
        X = img.getX();
        Y = img.getY();
        Color = color;
    }

    public Disk(Double x, Double y, com.reversi.Model.Utils.Color color){
        X = x;
        Y = y;
        Color = color;
    }
}
