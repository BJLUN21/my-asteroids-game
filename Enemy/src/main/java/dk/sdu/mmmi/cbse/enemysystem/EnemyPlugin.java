package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {


	//Default constructor
	public EnemyPlugin() {
	}

	@Override
	public void start(GameData gameData, World world) {

		// Add entities to the world
		for (int i = 0; i < 2; i++) {
			Entity enemy = createEnemyShip(gameData);
			world.addEntity(enemy);
		}
	}

	@Override
	public void stop(GameData gameData, World world) {
		// Remove entities
		for (Entity enemy : world.getEntities(Enemy.class)) {
			world.removeEntity(enemy);
		}
	}

	private Entity createEnemyShip(GameData gameData) {

		float[] shapeX = new float[8];
		float[] shapeY = new float[8];

		shapeX[0] = (float) (Math.cos(0) * 9);
		shapeY[0] = (float) (Math.sin(0) * 9);

		shapeX[1] = (float) (Math.cos(0 - Math.PI / 3) * 8);
		shapeY[1] = (float) (Math.sin(0 - Math.PI / 3) * 8);

		shapeX[2] = (float) (Math.cos(0 - 2 * Math.PI / 3) * 2);
		shapeY[2] = (float) (Math.sin(0 - 2 * Math.PI / 3) * 2);

		shapeX[3] = (float) (Math.cos(0 - 4 * Math.PI / 5) * 8);
		shapeY[3] = (float) (Math.sin(0 - 4 * Math.PI / 5) * 8);

		shapeX[4] = (float) (Math.cos(0 + Math.PI) * 9);
		shapeY[4] = (float) (Math.sin(0 + Math.PI) * 9);

		shapeX[5] = (float) (Math.cos(0 + 4 * Math.PI / 5) * 8);
		shapeY[5] = (float) (Math.sin(0 + 4 * Math.PI / 5) * 8);

		shapeX[6] = (float) (Math.cos(0 + 2 * Math.PI / 3) * 2);
		shapeY[6] = (float) (Math.sin(0 + 2 * Math.PI / 3) * 2);

		shapeX[7] = (float) (Math.cos(0 + Math.PI / 3) * 8);
		shapeY[7] = (float) (Math.sin(0 + Math.PI / 3) * 8);

		Entity enemyShip = new Enemy();
		enemyShip.setPolygonCoordinates(
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
				shapeY[7]
		);
		enemyShip.setX(gameData.getDisplayHeight() * Math.random());
		enemyShip.setY(gameData.getDisplayWidth() * Math.random());
		enemyShip.setRadius(7);
		enemyShip.setLife(3);

		return enemyShip;
	}
}
