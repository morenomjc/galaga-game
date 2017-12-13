package game.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Properties;

public class GameDataUtil {
	private static final String GAME_FILE = "game.properties";

	public static int readGameData() {
		int highscore = 0;
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(GAME_FILE)));
			
			highscore = Integer.parseInt(properties.getProperty("highscore"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return highscore;
	}

	public static void writeGameData(int score) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(GAME_FILE)));
			properties.setProperty("highscore", String.valueOf(score));
			
			FileOutputStream fileOutputStream = new FileOutputStream(GAME_FILE);
			properties.save(fileOutputStream, null);
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
