package game.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Game;
import game.objects.Player;

public class PlayerController implements Runnable, KeyListener{
	private Game game;
	private boolean running = false;
	private Thread thread;
	
	public PlayerController(Game game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(running){
			Player player = game.getPlayer();
			
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_LEFT){
				if(player.getX() > 0){
					player.setX(player.getX() - 20);
				}
			}else if(key == KeyEvent.VK_RIGHT){
				if(player.getX() < game.getWidth() - 65){
					player.setX(player.getX() + 20);
				}
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent paramKeyEvent) {
		
	}

	@Override
	public void keyReleased(KeyEvent paramKeyEvent) {
		
	}

	@Override
	public void run() {
		while(running){
			
		}
		stop();
	}
	
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.setName("player-thread");
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
}
