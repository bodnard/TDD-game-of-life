package com.epam.exercises.gameoflife;

import java.util.ArrayList;
import java.util.List;

public class DefaultBoard implements Board {
    List<Coordinate> coordinate = new ArrayList<>();
    List<Coordinate> neighbourCellsList = new ArrayList<>();
    Coordinate actual;


    @Override
    public Board getNextGenerationBoard() {
        Board nextGen = new DefaultBoard();
        for (Coordinate value : coordinate) {
            loadNeighboursCellList(value);
            if (neighbourCellsList.stream().filter(this::isAlive).count() == 2) {
                nextGen.insertCell(value);
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
        return this.coordinate.contains(coordinate);
    }

    private void addToAliveCellsToList(Coordinate actual) {
        if (!isAlive(actual)) {
            coordinate.add(actual);
        }
    }

    private void loadNeighboursCellList(Coordinate coordinate) {
        Coordinate leftNeighbourX = shiftLeftOnX(coordinate);
        Coordinate rightNeighbourX = shiftRightOnX(coordinate);

        Coordinate topNeighbourY = shiftLeftOnY(coordinate);
        Coordinate bottomNeighbourY = shiftRightOnY(coordinate);

        Coordinate topLeftNeighbour = shiftLeftOnX(topNeighbourY);
        Coordinate bottomRightNeighbour = shiftRightOnX(bottomNeighbourY);

        Coordinate topRightNeighbour = shiftRightOnX(topNeighbourY);
        Coordinate bottomLeftNeighbour = shiftLeftOnX(bottomNeighbourY);
        neighbourCellsList = List.of(
                topLeftNeighbour,
                topNeighbourY,
                topRightNeighbour,
                leftNeighbourX,
                rightNeighbourX,
                bottomLeftNeighbour,
                bottomNeighbourY,
                bottomRightNeighbour
        );
    }
}
