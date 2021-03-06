package pl.kl.chat.common;

public enum Actions {

    ME("/me"),
    HELP("/help"),
    CHANNEL("/channel"),
    CHANNEL_JOIN("/join"),
    ALL_CLIENTS("/clients"),
    ALL_CHANNELS("/channels"),
    SEND_FILE("/send"),
    CHAT_HISTORY("/history"),
    CLOSE_CONNECTION("/quit");

    private String input;

    Actions(String actionInput) {
        this.input = actionInput;
    }

    public String getInput() {
        return input;
    }

}
