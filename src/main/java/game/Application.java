package game;

import game.resources.GameDataUtil;

public class Application {

	public static void main(String[] args) {
		Game game = new Game();
		game.setHighScore(GameDataUtil.readGameData());
		game.start();
		
	}

}
