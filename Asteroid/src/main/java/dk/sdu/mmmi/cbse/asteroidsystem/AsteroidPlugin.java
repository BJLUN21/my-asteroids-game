package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

	@Override
	public void start(GameData gameData, World world) {

		// Add entities to the world
		for (int i = 0; i < 6; i++) {
			Entity asteroid = createAsteroid(gameData);
			world.addEntity(asteroid);
		}
	}

	@Override
	public void stop(GameData gameData, World world) {
		// Remove entities
		for (Entity asteroid : world.getEntities(Asteroid.class)) {
			world.removeEntity(asteroid);
		}
	}

	private Entity createAsteroid(GameData gameData) {

		Entity asteroid = new Asteroid(false);
		float sizeMod = 3f;

		float[] shapeX = new float[12];
		float[] shapeY = new float[12];

		shapeX[0] = (float) (Math.cos(0) * 9 * sizeMod);
		shapeY[0] = (float) (Math.sin(0) * 9 * sizeMod);

		shapeX[1] = (float) (Math.cos(0 + Math.PI / 8) * 7 * sizeMod);
		shapeY[1] = (float) (Math.sin(0 + Math.PI / 8) * 7 * sizeMod);

		shapeX[2] = (float) (Math.cos(0 - 7 * Math.PI / 4) * 9 * sizeMod);
		shapeY[2] = (float) (Math.sin(0 - 7 * Math.PI / 4) * 9 * sizeMod);

		shapeX[3] = (float) (Math.cos(0 + 4 * Math.PI / 10) * 8 * sizeMod);
		shapeY[3] = (float) (Math.sin(0 + 4 * Math.PI / 10) * 8 * sizeMod);

		shapeX[4] = (float) (Math.cos(0 + Math.PI / 2) * 6 * sizeMod);
		shapeY[4] = (float) (Math.sin(0 + Math.PI / 2) * 6 * sizeMod);

		shapeX[5] = (float) (Math.cos(0 + 2 * Math.PI / 3) * 8 * sizeMod);
		shapeY[5] = (float) (Math.sin(0 + 2 * Math.PI / 3) * 8 * sizeMod);

		shapeX[6] = (float) (Math.cos(0 - 7 * Math.PI / 6) * 7 * sizeMod);
		shapeY[6] = (float) (Math.sin(0 - 7 * Math.PI / 6) * 7 * sizeMod);

		shapeX[7] = (float) (Math.cos(0 + 6 * Math.PI / 5) * 7 * sizeMod);
		shapeY[7] = (float) (Math.sin(0 + 6 * Math.PI / 5) * 7 * sizeMod);

		shapeX[8] = (float) (Math.cos(0 + 5 * Math.PI / 4) * 6 * sizeMod);
		shapeY[8] = (float) (Math.sin(0 + 5 * Math.PI / 4) * 6 * sizeMod);

		shapeX[9] = (float) (Math.cos(0 + 3 * Math.PI / 2) * 7 * sizeMod);
		shapeY[9] = (float) (Math.sin(0 + 3 * Math.PI / 2) * 7 * sizeMod);

		shapeX[10] = (float) (Math.cos(0 - 7 * Math.PI / 3) * 6 * sizeMod);
		shapeY[10] = (float) (Math.sin(0 - 7 * Math.PI / 3) * 6 * sizeMod);

		shapeX[11] = (float) (Math.cos(0 - Math.PI / 4) * 8 * sizeMod);
		shapeY[11] = (float) (Math.sin(0 - Math.PI / 4) * 8 * sizeMod);

		asteroid.setPolygonCoordinates(
				shapeX[0],
				shapeY[0],
				shapeX[1],
				shapeY[1],
				shapeX[2],
				shapeY[2],
				shapeX[3],
				shapeY[3],
				shapeX[4],
				shapeY[4],
				shapeX[5],
				shapeY[5],
				shapeX[6],
				shapeY[6],
				shapeX[7],
				shapeY[7],
				shapeX[8],
				shapeY[8],
				shapeX[9],
				shapeY[9],
				shapeX[10],
				shapeY[10],
				shapeX[11],
				shapeY[11]
		);
		asteroid.setX(gameData.getDisplayHeight() * Math.random());
		asteroid.setY(gameData.getDisplayWidth() * Math.random());
		asteroid.setRadius(7.5f * sizeMod);
		asteroid.setRotation(Math.random() * 360);
		asteroid.setLife(1);

		return asteroid;
	}
}
