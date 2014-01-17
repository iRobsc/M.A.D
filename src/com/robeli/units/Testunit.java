package com.robeli.units;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.robeli.terrain.Tile;

public class Testunit extends Units {
	public float scaling = 0.3f;
	
	@Override
	public void create(AssetManager aM, Node rootNode, Node unitNode, Tile tile, float gridHeight, boolean side){
		try{
		createUnit("/Textures/metal.png", "/Models/mecha.obj", aM, unitNode, tile, scaling, gridHeight, side);
		rootNode.attachChild(unitNode);
		}
		catch(Exception e){
			System.out.println("createTest fail: "+e);
		}
	}
}
