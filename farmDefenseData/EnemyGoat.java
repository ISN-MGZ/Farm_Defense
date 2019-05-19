package farmDefenseData;

public class EnemyGoat extends Enemy{ //Classe chèvre ennemi

	public EnemyGoat(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTexture("enemy_goat");
	}
}
