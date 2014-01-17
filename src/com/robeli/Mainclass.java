package com.robeli;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import com.robeli.terrain.Grid;
import com.robeli.terrain.Terrain;
import com.robeli.terrain.Tile;
import com.robeli.units.Player;

public class Mainclass extends SimpleApplication {

	private int gridXlength = 15, gridZlength = 10;
	private float gridHeight = 0.5f;
	private Node unitNode = new Node();
	private String currentPhase = "movePhase";
	private Phases phases;
	
	public static void main(String[] args) {
		Mainclass app = new Mainclass();
		app.start();
	}

	@Override
	public void simpleInitApp() {
		// Basic initializing
		flyCam.setEnabled(false);
		cam.setLocation(new Vector3f(20,25,50));
		cam.lookAt(new Vector3f(30,0,20),new Vector3f(0,1,0));
		inputManager.isCursorVisible();
		
		ColorRGBA backgroundColor = new ColorRGBA(.8f, .85f, 1f, 1f);
		viewPort.setBackgroundColor(backgroundColor);
		//
		
		// Creating the grid
		Grid grid = new Grid(gridXlength,gridZlength,gridHeight, rootNode, assetManager);
		grid.createGrid((Terrain.planeLength/2)-(gridXlength*Tile.width-Tile.width)/2, (Terrain.planeLength/2)-(gridZlength*Tile.length-Tile.length)/2);
		//
		
		// Creating the players on each side
		Player player1 = new Player(true, assetManager, rootNode, unitNode);
		player1.createUnits(gridXlength, gridZlength, grid, gridHeight);
		
		Player player2 = new Player(false, assetManager, rootNode, unitNode);
		player2.createUnits(gridXlength, gridZlength, grid, gridHeight);
		
		Player currentPlayer = player2;
		//
		
		// Initializing phaseclass
		phases = new Phases(cam, currentPlayer, inputManager, assetManager, rootNode, grid);
		//
		
		// Lightning
		AmbientLight al = new AmbientLight();
		al.setColor(ColorRGBA.White.mult(5f));
		rootNode.addLight(al);
		//
		
		// Nodes
		Terrain terrain = new Terrain(assetManager);
		terrain.createTerrain(rootNode, 0, 0);
		//
	}
	
	@Override
	public void simpleUpdate(float tpf){
		
		phases.selectPhase(currentPhase);
		
		if (Mousepicking.moveUpdate){
			phases.mousePicking.updateUnits();
		}
		
	}
	
	@Override
	public void simpleRender(RenderManager rm){
	}
}