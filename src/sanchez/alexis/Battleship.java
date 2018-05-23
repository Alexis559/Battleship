package sanchez.alexis;

import java.util.Scanner;

import sanchez.alexis.coordinate.Coordinate;
import sanchez.alexis.coordinate.CoordinatesCheck;
import sanchez.alexis.player.Player;
import sanchez.alexis.player.ia.AI;
import sanchez.alexis.player.ia.AILevel0;
import sanchez.alexis.player.ia.AILevel1;
import sanchez.alexis.player.ia.AILevel2;

/**
 * [MAIN] Battleship - everything starts here
 * 
 * @author Alexis Sanchez
 *
 */
public class Battleship {
	
	public static void main(String[] args) {

		Player player1 = null;
		Player player2 = null;
		Player current = null;
		Player enemy = null;

		boolean finish = false;
		boolean preparation = false;
		boolean hit = false;
		boolean choice = false;
		boolean goOn = true;
		int nbGame = 0;
		Scanner sc = new Scanner(System.in);

		String missileCoord = null;

		int[] ship_size = { 5, 4, 3, 3, 2 };// list of ships size

		System.out.println("============ Battleship ============\n");

		// Choose the game mode and the level of AI

		while (!choice) {
			System.out.println("Hello, which game mode do you want ?\n");
			System.out.println("1 - Player vs Player");
			System.out.println("2 - Player vs AI");
			System.out.println("3 - AI vs AI");
			String mode = sc.nextLine();
			switch (mode) {
			case "1":
				System.out.println("You chose the Player vs Player mode");
				System.out.println("Name of the player 1: ");
				String name1 = sc.nextLine();
				System.out.println("Name of the player 2: ");
				String name2 = sc.nextLine();
				player1 = new Player(name1);
				player2 = new Player(name2);
				choice = true;
				break;
			case "2":
				System.out.println("You chose the Player vs AI mode\n");
				System.out.println("Name of the player 1: ");
				String name3 = sc.nextLine();
				player1 = new Player(name3);
				while (!choice) {
					System.out.println("Which level of AI do you want to play aginst ?\n");
					System.out.println("1 - Beginner");
					System.out.println("2 - Medium");
					System.out.println("3 - Hard");
					String ai = sc.nextLine();
					switch (ai) {
					case "1":
						System.out.println("\n You chose the beginner AI");
						player2 = new AILevel0("Bob");
						choice = true;
						break;
					case "2":
						System.out.println("\n You chose the medium AI");
						player2 = new AILevel1("Bob");
						choice = true;
						break;
					case "3":
						System.out.println("\n You chose the hard AI");
						player2 = new AILevel2("Bob");
						choice = true;
						break;
					default:
						System.out.println("Choice unrecognized");
						break;
					}
				}
				break;
			case "3":
				System.out.println("You chose the AI vs AI mode\n");
				while (!choice) {
					for (int j = 0; j < 2; j++) {
						if (!choice)
							j = 0;
						else
							choice = false;
						System.out.println("Which level of AI ?\n");
						System.out.println("1 - Beginner");
						System.out.println("2 - Medium");
						System.out.println("3 - Hard");
						String ai = sc.nextLine();
						switch (ai) {
						case "1":
							System.out.println("\n You chose the beginner AI");
							if (j % 2 == 0)
								player1 = new AILevel0("Bob");
							else
								player2 = new AILevel0("Lapin");
							choice = true;
							break;
						case "2":
							System.out.println("\n You chose the medium AI");
							if (j % 2 == 0)
								player1 = new AILevel1("Bob");
							else
								player2 = new AILevel1("Lapin");
							choice = true;
							break;
						case "3":
							System.out.println("\n You chose the hard AI");
							if (j % 2 == 0)
								player1 = new AILevel2("Bob");
							else
								player2 = new AILevel2("Lapin");
							choice = true;
							break;
						default:
							System.out.println("Choice unrecognized");
							break;
						}
					}
				}
				break;
			default:
				System.out.println("Choice unrecognized");
				break;
			}
		}
		// END OF CHOICE

		// START OF THE GAME LOOP
		while (goOn) {
			finish = false;
			preparation = false;
			goOn = true;

			current = player1;
			enemy = player2;

			// SETTING UP THE GAME (place ship)
			while (!preparation) {
				System.out.println("[Preparation] It's the turn of " + current.getPlayerName());
				for (int i = 0; i < ship_size.length; i++) {
					if (current.isAI()) {
						((AI) current).placeShip(ship_size[i]);
					} else {
						String cStart;
						String cStop;
						boolean shipAdded = false;
						while (!shipAdded) {
							System.out.println("Ship size: " + ship_size[i]);
							System.out.println(current.getPlayerName() + " where do you want to start your ship ?");
							cStart = sc.nextLine();
							System.out.println(current.getPlayerName() + " where do you want to end your ship ?");
							cStop = sc.nextLine();
							if (CoordinatesCheck.isValidCoord(cStart) && CoordinatesCheck.isValidCoord(cStop)) {
								try {
									if (CoordinatesCheck.isCoordAlign(new Coordinate(cStart), new Coordinate(cStop))) {
										try {
											Ship s = new Ship(cStart, cStop);
											if (!current.shipCrossed(s)) {
												if (s.getShipLength() == ship_size[i]) {
													current.addShip(s);
													shipAdded = true;
												} else {
													System.out.println("Ship of wrong size !");
												}
											} else {
												System.out.println("The ship will cross another ship !");
											}
										} catch (Exception e) {
											System.out.println(e.getMessage());
										}
									} else {
										System.out.println("Coordinates are not aligned !");
									}
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}
							} else {
								System.out.println("Coordinates are not valid !");
							}
						}
						System.out.println("\nShip added !");
					}
				}
				if (current.equals(player2)) {
					preparation = true;
				}
				current = player2;
			}
			// END OF PREPARATION

			System.out.println("\n===================================");
			System.out.println("Preparation is done !");
			System.out.println("===================================");

			System.out.println("\n" + player1.getPlayerName() + "'s Grid" + "\n");
			System.out.println(player1.displayGrid());
			System.out.println("\n");

			System.out.println("\n" + player2.getPlayerName() + "'s Grid" + "\n");
			System.out.println(player2.displayGrid());
			System.out.println("\n");

			//we switch of player who begin each game
			if (nbGame % 2 == 0) {
				current = player1;
				enemy = player2;
			} else {
				current = player2;
				enemy = player1;
			}

			// Start of the game
			while (!finish) {
				System.out.println("===================================");
				System.out.println(current.getPlayerName() + "'s turn !");

				System.out.println("\n" + current.getPlayerName() + "'s Grid" + "\n");
				System.out.println(current.displayGrid());
				System.out.println("\n");

				System.out.println("\n" + current.getPlayerName() + "'s missile grid" + "\n");
				System.out.println(current.displayMissileGrid());
				System.out.println("\n");

				//Attack
				if (!current.isAI()) {
					System.out.println(current.getPlayerName() + " where do you want to attack ?");
					missileCoord = sc.nextLine();
					while (!CoordinatesCheck.isValidCoord(missileCoord) || current.isCoordIn(missileCoord)) {
						if (!CoordinatesCheck.isValidCoord(missileCoord))
							System.out.println("Wrong coordinate !");
						else
							System.out.println(current.getPlayerName() + " you have already shot here !");
						System.out.println(current.getPlayerName() + " where do you want to attack ?");
						missileCoord = sc.nextLine();
					}
					if (current.equals(player1)) {
						hit = player1.attack(player2, missileCoord);
					} else {
						hit = player2.attack(player1, missileCoord);
					}
				} else {
					hit = ((AI) current).attack(enemy);
				}

				if (hit) {
					System.out.println(current.getPlayerName() + " your missile hit a ship !");
					if (!current.isAI()) {
						if (enemy.shipDestroyed(missileCoord)) {
							System.out.println(current.getPlayerName() + " you have destroyed a ship !");
						}
					}
					//If one player lost
					if (enemy.lost()) {
						System.out.println("\n\n===================================");
						System.out.println(current.getPlayerName() + " has win !!");
						System.out.println("===================================");
						System.out.println("Do you want to continue to play ? y/n");
						String cont = sc.nextLine();
						//He don't want to continue
						if (cont.equals("n")) {
							goOn = false;
						} else {//He want to continue so we reset the players
							nbGame++;
							if (player1.isAI())
								((AI) player1).resetPlayer();
							else
								player1.resetPlayer();
							if (player2.isAI())
								((AI) player2).resetPlayer();
							else
								player2.resetPlayer();
						}
						finish = true;
					}
				} else {
					System.out.println(current.getPlayerName() + " your missile missed");
				}
				
				//We switch of players
				if (current.equals(player1)) {
					current = player2;
					enemy = player1;
				} else {
					current = player1;
					enemy = player2;
				}
			}
		}
		sc.close();
		System.out.println("\nGoodbye !");
	}
}