package com.robeli.terrain;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;

public class Grid {
	private int x, z;
	private float height;
	private Tile[][] grid;
	private Node rootNode;
	private AssetManager assetManager;
	
	public Grid (int X, int Z, float HEIGHT, Node RootNode, AssetManager AssetManager){
		x = X;
		z = Z;
		height = HEIGHT;
		rootNode = RootNode;
		assetManager = AssetManager;
	}
	
	public void createGrid(float xPos, float zPos){
		grid = new Tile[x][z];
		
		for(int X = 0; X < x; X++){
			for(int Z = 0; Z < z; Z++){
				grid[X][Z] = new Tile(rootNode, assetManager);
				grid[X][Z].createTile(X*Tile.width + xPos, height, Z*Tile.length + zPos);
				grid[X][Z].setCoords(X,Z);
			}
		}
	}
	
	public Tile getGrid(int x, int z){
		return grid[x][z];
	}
	
	public Tile[][] getGrid(){
		return grid;
	}
	
	public void addTile(int x, int z, Tile[][] fullGrid){
		grid[x][z] = fullGrid[x][z];
	}
	
	public int getGridLength(){
		return z;
	}
	
	public int getGridWidth(){
		return x;
	}
	
}
