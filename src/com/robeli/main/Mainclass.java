package com.robeli.main;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.robeli.terrain.Terrain;

public class Mainclass extends SimpleApplication {

	public static void main(String[] args) {
		Mainclass app = new Mainclass();
		app.start();
	}

	@Override
	public void simpleInitApp() {
		flyCam.setMoveSpeed(10);
		
		AmbientLight al = new AmbientLight();
		al.setColor(ColorRGBA.White.mult(5.3f));
		rootNode.addLight(al);
		
		Terrain terrain = new Terrain();
		terrain.createTerrain(assetManager, rootNode);
	}
	
	@Override
	public void simpleUpdate(float tpf){
		
	}
	
	@Override
	public void simpleRender(RenderManager rm){
		
	}
}
