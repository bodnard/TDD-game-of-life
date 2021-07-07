package com.epam.exercises.gameoflife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
                assertFalse(result);
            }
        }
    }

    @Test
    public void newCellAddedByUser() {
        //GIVEN
        Coordinate coordinate = new Coordinate(10, 10);
        //WHEN
        subject.insertCell(coordinate);
        boolean result = subject.isAlive(coordinate);
        //THEN
        assertTrue(result);
    }

    @Test
    public void whenZeroNeighborThenDie() {
        //GIVEN
        Coordinate coordinate = new Coordinate(10, 10);
        //WHEN
        subject.insertCell(coordinate);
        boolean result = subject.isAlive(coordinate) &&
            !subject.isAlive(new Coordinate(9, 9)) &&
            !subject.isAlive(new Coordinate(9, 10)) &&
            !subject.isAlive(new Coordinate(9, 11)) &&
            !subject.isAlive(new Coordinate(10, 9)) &&
            !subject.isAlive(new Coordinate(10, 11)) &&
            !subject.isAlive(new Coordinate(11, 9)) &&
            !subject.isAlive(new Coordinate(11, 10)) &&
            !subject.isAlive(new Coordinate(11, 11));
        //THEN
        assertFalse(subject.getNextGenerationBoard().isAlive(coordinate));
    }
}