package sanchez.alexis;

import java.util.ArrayList;

import sanchez.alexis.coordinate.Coordinate;
import sanchez.alexis.coordinate.CoordinatesCheck;

/**
 * Ship class
 * 
 * @author Alexis Sanchez
 */
public class Ship {

	// List of coordinates of the ship
	private ArrayList<Coordinate> ship = new ArrayList<Coordinate>();

	/**
	 * Constructor of the ship object
	 * 
	 * @param startCoord the start of the ship
	 * @param endCoord the end of the ship
	 * @throws Exception
	 */
	public Ship(String startCoord, String endCoord) throws Exception {
		Coordinate cStart = new Coordinate(startCoord);
		Coordinate cStop = new Coordinate(endCoord);
		if (CoordinatesCheck.isCoordAlign(cStart, cStop)) {
			// the letter is the same ship = vertical / we change the digit
			if (cStart.getLetters().equals(cStop.getLetters())) {
				// if we have C6 and C2 we need to permute them
				if (Integer.parseInt(cStop.getNumbers()) < Integer.parseInt(cStart.getNumbers())) {
					Coordinate cTmp = cStart;
					cStart = cStop;
					cStop = cTmp;
				}
				for (int i = Integer.parseInt(cStart.getNumbers()); i <= Integer.parseInt(cStop.getNumbers()); i++) {
					String coord = cStart.getLetters().charAt(0) + String.valueOf(i);
					Coordinate c = new Coordinate(coord);
					ship.add(c);
				}
			} else {// the digit is the same ship = horizontal / we change the letter
				if ((int) cStart.getLetters().charAt(0) > (int) cStop.getLetters().charAt(0)) {
					Coordinate cTmp = cStart;
					cStart = cStop;
					cStop = cTmp;
				}
				for (int i = cStart.getLetters().charAt(0); i <= cStop.getLetters().charAt(0); i++) {
					String coord = ((char) i + cStart.getNumbers());
					Coordinate c = new Coordinate(coord);
					ship.add(c);
				}
			}
		} else {
			// We throw an exception if the coordinates are not good
			throw new Exception("Error ship constructor");
		}
	}

	/**
	 * To know if we can hit the coordinate with the missile
	 * 
	 * @param missileCoord the coordinate where we want to attack
	 * @return true if we can hit else false
	 */
	public boolean isHit(String missileCoord) {
		boolean hit = false;
		for (Coordinate c : this.ship) {
			if (c.toString().equals(missileCoord)) {
				hit = true;
			}
		}
		return hit;
	}

	/**
	 * Will hit the ship a the coord in param
	 * 
	 * @param missileCoord coordinate of the missile
	 * @return true if the missile has hit the ship else false
	 */
	public boolean hit(String missileCoord) {
		boolean hit = false;
		for (Coordinate c : this.ship) {
			if (c.toString().equals(missileCoord)) {
				if (!c.isHit()) {
					c.setHit(true);
				}
				hit = true;
			}
		}
		return hit;
	}

	/**
	 * To know if the ship is destroyed
	 * 
	 * @return true if destroyed else false
	 */
	public boolean isDestroyed() {
		boolean destroyed = true;
		for (Coordinate coord : this.ship) {
			if (!coord.isHit()) {
				destroyed = false;
			}
		}
		return destroyed;
	}

	/**
	 * Displays the coordinates of the ship
	 */
	public String toString() {
		String shipStr = "";
		for (Coordinate coord : this.ship) {
			shipStr += coord.toString() + " ";
		}
		return shipStr;
	}

	/**
	 * Function to know if the coordinate in param is in the ship
	 * 
	 * @param coord
	 * @return boolean true if the coordinate is in else false
	 */
	public boolean isCoordIn(String coord) {
		boolean isIn = false;
		for (Coordinate c : this.ship) {
			if (c.toString().equals(coord)) {
				isIn = true;
			}
		}
		return isIn;
	}

	/**
	 * @return the list of coordinates of the ship
	 */
	public ArrayList<Coordinate> getShip() {
		return this.ship;
	}

	/**
	 * @return the size of the ship
	 */
	public int getShipLength() {
		return this.getShip().size();
	}
}
