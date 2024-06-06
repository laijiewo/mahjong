package Message;

public class DiscardMessage extends Message {
    private final int index;
    public DiscardMessage(int index) {
        super(MessageType.DISCARD);
        this.index = index;
    }
    public int getIndex() {
        return index;
    }
}
