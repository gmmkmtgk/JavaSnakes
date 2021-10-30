package game.entities;

import java.util.Random;

import static game.utils.Constants.DICE_END;
import static game.utils.Constants.DICE_START;

public class Dice {

    private Random rand;

    public Dice() {
        this.rand = new Random();
    }

    int getNumber() {
        return rand.nextInt((DICE_END - DICE_START) + 1) + DICE_START;
    }
}
