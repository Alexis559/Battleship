package sanchez.alexis.coordinate;

/**
 * Coordinate Class
 * 
 * @author Alexis Sanchez
 */
public class Coordinate {
	private String letters = "";
	private String numbers = "";
	private boolean isHit;

	/**
	 * Constructor of Coordinate object
	 * @param coord
	 * @throws Exception
	 */
	public Coordinate(String coord) throws Exception {
		if (CoordinatesCheck.isValidCoord(coord)) {
			for (int i = 0; i < coord.length(); i++) {
				if (Character.isLetter(coord.charAt(i)))
					this.letters += coord.charAt(i);
				else
					this.numbers += coord.charAt(i);
			}
		} else {
			throw new Exception("Coordinate not valid !");
		}
		this.isHit = false;
	}

	/**
	 * @return "letters""numbers"
	 */
	public String toString() {
		return this.getLetters() + this.getNumbers();
	}

	/**
	 * @return the isHit
	 */
	public boolean isHit() {
		return isHit;
	}

	/**
	 * @param isHit the isHit to set
	 */
	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}

	/**
	 * @return the letters
	 */
	public String getLetters() {
		return letters;
	}

	/**
	 * @return the numbers
	 */
	public String getNumbers() {
		return numbers;
	}
}
