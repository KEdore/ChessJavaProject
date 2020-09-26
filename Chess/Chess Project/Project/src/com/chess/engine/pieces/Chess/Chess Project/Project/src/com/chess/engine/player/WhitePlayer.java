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

public class WhitePlayer extends Player {

    public WhitePlayer(final Board board, final Collection<Move> whiteStandardLegalMoves,
            final Collection<Move> blackStandardLegalMoves){
                super(board, whiteStandardLegalMoves, blackStandardLegalMoves);

    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        // TODO Auto-generated method stub
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        // TODO Auto-generated method stub
        return this.board.blackPlayer();
    }

    @Override
    public String toString() {
        return Alliance.WHITE.toString();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
            final Collection<Move> opponentsLegals) {
        
        final List<Move> kingCastles = new ArrayList<>();
        
        if(this.playerKing.isFirstMove() && !this.isInCheck()){
            // White King Side Castle
            if(!this.board.getTile(61).isTileOccupied() && !this.board.getTile(62).isTileOccupied());
            final Tile kingRookTile = this.board.getTile(63);
            if(kingRookTile.isTileOccupied() && kingRookTile.getPiece().isFirstMove()){
                if(Player.calculateAttacksOnTile(61, opponentsLegals).isEmpty() && Player.calculateAttacksOnTile(62,opponentsLegals).isEmpty() && kingRookTile.getPiece().getPieceType().isRook()){
                kingCastles.add(new Move.KingSideCastleMove(this.board,
                                                            this.playerKing, 
                                                            62, 
                                                             (Rook)kingRookTile.getPiece(),
                                                            kingRookTile.getTileCoordinate(), 
                                                            61));
                }
    
            }
            if(!this.board.getTile(59).isTileOccupied() && !this.board.getTile(58).isTileOccupied() && !this.board.getTile(57).isTileOccupied()){
                final Tile queenRookTile = this.board.getTile(56);
                if(queenRookTile.isTileOccupied() && queenRookTile.getPiece().isFirstMove()
                        && Player.calculateAttacksOnTile(58, opponentsLegals).isEmpty()
                        && Player.calculateAttacksOnTile(59, opponentsLegals).isEmpty()
                        && queenRookTile.getPiece().getPieceType().isRook()){
                 kingCastles.add(new Move.QueenSideCastleMove(this.board,this.playerKing,58,(Rook)queenRookTile.getPiece(),
                         queenRookTile.getTileCoordinate(),59));
                }

            }

        }
    
        return Collections.unmodifiableList(kingCastles);
    }


}
