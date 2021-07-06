package com.epam.exercises.gameoflife;

public class DefaultBoard implements Board {


    @Override
    public Board getNextGenerationBoard() {
        return Board.super.getNextGenerationBoard();
    }

    @Override
    public void insertCell(Coordinate coordinate) {
        Board.super.insertCell(coordinate);
    }

    @Override
    public boolean isAlive(Coordinate coordinate) {
        return Board.super.isAlive(coordinate);
    }
}
