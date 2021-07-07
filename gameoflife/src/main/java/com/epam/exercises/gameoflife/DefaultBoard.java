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
            Coordinate leftNeighbourX = shiftLeftOnX(aliveCellsList.get(i));
            Coordinate rightNeighbourX = shiftRightOnX(aliveCellsList.get(i));

            Coordinate topNeighbourY = shiftLeftOnY(aliveCellsList.get(i));
            Coordinate bottomNeighbourY = shiftRightOnY(aliveCellsList.get(i));

            Coordinate topLeftNeighbour = shiftLeftOnX(topNeighbourY);
            Coordinate bottomRightNeighbour = shiftRightOnX(bottomNeighbourY);

            Coordinate topRightNeighbour = shiftRightOnX(topNeighbourY);
            Coordinate bottomLeftNeighbour = shiftLeftOnX(bottomNeighbourY);

            if (isAlive(leftNeighbourX) && isAlive(rightNeighbourX) ||
                isAlive(topNeighbourY) && isAlive(bottomNeighbourY) ||
                isAlive(topRightNeighbour) && isAlive(bottomLeftNeighbour) ||
                isAlive(topLeftNeighbour) && isAlive(bottomRightNeighbour) ) {
                nextGen.insertCell(aliveCellsList.get(i));
            }
        }
        return nextGen;
    }

    private Coordinate shiftLeftOnX(Coordinate coordinate) {
        return new Coordinate(coordinate.getPositionX() - 1, coordinate.getPositionY());
    }
    private Coordinate shiftRightOnX(Coordinate coordinate) {
        return new Coordinate(coordinate.getPositionX() + 1, coordinate.getPositionY());
    }

    private Coordinate shiftLeftOnY(Coordinate coordinate) {
        return new Coordinate(coordinate.getPositionX(), coordinate.getPositionY() - 1);
    }
    private Coordinate shiftRightOnY(Coordinate coordinate) {
        return new Coordinate(coordinate.getPositionX(), coordinate.getPositionY() + 1);
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
