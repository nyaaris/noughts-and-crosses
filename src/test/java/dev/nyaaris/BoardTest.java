package dev.nyaaris;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    @Test
    public void checkInitialisedBoard() {
        Board board = new Board(2);
        Board.Field[][] expectedFields = new Board.Field[][] {
                { new Board.Field(), new Board.Field() }, { new Board.Field(), new Board.Field() }
        };
        Board.Field[][] actualFields = board.getBoardState();
        Assert.assertArrayEquals(expectedFields, actualFields);
        Assert.assertTrue(actualFields[0][0].getFilledBy().isEmpty());
    }

    @Test
    public void checkOngoingGameStates() {
        Board board = new Board(4);
        Assert.assertEquals(Board.GameState.ONGOING, board.getGameState());
        board.move(0, 0);
        Assert.assertEquals(Board.GameState.ONGOING, board.getGameState());
    }

    @Test
    public void checkNoughtWin() {
        Board board = new Board(2);
        board.move(0, 0);
        board.move(0, 1);
        board.move(1, 1);
        Assert.assertEquals(Board.GameState.NOUGHT_WON, board.getGameState());
    }

    @Test
    public void checkDraw() {
        Board board = new Board(3);
        board.move(1, 0);
        board.move(0, 0);
        board.move(0, 1);
        board.move(2, 0);
        board.move(1, 1);
        board.move(2, 1);
        board.move(0, 2);
        board.move(1, 2);
        board.move(2, 2);
        Assert.assertEquals(Board.GameState.DRAW, board.getGameState());
    }
}
