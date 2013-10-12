package com.robeli.terrain;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.FastMath;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

import org.lwjgl.opengl.*;

public class Tile {
	private float x, z;
	public static float width = 1.5f;
	public static float length = 1.5f;
	public Texture texture;
	private static AssetManager assetManager;
	private static Node rootNode;
	private Material spriteMaterial;
	private Geometry tile;
	
	public Tile(Node rootNode, AssetManager assetManager){
		Tile.rootNode = rootNode;
		Tile.assetManager = assetManager;
		
		GL11.glTexEnvf(GL14.GL_TEXTURE_FILTER_CONTROL, GL14.GL_TEXTURE_LOD_BIAS, -1);
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	public float getX(){
		return x;
	}
	
	public float getZ(){
		return z;
	}
	
	public void createTile(float X, float Y ,float Z){
		Quad quadShape = new Quad(width,length);
		tile = new Geometry ("tile", quadShape);
		setTexture("/Textures/tileA.png");
		spriteMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		spriteMaterial.setTexture("ColorMap", getTexture());
		spriteMaterial.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		getTexture().setMagFilter(Texture.MagFilter.Nearest);
		
		tile.setLocalTranslation(X,Y,Z);
		
		x = X;
		z = Z;
		
		tile.rotate(-90*FastMath.DEG_TO_RAD, 0, 0);
		tile.setQueueBucket(Bucket.Transparent);
		tile.setMaterial(spriteMaterial);

		rootNode.attachChild(tile);
	}
	
	public void setTexture(String textureName){
		texture = assetManager.loadTexture(textureName);
		spriteMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		spriteMaterial.setTexture("ColorMap", getTexture());
		getTexture().setMagFilter(Texture.MagFilter.Nearest);
		spriteMaterial.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		tile.setMaterial(spriteMaterial);
	}
}