package game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

import game.Game;

public class Enemy extends GameObj implements Runnable{
	private int motion;
	private boolean running = false;
	private Thread thread;
	private int velocity = 5;
	
	public Enemy(int x, int y, Game game, int type, int motion) {
		super(x, y, game);
		try {
			if(type == 0){
				this.setImage(getGame().getImageLoader().loadImage("/attack-plane-1.png"));
			}else if(type == 1){
				this.setImage(getGame().getImageLoader().loadImage("/attack-plane-2.png"));
			}else{
				this.setImage(getGame().getImageLoader().loadImage("/attack-plane-3.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.motion = motion;
	}
	
	public void goDown(){
		setY(getY() + velocity);
		if(motion == 0){
			goLeft();
		}else{
			goRight();
		}
		
		if (getX() < 1) {
			motion = 1;
		}
		if (getX() > getGame().getWidth() - 65) {
			motion = 0;
		}
	}
	
	public void goLeft(){
		setX(getX() - velocity);
	}
	public void goRight(){
		setX(getX() + velocity);
	}

	@Override
	public void render(Graphics g) { 
		g.drawImage(getImage(), getX(), getY(), 65, 65,  null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), 65, 65);
	}
	
	@Override
	public void run() {
		while (running) {
			
		}
		stop();
	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
