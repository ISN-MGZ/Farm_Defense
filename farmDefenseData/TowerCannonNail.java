package farmDefenseData;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerCannonNail extends Tower {//Classe canon clou

	//Constructeur par défaut
	public TowerCannonNail(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
		super(type, startTile, enemies);
	}

	//Tir
	public void shoot(Enemy target) {
		super.projectiles.add(new ProjectileNail(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
		super.target.reduceHiddenHealth(super.type.projectileType.damage);
	}
}