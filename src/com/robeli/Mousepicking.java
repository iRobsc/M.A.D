package com.robeli;

import java.util.ArrayList;
import java.util.List;

import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.Geometry;
import com.robeli.terrain.Grid;
import com.robeli.terrain.Tile;
import com.robeli.units.Player;
import com.robeli.units.Units;

public class Mousepicking {

	private Camera cam = new Camera();
	private InputManager inputManager;
	private Node rootNode;
	private Player currentPlayer;
	private Grid grid;
	private Geometry selectedGeo;
	private Ray ray;
	private List<Units> movingUnits;
	private Quaternion rotation = new Quaternion();
	public Units selectedUnit;
	public boolean unitSelection = false, unitMovement = false;
	public static boolean moveUpdate;
	
	public Mousepicking(Camera camera, Player currentPlayer, InputManager inputManager, AssetManager assetManager, Node rootNode, Grid grid){
		cam = camera;
		this.inputManager = inputManager;
		this.rootNode = rootNode;
		this.currentPlayer = currentPlayer;
		this.grid = grid;
		movingUnits = new ArrayList<Units>();
	}
	
	public void initialize(){
		inputManager.addMapping("clicked", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		inputManager.addListener(actionListener,"clicked");
	}
	
	public void moveInitializing(Tile tile){
		if (tile.currentUnit == null){
			
			selectedUnit.currentTile.currentUnit = null;
			selectedUnit.currentTile = null;
			tile.currentUnit = selectedUnit;
			selectedUnit.currentTile = tile;
			selectedUnit.targetTile = tile;
			movingUnits.add(selectedUnit);
			
			for(Units unit : movingUnits){
				Vector3f unitPos = unit.getGeometry().getLocalTranslation();
				float deltaZ = unit.currentTile.getZ() - unitPos.z;
				float deltaX = unit.currentTile.getX() - unitPos.x;
				double angle = Math.atan2(deltaX, deltaZ);
				rotation.fromAngleAxis((float) angle, new Vector3f(0,1,0));
				unit.getGeometry().setLocalRotation(rotation);
			}
			
			selectedUnit.setStandardTexture();
			tile.setTexture(Tile.tileTextureB);
			
			selectedUnit = null;
		}
	}
	
	public void updateUnits(){ // called by simpleUpdate if moveUpdate = true
		for(Units unit : movingUnits){
			Vector3f unitPos = unit.getGeometry().getLocalTranslation();
			
			float deltaZ =  unit.currentTile.getZ() - unitPos.z;
			float deltaX =  unit.currentTile.getX() - unitPos.x;
			double angle = Math.toDegrees(Math.atan2(deltaZ, deltaX));
			
			float moveX = (float) Math.cos(Math.toRadians(angle));
			float moveZ = (float) Math.sin(Math.toRadians(angle));

			if (Math.abs(unitPos.x - unit.currentTile.getX()) <= Math.abs(moveX/Units.speed) &&
				Math.abs(unitPos.z - unit.currentTile.getZ()) <= Math.abs(moveZ/Units.speed)){
					unit.getGeometry().setLocalTranslation
					(unit.currentTile.getX(), unitPos.y, unit.currentTile.getZ());
					
					unit.currentTile.setTexture(Tile.tileTextureA);
					movingUnits.remove(unit);
					break;
				}
			
			unit.getGeometry().setLocalTranslation
					(unitPos.x+moveX/Units.speed, 
					unitPos.y, 
					unitPos.z+moveZ/Units.speed);
		}
	}

	ActionListener actionListener = new ActionListener(){
		@Override
		public void onAction(String name, boolean keyPressed, float tpf) {
			if(name.equals("clicked") && !keyPressed){
				CollisionResults collisionResults = new CollisionResults();
				
				Vector2f click2d   = inputManager.getCursorPosition();
				Vector3f click3d   = cam.getWorldCoordinates(new Vector2f(click2d.x,click2d.y), 0).clone();
				Vector3f direction = cam.getWorldCoordinates(new Vector2f(click2d.x,click2d.y+0.1f), 1).subtractLocal(click3d).normalizeLocal();
				
				ray = new Ray(click3d, direction);
				
				rootNode.collideWith(ray, collisionResults);
				
				if (collisionResults.size() > 0){
					selectedGeo = collisionResults.getClosestCollision().getGeometry();
					
					if (unitSelection){
						for(Units[] i: currentPlayer.units){ // Finding the unit index from mouse picking the geometry
							for(Units unit : i){
									if (unit != null && selectedGeo != null){
										if (unit.getGeometry() == selectedGeo && movingUnits.contains(unit) == false){
											if (selectedUnit != null){
												selectedUnit.setStandardTexture(); // resetting texture to unselected units
												selectedUnit.selected = false;
											}
											selectedUnit = unit;
											selectedUnit.selected = true;
										}
									}
							}
						}	
						if (selectedUnit != null){
							selectedUnit.setSelectedTexture(); 
						}
					}	
					
					if (unitMovement && selectedUnit != null){ // clicking the tile while having a unit selected
							for(Tile[] i: grid.getGrid()){ // Finding the unit index from mouse picking the geometry
								for(Tile tile : i){
										if (tile != null && selectedGeo != null && tile.currentUnit == null){
											if (tile.getGeometry() == selectedGeo){
												moveInitializing(tile);
											}
										}
								}
							}
					}
					
					if (movingUnits.isEmpty()){
						moveUpdate = false;
					} else {
						moveUpdate = true;
					}
					
				}
			}
			
		}
	};
}
