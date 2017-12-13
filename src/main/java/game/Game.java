package game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;

import game.controllers.CollisionChecker;
import game.controllers.EnemyController;
import game.controllers.GameOverMenu;
import game.controllers.GameStatus;
import game.controllers.MenuController;
import game.controllers.PlayerController;
import game.controllers.RocketController;
import game.objects.Player;
import game.resources.GameDataUtil;
import game.resources.ImageLoader;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Galaga";
	private int highScore = 0;

	// dimensions
	private static final int WIDTH = 400;
	private static final int HEIGHT = (WIDTH / 12) * 9;
	private static final int SCALE = 2;

	private boolean running = false;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage background = null;
	private BufferedImage logo = null;
	private ImageLoader imageLoader = new ImageLoader();

	private Player player;
	private PlayerController playerController;
	private RocketController rocketController;
	private EnemyController enemyController;
	private CollisionChecker collisionChecker;

	private MenuController menuController;
	private GameStatus gameStatus;
	private GameOverMenu gameOverMenu;
	
	public enum STATE {
		MENU, GAME, END;
	};

	private STATE state = STATE.MENU;

	public Game() {
		// set canvas dimensions
		Dimension dimension = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setSize(dimension);
		setPreferredSize(dimension);
		setMaximumSize(dimension);

		JFrame frame = new JFrame(TITLE);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		playerController = new PlayerController(this);
		frame.addKeyListener(playerController);

		rocketController = new RocketController(this);
		frame.addKeyListener(rocketController);

		enemyController = new EnemyController(this);
		collisionChecker = new CollisionChecker(enemyController.getEnemies(), rocketController.getRockets(), this);

		player = new Player(400, getHeight() - 100, this);

		menuController = new MenuController(this);
		frame.addKeyListener(menuController);

		gameStatus = new GameStatus(this);

		gameOverMenu = new GameOverMenu(this);
		frame.addKeyListener(gameOverMenu);
		
	}

	@Override
	public void run() {
		while (running) {
			render();
			tick();
		}
		stop();
	}

	private void render() {
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if (bufferStrategy == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bufferStrategy.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		try {
			background = imageLoader.loadImage("/space.png");
			g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

			if (state == STATE.MENU) {
				logo = imageLoader.loadImage("/galaga-logo.png");
				g.drawImage(logo, 250, 100, 300, 150, this);

				menuController.render(g);
			} else if (state == STATE.GAME) {
				player.render(g);
				rocketController.render(g);
				enemyController.render(g);
				gameStatus.render(g);
			} else if (state == STATE.END) {
				gameOverMenu.render(g);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		g.dispose();
		bufferStrategy.show();
	}

	private void tick() {
		if (state == STATE.GAME) {
			rocketController.tick();
			enemyController.tick();
			collisionChecker.checkCollissions();
		}
	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.setName("game-thread");
		thread.start();

		player.setScore(0);

		playerController.start(); // start waiting for keyboard inputs
		rocketController.start(); // start controlling fired rockets
		enemyController.start(); // start spawning enemies
		collisionChecker.start(); // start checking for collisions
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
		System.exit(1);
	}

	public ImageLoader getImageLoader() {
		return imageLoader;
	}

	public void setImageLoader(ImageLoader imageLoader) {
		this.imageLoader = imageLoader;
	}

	public Player getPlayer() {
		return player;
	}

	public PlayerController getPlayerController() {
		return playerController;
	}

	public void setPlayerController(PlayerController playerController) {
		this.playerController = playerController;
	}

	public RocketController getRocketController() {
		return rocketController;
	}

	public void setRocketController(RocketController rocketController) {
		this.rocketController = rocketController;
	}

	public EnemyController getEnemyController() {
		return enemyController;
	}

	public void setEnemyController(EnemyController enemyController) {
		this.enemyController = enemyController;
	}

	public CollisionChecker getCollisionChecker() {
		return collisionChecker;
	}

	public void setCollisionChecker(CollisionChecker collisionChecker) {
		this.collisionChecker = collisionChecker;
	}

	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
}
