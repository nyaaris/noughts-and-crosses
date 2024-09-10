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
}
