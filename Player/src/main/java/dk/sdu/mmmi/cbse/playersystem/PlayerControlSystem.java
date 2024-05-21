package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.BulletSPI;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

/**
 * @author jcs
 */
public class PlayerControlSystem implements IEntityProcessingService {

	@Override
	public void process(GameData gameData, World world) {

		for (Entity player : world.getEntities(Player.class)) {
			if (gameData.getKeys().isDown(GameKeys.LEFT)) {
				player.setRotation(player.getRotation() - 5);
			}
			if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
				player.setRotation(player.getRotation() + 5);
			}
			if (gameData.getKeys().isDown(GameKeys.UP)) {
				double changeX = Math.cos(Math.toRadians(player.getRotation()));
				double changeY = Math.sin(Math.toRadians(player.getRotation()));
				player.setX(player.getX() + changeX);
				player.setY(player.getY() + changeY);
			}
			if(gameData.getKeys().isDown(GameKeys.SPACE)) {
				if(canShoot(player)) {
					for (BulletSPI bullet : getBulletSPIs()) {
						world.addEntity(bullet.createBullet(player, gameData));
					}
					player.setLastShotTime(System.currentTimeMillis());
				}
			}

			if (player.getX() < 0) {
				player.setX(player.getX() + gameData.getDisplayWidth());
			}
			if (player.getX() > gameData.getDisplayWidth()) {
				player.setX(player.getX() % gameData.getDisplayWidth());
			}
			if (player.getY() < 0) {
				player.setY(player.getY() + gameData.getDisplayHeight());
			}
			if (player.getY() > gameData.getDisplayHeight()) {
				player.setY(player.getY() % gameData.getDisplayHeight());
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
