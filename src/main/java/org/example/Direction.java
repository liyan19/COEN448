package org.example;

enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Direction rotate(Turn turn) {
        int newOrdinal = (this.ordinal() + (turn == Turn.RIGHT ? 1 : -1) + 4) % 4;//https://stackoverflow.com/questions/29040567/the-easiest-way-to-achieve-a-responsive-orientation-enum-java
        return Direction.values()[newOrdinal];
    }
}
