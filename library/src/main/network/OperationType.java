package network;

public enum OperationType{
    LOGIN,
    REGISTER_USER,
    CREATE_GAME,
    TRY_MOVE;

    @Override
    public String toString() {
        return this.name();
    }
}
