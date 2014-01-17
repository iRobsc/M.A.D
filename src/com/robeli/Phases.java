package com.robeli;

import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.robeli.terrain.Grid;
import com.robeli.units.Player;

public class Phases {
		
	public Mousepicking mousePicking;
		
	public Phases(Camera camera, Player currentPlayer, InputManager inputManager, AssetManager assetManager, Node rootNode, Grid grid){
		mousePicking = new Mousepicking(camera, currentPlayer, inputManager, assetManager, rootNode, grid);
		mousePicking.initialize();
	}

	public void selectPhase(String currentPhase){
		if (currentPhase == "movePhase"){
			mousePicking.unitSelection = true;
			mousePicking.unitMovement = true;
		}
		else if (currentPhase == "attackPhase"){
			
		}
		else if (currentPhase == "defensePhase"){
			
		}
	}
}
