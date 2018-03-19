package game.chess.model;

import java.util.Set;


public abstract class Piece implements Movable<Piece> {
    protected final Pose2d position;
    protected final Player player;
    protected final ChessBoard board;

    public Piece(final Pose2d position, final Player player, final ChessBoard board) {
        this.position = position;
        this.player = player;
        this.board = board;
    }

    public Move isMovableTo(final Pose2d pos) {
        final Move move = new Move(this.position, pos, this, false);
        if (this.isSafeToMove(pos) && this.allPossibleMoves().contains(move)) {
            return new Move(this.position, pos, this, false);
        }
        return null;
    }
    public boolean isSafeToMove(final Pose2d pos) {
        return this.board.isFree(pos) || this.board.containsEnemy(this.position, pos);
    }

    public Pose2d position() {
        return this.position;
    }

    public Player player() {
        return this.player;
    }

    public abstract Set<Move> allPossibleMoves();

    public abstract Piece withPosition(final Pose2d pos);
}
