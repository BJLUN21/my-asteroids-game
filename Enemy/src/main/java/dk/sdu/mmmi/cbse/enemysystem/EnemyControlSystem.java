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

		for (Entity enemy : world.getEntities(Enemy.class)) {

			double rnd = Math.random();

			// turning
			if (rnd < 0.5) {
				enemy.setRotation(enemy.getRotation() + 6);
			} else {
				enemy.setRotation(enemy.getRotation() - 6);
			}

			// accelerating
			//if (rnd < 0.15 || rnd >= 0.85) {}
			double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
			double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
			enemy.setX(enemy.getX() + changeX);
			enemy.setY(enemy.getY() + changeY);

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

			// shooting
			if (canShoot(enemy)) {
				for (BulletSPI bullet : getBulletSPIs()) {
					world.addEntity(bullet.createBullet(enemy, gameData));
				}
				enemy.setLastShotTime(System.currentTimeMillis());
			}
		}
	}

	public boolean canShoot(Entity entity) {
		long currentTime = System.currentTimeMillis();
		double elapsedTimeSinceLastShot = (currentTime - entity.getLastShotTime()) / 1000.0; // Convert milliseconds to seconds
		return elapsedTimeSinceLastShot >= entity.getCoolDownTime();
	}

	private Collection<? extends BulletSPI> getBulletSPIs() {
		return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
	}
}
