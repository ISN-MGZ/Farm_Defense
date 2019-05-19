package farmDefenseData;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerFlameThrower extends Tower{//Classe cannon lance-flame

	//Constructeur par défaut
	public TowerFlameThrower(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
		super(type, startTile, enemies);

	}

	//Tir
	public void shoot(Enemy target) {
		super.projectiles.add(new ProjectileFire(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
		super.target.reduceHiddenHealth(super.type.projectileType.damage);		
	}

}
