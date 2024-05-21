package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.Random;
//import java.util.concurrent.CompletableFuture;

public class AsteroidControlSystem implements IEntityProcessingService, IAsteroidSplitter {
	@Override
	public void process(GameData gameData, World world) {

		for (Entity entity : world.getEntities(Asteroid.class)) {
			Asteroid asteroid = (Asteroid) entity;

			// movement
			double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
			double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

			asteroid.setX(asteroid.getX() + changeX * asteroid.getSpeedFactor());
			asteroid.setY(asteroid.getY() + changeY * asteroid.getSpeedFactor());

			// keep asteroid within the bounds of the map
			if (asteroid.getX() < 0) {
				asteroid.setX(asteroid.getX() + gameData.getDisplayWidth());
			}
			if (asteroid.getX() > gameData.getDisplayWidth()) {
				asteroid.setX(asteroid.getX() % gameData.getDisplayWidth());
			}
			if (asteroid.getY() < 0) {
				asteroid.setY(asteroid.getY() + gameData.getDisplayHeight());
			}
			if (asteroid.getY() > gameData.getDisplayHeight()) {
				asteroid.setY(asteroid.getY() % gameData.getDisplayHeight());
			}

			// check if destroyed, and if it can split
			if (asteroid.getLife() <= 0) {
				if (!asteroid.getIsSplit()) {
					createSplitAsteroid(asteroid, world);
				}
				//updateScore(1);
				world.removeEntity(asteroid);
			}
		}
	}


	@Override
	public void createSplitAsteroid(Entity entity, World world) {

		double[] displacements = {
			 20f,
			-20f
		};

		for (int i = 0; i < 2; i++) {
			Entity asteroid = new Asteroid(true);

			double x = entity.getX() + displacements[i];
			double y = entity.getY() + displacements[i];

			float sizeMod = 2f;

			float[] shapeX = new float[12];
			float[] shapeY = new float[12];

			shapeX[0] = (float) (Math.cos(0) * 9 * sizeMod);
			shapeY[0] = (float) (Math.sin(0) * 9 * sizeMod);

			shapeX[1] = (float) (Math.cos(0 + Math.PI / 8) * 7 * sizeMod);
			shapeY[1] = (float) (Math.sin(0 + Math.PI / 8) * 7 * sizeMod);

			shapeX[2] = (float) (Math.cos(0 - 7 * Math.PI / 4) * 9 * sizeMod);
			shapeY[2] = (float) (Math.sin(0 - 7 * Math.PI / 4) * 9 * sizeMod);

			shapeX[3] = (float) (Math.cos(0 + 4 * Math.PI / 10) * 8 * sizeMod);
			shapeY[3] = (float) (Math.sin(0 + 4 * Math.PI / 10) * 8 * sizeMod);

			shapeX[4] = (float) (Math.cos(0 + Math.PI / 2) * 6 * sizeMod);
			shapeY[4] = (float) (Math.sin(0 + Math.PI / 2) * 6 * sizeMod);

			shapeX[5] = (float) (Math.cos(0 + 2 * Math.PI / 3) * 8 * sizeMod);
			shapeY[5] = (float) (Math.sin(0 + 2 * Math.PI / 3) * 8 * sizeMod);

			shapeX[6] = (float) (Math.cos(0 - 7 * Math.PI / 6) * 7 * sizeMod);
			shapeY[6] = (float) (Math.sin(0 - 7 * Math.PI / 6) * 7 * sizeMod);

			shapeX[7] = (float) (Math.cos(0 + 6 * Math.PI / 5) * 7 * sizeMod);
			shapeY[7] = (float) (Math.sin(0 + 6 * Math.PI / 5) * 7 * sizeMod);

			shapeX[8] = (float) (Math.cos(0 + 5 * Math.PI / 4) * 6 * sizeMod);
			shapeY[8] = (float) (Math.sin(0 + 5 * Math.PI / 4) * 6 * sizeMod);

			shapeX[9] = (float) (Math.cos(0 + 3 * Math.PI / 2) * 7 * sizeMod);
			shapeY[9] = (float) (Math.sin(0 + 3 * Math.PI / 2) * 7 * sizeMod);

			shapeX[10] = (float) (Math.cos(0 - 7 * Math.PI / 3) * 6 * sizeMod);
			shapeY[10] = (float) (Math.sin(0 - 7 * Math.PI / 3) * 6 * sizeMod);

			shapeX[11] = (float) (Math.cos(0 - Math.PI / 4) * 8 * sizeMod);
			shapeY[11] = (float) (Math.sin(0 - Math.PI / 4) * 8 * sizeMod);

			asteroid.setPolygonCoordinates(
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
					shapeY[7],
					shapeX[8],
					shapeY[8],
					shapeX[9],
					shapeY[9],
					shapeX[10],
					shapeY[10],
					shapeX[11],
					shapeY[11]
			);
			asteroid.setX(x);
			asteroid.setY(y);
			asteroid.setRadius(7.5f * sizeMod);
			asteroid.setRotation(Math.random() * 360);
			asteroid.setLife(1);

			world.addEntity(asteroid);
		}
	}
/*
	public void updateScore(int points) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/score/add?points=" + points))
				.GET()
				.build();

		try {
			client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// Enhanced logging
			e.printStackTrace(); // Or use a logger for more control over output
			System.err.println("Failed to update score: " + e.getMessage());
		}
	}
*/
}
