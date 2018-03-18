package network;


public enum RequestCode {
    OK,
    ERROR,
    RECONECT,
    CONNECTION_ERROR;

    @Override
    public String toString() {
        return name();
    }
}