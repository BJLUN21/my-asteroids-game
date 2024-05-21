package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

	private Entity player;

	public PlayerPlugin() {
	}

	@Override
	public void start(GameData gameData, World world) {

		// Add entities to the world
		player = createPlayerShip(gameData);
		world.addEntity(player);
	}

	@Override
	public void stop(GameData gameData, World world) {
		// Remove entities
		world.removeEntity(player);
	}

	private Entity createPlayerShip(GameData gameData) {

		float[] shapeX = new float[4];
		float[] shapeY = new float[4];

		shapeX[0] = (float) (Math.cos(0) * 8);
		shapeY[0] = (float) (Math.sin(0) * 8);
		shapeX[1] = (float) (Math.cos(0 - 4 * Math.PI / 5) * 8);
		shapeY[1] = (float) (Math.sin(0 - 4 * Math.PI / 5) * 8);
		shapeX[2] = (float) (Math.cos(0 + Math.PI) * 5);
		shapeY[2] = (float) (Math.sin(0 + Math.PI) * 5);
		shapeX[3] = (float) (Math.cos(0 + 4 * Math.PI / 5) * 8);
		shapeY[3] = (float) (Math.sin(0 + 4 * Math.PI / 5) * 8);

		Entity playerShip = new Player();
		playerShip.setPolygonCoordinates(
				shapeX[0],
				shapeY[0],
				shapeX[1],
				shapeY[1],
				shapeX[2],
				shapeY[2],
				shapeX[3],
				shapeY[3]
		);
		playerShip.setX(gameData.getDisplayHeight()/2f);
		playerShip.setY(gameData.getDisplayWidth()/2f);
		playerShip.setRadius(4);
		playerShip.setLife(3);

		return playerShip;
	}

}
