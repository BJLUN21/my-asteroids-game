package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {
	@Override
	public void process(GameData gameData, World world) {
		for (Entity e1 : world.getEntities()) {
			for (Entity e2 : world.getEntities()) {
				if (checkCollisions(e1, e2)) {

					if (checkIfFriendly(e1, e2)) {
						continue;
					}

					if (e1.getIsAsteroid() && e2.getIsAsteroid()) {
						continue;
					}

					decreaseLife(e1);
					decreaseLife(e2);

					if (e1.getIsPlayer()) {
						System.out.println("Player HP = " + e1.getLife());
					} else if (e2.getIsPlayer()) {
						System.out.println("Player HP = " + e2.getLife());
					}
					if (e1.getIsEnemy()) {
						System.out.println("Enemy HP = " + e1.getLife());
					} else if (e2.getIsEnemy()) {
						System.out.println("Enemy HP = " + e2.getLife());
					}
					if (e1.getIsAsteroid()) {
						System.out.println("Asteroid HP = " + e1.getLife());
					} else if (e2.getIsAsteroid()) {
						System.out.println("Asteroid HP = " + e2.getLife());
					}

					if (!e1.getIsAsteroid()) {
						if (e1.getLife() <= 0) {
							world.removeEntity(e1);
						}
					}
					if (!e2.getIsAsteroid()) {
						if (e2.getLife() <= 0) {
							world.removeEntity(e2);
						}
					}
				}
			}
		}
	}

	public boolean checkIfFriendly(Entity e1, Entity e2) {
		return e1.getIsFriendly() == e2.getIsFriendly();
	}

	public boolean checkCollisions(Entity e1, Entity e2) {
		if (e1 != e2) {
			float dx = (float) (e1.getX() - e2.getX());
			float dy = (float) (e1.getY() - e2.getY());
			float distance = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

			return distance < (e1.getRadius() + e2.getRadius());
		}
		return false;
	}

	public void decreaseLife(Entity entity) {
		entity.setLife(entity.getLife() - 1);
	}
}