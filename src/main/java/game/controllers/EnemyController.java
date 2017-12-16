package game.controllers;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import game.Game;
import game.objects.Enemy;

public class EnemyController implements Runnable{
	private LinkedList<Enemy> enemies = new LinkedList<>();
	private Game game;
	private boolean running = false;
	private Thread thread;
	private int lastX = 0;
	private int lastY = 0;
	
	public EnemyController(Game game) {
		this.game = game;
		
		spawnManyEnemies();
	}

	public void tick() {
		Enemy enemy = null;
		for (int i = 0; i < enemies.size(); i++) {
			enemy = enemies.get(i);

			//if the enemy has reached the bottom, return to top
			if(enemy.getY() > game.getHeight()){
//				int start = ThreadLocalRandom.current().nextInt(0, game.getWidth() - 65);
//				enemy.setY(0);
//				enemy.setX(start);
				
				enemy.stop();
				enemies.remove(enemy);
			}
			
			enemy.goDown();
		}
		
		if(enemies.size() < 5){
			spawnNewEnemy();
		}
	}

	private void spawnManyEnemies(){
		for(int x=0; x<game.getWidth()-65; x+=100){
			int type = ThreadLocalRandom.current().nextInt(0, 3);
			int motion = ThreadLocalRandom.current().nextInt(0, 2);
			Enemy newEnemy = new Enemy(x, 0, game, type, motion);
			enemies.add(newEnemy);
		}
	}
	private void spawnNewEnemy(){
		int type = ThreadLocalRandom.current().nextInt(0, 3);
		int motion = ThreadLocalRandom.current().nextInt(0, 2);
		int start = ThreadLocalRandom.current().nextInt(0, game.getWidth() - 65);
		
		System.out.println("lastX=" + lastX + ", start=" + start);
		
//		if(lastX + 65 < game.getWidth()/2){
//			start = ThreadLocalRandom.current().nextInt(lastX+65, game.getWidth() - 65);
//		}
//		if(lastX - 65 > game.getWidth()/2){
//			start = ThreadLocalRandom.current().nextInt(0, lastX-65);
//		}
		
		if(start < (lastX + 65)){
			start = lastX - start;
		}else if(start > (lastX - 65)){
			start = lastX + start;
		}
		if(start < 0){
			start = 0;
		}else if(start > game.getWidth() - 65){
			start = game.getWidth() - 65;
		}
		Enemy newEnemy = new Enemy(start, 0, game, type, motion);
		enemies.add(newEnemy);	
		lastX = start + 65;
	}
	
	public void render(Graphics g) {
		Enemy enemy = null;
		for (int i = 0; i < enemies.size(); i++) {
			enemy = enemies.get(i);

			enemy.render(g);
		}
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
		thread.setName("enemies-thread");
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(LinkedList<Enemy> enemies) {
		this.enemies = enemies;
	}

}
