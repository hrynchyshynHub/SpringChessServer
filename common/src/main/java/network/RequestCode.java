package network;


public enum RequestCode {
    OK,
    ERROR,
    CONNECTION_ERROR;

    @Override
    public String toString() {
        return name();
    }
}