package game.entities;

public class Snake extends Element {

    Snake(int head, int tail) {
        this.startPosition = head;
        this.endPosition = tail;
    }
}
