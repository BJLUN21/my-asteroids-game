package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService {
	@Override
	public void process(GameData gameData, World world) {

		for (Entity bullet : world.getEntities(Bullet.class)) {
			double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
			double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
			bullet.setX(bullet.getX() + changeX * 3);
			bullet.setY(bullet.getY() + changeY * 3);

			if (bullet.getX() < 0 || bullet.getX() > gameData.getDisplayWidth()) {
				world.removeEntity(bullet);
			}
			if (bullet.getY() < 0 || bullet.getY() > gameData.getDisplayHeight()) {
				world.removeEntity(bullet);
			}
		}
	}
}
