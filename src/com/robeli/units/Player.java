package com.robeli.units;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.robeli.terrain.Grid;

public class Player {

	private boolean side;
	private Node unitNode = new Node();
	private int unitsAmount = 50, lines, lineStart;
	public Units[][] units;
	
	public Player (boolean sides){
		side = sides;
	}
	
	public void createUnits(int gridXlength, int gridZlength, Grid grid, float gridHeight, AssetManager assetManager, Node rootNode){
		
		if (side){
			lines = 0;  // the variable lines is added to check if it's player 1 or player 2 position, if it's player 2 it starts on the other edge of the grid
		} else {
			lines = gridXlength;
			lineStart = lines; // lineStart is always the same as line was before the loop started
		}
		
		units = new Units[gridZlength][gridXlength]; // creating the Units multiarray for the player class so we can find a specific unit in the class
		
		for(int i = 0; i < (unitsAmount > (gridXlength*gridZlength)?(gridXlength*gridZlength): unitsAmount); i++){ // the for loops never loops more than the amount of tile there is on the field
			
			int currentColumn = (int)(i/gridZlength);
			int direction = (side? 1:-1);

			units[i-currentColumn*gridZlength]
					[(currentColumn*direction)+lineStart+(side?0:-1)]  //creating the units on different indexes depending on if it's player 1 or 2 
					= new Testunit();
			
			units[i-currentColumn*gridZlength]
					[(currentColumn*direction)+lineStart+(side?0:-1)].create   // placing the units on different locations depending on if it's player 1 or 2
					(assetManager, rootNode, unitNode, grid.getGrid((currentColumn*direction)+lineStart+(side?0:-1),
													   i-currentColumn*gridZlength), gridHeight, side);
			
			grid.getGrid((currentColumn*direction)+lineStart+(side?0:-1),    // adding current unit to the tile so we can handle the tile as the unit later
						i-currentColumn*gridZlength).currentUnit = units[i-(currentColumn*gridZlength)]
																			    [(currentColumn*direction)+lineStart+(side?0:-1)];
			if (side == false){
				lines = lineStart - currentColumn*2; // decreasing the lines depending on what column the unit is on (*2 because of currentColumn adds +1 column each "i" 1-2=-1 insead of 1-1=0 to make it decrease in columns each time it has looped each column)
			}
		}
		
	}
	
}
