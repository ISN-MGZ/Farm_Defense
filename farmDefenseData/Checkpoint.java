package farmDefenseData;

public class Checkpoint { //Classe déclaration direction pour checkpoint

	private Tile tile;
	private int xDirection, yDirection;

	//Constructeur par défaut + objets checkpoints référencés
	public Checkpoint(Tile tile, int xDirection, int yDirection) {
		this.tile = tile;
		this.xDirection = xDirection;
		this.yDirection = yDirection;
	}

	
	
	/*Méthode déclarées publiques*/
	public Tile getTile() {
		return tile;
	}

	public int getxDirection() {
		return xDirection;
	}

	public int getyDirection() {
		return yDirection;
	}
}
