#!/bin/bash
#MADE BY SANCHEZ ALEXIS IG3 19/05/2018
echo "SANCHEZ ALEXIS IG3 BATTLESHIP - COMPILATION LINUX"
if [ ! -d "build" ]; then
	mkdir build;
fi
cd src;
javac -d ../build sanchez/alexis/*.java;
javac -d ../build fr/battleship/TestIA.java;
cd ..;
echo "BUILD COMPLETED !";
read -p 'Type 1 to run the Battleship main or Type 2 to run the AI Test' option;
if [ $option == "1" ]; then
	cd build;
	java sanchez/alexis/Battleship;
elif [ $option == "2" ]; then
	cd build;
	java fr/battleship/TestIA;
	echo "CSV file can be found in the build directory";
else
	echo "Unknown";
fi
