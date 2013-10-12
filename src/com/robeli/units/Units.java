package com.robeli.units;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.robeli.terrain.Tile;

public class Units {
	public int hp;
	protected int tileRange, movementPoints;
	protected float scale, x, z, damage;
	private Spatial unitModel;
	private Texture unitTexture;
	
	public void create(AssetManager aM, Node rootNode, Node unitNode, Tile tile, float gridHeight) {/* polymorphic method*/}
	
	protected void createUnit(String texture, String model, AssetManager aM, Node unitNode, Tile tile, float scaling, float gridHeight){
		try{
		unitModel = aM.loadModel(model);
		unitTexture = aM.loadTexture(texture);
		Material unitMaterial = new Material(aM, "Common/MatDefs/Light/Lighting.j3md");
		unitMaterial.setTexture("DiffuseMap", unitTexture);
		unitModel.setMaterial(unitMaterial);
		
		unitModel.setLocalTranslation(tile.getX() + Tile.width/2, gridHeight , tile.getZ() - Tile.length/2);
		unitModel.setLocalScale(scaling);
		unitModel.rotate(0, +90*FastMath.DEG_TO_RAD, 0);
		
		unitNode.attachChild(unitModel);
		}catch(Exception e){
			System.out.println("createUnit fail: "+ e);
		}
	}
	
	public void move(Tile tile){
		unitModel.setLocalTranslation(tile.getX(), 0, tile.getZ());
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
	
}
