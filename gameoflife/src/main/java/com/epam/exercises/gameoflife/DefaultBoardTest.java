package com.epam.exercises.gameoflife;

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
        boolean condition = subject.isAlive(coordinate) &&
            !subject.isAlive(new Coordinate(9, 9)) &&
            !subject.isAlive(new Coordinate(9, 10)) &&
            !subject.isAlive(new Coordinate(9, 11)) &&
            !subject.isAlive(new Coordinate(10, 9)) &&
            !subject.isAlive(new Coordinate(10, 11)) &&
            !subject.isAlive(new Coordinate(11, 9)) &&
            !subject.isAlive(new Coordinate(11, 10)) &&
            !subject.isAlive(new Coordinate(11, 11));
        assertTrue(condition);
        boolean result = subject.getNextGenerationBoard().isAlive(coordinate);
        //THEN
        assertFalse(result);
    }

    @Test
    public void whenOneNeighbourThenDie() {
        //GIVEN
        Coordinate coordinateCentral = new Coordinate(10, 10);
        Coordinate coordinateNeighbour = new Coordinate(11, 10);
        //WHEN
        subject.insertCell(coordinateCentral);
        subject.insertCell(coordinateNeighbour);
        boolean condition = subject.isAlive(coordinateCentral) &&
            !subject.isAlive(new Coordinate(9, 9)) &&
            !subject.isAlive(new Coordinate(9, 10)) &&
            !subject.isAlive(new Coordinate(9, 11)) &&
            !subject.isAlive(new Coordinate(10, 9)) &&
            !subject.isAlive(new Coordinate(10, 11)) &&
            !subject.isAlive(new Coordinate(11, 9)) &&
            subject.isAlive(coordinateNeighbour) &&
            !subject.isAlive(new Coordinate(11, 11));
        assertTrue(condition);
        boolean result = subject.getNextGenerationBoard().isAlive(coordinateCentral);
        //THEN
        assertFalse(result);
    }

    @Test
    public void whenAliveAndTwoNeighboursAreOnTheHorizontalAxesSurvives() {
        //GIVEN
        Coordinate coordinateCentral = new Coordinate(10, 10);
        Coordinate coordinateNeighbourRight = new Coordinate(11, 10);
        Coordinate coordinateNeighbourLeft = new Coordinate(9, 10);
        //WHEN
        subject.insertCell(coordinateCentral);
        subject.insertCell(coordinateNeighbourRight);
        subject.insertCell(coordinateNeighbourLeft);
        boolean condition = subject.isAlive(coordinateCentral) &&
            subject.isAlive(coordinateNeighbourLeft) &&
            subject.isAlive(coordinateNeighbourRight) &&
            !subject.isAlive(new Coordinate(9, 9)) &&
            !subject.isAlive(new Coordinate(9, 11)) &&
            !subject.isAlive(new Coordinate(10, 9)) &&
            !subject.isAlive(new Coordinate(10, 11)) &&
            !subject.isAlive(new Coordinate(11, 9)) &&
            !subject.isAlive(new Coordinate(11, 11));
        assertTrue(condition);
        boolean result = subject.getNextGenerationBoard().isAlive(coordinateCentral);
        //THEN
        assertTrue(result);
    }

    @Test
    public void whenAliveAndTwoNeighboursAreOnTheVerticalAxesSurvives() {
        //GIVEN
        Coordinate coordinateCentral = new Coordinate(10, 10);
        Coordinate coordinateNeighbourTop = new Coordinate(10, 9);
        Coordinate coordinateNeighbourBottom = new Coordinate(10, 11);
        //WHEN
        subject.insertCell(coordinateCentral);
        subject.insertCell(coordinateNeighbourTop);
        subject.insertCell(coordinateNeighbourBottom);
        boolean condition = subject.isAlive(coordinateCentral) &&
            subject.isAlive(coordinateNeighbourBottom) &&
            subject.isAlive(coordinateNeighbourTop) &&
            !subject.isAlive(new Coordinate(9, 9)) &&
            !subject.isAlive(new Coordinate(9, 10)) &&
            !subject.isAlive(new Coordinate(9, 11)) &&
            !subject.isAlive(new Coordinate(11, 9)) &&
            !subject.isAlive(new Coordinate(11, 10)) &&
            !subject.isAlive(new Coordinate(11, 11));
        assertTrue(condition);
        boolean result = subject.getNextGenerationBoard().isAlive(coordinateCentral);
        //THEN
        assertTrue(result);
    }

    @Test
    public void whenAliveAndTwoNeighboursAreOnTheLeftDiagonalAxesSurvives() {
        //GIVEN
        Coordinate coordinateCentral = new Coordinate(10, 10);
        Coordinate coordinateNeighbourTopLeft = new Coordinate(9, 9);
        Coordinate coordinateNeighbourBottomRight = new Coordinate(11, 11);
        //WHEN
        subject.insertCell(coordinateCentral);
        subject.insertCell(coordinateNeighbourTopLeft);
        subject.insertCell(coordinateNeighbourBottomRight);
        boolean condition = subject.isAlive(coordinateCentral) &&
            subject.isAlive(coordinateNeighbourBottomRight) &&
            subject.isAlive(coordinateNeighbourTopLeft) &&
            !subject.isAlive(new Coordinate(9, 10)) &&
            !subject.isAlive(new Coordinate(9, 11)) &&
            !subject.isAlive(new Coordinate(10, 11)) &&
            !subject.isAlive(new Coordinate(10, 9)) &&
            !subject.isAlive(new Coordinate(11, 9)) &&
            !subject.isAlive(new Coordinate(11, 10));
        assertTrue(condition);
        boolean result = subject.getNextGenerationBoard().isAlive(coordinateCentral);
        //THEN
        assertTrue(result);
    }

    @Test
    public void whenAliveAndTwoNeighboursAreOnTheRightDiagonalAxesSurvives() {
        //GIVEN
        Coordinate coordinateCentral = new Coordinate(10, 10);
        Coordinate coordinateNeighbourTopRight = new Coordinate(11, 9);
        Coordinate coordinateNeighbourBottomLeft = new Coordinate(9, 11);
        //WHEN
        subject.insertCell(coordinateCentral);
        subject.insertCell(coordinateNeighbourTopRight);
        subject.insertCell(coordinateNeighbourBottomLeft);
        boolean condition = subject.isAlive(coordinateCentral) &&
            subject.isAlive(coordinateNeighbourBottomLeft) &&
            subject.isAlive(coordinateNeighbourTopRight) &&
            !subject.isAlive(new Coordinate(9, 9)) &&
            !subject.isAlive(new Coordinate(9, 10)) &&
            !subject.isAlive(new Coordinate(10, 9)) &&
            !subject.isAlive(new Coordinate(10, 11)) &&
            !subject.isAlive(new Coordinate(11, 10)) &&
            !subject.isAlive(new Coordinate(11, 11));
        assertTrue(condition);
        boolean result = subject.getNextGenerationBoard().isAlive(coordinateCentral);
        //THEN
        assertTrue(result);
    }

    @Test
    public void whenAliveAndThreeNeighboursThenSurvives() {
        //GIVEN
        Coordinate coordinateCentral = new Coordinate(10, 10);
        Coordinate coordinateNeighbourTop = new Coordinate(10, 9);
        Coordinate coordinateNeighbourBottom = new Coordinate(10, 11);
        Coordinate coordinateNeighbourLeft = new Coordinate(9, 10);
        //WHEN
        subject.insertCell(coordinateCentral);
        subject.insertCell(coordinateNeighbourTop);
        subject.insertCell(coordinateNeighbourLeft);
        boolean condition = subject.isAlive(coordinateCentral) &&
            subject.isAlive(coordinateNeighbourLeft) &&
            subject.isAlive(coordinateNeighbourTop) &&
            subject.isAlive(coordinateNeighbourBottom) &&
            !subject.isAlive(new Coordinate(9, 9)) &&
            !subject.isAlive(new Coordinate(9, 11)) &&
            !subject.isAlive(new Coordinate(10, 9)) &&
            !subject.isAlive(new Coordinate(11, 9)) &&
            !subject.isAlive(new Coordinate(11, 10)) &&
            !subject.isAlive(new Coordinate(11, 11));
        assertTrue(condition);
        boolean result = subject.getNextGenerationBoard().isAlive(coordinateCentral);
        //THEN
        assertTrue(result);
    }

}