package dev.nyaaris;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Board board_model = new Board(3,3);
        BoardView board_view = new BoardView();
        BoardController controller = new BoardController(board_model, board_view);
    }
}
