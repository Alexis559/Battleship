package sanchez.alexis.player.ia;

import sanchez.alexis.Ship;
import sanchez.alexis.coordinate.Coordinate;
import sanchez.alexis.coordinate.CoordinatesCheck;
import sanchez.alexis.player.Player;

/**
 * AI Class
 * @author Alexis Sanchez
 *
 */

public abstract class AI extends Player implements AIInterface{

	protected AI(String playerName) {
		super(playerName);
	}

	
	@Override
	public boolean isAI(){
		return true;
	}
	
	/**
	 * Function to place the ship randomly
	 * @param size size of the ship 
	 */
	public void placeShip(int size){
		boolean added = false;
		while(!added){
			String cStop = "";
			Coordinate cStart = null;
			Ship s;
			do{
				cStart = CoordinatesCheck.getRandomCoordinate();
			}while(!CoordinatesCheck.isValidCoord(cStart.toString()));
			//0 = vertical and 1 horizontal
			int orientation = 0 + (int)(Math.random() * ((1 - 0) + 1));
			if(orientation == 0){
				cStop = cStart.getLetters();
				cStop += String.valueOf(Integer.parseInt(cStart.getNumbers())+(size-1));
				if(!CoordinatesCheck.isValidCoord(cStop)){
					cStop = cStart.getLetters();
					cStop += String.valueOf(Integer.parseInt(cStart.getNumbers())-(size-1));
				}
			}else{
				if(!CoordinatesCheck.isValidCoord(cStop)){
					cStop = "" + (char)(cStart.getLetters().charAt(0) - (size-1));
					cStop += cStart.getNumbers();
				}
			}
			try {
				if(CoordinatesCheck.isValidCoord(cStop) &&CoordinatesCheck.isValidCoord(cStart.toString()) && CoordinatesCheck.isCoordAlign(cStart, new Coordinate(cStop))){
					s = new Ship(cStart.toString(), cStop);
					if(!this.shipCrossed(s)){
						this.addShip(s);
						added = true;
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
