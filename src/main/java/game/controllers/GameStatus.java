package game.controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Game;

public class GameStatus{
	private Game game;
	
	public GameStatus(Game game) {
		this.game = game;
	}
	
	public void render(Graphics g){
		Font font = new Font("courier new", Font.BOLD, 20);
		
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("HI-SCORE", 10, 20);
		
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(game.getHighScore()), 10, 40);
		
		g.setColor(Color.RED);
		g.drawString("SCORE", 10, 60);
		
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(game.getPlayer().getScore()), 10, 80);
		
		g.setColor(Color.YELLOW);
		g.drawString("KILLS", 10, game.getHeight() - 80);
		
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(game.getPlayer().getScore()/10), 10, game.getHeight() - 60);
		
		
		g.setColor(Color.GREEN);
		g.drawString("CANONS", 10, game.getHeight() - 40);
		
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(game.getRocketController().getMissiles()), 10, game.getHeight() - 20);
	}
}
