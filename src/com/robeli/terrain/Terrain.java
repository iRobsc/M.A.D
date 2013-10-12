package com.robeli.terrain;


import jme3tools.optimize.GeometryBatchFactory;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

public class Terrain {
	
	private static Node terrainNode = new Node();
	private static AssetManager aM;
	public static int planeLength = 50;
	
	public Terrain(AssetManager assetManager){
		aM = assetManager;
	}
	
	public static void main(String args[]){
	}
	
	public void createTerrain(Node rootNode, int x, int z){
		
		// Creates the plane
		Quad planeShape = new Quad(planeLength,planeLength);
		Geometry plane = new Geometry("Ground", planeShape);
		Texture grass = aM.loadTexture("/Textures/terrain.png");
		Material planeMat = new Material(aM, "Common/MatDefs/Light/Lighting.j3md");
		planeMat.setTexture("DiffuseMap", grass);
		plane.setMaterial(planeMat);
		
		
		// Sets the position and rotation
		plane.setLocalTranslation(x,0,z);
		plane.rotate(-90*FastMath.DEG_TO_RAD,-90*FastMath.DEG_TO_RAD, 0);
		
		// Creates the Environment
		createEnvironment();
		
		// Adds it to the terrain node
		terrainNode.attachChild(plane);
		rootNode.attachChild(terrainNode);
		GeometryBatchFactory.optimize(terrainNode);
	}
	
	private static void createEnvironment(){
		Spatial environment = aM.loadModel("/Models/colosseum.obj");
		Texture environmentTexture = aM.loadTexture("/Textures/stonetexture.png");
		environmentTexture.setMagFilter(Texture.MagFilter.Nearest);
		Material environmentMaterial = new Material(aM, "Common/MatDefs/Light/Lighting.j3md");
		environmentMaterial.setTexture("DiffuseMap", environmentTexture);
		environment.setMaterial(environmentMaterial);
		
		// Sets the position and scaling
		environment.setLocalScale(5);
		environment.setLocalTranslation(planeLength/2,0,planeLength/2);
		
		terrainNode.attachChild(environment);
	}
}
