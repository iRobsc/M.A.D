package com.robeli.units;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.robeli.terrain.Grid;
import com.robeli.terrain.Tile;

public class Testunit extends Units {
	public float scaling = 0.04f;
	public int range = 0;
	
	@Override
	public void create(AssetManager aM, Node rootNode, Node unitNode, Grid grid, Tile tile, float gridHeight, boolean side){
		try{
		createUnit("/Textures/metal.png", "/Models/cube.obj", aM, unitNode, grid, tile, scaling, gridHeight, side);
		rootNode.attachChild(unitNode);
		}
		catch(Exception e){
			System.out.println("createTest fail: "+e);
		}
	}
}
