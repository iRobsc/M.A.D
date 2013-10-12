package com.robeli;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import com.robeli.terrain.Grid;
import com.robeli.terrain.Terrain;
import com.robeli.terrain.Tile;
import com.robeli.units.Testunit;
import com.robeli.units.Units;

public class Mainclass extends SimpleApplication {

	private Node unitNode = new Node();
	private int gridXlength = 15;
	private int gridZlength = 10;
	private float gridHeight = 0.5f;
	private int unitsAmount = 20;
	private Units[][] units = new Units[gridZlength][gridXlength];
	
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

		for(int i = 0; i < (unitsAmount > (gridXlength*gridZlength)?(gridXlength*gridZlength): unitsAmount); i++){
			units[i-((int)(i/gridZlength)*gridZlength)][(int)(i/gridZlength)] = new Testunit();
			units[i-((int)(i/gridZlength)*gridZlength)][(int)(i/gridZlength)].create(assetManager, rootNode, unitNode, grid.getGrid((int)(i/gridZlength),i-(int)(i/gridZlength)*gridZlength), gridHeight);
		}
		
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