package assistant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import farmDefenseData.Tile;
import farmDefenseData.TileGrid;
import farmDefenseData.TileType;

public class Leveler {//Classe map 

	//Sauvegarder map
	public static void SaveMap(String mapName, TileGrid grid) {
		String mapData = "";
		for (int i = 0; i < grid.getTilesWide(); i++) {
			for (int j = 0; j < grid.getTilesHigh(); j++) {// Pour toutes les cases/tuiles
				mapData += getTileID(grid.getTile(i, j));//Stocker ID des tuiles
			}
		}
		try { /*Regroupement des instructions à exécuter pour définir une réponse si l'une de ces instructions provoque une exception*/
			File file = new File(mapName); //Fichier map
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));//Utilisation "Buffered" pour éviter le fichier d'être convertis en Bytes et d'être tamponnés 
																			//(=méthode efficace pour enregistrer map) 
			bw.write(mapData);//Ouvrir map
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();// Nous donne la ligne qui nous donne une erreur si problème
		}
	}

	//Charger la grille de la map
	public static TileGrid LoadMap(String mapName) {
		TileGrid grid = new TileGrid();
		try {
			BufferedReader br = new BufferedReader(new FileReader(mapName));//Utilisation "Buffered" pour éviter le fichier d'être convertis en Bytes et d'être tamponnés
																				//(=méthode efficace pour charger map) 
			String data = br.readLine();//Lire map
			for (int i = 0; i < grid.getTilesWide(); i++) {
				for (int j = 0; j < grid.getTilesHigh(); j++) {
					grid.setTile(i, j,
							getTileType(data.substring(i * grid.getTilesHigh() + j, i * grid.getTilesHigh() + j + 1)));
				}
			}
			br.close();
		} catch (Exception e) {//Sortir erreur si besoin
			e.printStackTrace();
		}
		return grid;//Retourner grille
	}

	//Définir types de tuiles
	public static TileType getTileType(String ID) {
		TileType type = TileType.NULL;
		switch (ID) {
		case "0":
			type = TileType.Grass;
			break;
		case "1":
			type = TileType.Dirt;
			break;
		case "2":
			type = TileType.Water;
			break;
		case "3":
			type = TileType.Rock;
			break;
		case "4":
			type = TileType.NULL;
			break;
		}
		return type;
	}

	//Définir les tuiles par ID
	public static String getTileID(Tile t) {
		String ID = "E";
		switch (t.getType()) {
		case Grass:
			ID = "0";
			break;
		case Dirt:
			ID = "1";
			break;
		case Water:
			ID = "2";
			break;
		case Rock:
			ID = "3";
			break;
		case NULL:
			ID = "4";
			break;
		}
		return ID;
	}
}
