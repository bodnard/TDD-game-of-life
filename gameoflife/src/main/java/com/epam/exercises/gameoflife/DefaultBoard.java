package com.epam.exercises.gameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultBoard implements Board {
    List<Coordinate> aliveCellsList = new ArrayList<>();
//    List<Coordinate> neighbourCellsList = new ArrayList<>();

    @Override
    public Board getNextGenerationBoard() {
        Board nextGen = new DefaultBoard();
        for (Coordinate currentAliveCell : aliveCellsList) {
            if (survives(currentAliveCell)) {
                nextGen.insertCell(currentAliveCell);
            }

            List<Coordinate> deadNeighbourCellList =
                getNeighbours(currentAliveCell)
                    .stream().filter(this::isDead).collect(Collectors.toList());

            for (Coordinate currentDeadCell : deadNeighbourCellList) {
                if (rises(currentDeadCell)) {
                    nextGen.insertCell(currentDeadCell);
                }
            }
        }
        return nextGen;
    }

    private boolean survives(Coordinate coordinate) {
        long aliveNeighbours = getNumberOfAliveNeighbours(coordinate);
        return aliveNeighbours == 2 || aliveNeighbours == 3;
    }

    private boolean rises(Coordinate coordinate) {
        long countNeighboursOfDead = getNumberOfAliveNeighbours(coordinate);
        return countNeighboursOfDead == 3;
    }

    private long getNumberOfAliveNeighbours(Coordinate coordinate) {
        return getNeighbours(coordinate).stream().filter(this::isAlive).count();
    }

    private Coordinate shiftLeft(Coordinate coordinate) {
        return new Coordinate(coordinate.getPositionX() - 1, coordinate.getPositionY());
    }

    private Coordinate shiftRight(Coordinate coordinate) {
        return new Coordinate(coordinate.getPositionX() + 1, coordinate.getPositionY());
    }

    private Coordinate shiftUp(Coordinate coordinate) {
        return new Coordinate(coordinate.getPositionX(), coordinate.getPositionY() - 1);
    }

    private Coordinate shiftDown(Coordinate coordinate) {
        return new Coordinate(coordinate.getPositionX(), coordinate.getPositionY() + 1);
    }

    @Override
    public void insertCell(Coordinate coordinate) {
        addToAliveCellsToList(coordinate);
    }

    @Override
    public boolean isAlive(Coordinate coordinate) {
        return this.aliveCellsList.contains(coordinate);
    }

    public boolean isDead(Coordinate coordinate) {
        return !isAlive(coordinate);
    }

    private void addToAliveCellsToList(Coordinate actual) {
        if (!isAlive(actual)) {
            aliveCellsList.add(actual);
        }
    }

    private List<Coordinate> getNeighbours(Coordinate central) {
        return List.of(
                shiftUp(shiftLeft(central)),
                shiftUp(central),
                shiftUp(shiftRight(central)),
                shiftLeft(central),
                shiftRight(central),
                shiftDown(shiftLeft(central)),
                shiftDown(central),
                shiftDown(shiftRight(central))
        );
    }
}
