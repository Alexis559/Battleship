package sanchez.alexis.player;

import sanchez.alexis.Ship;

public abstract interface IPlayer {
	public boolean lost();
	public void addShip(Ship ship);
	public boolean attack(Player p, String missileCoord);
	public void resetPlayer();
	public String displayGrid();
	public String displayMissileGrid();
	public boolean isAI();
}
