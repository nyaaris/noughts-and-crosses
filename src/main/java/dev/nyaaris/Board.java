package dev.nyaaris;

import java.util.Optional;

public class Board {
    private enum Player {
        NOUGHT, CROSS
    }

    private class Field {
        private Optional<Player> filled_by = Optional.empty();
    }

    private final Field[][] fields;

    public Board(int width, int height) {
        fields = new Field[width][height];
    }

    public boolean isGameFinished() {
        return false;
    }

    public Player whoMovesNext(){
        int moves = 0;
        for (Field[] fields2 : fields) {
            for (Field field : fields2) {
                if (field.filled_by.isPresent()){
                    moves++;
                }
            }
        }
        return moves % 2 == 0 ? Player.NOUGHT : Player.CROSS;
    }

    public boolean move(int x, int y){
        Field field = fields[x][y];
        if (field.filled_by.isPresent()){
            return false;
        }
        else{
            field.filled_by = Optional.of(whoMovesNext());
            return true;
        }
    }
}
