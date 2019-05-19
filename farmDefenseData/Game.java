package farmDefenseData;

import static assistant.Artist.DrawQuadTex;
import static assistant.Artist.QuickLoad;
import static assistant.Artist.TILE_SIZE;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import assistant.StateManager;
import userInterface.UserInterface;
import userInterface.UserInterface.Menu;

public class Game {//Classe pour actualiser tout dans le jeu

	//Variable privées déclarées
	private TileGrid grid;
	private Player player;
	private WaveManager waveManager;
	private UserInterface gameUI;
	private Menu towerPickerMenu;
	private Texture menuBackground;
	private Enemy[] enemyTypes;
	public int enemiesPerWave;

	//Constructeur par défaut + objets du jeu référencés
	public Game(TileGrid grid) {
		this.grid = grid; //Créer la grille/map
		enemyTypes = new Enemy[5];
		enemyTypes[0] = new EnemySRabbit(7, 0, grid);
		enemyTypes[1] = new EnemyCat(7, 0, grid);
		enemyTypes[2] = new EnemyCatHat(7, 0, grid);
		enemyTypes[3] = new EnemyGoat(7, 0, grid);
		enemyTypes[4] = new EnemyLama(7, 0, grid);		
		
		//Vague ennemies
		waveManager = new WaveManager(enemyTypes, 3, 3);
		//Mise en place du joueur
		player = new Player(grid, waveManager);
		player.setup();
		this.menuBackground = QuickLoad("menu_background2");
		setupUI();
	}
	
	//Méthode mise en place de l'interface de l'utilisateur
	private void setupUI() {
		gameUI = new UserInterface();
		gameUI.createMenu("TowerPicker", 1280, 100, 192, 960, 2, 0);
		towerPickerMenu = gameUI.getMenu("TowerPicker");
		towerPickerMenu.quickAdd("NailCannon","nailCannonGun");
		towerPickerMenu.quickAdd("IceCannon", "iceCannonGun");
		towerPickerMenu.quickAdd("CannonLaser", "cannonGunLaser");
		towerPickerMenu.quickAdd("FlameThrower", "flameThrowerGun");
	}
	
	//Actualisation de l'interface de l'utilisateur
	private void updateUI() {
		gameUI.draw();
		gameUI.drawString(1320, 775, "Lives: " + Player.Lives);
		gameUI.drawString(1320, 875, "Cash: " + Player.Cash);
		gameUI.drawString(1320, 825, "Wave " + waveManager.getWaveNumber());
		gameUI.drawString(0, 0, StateManager.framesInLastSecond + " fps");
		
		//Prochaine action de la souris
		if(Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);//Boucle du choix de la tourelle sélectionnée par l'utilisateur
			if(mouseClicked) {
				if(towerPickerMenu.isButtonClicked("NailCannon"))
					player.pickTower(new TowerCannonNail(TowerType.CannonNail, grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
				if(towerPickerMenu.isButtonClicked("IceCannon"))
					player.pickTower(new TowerCannonIce(TowerType.CannonIce, grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
				if(towerPickerMenu.isButtonClicked("CannonLaser"))
					player.pickTower(new TowerCannonLaser(TowerType.CannonLaser, grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
				if(towerPickerMenu.isButtonClicked("FlameThrower"))
					player.pickTower(new TowerFlameThrower(TowerType.FlameThrower, grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
			}
		}
	}

	//Actualisation de la fenêtre
	public void update() {
		DrawQuadTex(menuBackground, 1280, 0, 192, 960);
		grid.draw();
		waveManager.update();
		player.update();
		updateUI();
	}
}
