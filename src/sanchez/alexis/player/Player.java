package sanchez.alexis.player;

import java.util.ArrayList;

import sanchez.alexis.Ship;
import sanchez.alexis.coordinate.Coordinate;
import sanchez.alexis.coordinate.CoordinatesCheck;

/**
 * Player class
 * 
 * @author Alexis Sanchez
 */
public class Player implements IPlayer{
	private ArrayList<Ship> ships; // list of ships
	private ArrayList<Coordinate> missileCoord;// list of missiles coordinates
	private ArrayList<Coordinate> enemyMissile;
	private String playerName; // name of the player
	private int score;//nb of game win
	/**
	 * Player constructor
	 * 
	 * @param playerName Name of the player
	 */
	public Player(String playerName) {
		this.ships = new ArrayList<Ship>();
		this.missileCoord = new ArrayList<Coordinate>();
		this.enemyMissile = new ArrayList<Coordinate>();
		this.playerName = playerName;
		this.score = 0;
	}

	/**
	 * @return the score of the player
	 */
	public int getScore() {
		return score;
	}

	/**
	 * To update the score of the player
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * To know if the player is an AI
	 * @return true if yes else false
	 */
	public boolean isAI(){
		return false;
	}
	/**
	 * @return true if the player has lost (all his ships are destroyed) else false
	 */
	public boolean lost() {
		boolean lost = true;
		for (Ship ship : this.ships) {
			if (!ship.isDestroyed()) {
				lost = false;
			}
		}
		return lost;
	}

	/**
	 * Add the ship in param to the list of the player
	 * 
	 * @param ship to add at the player
	 */
	public void addShip(Ship ship) {
		this.ships.add(ship);
	}

	/**
	 * @return the list of ships of the player
	 */
	private ArrayList<Ship> getShips() {
		return this.ships;
	}

	/**
	 * Displays the list of coordinates of all the player's ships
	 */
	public void displayShips() {
		String ships = "";
		for (Ship s : getShips()) {
			ships += s.toString() + "\n";
		}
		System.out.println(ships);
	}
	
