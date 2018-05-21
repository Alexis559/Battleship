package sanchez.alexis.player.ia;

import java.util.Stack;

import sanchez.alexis.coordinate.Coordinate;
import sanchez.alexis.coordinate.CoordinatesCheck;
import sanchez.alexis.player.Player;

/**
 * AI level 2 class
 * @author Alexis Sanchez
 */

public class AILevel2 extends AI{

	private Stack<Coordinate> coordToHit;//here we will use a stack to store the coordinates
	private Coordinate currentCoord;//the current coordinate that we hit
	
	/**
	 * AI Constructor
	 * @param playerName name of the AI
	 */
	public AILevel2(String playerName) {
		super("AI2 " + playerName);
		int x = 1;
		this.coordToHit = new Stack<Coordinate>();
		try {
			//we fill the stack with all the coordinates from the first one 'A1'
			for (int i = CoordinatesCheck.getMaxLine(); i >=  CoordinatesCheck.getMinLine(); i--) {
				for (int j = CoordinatesCheck.getMaxColumn()-x; j >= CoordinatesCheck.getMinColumn() ; j--) {
					this.coordToHit.push(new Coordinate((char)j + String.valueOf(i)));
					j--;//we only add one coordinate on two
				}
				//we advance of one column on the line of odd numbers
				if((i%2) == 0) {
					x = 0;
				}else {
					x = 1;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * @see Player
	 */
	@Override
	public void resetPlayer(){
		super.resetPlayer();
		int x = 1;
		this.coordToHit.clear();
		try {
			//we fill the stack with all the coordinates from the first one 'A1'
			for (int i = CoordinatesCheck.getMaxLine(); i >=  CoordinatesCheck.getMinLine(); i--) {
				for (int j = CoordinatesCheck.getMaxColumn()-x; j >= CoordinatesCheck.getMinColumn() ; j--) {
					this.coordToHit.push(new Coordinate((char)j + String.valueOf(i)));
					j--;//we only add one coordinate on two
				}
				//we advance of one column on the line of even numbers
				if((i%2) == 0) {
					x = 0;
				}else {
					x = 1;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * @param p player we want to attack
	 */
	@Override
	public boolean attack(Player p) {
		boolean hit = false;
		//if we have already hit the coordinate we take another one in the stack
		do{
			try{
				currentCoord = this.coordToHit.pop(); 
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}while(this.isCoordIn(currentCoord.toString()));
		hit = super.attack(p, this.currentCoord.toString());
		//if the coordinate comes at the end of the line we need to change of line and come back at the 1st column
		if(hit){
			//right
			if(CoordinatesCheck.isValidCoord((char)(currentCoord.getLetters().charAt(0)+1)+currentCoord.getNumbers()) && !this.isCoordIn((char)(currentCoord.getLetters().charAt(0)+1)+currentCoord.getNumbers())){
				try {
					this.coordToHit.push(new Coordinate((char)(currentCoord.getLetters().charAt(0)+1)+currentCoord.getNumbers()));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			//down
			if(CoordinatesCheck.isValidCoord(currentCoord.getLetters()+String.valueOf(Integer.parseInt(currentCoord.getNumbers())+1)) && !this.isCoordIn(currentCoord.getLetters()+String.valueOf(Integer.parseInt(currentCoord.getNumbers())+1))){
				try {
					this.coordToHit.push(new Coordinate(currentCoord.getLetters()+String.valueOf(Integer.parseInt(currentCoord.getNumbers())+1)));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			//left
			if(CoordinatesCheck.isValidCoord((char)(currentCoord.getLetters().charAt(0)-1)+currentCoord.getNumbers()) && !this.isCoordIn((char)(currentCoord.getLetters().charAt(0)-1)+currentCoord.getNumbers())){
				try {
					this.coordToHit.push(new Coordinate((char)(currentCoord.getLetters().charAt(0)-1)+currentCoord.getNumbers()));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			//up
			if(CoordinatesCheck.isValidCoord(currentCoord.getLetters()+String.valueOf(Integer.parseInt(currentCoord.getNumbers())-1)) && !this.isCoordIn(currentCoord.getLetters()+String.valueOf(Integer.parseInt(currentCoord.getNumbers())-1))){
				try {
					this.coordToHit.push(new Coordinate(currentCoord.getLetters()+String.valueOf(Integer.parseInt(currentCoord.getNumbers())-1)));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return hit;
	}	
}