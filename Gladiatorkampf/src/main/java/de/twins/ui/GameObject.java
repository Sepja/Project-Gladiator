package de.twins.ui;

import java.awt.Graphics;

import de.twins.gladiator.domain.AbstractFighter;

public abstract class GameObject implements UIObject{
	protected int x;
	protected int y;

	protected int velx;
	protected int vely;

	protected int width;
	protected int height;


	public GameObject(int x, int y) {
		super();

		this.x = x;
		this.y = y;
	}



	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelx() {
		return velx;
	}

	public void setVelx(int velx) {
		this.velx = velx;
	}

	public int getVely() {
		return vely;
	}

	public void setVely(int vely) {
		this.vely = vely;
	}



	
}
