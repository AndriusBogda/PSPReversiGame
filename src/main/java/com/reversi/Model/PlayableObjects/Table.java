package com.reversi.Model.PlayableObjects;

import com.reversi.Model.Utils.Color;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;

public class Table {
    private Disk[][] Matrix;

    public ArrayList<Disk> getAllDisksFromTo(Disk sD, Disk eD){
        ArrayList<Disk> disks = new ArrayList();

        var betweenDisk = new Disk(sD.X, sD.Y, Color.Grey);

        while(betweenDisk.X.compareTo( eD.X) != 0 || betweenDisk.Y.compareTo( eD.Y) != 0){
            if (betweenDisk.X.compareTo( eD.X) < 0) {
                betweenDisk.X++;
            } else if (betweenDisk.X.compareTo( eD.X) > 0){
                betweenDisk.X--;
            }

            if (betweenDisk.Y.compareTo( eD.Y) < 0) {
                betweenDisk.Y++;
            } else if (betweenDisk.Y.compareTo( eD.Y) > 0){
                betweenDisk.Y--;
            }

            if (betweenDisk.X.compareTo( eD.X) != 0 || betweenDisk.Y.compareTo( eD.Y) != 0){
                var disk = new Disk(betweenDisk.X, betweenDisk.Y, betweenDisk.Color);
                disks.add(disk);
            }
        }

        return disks;
    }

    public Disk getByCoords(Double x, Double y) throws IOException {
        for (var array:
             Matrix) {
            for (var disk:
                    array) {
                if ( disk != null &&
                        disk.X.compareTo(x) == 0 &&
                        disk.Y.compareTo(y) == 0){
                    return disk;
                }
            }
        }
        throw new IOException("Such disk is not in the matrix");
    }

    public ArrayList<Disk> getAllBlackDisks(){
        var disks = new ArrayList<Disk>();

        for (var array:
                Matrix) {
            for (var disk:
                    array) {
                if (disk != null && disk.Color == Color.Black){
                    disks.add(disk);
                }
            }
        }

        return disks;
    }

    public ArrayList<Disk> getAllWhiteDisks(){
        var disks = new ArrayList<Disk>();

        for (var array:
                Matrix) {
            for (var disk:
                    array) {
                if (disk != null && disk.Color == Color.White){
                    disks.add(disk);
                }
            }
        }

        return disks;
    }

    public void changeColor(Color color, Disk disk){
        Matrix[Integer.parseInt(Double.toString(disk.X).substring(0, 1))][Integer.parseInt(Double.toString(disk.Y).substring(0, 1))].Color = color;
    }

    public Table(){
        Matrix = new Disk[8][8];

        Matrix[3][3] = new Disk(3., 3., Color.Black);
        Matrix[4][3] = new Disk(4., 3., Color.White);
        Matrix[3][4] = new Disk(3., 4., Color.White);
        Matrix[4][4] = new Disk(4., 4., Color.Black);
    }

    public boolean add(ImageView img, Color currentColor){
        if (containsCoordinates(img.getX(), img.getY())){
            return false;
        } else {
            Matrix[Integer.parseInt(Double.toString(img.getX()).substring(0, 1))][Integer.parseInt(Double.toString(img.getY()).substring(0, 1))] = new Disk(img, currentColor);
            return true;
        }
    }

    public Disk[][] getMatrix(){ return Matrix; }

    private boolean containsCoordinates(Double x, Double y){
        for (var diskArray:
             Matrix) {
            for (var disk:
                    diskArray) {
                if (disk != null &&
                        disk.X.compareTo(x) == 0 &&
                        disk.Y.compareTo(y) == 0){
                    return true;
                }
            }
        }
        return false;
    }
}
