package com.robeli.units;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.robeli.Mousepicking;
import com.robeli.Phases;
import com.robeli.terrain.Tile;

public class Units{
	public int hp;
	public boolean selected;
	public Tile currentTile, targetTile;
	public double angle;
	public static float speed = 40; // closer to 0 equals faster
	protected int tileRange, movementPoints;
	protected float scale, x, z, damage, gridHeight;
	private Spatial unitModel;
	private Texture unitStandardTexture, unitSelectedTexture;
	
	public void create(AssetManager aM, Node rootNode, Node unitNode, Tile tile, float gridHeight, boolean side) {/* polymorphic method*/}
	
	protected void createUnit(String texture, String model, AssetManager aM, Node unitNode, Tile tile, float scaling, float GridHeight, boolean side){
		try{
		gridHeight = GridHeight;
		unitModel = aM.loadModel(model);
		unitStandardTexture = aM.loadTexture(texture);
		unitSelectedTexture = aM.loadTexture("/Textures/stonetexture.png");
		Material unitMaterial = new Material(aM, "Common/MatDefs/Light/Lighting.j3md");
		unitMaterial.setTexture("DiffuseMap", unitStandardTexture);
		unitModel.setMaterial(unitMaterial);
		unitModel.setName("unit");
		
		unitModel.setLocalTranslation(tile.getX(), gridHeight , tile.getZ());
		unitModel.setLocalScale(scaling);
		unitModel.rotate(0, (side?+90*FastMath.DEG_TO_RAD:-90*FastMath.DEG_TO_RAD), 0);
		
		unitNode.attachChild(unitModel);
		}catch(Exception e){
			System.out.println("createUnit fail: "+ e);
		}
	}
	
	public void moveToTile(Units unit, Tile targetTile){
		if (unit != null){
			Mousepicking.moveUpdate = true;
			Phases.mousePicking.selectedUnit = unit;
			Phases.mousePicking.moveInitializing(targetTile);
		}
	}
	
	public void attack(Tile tile){
		
	}
	
	public int getTileRange(){
		return tileRange;
	}
	
	public int getMovementPoints(){
		return movementPoints;
	}
	
	public float getScale(){
		return scale;
	}
	
	public float getX(){
		return x;
	}
	
	public float getZ(){
		return z;
	}
	
	public float getDamage(){
		return damage;
	}

	public Geometry getGeometry() {
		Geometry geo = (Geometry) unitModel;
		return geo;
	}
	
	public void setStandardTexture(){
		((Geometry) unitModel).getMaterial().setTexture("DiffuseMap", unitStandardTexture);
	}
	
	public void setSelectedTexture(){
		((Geometry) unitModel).getMaterial().setTexture("DiffuseMap", unitSelectedTexture);
	}
	
}
