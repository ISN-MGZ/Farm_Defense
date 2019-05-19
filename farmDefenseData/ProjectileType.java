package farmDefenseData;

import static assistant.Artist.*;

import org.newdawn.slick.opengl.Texture;

public enum ProjectileType {//Classe types de projectiles existants (enum)

		//Types de projectiles (Texture, dégats, vitesse)
		Nail(QuickLoad("nailBullet"), 10, 200),
		Iceball(QuickLoad("iceBullet"), 6, 450),
		Laser(QuickLoad("laserBullet"), 20, 500),
		Fire(QuickLoad("fireBullet"), 20, 500);
		
		//Variables
		Texture texture;
		int damage;
		float speed;
		
		//Constructeur par défaut + objets des projectiles référencés
		ProjectileType(Texture texture, int damage, float speed){
			this.texture = texture;
			this.damage = damage;
			this.speed = speed;
		}
	}
