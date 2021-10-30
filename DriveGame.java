package game.entities;

import game.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DriveGame {

    private static List<Player> getPlayers(final int limit, final Scanner scannerObject) {
        final List<Player> players = new ArrayList<>();
        for(int i=0; i<limit; i++) {
            players.add(Player.builder()
                    .position(0)
                    .name(scannerObject.next())
                    .build());
        }
        return players;
    }

    private static Map<Integer, Ladder> getLadders(final int limit, final Scanner scannerObject) {
        final Map<Integer, Ladder> ladders = new HashMap<>();
        for(int i=0; i<limit; i++) {
            final int startPosition = scannerObject.nextInt();
            final int endPosition = scannerObject.nextInt();
            ladders.put(startPosition, new Ladder(startPosition, endPosition));
        }
        return ladders;
    }

    private static Map<Integer, Snake> getSnakes(final int limit, final Scanner scannerObject) {
        final Map<Integer, Snake> snakes = new HashMap<>();
        for(int i=0; i<limit; i++) {
            final int startPosition = scannerObject.nextInt();
            final int endPosition = scannerObject.nextInt();
            snakes.put(startPosition, new Snake(startPosition, endPosition));
        }
        return snakes;
    }

    private static void initializeConstants() {
        Constants.BOARD_SIZE = 100;
        Constants.DICE_START = 1;
        Constants.DICE_END = 6;
    }
    public static void main(String [] args) {
       final Scanner scannerObject = new Scanner(System.in);
        initializeConstants();
        final Board board = Board.builder()
                .boardSize(Constants.BOARD_SIZE)
                .snakePositions(getSnakes(scannerObject.nextInt(), scannerObject))
                .ladderPositions(getLadders(scannerObject.nextInt(), scannerObject))
                .players(getPlayers(scannerObject.nextInt(), scannerObject))
                .build();
        board.playGame();
    }
}
