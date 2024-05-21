package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.BulletSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {
	@Override
	public void process(GameData gameData, World world) {

		for (Entity entity : world.getEntities(Enemy.class)) {
			Enemy enemy = (Enemy) entity;

			double rnd = Math.random();

			// rotation
			if (rnd < 0.5) {
				enemy.setRotation(enemy.getRotation() + 5);
			} else {
				enemy.setRotation(enemy.getRotation() - 5);
			}

			// movement
			double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
			double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
			enemy.setX(enemy.getX() + changeX * 2);
			enemy.setY(enemy.getY() + changeY * 2);

			// shooting
			if (enemy.isReadyToFire()) {
				for (BulletSPI bullet : getBulletSPIs()) {
					world.addEntity(bullet.createBullet(enemy, gameData));
				}
				enemy.setLastFireTime(System.currentTimeMillis());
			}

			// keep ship within the bounds of the map
			if (enemy.getX() < 0) {
				enemy.setX(enemy.getX() + gameData.getDisplayWidth());
			}
			if (enemy.getX() > gameData.getDisplayWidth()) {
				enemy.setX(enemy.getX() % gameData.getDisplayWidth());
			}
			if (enemy.getY() < 0) {
				enemy.setY(enemy.getY() + gameData.getDisplayHeight());
			}
			if (enemy.getY() > gameData.getDisplayHeight()) {
				enemy.setY(enemy.getY() % gameData.getDisplayHeight());
			}
		}
	}

	private Collection<? extends BulletSPI> getBulletSPIs() {
		return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
	}
}
