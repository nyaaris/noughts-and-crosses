package edu.nyaaris;

public class BoardController {
    private Board model;
    private BoardView view;

    public BoardController(Board model, BoardView view) {
        this.model = model;
        this.view = view;
    }
}
