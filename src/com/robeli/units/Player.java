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
			lines = 0;
		} else {
			lines = gridXlength;
			lineStart = lines;
		}
		
		units = new Units[gridZlength][gridXlength];
		
		for(int i = 0; i < (unitsAmount > (gridXlength*gridZlength)?(gridXlength*gridZlength): unitsAmount); i++){
			
			int currentColumn = (int)(i/gridZlength);
			int direction = (side? 1:-1);

			units[i-currentColumn*gridZlength]
					[(currentColumn*direction)+lineStart+(side?0:-1)] 
					= new Testunit();
			
			units[i-currentColumn*gridZlength]
					[(currentColumn*direction)+lineStart+(side?0:-1)].create
					(assetManager, rootNode, unitNode, grid.getGrid((currentColumn*direction)+lineStart+(side?0:-1),
													   i-currentColumn*gridZlength), gridHeight, side);
			
			grid.getGrid((currentColumn*direction)+lineStart+(side?0:-1),
						i-currentColumn*gridZlength).currentUnit = units[i-(currentColumn*gridZlength)]
																			    [(currentColumn*direction)+lineStart+(side?0:-1)];
			if (side == false){
				lines = lineStart - currentColumn*2;
				System.out.println((int)(i/gridZlength)+(lines-1) + " + " + 
								   (i-(int)(i/gridZlength)*gridZlength));
			}
		}
		
	}
	
}
