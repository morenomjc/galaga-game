package game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

import game.Game;

public class Player extends GameObj{

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
}
