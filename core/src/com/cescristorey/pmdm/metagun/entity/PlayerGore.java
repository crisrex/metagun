
package com.cescristorey.pmdm.metagun.entity;

import com.cescristorey.pmdm.metagun.Art;
import com.cescristorey.pmdm.metagun.level.Camera;
import com.cescristorey.pmdm.metagun.level.Level;
import com.cescristorey.pmdm.metagun.screen.Screen;

public class PlayerGore extends Entity {
	private int life;

	public PlayerGore (double x, double y) {
		this.x = x;
		this.y = y;
		this.w = 2;
		this.h = 2;
		bounce = 0.8;
		this.xa = 0 + (random.nextDouble() - random.nextDouble()) * 1.5;
		this.ya = -1 + (random.nextDouble() - random.nextDouble()) * 1.5;

		life = random.nextInt(90) + 60;
	}

	@Override
	public void tick () {
		if (life-- <= 0) remove();
		onGround = false;
		tryMove(xa, ya);

		xa *= Level.FRICTION;
		ya *= Level.FRICTION;
		ya += Level.GRAVITY * 0.5;
		level.add(new Gore(x + random.nextDouble(), y + random.nextDouble() - 1, xa, ya));
	}

	@Override
	protected void hitWall (double xa, double ya) {
		this.xa *= 0.9;
		this.ya *= 0.9;
	}

	@Override
	public void render (Screen g, Camera camera) {
		int xp = (int)x;
		int yp = (int)y;
		g.draw(Art.guys[6][1], xp, yp);
	}

	@Override
	public void hitSpikes () {
		for (int i = 0; i < 4; i++) {
			xa = (random.nextFloat() - random.nextFloat()) * 6;
			ya = (random.nextFloat() - random.nextFloat()) * 6;
			level.add(new Gore(x + random.nextDouble(), y + random.nextDouble() - 1, xa, ya));
		}
		remove();
	}
}
