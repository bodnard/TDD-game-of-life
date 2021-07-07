package com.epam.exercises.gameoflife;

public class DefaultBoard implements Board {
    Coordinate last;


    @Override
    public Board getNextGenerationBoard() {
        return new DefaultBoard();
    }

    @Override
    public void insertCell(Coordinate coordinate) {
        last = coordinate;
    }

    @Override
    public boolean isAlive(Coordinate coordinate) {
        return coordinate.equals(last);
    }
}
