package farmDefenseData;

public class EnemyCat extends Enemy{//Classe chat ennemi

	public EnemyCat(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTexture("enemy_cat1");
	}

}
