package farmDefenseData;

import static assistant.Artist.*;

import org.newdawn.slick.opengl.Texture;

public enum TowerType {//Classe enum types de tourelles

	//Liste des tourelles
	CannonNail(new Texture[] {QuickLoad("nailCannonBase"), QuickLoad("nailCannonGun")}, ProjectileType.Nail, 30, 1000, 3, 15),
	CannonIce(new Texture[] {QuickLoad("iceCannonBase"), QuickLoad("iceCannonGun")}, ProjectileType.Iceball, 30, 1000, 3, 20),
	CannonLaser(new Texture[] {QuickLoad("cannonBaseLaser"), QuickLoad("cannonGunLaser")}, ProjectileType.Laser, 30, 1000, 10, 20),
	FlameThrower(new Texture[] {QuickLoad("flameThrowerBase"), QuickLoad("flameThrowerGun")}, ProjectileType.Fire, 30, 1000, 10, 20);
	
	//Déclaration des variables
	Texture[] textures;
	ProjectileType projectileType;
	int damage, range, cost;
	float firingSpeed;
	
	//Constructeur par défaut
	TowerType(Texture[] textures, ProjectileType projectileType, int damage, int range, float firingSpeed, int cost){
		this.textures = textures;
		this.projectileType = projectileType;
		this.damage = damage;
		this.range = range;
		this.firingSpeed = firingSpeed;
		this.cost = cost;
	}
}
