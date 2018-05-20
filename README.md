# Battleship!

IG3 POO project : Make a battleship


## Architecture

Test IA : package fr/battleship/TestIA.java

Main package : src/sanchez/alexis

 * Battleship.java
 * Ship.java

 - /coordinate : here are all the files concerning the coordinates
	 * Coordinate.java
	 * CoordinateCheck.java
	 
 - /player : here all the files concerning the players
	 * Player.java
	 * IPlayer.java
	 
	 * /ia : here all the files concerning the AI
		 * AI.java
		 * AIInterface.java
		 * AILevel0.java
		 * AILevel1.java
		 * AILevel2.java


## Compilation & Execute
At the root of the project you can find two files:

* [WINDOWS]_Compilation_java.bat
*  [LINUX]_Compilation_java.sh

#### Keep sure to let the scripts at the root of the project !

#### You also need to verify that Java is installed and configured (PATH variable etc...)

##### Windows : Use the script named "[WINDOWS]_Compilation_java.bat"
First to launch the script :

1) Use a Terminal and move to the project with the "cd" command
2) Then launch it with the name of the script :  [WINDOWS]_Compilation_java.bat

#### or

1) Double click on the script :)

##### Linux :

1) Open a terminal and move to the project with the "cd" command
2) Launch the script with : ./[LINUX]_Compilation_Java.sh

Then :

Once launched for both the script will create a "build" folder at the root with all the .class files.

Then the script will ask you if you want to launch the Battleship main program or if you want to launch the AI test.


#### You can also run the programs with those commands (you need to be in the build folder once you have launched the script above):

1) Main program Battleship : java sanchez/alexis/Battleship
2) TestIA : java fr/battleship/TestIA


# FINISH ! 
