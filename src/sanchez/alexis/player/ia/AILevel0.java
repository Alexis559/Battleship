package sanchez.alexis.player.ia;

import sanchez.alexis.coordinate.Coordinate;
import sanchez.alexis.coordinate.CoordinatesCheck;
import sanchez.alexis.player.Player;

/**
 * AI level 0 class shot only randomly and can shoot on the same coordinates
 * @author Alexis Sanchez
 *
 */

public class AILevel0 extends AI{
	
	/**
	 * AI constructor
	 * @param playerName name of the AI
	 */
	public AILevel0(String playerName) {
		super("AI0 " + playerName);
	}
	
	/**
	 * Function to shot randomly
	 * @param p player we want to attack
	 */
	@Override
	public boolean attack(Player p) {
		Coordinate missileCoord = CoordinatesCheck.getRandomCoordinate();
		return super.attack(p, missileCoord.toString());
	}
}