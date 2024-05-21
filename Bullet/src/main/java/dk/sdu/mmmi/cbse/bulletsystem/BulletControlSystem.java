package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService {
	@Override
	public void process(GameData gameData, World world) {

		for (Entity entity : world.getEntities(Bullet.class)) {
			Bullet bullet = (Bullet) entity;

			if (bullet.isExpired()) {
				world.removeEntity(bullet);
				continue;
			}

			double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
			double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
			bullet.setX(bullet.getX() + changeX * 5);
			bullet.setY(bullet.getY() + changeY * 5);

			// keep bullet within the bounds of the map
			if (bullet.getX() < 0) {
				bullet.setX(bullet.getX() + gameData.getDisplayWidth());
			}
			if (bullet.getX() > gameData.getDisplayWidth()) {
				bullet.setX(bullet.getX() % gameData.getDisplayWidth());
			}
			if (bullet.getY() < 0) {
				bullet.setY(bullet.getY() + gameData.getDisplayHeight());
			}
			if (bullet.getY() > gameData.getDisplayHeight()) {
				bullet.setY(bullet.getY() % gameData.getDisplayHeight());
			}
		}
	}
}
