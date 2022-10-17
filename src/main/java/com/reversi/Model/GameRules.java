package com.reversi.Model;

import com.reversi.Model.PlayableObjects.Disk;
import com.reversi.Model.PlayableObjects.Table;
import com.reversi.Model.Utils.DiskConfigurations;
import com.reversi.Model.Utils.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;

public class GameRules {
    private Color currentColor;
    private Disk fromPlayedDisk;
    private final Table table;

    public boolean GameIsRunning;

    private boolean containsOnlyDisksByColor(Color color, ArrayList<Disk> fromDisks){
        if (fromDisks.size() == 0){
            return false;
        }

        try {
            for (var disk:
                    fromDisks) {
                if (table.getByCoords(disk.X, disk.Y).Color != color){
                    return false;
                }
            }
            return true;
        }catch (IOException e){
            return false;
        }
    }

    private boolean isPlayable(ImageView img){
        try{
            var selectedDisk = table.getByCoords(img.getX(), img.getY());
            return false;
        } catch (IOException e) {
            if (currentColor == Color.White){
                var disks = table.getAllWhiteDisks();
                for (var disk:
                     disks) {
                    if (containsOnlyDisksByColor(Color.Black, table.getAllDisksFromTo(disk, new Disk(img.getX(), img.getY(), Color.White)))){
                        fromPlayedDisk = disk;
                        return true;
                    }
                }
            } else {
                var disks = table.getAllBlackDisks();
                for (var disk:
                        disks) {
                    if (containsOnlyDisksByColor(Color.White, table.getAllDisksFromTo(disk, new Disk(img.getX(), img.getY(), Color.Black)))){
                        fromPlayedDisk = disk;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void turnAllToColor(Color color, ArrayList<Disk> fromDisks){
        for (var disk:
                fromDisks) {
            table.changeColor(color, disk);
        }
    }

    private boolean isTableFull(){
        for (var array:
                getMatrix()) {
            for (var disk:
                    array) {
                if (disk == null){
                    return false;
                }
            }
        }
        return true;
    }

    public Disk[][] getMatrix(){
        return table.getMatrix();
    }

    public Image playDisk(ImageView img) throws IOException {
        if (currentColor == Color.White){
            if(isPlayable(img)) {
                table.add(img, currentColor);
                if (isTableFull()){
                    GameIsRunning = false;
                }
                turnAllToColor(Color.White, table.getAllDisksFromTo(fromPlayedDisk, new Disk(img.getX(), img.getY(), Color.Black)));
                return DiskConfigurations.getWhiteDisk();
            }
        } else {
            if(isPlayable(img)) {
                table.add(img, currentColor);
                if (isTableFull()){
                    GameIsRunning = false;
                }
                turnAllToColor(Color.Black, table.getAllDisksFromTo(fromPlayedDisk, new Disk(img.getX(), img.getY(), Color.Black)));
                return DiskConfigurations.getBlackDisk();
            }
        }

        throw new IOException("Can not play in the chosen grid");
    }

    public void endTurn(){
        currentColor = currentColor == Color.White ? Color.Black : Color.White;
    }

    public GameRules(){
        GameIsRunning = true;
        currentColor = Color.White;
        table = new Table();
    }
}
