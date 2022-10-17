package com.reversi;

import com.reversi.Model.GameRules;
import com.reversi.Model.Utils.Color;
import javafx.scene.image.ImageView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class Tests{
    private GameRules Game;

    @Before
    public void setup(){
        Game = new GameRules();
    }

    @Test
    public void testStartingDiskExist(){
        var matrix = Game.getMatrix();

        Assert.assertTrue("starting disk was not placed on the table", matrix[3][3] != null);
        Assert.assertTrue("starting disk was correct color", matrix[3][3].Color == Color.Black);

        Assert.assertTrue("starting disk was not placed on the table", matrix[3][4] != null);
        Assert.assertTrue("starting disk was correct color", matrix[3][4].Color == Color.White);

        Assert.assertTrue("starting disk was not placed on the table", matrix[4][3] != null);
        Assert.assertTrue("starting disk was correct color", matrix[4][3].Color == Color.White);

        Assert.assertTrue("starting disk was not placed on the table", matrix[4][4] != null);
        Assert.assertTrue("starting disk was correct color", matrix[4][4].Color == Color.Black);
    }

    @Test
    public void testAddDiskToTable(){
        var matrix = Game.getMatrix();

        var imgToBePlayed = new ImageView();
        imgToBePlayed.setX(3);
        imgToBePlayed.setY(2);

        try{
            Game.playDisk(imgToBePlayed);
        } catch (IOException e){
            Assert.fail();
        } catch (RuntimeException e){
            Assert.assertTrue("disk is played on the table",matrix[3][2] != null);
        }
    }

    @Test
    public void testCantAddNonAdjacentDisk(){
        var matrix = Game.getMatrix();

        var imgToBePlayed = new ImageView();
        imgToBePlayed.setX(1);
        imgToBePlayed.setY(1);

        try{
            Game.playDisk(imgToBePlayed);
        } catch (IOException e){
            Assert.assertTrue("Could not play a disk without any adjacent disks on the table", true);
        } catch (RuntimeException e){
            Assert.fail("disk was played on the table");
        }
    }

    @Test
    public void testFirstMoveIsWhiteDisk(){
        var matrix = Game.getMatrix();

        var imgToBePlayed = new ImageView();
        imgToBePlayed.setX(3);
        imgToBePlayed.setY(2);

        try{
            Game.playDisk(imgToBePlayed);
        } catch (IOException e){
            Assert.fail("disk was not played on the table");
        } catch (RuntimeException e){
            Assert.assertTrue("correct disk is played on the table",matrix[3][2] != null && matrix[3][2].Color == Color.White);
        }
    }
}
