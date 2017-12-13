package game.controllers;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import game.Game;
import game.objects.Player;
import game.objects.Rocket;
import game.resources.GameDataUtil;

public class RocketController implements Runnable, KeyListener {
	private LinkedList<Rocket> rockets = new LinkedList<>();
	private Game game;
	private int missiles = 50;
	private boolean running = false;
	private Thread thread;

	public RocketController(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (game.getState() == Game.STATE.GAME) {
			if (running && missiles > 0) {
				Player player = game.getPlayer();

				int key = e.getKeyCode();

				if (key == KeyEvent.VK_SPACE) {
					System.out.println("FIRE");
					Rocket rocket = new Rocket(player.getX(), player.getY(), game);
					rockets.add(rocket);
					missiles--;
				}
			} else {
				if(game.getPlayer().getScore() > game.getHighScore()){
					game.setHighScore(game.getPlayer().getScore());
					
					GameDataUtil.writeGameData(game.getHighScore());
				}
				game.setState(Game.STATE.END);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public void tick() {
		Rocket rocket = null;
		for (int i = 0; i < rockets.size(); i++) {
			rocket = rockets.get(i);

			if (rocket.getY() < 0)
				rockets.remove(rocket);

			rocket.tick();
		}
	}

	public void render(Graphics g) {
		Rocket rocket = null;
		for (int i = 0; i < rockets.size(); i++) {
			rocket = rockets.get(i);

			rocket.render(g);
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
		thread.setName("rockets-thread");
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

	public LinkedList<Rocket> getRockets() {
		return rockets;
	}

	public void setRockets(LinkedList<Rocket> rockets) {
		this.rockets = rockets;
	}

	public int getMissiles() {
		return missiles;
	}

	public void setMissiles(int missiles) {
		this.missiles = missiles;
	}

	public void addMissiles(int m) {
		this.missiles += m;
	}

}
