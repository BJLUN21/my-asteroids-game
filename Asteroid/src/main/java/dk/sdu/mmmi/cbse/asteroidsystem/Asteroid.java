package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity {

	private double speedFactor;

	public Asteroid(boolean isSplit) {
		setIsAsteroid(true);
		setIsSplit(isSplit);
		double factor = 0.5 + Math.random() * 2;
		setSpeedFactor(factor);
	}

	public double getSpeedFactor() {
		return this.speedFactor;
	}

	public void setSpeedFactor(double speedFactor) {
		this.speedFactor = speedFactor;
	}
}
