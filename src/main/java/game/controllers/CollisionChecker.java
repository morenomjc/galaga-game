package game.controllers;

import java.util.LinkedList;

import game.Game;
import game.objects.Enemy;
import game.objects.Explosion;
import game.objects.Rocket;
import game.resources.GameDataUtil;

public class CollisionChecker implements Runnable {
	private LinkedList<Enemy> enemies = new LinkedList<>();
	private LinkedList<Rocket> rockets = new LinkedList<>();

	private Game game;
	private boolean running = false;
	private Thread thread;

	public CollisionChecker(LinkedList<Enemy> enemies, LinkedList<Rocket> rockets, Game game) {
		super();
		this.enemies = enemies;
		this.rockets = rockets;
		this.game = game;
	}

	public void checkCollissions() {
		if (running) {
			Rocket rocket = null;

			Enemy enemy = null;
			for (int e = 0; e < enemies.size(); e++) {
				enemy = enemies.get(e);

				for (int r = 0; r < rockets.size(); r++) {
					rocket = rockets.get(r);

					// check if a rocket collided with an enemy
					if (enemy.getBounds().intersects(rocket.getBounds())) {

						Explosion explosion = new Explosion(enemy.getX(), enemy.getY(), game);
						explosion.render(game.getGraphics());

						enemies.remove(enemy);
						rockets.remove(rocket);

						game.getRocketController().addMissiles(5);
						
						System.out.println("ENEMY KILLED");
						
						game.getPlayer().addScore(10);
					}

				}
	
				if (enemy.getBounds().intersects(game.getPlayer().getBounds())) {

					Explosion explosion = new Explosion(enemy.getX(), enemy.getY(), game);
					explosion.render(game.getGraphics());

					enemies.remove(enemy);
					System.out.println("PLAYER COLLIDED WITH AN ENEMY");
					
					if(game.getPlayer().getScore() > game.getHighScore()){
						game.setHighScore(game.getPlayer().getScore());
						
						GameDataUtil.writeGameData(game.getHighScore());
					}
					game.setState(Game.STATE.END);
				}
			}
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
		thread.setName("collision-checker-thread");
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

}
