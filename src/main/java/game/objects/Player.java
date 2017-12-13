package game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

import game.Game;

public class Player extends GameObj{
	private int score = 0;
	
	public Player(int x, int y, Game game) {
		super(x, y, game);
		try {
			this.setImage(getGame().getImageLoader().loadImage("/player-ship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), 64, 64,  null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), 64, 64);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int i) {
		this.score += i;
	}
}
