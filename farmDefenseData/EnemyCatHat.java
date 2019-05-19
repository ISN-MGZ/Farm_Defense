package farmDefenseData;

public class EnemyCatHat extends Enemy{//Classe chat (avec chapeau) ennemi 

	public EnemyCatHat(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTexture("enemy_cat_hat1");
	}
}
