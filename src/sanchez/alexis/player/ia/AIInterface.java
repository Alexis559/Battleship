package sanchez.alexis.player.ia;

import sanchez.alexis.player.Player;

/**
 * Interface for AI
 * @author Alexis Sanchez
 *
 */

public abstract interface AIInterface {
	/**
	 * To place ship randomly
	 * @param size size of the ship
	 */
	public void placeShip(int size);
	
	/**
	 * Function to attack a player
	 * @param p player we want to attack
	 * @return true if hit else no
	 */
	public boolean attack(Player p);
}
