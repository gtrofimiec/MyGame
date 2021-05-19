package com.kodilla.mygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyGameTestSuite {
    @Test
    void testFirstMove() {
        //Given
        Random throwing = new Random();
        List<Pawn> pawnList = new ArrayList<>();
        List<ImageView> pawnsImageView = new ArrayList<>();
        Image playerPawnImage= new Image((this.getClass().getResource("/pawn1.png")).toString(),
                30,30,false,false);
        ImageView imageView = new ImageView();
        Player player1 = new Player("player1", pawnList, pawnsImageView, playerPawnImage, 0);
        Pawn pawn = new Pawn(player1,imageView,0);

        //When

        //Then

    }
}
