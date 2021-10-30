package game.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import game.utils.Constants;

@Builder
@Setter
@Getter
public class Player {

    int position;
    String name;

    public boolean hasWon() {
        return this.position == Constants.BOARD_SIZE;
    }
}
