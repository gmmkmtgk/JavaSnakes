package game.entities;

import lombok.Builder;
import game.utils.Constants;

import java.util.List;
import java.util.Map;

import static game.utils.Constants.BONUS_DICE_NUMBER;

@Builder
public class Board {

    Map<Integer, Snake> snakePositions;
    Map<Integer, Ladder> ladderPositions;
    List<Player> players;
    int boardSize;

    private Dice dice;

    private int checkLadderAndUpdatePosition(final String name, final int position) {
        if(ladderPositions.containsKey(position)) {
            System.out.println(name + " climbed up on a ladder :D at ------------------> " + position);
            return ladderPositions.get(position).endPosition;
        }
        return position;
    }

    private int checkSnakeAndUpdatePosition(final String name, final int position) {
        if(snakePositions.containsKey(position)) {
            System.out.println(name + " got bit by snake :'( at ------------------> " + position);
            return snakePositions.get(position).endPosition;
        }
        return position;
    }

    private int getBonusDiceSum(int diceRoll) {
        int rollNumber = 1;
        int diceSum = diceRoll;
        while(diceRoll == BONUS_DICE_NUMBER && rollNumber <= 3) {
            diceRoll = this.dice.getNumber();
            diceSum += diceRoll;
            rollNumber += 1;
        }
        return rollNumber > 3 ? 0 : diceSum;
    }
    private int getPlayerPosition(final Player player, final int diceRoll) {
        final int startPosition = player.getPosition();
        int diceSum = diceRoll == BONUS_DICE_NUMBER ? getBonusDiceSum(diceRoll) : diceRoll;
        final int newPosition = startPosition + diceSum;
        int endPosition = newPosition < Constants.BOARD_SIZE ? startPosition + diceSum : startPosition;
        endPosition = checkLadderAndUpdatePosition(player.name, endPosition);
        endPosition = checkSnakeAndUpdatePosition(player.name, endPosition);
        System.out.println(String.format("%s rolled a %d and moved from %d to %d", player.name, diceRoll, startPosition, endPosition));
        return endPosition;
    }

    public void playGame() {
        this.dice = new Dice();
        int currentPlayers = this.players.size();
        while(currentPlayers >= Constants.PLAYERS_IN_GAME) {
            for(final Player player: players) {
                if(!player.hasWon()) {
                    player.setPosition(getPlayerPosition(player, this.dice.getNumber()));
                    if (player.hasWon()) {
                        System.out.println(player.name + " won the game!");
                        currentPlayers--;
                    }
                    if(currentPlayers < Constants.PLAYERS_IN_GAME) {
                        return;
                    }
                }
            }
        }
    }

}
