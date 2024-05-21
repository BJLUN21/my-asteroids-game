package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.BulletSPI;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService, BulletSPI {

	@Override
	public void start(GameData gameData, World world) {
	}

	@Override
	public void stop(GameData gameData, World world) {
	}

	@Override
	public Entity createBullet(Entity entity, GameData gameData) {
		Entity bullet = new Bullet(entity.getIsFriendly());
		bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
		double changeX = Math.cos(Math.toRadians(entity.getRotation()));
		double changeY = Math.sin(Math.toRadians(entity.getRotation()));
		bullet.setX(entity.getX() + changeX * 10);
		bullet.setY(entity.getY() + changeY * 10);
		bullet.setRotation(entity.getRotation());
		bullet.setRadius(1);
		bullet.setLife(1);

		return bullet;
	}
}
