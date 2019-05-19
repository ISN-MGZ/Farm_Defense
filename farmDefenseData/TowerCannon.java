package farmDefenseData;

import static assistant.Artist.*;
import static assistant.Clock.*;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

public class TowerCannon { //Classe canon

	//D�claration variables priv�es
	private float x, y, timeSinceLastShot, firingSpeed, angle;
	private int width, height, range;
	private Texture baseTexture, cannonTexture;
	private ArrayList<Projectile> projectiles;
	private CopyOnWriteArrayList<Enemy> enemies;
	private Enemy target;
	private boolean targeted;

	//Constructeur par d�faut + objets canon r�f�renc�s
	public TowerCannon(Texture baseTexture, Tile startTile, int damage, int range, CopyOnWriteArrayList<Enemy> enemies) {
		this.baseTexture = baseTexture;
		this.cannonTexture = QuickLoad("cannonGunBlue");
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = (int) startTile.getWidth();
		this.height = (int) startTile.getHeight();
		this.range = range;
		this.firingSpeed = 3;
		this.timeSinceLastShot = 0;
		this.projectiles = new ArrayList<Projectile>();
		this.enemies = enemies;
		this.targeted = false;
	}

	//Pr�paration tir
	private Enemy acquireTarget() {
		Enemy closest = null;
		float closestDistance = 10000;
		for (Enemy e : enemies) {
			if (isInRange(e) && findDistance(e) < closestDistance) {
				closestDistance = findDistance(e);
				closest = e;
			}
		}
		if (closest != null)
			targeted = true;
		return closest;
	}

	//Retourner port�e
	private boolean isInRange(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		if (xDistance < range && yDistance < range)
			return true;
		return false;
	}

	//Calculer distance
	private float findDistance(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance + yDistance;
	}

	//Calculer angke
	private float calculateAngle() {
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		return (float) Math.toDegrees(angleTemp) - 90;
	}

	//M�thode tir
	private void shoot() {
		timeSinceLastShot = 0;
		/*projectiles.add(new ProjectileIceball(QuickLoad("bullet"), target, x + TILE_SIZE / 2 - TILE_SIZE / 4,
				y + TILE_SIZE / 2 - TILE_SIZE / 4, 32, 32, 500, 10));*/
	}

	//Actualiser liste ennemies
	public void updateEnemyList(CopyOnWriteArrayList<Enemy> newList) {
		enemies = newList;
	}

	//Actualisation de la classe
	public void update() {
		if (!targeted) {
			target = acquireTarget();
		}

		//Tir
		if (target == null || target.isAlive() == false)
			targeted = false;
		timeSinceLastShot += Delta();
		if (timeSinceLastShot > firingSpeed)
			shoot();

		//Liste projectile
		for (Projectile p : projectiles)
			p.update();

		//Angle calcul�
		angle = calculateAngle();
		
		//Dessin�
		draw();
	}

	//M�thode dessin�e
	public void draw() {
		DrawQuadTex(baseTexture, x, y, width, height);
		DrawQuadTexRot(cannonTexture, x, y, width, height, angle);
	}
}
