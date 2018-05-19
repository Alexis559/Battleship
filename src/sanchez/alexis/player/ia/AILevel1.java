package sanchez.alexis.player.ia;

import sanchez.alexis.coordinate.Coordinate;
import sanchez.alexis.coordinate.CoordinatesCheck;
import sanchez.alexis.player.Player;

/**
 * AI level 1 class shot only randomly but can't shoot on the same coordinates
 * @author Alexis Sanchez
 *
 */

public class AILevel1 extends AI{

	/**
	 * AI constructor
	 * @param playerName name of the AI
	 */
	public AILevel1(String playerName) {
		super("AI1 " + playerName);
	}

	/**
	 * Function to shot randomly
	 * @param p player we want to attack
	 */
	@Override
	public boolean attack(Player p) {
		Coordinate missileCoord = CoordinatesCheck.getRandomCoordinate();
		//if we have already hit the coordinate we change
		while(super.isCoordIn(missileCoord.toString())){
			missileCoord = CoordinatesCheck.getRandomCoordinate();
		}
		return super.attack(p, missileCoord.toString());
	}

}
