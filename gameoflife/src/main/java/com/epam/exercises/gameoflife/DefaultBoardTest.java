package com.epam.exercises.gameoflife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultBoardTest {


    private DefaultBoard subject;

    @Before
    public void setup() {
         subject = new DefaultBoard();
    }

    @Test
    public void newBoardIsPopulatedWithEmptyCells() {
        for (int x = 0; x < GameOfLife.BOARD_DIMENSION_X; x++) {
            for (int y = 0; y < GameOfLife.BOARD_DIMENSION_Y; y++) {
                //GIVEN
                Coordinate coordinate = new Coordinate(x, y);
                //WHEN
                boolean result = subject.isAlive(coordinate);
                //THEN
                Assert.assertFalse(result);
            }
        }
    }

}