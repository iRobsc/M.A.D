package com.robeli.terrain;


import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

public class Terrain {
	
	private static Node terrainNode = new Node();
	private static AssetManager aM;
	
	public Terrain(AssetManager assetManager){
		aM = assetManager;
	}
	
	public static void main(String args[]){
	}
	
	public void createTerrain(Node rootNode){
		
		// Creates the plane
		Quad planeShape = new Quad(60,30);
		Geometry plane = new Geometry("Ground", planeShape);
		Texture grass = aM.loadTexture("/Textures/kappa.jpg");
		Material planeMat = new Material(aM, "Common/MatDefs/Light/Lighting.j3md");
		planeMat.setTexture("DiffuseMap", grass);
		plane.setMaterial(planeMat);
		
		// Sets the position and rotation
		plane.setLocalTranslation(-30,-5,25);
		plane.rotate(-90*FastMath.DEG_TO_RAD,0*FastMath.DEG_TO_RAD,0);
		
		// Creates the Environment
		createEnvironment();
		
		// Adds it to the terrain node
		terrainNode.attachChild(plane);
		rootNode.attachChild(terrainNode);
	}
	
	private static void createEnvironment(){
		Spatial tree = aM.loadModel("/Models/trees.obj");
		Texture treeTexture = aM.loadTexture("/Textures/kappa.jpg");
		Material treeMaterial = new Material(aM, "Common/MatDefs/Light/Lighting.j3md");
		treeMaterial.setTexture("DiffuseMap", treeTexture);
		tree.setMaterial(treeMaterial);
		
		// Sets the position
		tree.setLocalTranslation(1,-5,1);
		
		terrainNode.attachChild(tree);
	}
}
