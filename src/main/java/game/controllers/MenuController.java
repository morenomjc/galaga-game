package game.controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Game;

public class MenuController implements KeyListener{
	private Game game;
	
	public MenuController(Game game) {
		this.game = game;
	}
	
	public void render(Graphics g){
		Font font = new Font("courier new", Font.BOLD, 20);
		
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString("PRESS SPACE TO START", 270, 300);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(game.getState() == Game.STATE.MENU){
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_SPACE){
				game.setState(Game.STATE.GAME);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent paramKeyEvent) {
		
	}
}
