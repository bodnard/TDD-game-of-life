package com.epam.exercises.gameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultBoard implements Board {
    List<Coordinate> aliveCellsList = new ArrayList<>();
    List<Coordinate> neighbourCellsList = new ArrayList<>();
    Coordinate actual;


    @Override
    public Board getNextGenerationBoard() {
        Board nextGen = new DefaultBoard();
        for (Coordinate currentAliveCell : aliveCellsList) {
            loadNeighboursCellList(currentAliveCell);
            long countAlives = neighbourCellsList.stream().filter(this::isAlive).count();
            if (countAlives == 2 || countAlives == 3) {
                nextGen.insertCell(currentAliveCell);
            }
            List<Coordinate> deadNeighboursCellList = neighbourCellsList.stream()
                    .filter(coordinate -> !isAlive(coordinate))
                    .collect(Collectors.toList());
            for (Coordinate currentDeadCell : deadNeighboursCellList) {
                loadNeighboursCellList(currentDeadCell);
                long countNeighboursOfDead = neighbourCellsList.stream().filter(this::isAlive).count();
                if (countNeighboursOfDead == 3) {
                    nextGen.insertCell(currentDeadCell);
                }
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
        return this.aliveCellsList.contains(coordinate);
    }

    private void addToAliveCellsToList(Coordinate actual) {
        if (!isAlive(actual)) {
            aliveCellsList.add(actual);
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
