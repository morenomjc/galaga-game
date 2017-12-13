package game.controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Game;

public class GameOverMenu implements KeyListener{
	private Game game;
	
	public GameOverMenu(Game game) {
		this.game = game;
	}
	
	public void render(Graphics g){
		Font font = new Font("courier new", Font.BOLD, 20);
		
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("GAME OVER", 340, 280);
		
		g.setColor(Color.GREEN);
		g.drawString("YOUR SCORE", 335, 320);
		
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(game.getPlayer().getScore()), 335, 340);
		
		
		g.setColor(Color.YELLOW);
		g.drawString("PRESS SPACE TO START", 290, 500);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(game.getState() == Game.STATE.END){
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_SPACE){
				game.setState(Game.STATE.GAME);
				game.getPlayer().setScore(0);
				game.getRocketController().setMissiles(50);
				game.getEnemyController().getEnemies().clear();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent paramKeyEvent) {
		
	}
}
