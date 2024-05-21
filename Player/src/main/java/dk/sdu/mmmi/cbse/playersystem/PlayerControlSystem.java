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

		for (Entity entity : world.getEntities(Player.class)) {
			Player player = (Player) entity;

			// rotation
			if (gameData.getKeys().isDown(GameKeys.LEFT)) {
				player.setRotation(player.getRotation() - 5);
			}
			if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
				player.setRotation(player.getRotation() + 5);
			}

			// movement
			if (gameData.getKeys().isDown(GameKeys.UP)) {
				double changeX = Math.cos(Math.toRadians(player.getRotation()));
				double changeY = Math.sin(Math.toRadians(player.getRotation()));
				player.setX(player.getX() + changeX * 2);
				player.setY(player.getY() + changeY * 2);
			}

			// shooting
			if(gameData.getKeys().isDown(GameKeys.SPACE)) {
				if(player.isReadyToFire()) {
					for (BulletSPI bullet : getBulletSPIs()) {
						world.addEntity(bullet.createBullet(player, gameData));
					}
					player.setLastFireTime(System.currentTimeMillis());
				}
			}

			// keep ship within the bounds of the map
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

	private Collection<? extends BulletSPI> getBulletSPIs() {
		return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
	}
}