	/**
	 * Function to know if the missile hit the player
	 * @param missileCoord the missile's coordinate
	 * @return true if hit else false
	 */
	private boolean hit(String missileCoord) {
		boolean hit = false;
		try {
			this.enemyMissile.add(new Coordinate(missileCoord));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for (Ship s : this.getShips()) {
			if (s.hit(missileCoord)) {
				hit = true;
				break;
			}
		}
		return hit;
	}
	
	/**
	 * To attack a player
	 * 
	 * @param p player to attack
	 * @param missileCoord coordinate of the missile
	 * @return boolean true if the attack hit a ship of the enemy else false
	 */
	public boolean attack(Player p, String missileCoord) {
		boolean hit = p.hit(missileCoord);
		try {
			Coordinate c = new Coordinate(missileCoord);
			c.setHit(hit);
			this.getListMissile().add(c);
		} catch (Exception e) {
			e.getMessage();
		}
		return hit;
	}

	/**
	 * @return the name of the player
	 */
	public String getPlayerName() {
		return this.playerName;
	}

	/**
	 * List of all the missiles shot by the player
	 * 
	 * @return the list of missiles coordinates
	 */
	protected ArrayList<Coordinate> getListMissile() {
		return this.missileCoord;
	}

	/**
	 * Function to know if the coordinate in param is in the missile list
	 * 
	 * @param coord
	 * @return boolean true if the coordinate is in else false
	 */
	public boolean isCoordIn(String coord) {
		boolean isIn = false;
		for (Coordinate c : this.getListMissile()) {
			if (c.toString().equals(coord)) {
				isIn = true;
				break;
			}
		}
		return isIn;
	}

	/**
	 * Function to know if the missile has destroyed the ship
	 * 
	 * @param missileCoord coordinate of the missile
	 * @return true if destroyed else false
	 */
	public boolean shipDestroyed(String missileCoord) {
		boolean destroyed = false;
		for (Ship s : this.getShips()) {
			for (Coordinate c : s.getShip()) {
				if (c.toString().equals(missileCoord)) {
					destroyed = s.isDestroyed();
					break;
				}
			}
			if (destroyed)
				break;
		}
		return destroyed;
	}

	/**
	 * Function to know if the ship in param will cross another ship of the player
	 * @param s the ship
	 * @return true if it will cross else false
	 */
	public boolean shipCrossed(Ship s){
		boolean crossed = false;
		for(Coordinate c : s.getShip()){
			for(Ship s1 : this.getShips()){
				for(Coordinate c1 : s1.getShip()){
					if(c.toString().equals(c1.toString())){
						crossed = true;
						break;
					}
				}
				if(crossed)
					break;
			}
			if(crossed)
				break;
		}
		return crossed;
	}
	
	/**
	 * To display the grid of the player with all of his ships with beautiful details and great design
	 */
	public String displayGrid() {
		String grid = "  ";
		// displays A B C ...
		for (int j = CoordinatesCheck.getMinColumn(); j <= CoordinatesCheck.getMaxColumn(); j++) {
			grid += "  " + (char) j;
		}
		// lines
		for (int i = CoordinatesCheck.getMinLine(); i <= CoordinatesCheck.getMaxLine(); i++) {
			if (i < 10) {// to align numbers under 10 with 10 and more
				grid += "\n " + i;
			} else {
				grid +="\n" + i;
			}
			// columns
			for (int j = CoordinatesCheck.getMinColumn(); j <= CoordinatesCheck.getMaxColumn(); j++) {
				boolean isUsed = false;
				boolean isHit = false;
				for (Ship s : this.getShips()) {
					if (s.isCoordIn("" + (char) j + i)) {
						for (Coordinate c : s.getShip()) {
							if (c.toString().equals("" + (char) j + i)) {
								if (c.isHit()) {
									isHit = true;
									break;
								}
							}
						}
						isUsed = true;
						break;
					}
				}
				if (isUsed) {
					if (isHit) {
						grid += "  x";// ship coordinate hit by a
												// missile
					} else {
						grid += "  o";// ship
					}
				} else {
					isUsed = false;
					for(Coordinate c : this.getEnemyMissile()){
						if(c.toString().equals("" + (char) j + i)){
							isUsed = true;
							break;
						}
					}
					if(isUsed)
						grid += "  *";//the enemy has shot here and missed
					else
						grid += "  .";// water or maybe air don't know :)
				}
			}
		}
		return grid;
	}

	/**
	 * To display the grid of the missiles shot by the player with beautiful details and great design
	 */
	public String displayMissileGrid() {
		String grid = "  ";
		// displays A B C ...
		for (int j = CoordinatesCheck.getMinColumn(); j <= CoordinatesCheck.getMaxColumn(); j++) {
			grid += "  " + (char) j;
		}
		// lines
		for (int i = CoordinatesCheck.getMinLine(); i <= CoordinatesCheck.getMaxLine(); i++) {
			if (i < 10) {// to align numbers under 10 with 10 and more
				grid += "\n " + i;
			} else {
				grid += "\n" + i;
			}
			// columns
			for (int j = CoordinatesCheck.getMinColumn(); j <= CoordinatesCheck.getMaxColumn(); j++) {
				boolean isUsed = false;
				boolean isHit = false;
				for (Coordinate c : this.getListMissile()) {
					if (c.toString().equals("" + (char) j + i)) {
						isUsed = true;
						isHit = c.isHit();
					}
				}
				if (isUsed) {
					if (isHit) {
						grid += "  x";// the missile hit the ship
					} else {
						grid +="  *";// missile in the sea
					}
				} else {
					grid += "  .";// water or maybe air don't know :)
				}
			}
		}
		return grid;
	}
	
	/**
	 * @return missileCoord of the enemy
	 */
	public ArrayList<Coordinate> getEnemyMissile(){
		return this.enemyMissile;
	}
	
	/**
	 * To reset the player between games
	 */
	public void resetPlayer(){
		this.getShips().clear();
		this.getListMissile().clear();
		this.getEnemyMissile().clear();
	}
}
