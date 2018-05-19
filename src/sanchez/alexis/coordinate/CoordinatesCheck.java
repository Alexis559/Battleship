package sanchez.alexis.coordinate;

/**
 * Class to check coordinates
 * ONLY STATIC METHODS
 * @author Alexis Sanchez
 */

public abstract class CoordinatesCheck {
	
	/**
	 * To know the size of the grid
	 */
	private static final int MIN_LINE = 1;
	private static final int MAX_LINE = 10;
	private static final char MIN_COLUMN = 'A';
	private static final char MAX_COLUMN = 'J';
	

	/**
	 * If the coordinate is composed of a letter between MIN_COLUMN and MAX_COLUMN and a number between MIN_LINE and MAX_LINE then the coordinate is valid
	 * @param coord coordinate to verify
	 * @return true if the coord in param is valid else false
	 */
	public static boolean isValidCoord(String coord){
		boolean valid = true;
		String numbers = "";
		for(int i=1; i<coord.length(); i++){
			if(!Character.isDigit(coord.charAt(i))){
				valid = false;
			}else{
				numbers += coord.charAt(i);
			}
		}
		return (coord.length() >= 2)  && valid && Character.isLetter(coord.charAt(0)) && coord.charAt(0) <= MAX_COLUMN && coord.charAt(0) >= MIN_COLUMN && (Integer.parseInt(numbers) >= MIN_LINE) && (Integer.parseInt(numbers) <= MAX_LINE);
	}
	
	/**
	 * To know if the coordinates in param are aligned
	 * @param coord1
	 * @param coord2
	 * @return true if aligned else false
	 */
	public static boolean isCoordAlign(Coordinate coord1, Coordinate coord2){
		return (coord1.getLetters().equals(coord2.getLetters())) || (coord1.getNumbers().equals(coord2.getNumbers()));
	}
	
	/**
	 * Function that returns a random coordinate
	 * @return Coordinate
	 */
	public static Coordinate getRandomCoordinate(){
		Coordinate coord = null;
		int lineRandom = CoordinatesCheck.getMinLine() + (int)(Math.random() * ((CoordinatesCheck.getMaxLine() - CoordinatesCheck.getMinLine()) + 1));
		int columnRandom = (int)CoordinatesCheck.getMinColumn() + (int)(Math.random() * (((int)CoordinatesCheck.getMaxColumn() - (int)CoordinatesCheck.getMinColumn()) + 1));
		try {
			coord =  new Coordinate(""+(char)columnRandom+lineRandom);
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
		return coord;
	}
	
	/**
	 * @return the min line
	 */
	public static int getMinLine() {
		return MIN_LINE;
	}

	/**
	 * @return the max line
	 */
	public static int getMaxLine() {
		return MAX_LINE;
	}

	/**
	 * @return the min column
	 */
	public static char getMinColumn() {
		return MIN_COLUMN;
	}

	/**
	 * @return the max column
	 */
	public static char getMaxColumn() {
		return MAX_COLUMN;
	}	
}
