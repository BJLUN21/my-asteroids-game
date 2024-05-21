package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionDetectorTest {

	private Entity player = new Entity();
	private Entity enemy = new Entity();
	private CollisionDetector collisionDetector = new CollisionDetector();


	@BeforeEach
	void setUp() {
		player.setRadius(5);
		enemy.setRadius(5);
	}

	@Test
	void collision() {
		player.setX(1);
		player.setY(4);

		enemy.setX(1);
		enemy.setY(4);

		assertTrue(collisionDetector.checkCollisions(player, enemy));
	}

	@Test
	void noCollision() {
		player.setX(1);
		player.setY(4);

		enemy.setX(20);
		enemy.setY(24);

		assertFalse(collisionDetector.checkCollisions(player, enemy));
	}
}