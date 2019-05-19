package farmDefenseData;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import assistant.Clock;
import assistant.StateManager;
import assistant.StateManager.GameState;

import static assistant.Artist.*;

import java.util.ArrayList;

public class Player {

	//Déclaration de variables privées
	private TileGrid grid;
	private TileType[] types;
	private WaveManager waveManager;
	private ArrayList<Tower> towerList;
	private boolean leftMouseButtonDown, rightMouseButtonDown, holdingTower;
	private Tower tempTower;
	public static int Cash, Lives;
	
	//Constructeur par défaut + objets du joueur référencié
	public Player(TileGrid grid, WaveManager waveManager) {
		this.grid = grid;
		this.types = new TileType[4];
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
		this.types[3] = TileType.Rock;
		this.waveManager = waveManager;
		this.towerList = new ArrayList<Tower>();
		this.leftMouseButtonDown = false;
		this.rightMouseButtonDown = false;
		this.holdingTower = false;
		this.tempTower = null;
		Cash = 0;
		Lives = 0;
	}
	
	//Initialiser l'argent et la vie du joueur
	public void setup() {
		Cash = 200;
		Lives = 3;
	}
	
	//Checker si le joueur a assez d'argent pour la tourelle, si oui le faire payer le prix de la tourelle
	public static boolean modifyCash(int amount) {
		if(Cash + amount >= 0) {
			Cash += amount;//Calculer la différence
			System.out.println(Cash);
			return true;
		}
		System.out.println(Cash);
		return false;
	}
	
	//Modifer la vie du joueur
	public static void modifyLives(int amount) {
		if(Lives <= 1)//Si la vie du joueur est strictement inférieur à 1 
			StateManager.setState(GameState.GAMEOVER);//Changer le status du jeu en GameOver
		Lives += amount;//Différence vie actuel et vie à enlever
	}
	
	//Actualisation de la classe du joueur
	public void update() {
		//Actualiser la tourelle en suspension
		if (holdingTower) {
			tempTower.setX(getMouseTile().getX());
			tempTower.setY(getMouseTile().getY());
			tempTower.draw();
		}
		//Actualiser toutes les tourelles du jeu
		for(Tower t : towerList) {
			t.update();
			t.draw();
			t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
		}

		//Suivit des actions de la souris
		if(Mouse.isButtonDown(0) && !leftMouseButtonDown) {//Clique gauche pour poser la tourelle
			placeTower();
		}
		if(Mouse.isButtonDown(1) && !rightMouseButtonDown) {
			 System.out.println("Clique droit !");
		}
		
		leftMouseButtonDown = Mouse.isButtonDown(0);//0=clique gauche de la souris --Eviter de spam pour toutes les actualisations
		rightMouseButtonDown = Mouse.isButtonDown(1);//1= clique droit de la souris--Eviter de spam pour toutes les actualisations
		
		//Suivit des actions du clavier //////////////////////OPTION ADMINISTRATEUR/////////////////////////////////
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				Clock.ChangeMultiplier(0.2f);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
				Clock.ChangeMultiplier(-0.2f);
			}
		}
	}
	
	//Placement de la tourelle
	private void placeTower() {
		Tile currentTile = getMouseTile();
		if(holdingTower)
			if(!currentTile.getOccupied() && modifyCash(-tempTower.getCost())) {//Si la tuile est inoccupé et que nous avons assez d'argent
				towerList.add(tempTower);
				currentTile.setOccupied(true);
				holdingTower = false;
				tempTower = null;
			}
	}
	
	//Prendre la tourelle en suspension
	public void pickTower(Tower t) {
		tempTower = t;
		holdingTower = true;
	}
	
	//Suivit de la tuile par rapport à la souris
	private Tile getMouseTile() {
		return grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);
	}
}
