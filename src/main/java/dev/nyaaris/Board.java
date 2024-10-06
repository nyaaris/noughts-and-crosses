package dev.nyaaris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Board {
    public enum Player {
        NOUGHT, CROSS
    }

    public enum GameState {
        NOUGHTS_MOVE, CROSS_MOVE, DRAW, NOUGHT_WON, CROSS_WON
    }

    public static class Field {
        private Optional<Player> filledBy = Optional.empty();

        public Optional<Player> getFilledBy() {
            return filledBy;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Field)
                return filledBy.equals(((Field) obj).filledBy);
            return false;
        }

        @Override
        public int hashCode() {
            return filledBy.hashCode();
        }
    }

    private final Field[][] fields;
    private final List<Field[]> winConditionRows;

    public Board(int boardSize) {
        fields = new Field[boardSize][boardSize];
        for (Field[] fields2 : fields) {
            Arrays.setAll(fields2, (i) -> new Field());
        }
        winConditionRows = getWinConditionRows();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Board)
            return Arrays.deepEquals(fields, ((Board) obj).fields);
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(fields);
    }

    public Field[][] getBoardState() {
        Field[][] returnArray = new Field[fields.length][];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = fields[i].clone();
        }
        return returnArray;
    }

    public GameState getGameState() {
        for (Field[] row : winConditionRows) {
            Optional<Player> winningPlayer = checkRowWins(row);
            if (winningPlayer.isPresent()) {
                return winningPlayer.get() == Player.NOUGHT ? GameState.NOUGHT_WON : GameState.CROSS_WON;
            }
        }
        if (getMovesDone() == fields.length * fields.length) {
            return GameState.DRAW;
        }
        return whoMovesNext() == Player.NOUGHT ? GameState.NOUGHTS_MOVE : GameState.CROSS_MOVE;
    }

    public Player whoMovesNext() {
        return getMovesDone() % 2 == 0 ? Player.NOUGHT : Player.CROSS;
    }

    public boolean move(int x, int y) {
        Field field = fields[x][y];
        if (field.filledBy.isPresent()) {
            return false;
        } else {
            field.filledBy = Optional.of(whoMovesNext());
            return true;
        }
    }

    private int getMovesDone() {
        int movesDone = 0;
        for (Field[] fieldRow : fields) {
            for (Field field : fieldRow) {
                if (field.filledBy.isPresent()) {
                    movesDone++;
                }
            }
        }
        return movesDone;
    }

    private List<Field[]> getWinConditionRows() {
        List<Field[]> result = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            result.add(fields[i]);
            Field[] row = new Field[fields.length];
            for (int j = 0; j < fields.length; j++) {
                row[j] = fields[j][i];
            }
            result.add(row);
        }
        Field[] diagonal1 = new Field[fields.length];
        Field[] diagonal2 = new Field[fields.length];
        for (int i = 0; i < fields.length; i++) {
            diagonal1[i] = fields[i][i];
            diagonal2[i] = fields[i][fields.length - 1 - i];
        }
        result.add(diagonal1);
        result.add(diagonal2);
        return result;
    }

    private Optional<Player> checkRowWins(final Field[] row) {
        Optional<Player> result = row[0].filledBy;
        if (result.isPresent()) {
            for (int i = 1; i < row.length; i++) {
                if (!row[i].filledBy.equals(result)) {
                    result = Optional.empty();
                    break;
                }
            }
        }
        return result;
    }

}
