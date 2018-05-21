package sanchez.alexis.player;

import sanchez.alexis.Ship;

/**
 * Interface for Palyer
 * @author Alexis Sanchez
 *
 */
public abstract interface IPlayer {
	
	/**
	 * @return true if the player has lost (all his ships are destroyed) else false
	 */
	public boolean lost();
	
	/**
	 * Add the ship in param to the list of the player
	 * 
	 * @param ship to add at the player
	 */
	public void addShip(Ship ship);
	
	/**
	 * To attack a player
	 * 
	 * @param p player to attack
	 * @param missileCoord coordinate of the missile
	 * @return boolean true if the attack hit a ship of the enemy else false
	 */
	public boolean attack(Player p, String missileCoord);
	
	/**
	 * To reset the player between games
	 */
	public void resetPlayer();
	
	/**
	 * To display the grid of the player with all of his ships with beautiful details and great design
	 */
	public String displayGrid();
	
	/**
	 * To display the grid of the missiles shot by the player with beautiful details and great design
	 */
	public String displayMissileGrid();
	
	/**
	 * To know if the player is an AI
	 * @return true if yes else false
	 */
	public boolean isAI();
}
