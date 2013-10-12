package com.robeli;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.robeli.terrain.Grid;
import com.robeli.terrain.Terrain;
import com.robeli.terrain.Tile;
import com.robeli.units.Player;

public class Mainclass extends SimpleApplication {

	private int gridXlength = 15, gridZlength = 10;
	private float gridHeight = 0.5f;
	
	public static void main(String[] args) {
		Mainclass app = new Mainclass();
		app.start();
	}

	@Override
	public void simpleInitApp() {
		flyCam.setMoveSpeed(50);
		ColorRGBA backgroundColor = new ColorRGBA(.8f, .85f, 1f, 1f);
		viewPort.setBackgroundColor(backgroundColor);
		
		Grid grid = new Grid(gridXlength,gridZlength,gridHeight, rootNode, assetManager);
		grid.createGrid((Terrain.planeLength/2)-(gridXlength*Tile.width-Tile.width)/2, (Terrain.planeLength/2)-(gridZlength*Tile.length-Tile.length)/2);
		
		Player player1 = new Player(true);
		player1.createUnits(gridXlength, gridZlength, grid, gridHeight, assetManager, rootNode);
		
		Player player2 = new Player(false);
		player2.createUnits(gridXlength, gridZlength, grid, gridHeight, assetManager, rootNode);
		
		AmbientLight al = new AmbientLight();
		al.setColor(ColorRGBA.White.mult(5f));
		rootNode.addLight(al);
		
		Terrain terrain = new Terrain(assetManager);
		terrain.createTerrain(rootNode, 0, 0);
		
		grid.getGrid(0,0).setTexture("/Textures/tileB.png");
		System.out.println("X: " + grid.getGrid(0,0).getX() + "\tY: " + grid.getGrid(0,0).getZ());
	}
	
	@Override
	public void simpleUpdate(float tpf){
		
	}
	
	@Override
	public void simpleRender(RenderManager rm){
	}
}