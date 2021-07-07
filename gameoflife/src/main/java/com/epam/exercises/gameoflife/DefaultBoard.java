package com.epam.exercises.gameoflife;

import java.util.ArrayList;
import java.util.List;

public class DefaultBoard implements Board {
    List<Coordinate> aliveCellsList = new ArrayList<>();
    Coordinate actual;


    @Override
    public Board getNextGenerationBoard() {
        Board nextGen = new DefaultBoard();
        for (int i = 0; i < aliveCellsList.size(); i++) {
            Coordinate leftNeighbour = new Coordinate(aliveCellsList.get(i).getPositionX() -1, aliveCellsList.get(i).getPositionY());
            Coordinate rightNeighbour = new Coordinate(aliveCellsList.get(i).getPositionX() +1, aliveCellsList.get(i).getPositionY());
            if(isAlive(leftNeighbour) && isAlive(rightNeighbour)) {
                nextGen.insertCell(aliveCellsList.get(i));
            }
        }
        return nextGen;
    }

    @Override
    public void insertCell(Coordinate coordinate) {
        addToAliveCellsToList(coordinate);
        actual = coordinate;
    }

    @Override
    public boolean isAlive(Coordinate coordinate) {
        return aliveCellsList.contains(coordinate);
    }

    private void addToAliveCellsToList(Coordinate actual) {
        if (!isAlive(actual)) {
            aliveCellsList.add(actual);
        }
    }
}
