@echo off
REM MADE BY SANCHEZ ALEXIS 19/05/2018
echo SANCHEZ ALEXIS IG3 BATTLESHIP - COMPILATION WINDOWS
mkdir build
cd src
javac -d ../build sanchez/alexis/coordinate/*.java
javac -d ../build sanchez/alexis/ship.java
javac -d ../build sanchez/alexis/player/*.java
javac -d ../build sanchez/alexis/player/ia/*.java
javac -d ../build sanchez/alexis/*.java
cd ..
echo BUILD COMPLETED WITH SUCCES

set /p option="Type 1 to run the Battleship main or Type 2 to run the AI Test "
if "%option%"=="1" goto choix1
if "%option%"=="2" goto choix2
 
:choix1
cd build
java sanchez/alexis/Battleship
goto end
 
:choix2
cd build
java sanchez/alexis/TestIA
echo FICHIER GENERE (/build/ai_proof.csv)
ai_proof.csv
pause
goto end
 
:end