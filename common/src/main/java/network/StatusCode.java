package network;


public enum StatusCode {
    OK,
    ERROR,
    CONNECTION_ERROR;

    @Override
    public String toString() {
        return name();
    }
}