package game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

import game.Game;

public class Explosion extends GameObj{

	public Explosion(int x, int y, Game game) {
		super(x, y, game);
		try {
			this.setImage(getGame().getImageLoader().loadImage("/explosion.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(Graphics g) { 
		g.drawImage(getImage(), getX(), getY(), 100, 100,  null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), 100, 100);
	}
}
