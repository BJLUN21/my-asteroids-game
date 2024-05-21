package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Bullet extends Entity {

	private double expirationTime = 1.0;
	private double timeOfCreation;

	public Bullet(boolean isFriendly) {
		setIsBullet(true);
		setIsFriendly(isFriendly);
		double time = System.currentTimeMillis();
		setTimeOfCreation(time);
	}

	public boolean isExpired() {
		long currentTime = System.currentTimeMillis();
		double timeElapsed = (currentTime - getTimeOfCreation()) / 1000.0; // Convert milliseconds to seconds
		return timeElapsed >= getExpirationTime();
	}

	public double getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(double expirationTime) {
		this.expirationTime = expirationTime;
	}

	public double getTimeOfCreation() {
		return this.timeOfCreation;
	}

	public void setTimeOfCreation(double time) {
		this.timeOfCreation = time;
	}
}
