package com.pest.demo;

public class Tile {

    int tileX;
    int tileY;
    int tileType;
    boolean tileUncovered = false;

    public Tile(int y, int x, int type) {
        tileX = x;
        tileY = y;
        tileType = type;
    }
    
    void uncoverTile() {
        this.tileUncovered = true;
    }
}
