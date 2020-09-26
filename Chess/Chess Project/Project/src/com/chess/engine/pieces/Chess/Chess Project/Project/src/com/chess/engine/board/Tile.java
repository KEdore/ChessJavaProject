package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

public abstract class Tile{
    
    protected final int tileCoordinate;

    private static final Map<Integer, Emptytile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
    
     private static Map<Integer, Emptytile> createAllPossibleEmptyTiles() {
        
        final Map<Integer,Emptytile> emptyTileMap = new HashMap<>();

        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            emptyTileMap.put(i, new Emptytile(i));
        }
        
        return ImmutableMap.copyOf(emptyTileMap);
    }
    

    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordinate, piece): EMPTY_TILES_CACHE.get(tileCoordinate);
    }


    private Tile(final int tileCoordinate){
        this.tileCoordinate = tileCoordinate;

    }

    public abstract boolean isTileOccupied(); // Checks to see if theres a chess piece on the tile //

    public abstract Piece getPiece();

    public int getTileCoordinate(){
        return this.tileCoordinate;
    }

    public static final class Emptytile extends Tile{
        Emptytile(final int coordinate){
            super(coordinate);
        }


        @Override
        public String toString(){
            return "-";
        }


        @Override
        public boolean isTileOccupied(){
            return false;
        }

        @Override
        public Piece getPiece(){
            return null;
        }

    }

    public static final class OccupiedTile extends Tile{
        private final Piece pieceOnTile;

        OccupiedTile(int tileCoordinate, final Piece pieceonTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceonTile;

        }

        @Override
        public String toString(){
            return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() : 
            getPiece().toString();
        }


        @Override
        public boolean isTileOccupied(){
            return true;
        }

        @Override
        public Piece getPiece(){
            return this.pieceOnTile;

        }
    }
}
