package farmDefenseData;

import static assistant.Artist.*;
import static assistant.Clock.*;

import org.newdawn.slick.opengl.Texture;

public abstract class Projectile implements Entity{ //Classe projectile abstraite (sert de base)

	//D�claration de variables priv�es
	private Texture texture;
	private float x, y, speed, xVelocity, yVelocity;
	private int damage, width, height;
	private Enemy target;
	private boolean alive;

	//Constructeur par d�faut + objets projectiles r�f�renci�s
	public Projectile(ProjectileType type, Enemy target, float x, float y, int width, int height) {
		this.texture = type.texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = type.speed;
		this.damage = type.damage;
		this.target = target;
		this.alive = true;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		calculateDirection();
	}

	//Calculer direction du projectile
	private void calculateDirection() {
		float totalAllowedMovement = 1.0f;//Max r�f�rence
		float xDistanceFromTarget = Math.abs(target.getX() - x - TILE_SIZE / 4 + TILE_SIZE / 2);// Valeur
																								// absolue -- Calcul direction X
		float yDistanceFromTarget = Math.abs(target.getY() - y - TILE_SIZE / 4 + TILE_SIZE / 2);// Valeur
																								// absolue -- Calcul direction Y
		float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget; //Calculer la diastance totale jusqu'� la cible
		float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
		xVelocity = xPercentOfMovement;
		yVelocity = totalAllowedMovement - xPercentOfMovement;
		//Placer la direction en fonction de la position de l'ennemi relative � la tourelle
		if (target.getX() < x)
			xVelocity *= -1;//Inverser la v�locit� de X
		if (target.getY() < y)
			yVelocity *= -1;//Inverser la v�locit� de Y
	}
	
	//Faire des d�gats aux ennemis
	public void damage() {
		target.damage(damage);
		alive = false;
	}

	//Actualiser la classe projectile
	public void update() {
		if (alive) {
			calculateDirection();
			x += xVelocity * speed * Delta();
			y += yVelocity * speed * Delta();
			if (CheckCollision(x, y, width, height, target.getX(), target.getY(), target.getWidth(),target.getHeight()))
				damage();
			draw();
		}
	}
	
	
	
	/*M�thodes publiques d�clar�es*/
	public void draw() {
		DrawQuadTex(texture, x, y, 32, 32);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Enemy getTarget() {
		return target;
	}
	
	public void setAlive(boolean status) {
		alive = status;
	}
}
