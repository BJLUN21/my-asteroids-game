package dk.sdu.mmmi.cbse.common.data;


public class GameData {

	private final GameKeys keys = new GameKeys();
	private int displayWidth = 800;
	private int displayHeight = 800;

	public GameKeys getKeys() {
		return keys;
	}

	public int getDisplayWidth() {
		return displayWidth;
	}

	public void setDisplayWidth(int width) {
		this.displayWidth = width;
	}

	public int getDisplayHeight() {
		return displayHeight;
	}

	public void setDisplayHeight(int height) {
		this.displayHeight = height;
	}
}
