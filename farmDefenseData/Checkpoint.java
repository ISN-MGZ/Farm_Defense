package farmDefenseData;

public class Checkpoint { //Classe d�claration direction pour checkpoint

	private Tile tile;
	private int xDirection, yDirection;

	//Constructeur par d�faut + objets checkpoints r�f�renc�s
	public Checkpoint(Tile tile, int xDirection, int yDirection) {
		this.tile = tile;
		this.xDirection = xDirection;
		this.yDirection = yDirection;
	}

	
	
	/*M�thode d�clar�es publiques*/
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
