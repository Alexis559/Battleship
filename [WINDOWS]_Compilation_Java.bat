@echo off
REM MADE BY SANCHEZ ALEXIS 19/05/2018
echo SANCHEZ ALEXIS IG3 BATTLESHIP - COMPILATION WINDOWS
mkdir build
cd src
javac -d ../build sanchez/alexis/*.java
javac -d ../build fr/battleship/TestIA.java
cd ..
echo BUILD COMPLETED WITH SUCCES

set /p option="Type 1 to run the Battleship main or Type 2 to run the AI Test "
if "%option%"=="1" goto choix1
if "%option%"=="2" goto choix2
 
goto inconnu
 
:choix1
cd build
java sanchez/alexis/Battleship
goto end
 
:choix2
cd build
java fr/battleship/TestIA
echo FICHIER GENERE (/build/ai_proof.csv)
ai_proof.csv
goto end
 
:inconnu
echo COMMANDE UNKNOWN 
 
:end
echo END OF THE PROGRAM
pause