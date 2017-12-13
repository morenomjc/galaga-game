package game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Game;

public abstract class GameObj {
	private int x;
	private int y;
	private Game game;
	private BufferedImage image;
	
	public GameObj(int x, int y, Game game) {
		super();
		this.x = x;
		this.y = y;
		this.game = game;
	}

	public void render(Graphics g){}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public abstract Rectangle getBounds();
}
