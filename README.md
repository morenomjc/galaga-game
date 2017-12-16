# Galaga Game
CS 140 Machine Problem

## Game Rules

 * Player has 50 missiles at the start of the game
 * For every enemy killed, 3 additional missiles are added to the player and the player scores 1 point
 * The game ends when the player has no more missiles or collided with an enemy ship
 * The game records the high score

## Game Controls
 
 * Use left and right keys to move the player's ship
 * Use space key to fire a rocket
 
## Compile
The game is a maven project, to compile:
```bash
mvn clean package
```

## Run
To run:
```bash
cd galaga-game
java -classpath target\galaga-game-0.0.1.jar game.Application
```
