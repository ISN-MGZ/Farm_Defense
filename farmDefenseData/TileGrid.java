package farmDefenseData;

import static assistant.Artist.*;

public class TileGrid { //Classe pour tuile en mode �ch�quier  

	//D�claration des variables
	public Tile[][] map;
	private int tilesWide, tilesHigh;

	//Constructeur par d�faut + objets de l'�chequier r�f�renci�s
	public TileGrid() {
		this.tilesWide = 20;
		this.tilesHigh = 15;
		map = new Tile[tilesWide][tilesHigh];
		for (int i = 0; i < map.length; i++) { //Terrain par d�faut
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Grass);
			}
		}
	}

	//Ech�quier de la map avec toutes les tuiles
	public TileGrid(int[][] newMap) {
		//Variables r�f�renc�es
		this.tilesWide = newMap[0].length;
		this.tilesHigh = newMap.length;
		map = new Tile[tilesWide][tilesHigh]; // [20][15]
		//D�finition de l'�ch�quier
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				switch (newMap[j][i]) {
				case 0:
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Grass);
					break;
				case 1:
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Dirt);
					break;
				case 2:
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Water);
					break;
				case 3:
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Rock);
					break;
				}
			}
		}
	}

	public void setTile(int xCoord, int yCoord, TileType type) {
		map[xCoord][yCoord] = new Tile(xCoord * TILE_SIZE, yCoord * TILE_SIZE, TILE_SIZE, TILE_SIZE, type);
	}

	public Tile getTile(int xPlace, int yPlace) {
		if (xPlace < tilesWide && yPlace < tilesHigh && xPlace > -1 && yPlace > -1)
			return map[xPlace][yPlace];
		else
			return new Tile(0, 0, 0, 0, TileType.NULL);// Eviter crache hors map
	}

	public void draw() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].draw();
			}
		}
	}

	
	/*M�thodes publiques d�clar�es*/

	public int getTilesWide() {
		return tilesWide;
	}

	public void setTilesWide(int tilesWide) {
		this.tilesWide = tilesWide;
	}

	public int getTilesHigh() {
		return tilesHigh;
	}

	public void setTilesHigh(int tilesHigh) {
		this.tilesHigh = tilesHigh;
	}
}