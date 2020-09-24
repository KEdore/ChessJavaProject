package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;


public class BlackPlayer extends Player {

    public BlackPlayer(final Board board, final Collection<Move> whiteStandardLegalMoves,
            final Collection<Move> blackStandardLegalMoves) {
                super(board, blackStandardLegalMoves,whiteStandardLegalMoves);
    
            }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        // TODO Auto-generated method stub
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        // TODO Auto-generated method stub
        return this.board.whitePlayer();
    }

    @Override
    public String toString() {
        return Alliance.BLACK.toString();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
            final Collection<Move> opponentsLegals) {
        final List<Move> kingCastles = new ArrayList<>();

        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            // Black King Side Castle
            if (!this.board.getTile(5).isTileOccupied() && !this.board.getTile(6).isTileOccupied())
                ;
            final Tile kingRookTile = this.board.getTile(7);
            if(kingRookTile.isTileOccupied() && kingRookTile.getPiece().isFirstMove()) {
                if (Player.calculateAttacksOnTile(5, opponentsLegals).isEmpty()
                        && Player.calculateAttacksOnTile(6, opponentsLegals).isEmpty()
                        && kingRookTile.getPiece().getPieceType().isRook()) {
                    kingCastles.add(new Move.KingSideCastleMove(this.board, this.playerKing, 6,
                            (Rook) kingRookTile.getPiece(), kingRookTile.getTileCoordinate(), 5));
                }

            }
            if (!this.board.getTile(1).isTileOccupied() && !this.board.getTile(2).isTileOccupied()
                    && !this.board.getTile(3).isTileOccupied()) {
                final Tile queenRookTile = this.board.getTile(0);
                if (queenRookTile.isTileOccupied() && queenRookTile.getPiece().isFirstMove() && 
                Player.calculateAttacksOnTile(2, opponentsLegals).isEmpty() && Player.calculateAttacksOnTile(3, opponentsLegals).isEmpty() && 
                queenRookTile.getPiece().getPieceType().isRook() ) 
                 {
                    kingCastles.add(new Move.QueenSideCastleMove(this.board, this.playerKing, 2,
                            (Rook) queenRookTile.getPiece(), queenRookTile.getTileCoordinate(), 3));
                }

            }

        }

        return Collections.unmodifiableList(kingCastles);
    }

}
