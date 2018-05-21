package fr.battleship;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import sanchez.alexis.player.ia.AI;
import sanchez.alexis.player.ia.AILevel0;
import sanchez.alexis.player.ia.AILevel1;
import sanchez.alexis.player.ia.AILevel2;

/**
 * [MAIN] IaVsIa - everything starts here
 * 
 * @author Alexis Sanchez
 *
 */
public class TestIA {

	public static void main(String[] args) {
		
		final int NB_GAME = 100;
		int loopGame = 1;//variable to help us to switch between players
		
		AI player1 = new AILevel0("Lapin 1");
		AI player2 = new AILevel1("Lapin 2");
		AI player3 = new AILevel2("Lapin 3");
		AI players[] = {player1, player2, player3, player1};//list of players
		
		PrintWriter pw = null;
		
		int[] ship_size = {5, 4, 3, 3, 2};// list of ships size
		
		try {
			pw = new PrintWriter(new File("ai_proof.csv"));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("AI Name 1");
		sb.append(";");
		sb.append("Score");
		sb.append(";");
		sb.append("AI Name 2");
		sb.append(";");
		sb.append("Score");	
		
		while(loopGame < 4){//we play 3 matches of 100 games

			AI current = players[loopGame-1];
			AI enemy = players[loopGame];
			
			for(int w=0; w<NB_GAME; w++){
		
				boolean finish = false;
				boolean hit;
		
				//players place their ships
				for (int i = 0; i < ship_size.length; i++) {
					current.placeShip(ship_size[i]);
				}
				
				for (int i = 0; i < ship_size.length; i++) {
					enemy.placeShip(ship_size[i]);
				}
					
				//we switch of player who begin each game
				if(w%2 == 0){
					current = players[loopGame-1];
					enemy = players[loopGame];
					
				}else{
					current = players[loopGame];
					enemy = players[loopGame-1];
				}
				
				//Start of the game
				while (!finish) {
					
					//attack
					if (current.equals(players[loopGame-1])) {
						hit = players[loopGame-1].attack(players[loopGame]);
					} else {
						hit = players[loopGame].attack(players[loopGame-1]);
					}
					
					//check for the end of the game
					if (hit) {
						if (enemy.lost()) {
							current.setScore(current.getScore()+1);
							finish = true;
						}
					} 
					
					//we switch the players
					if (current.equals(players[loopGame-1])) {
						current = players[loopGame];
						enemy = players[loopGame-1];
					} else {
						current = players[loopGame-1];
						enemy = players[loopGame];
					}
				}
				//we "reset" the players for the next game
				current.resetPlayer();
				enemy.resetPlayer();
			}
			
			//once the 100 games are finished we save the score in a buffer
			System.out.println(current.getPlayerName() + " " + current.getScore() + " - " + enemy.getScore() + " " + enemy.getPlayerName());
			sb.append("\n");
			sb.append(current.getPlayerName());
			sb.append(";");
			sb.append(current.getScore());
			sb.append(";");
			sb.append(enemy.getPlayerName());
			sb.append(";");
			sb.append(enemy.getScore());
			
			//we reset the score
			current.setScore(0);
			enemy.setScore(0);
			loopGame++;
		}
		//we write in a csv file what is saved in the buffer
		pw.write(sb.toString());
		pw.close();
	}
}