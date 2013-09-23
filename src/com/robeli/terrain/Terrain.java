package com.robeli.terrain;


import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

public class Terrain {
	
	private static Node terrainNode = new Node();
	
	public static void main(String args[]){
	}
	
	public void createTerrain(AssetManager assetManager, Node rootNode){
		// Creates the plane
		Quad planeShape = new Quad(20,10);
		Geometry plane = new Geometry("Ground", planeShape);
		Texture grass = assetManager.loadTexture("/Textures/kappa.jpg");
		Material planeMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
		planeMat.setTexture("DiffuseMap", grass);
		plane.setMaterial(planeMat);
		
		// Sets the position and rotation
		plane.setLocalTranslation(-12,-5,8);
		plane.rotate(-90*FastMath.DEG_TO_RAD,0*FastMath.DEG_TO_RAD,0);
		
		// Adds it to the terrain node
		terrainNode.attachChild(plane);
		rootNode.attachChild(terrainNode);
	}
	
	private static void createEnvironment(){
		Box treeshape = new Box();
		Geometry tree = new Geometry("tree", treeshape);
		
	}
}
