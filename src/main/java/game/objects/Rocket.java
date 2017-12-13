package game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

import game.Game;

public class Rocket extends GameObj{

	public Rocket(int x, int y, Game game) {
		super(x, y, game);
		try {
			this.setImage(getGame().getImageLoader().loadImage("/rocket.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick(){
		setY(getY() - 10);
	}

	@Override
	public void render(Graphics g) { 
		g.drawImage(getImage(), getX()+18, getY(), 30, 30,  null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), 30, 30);
	}
}
