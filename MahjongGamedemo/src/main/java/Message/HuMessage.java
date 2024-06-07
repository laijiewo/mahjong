package Message;

public class HuMessage extends Message {
    private final int winnerIndex;
    public HuMessage(int winnerIndex) {
        super(MessageType.HU);
        this.winnerIndex = winnerIndex;
    }
    public int getWinnerIndex() {
        return winnerIndex;
    }
}
