package farmDefenseData;

public class EnemyGoat extends Enemy{ //Classe ch�vre ennemi

	public EnemyGoat(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTexture("enemy_goat");
	}
}
