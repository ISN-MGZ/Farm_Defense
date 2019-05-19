package farmDefenseData;

public class EnemyLama extends Enemy{//Classe lama ennemi

	public EnemyLama(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTexture("enemy_lama");
	}
}
