package farmDefenseData;

import static assistant.Artist.DrawQuadTex;
import static assistant.Artist.HEIGHT;
import static assistant.Artist.QuickLoad;
import static assistant.Artist.TILE_SIZE;
import static assistant.Artist.WIDTH;
import static assistant.Leveler.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import assistant.StateManager;
import assistant.StateManager.GameState;
import userInterface.UserInterface;
import userInterface.UserInterface.Menu;

public class Editor { //Classe mode éditeur

	//Déclaration variables privées
	private TileGrid grid;
	private int index;
	private TileType[] types;
	private UserInterface editorUI;
	private Menu tilePickerMenu;
	private Texture menuBackground;
	private UserInterface menuUI;

	//Constructeur par défaut + objets éditeur référencés
	public Editor() {
		this.grid = LoadMap("newMap_1");
		this.index = 0;
		this.types = new TileType[4];
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
		this.types[3] = TileType.Rock;
		this.menuBackground = QuickLoad("menu_background_editor");
		setupUI();
	}

	//Référencer objets interface utilisateur
	private void setupUI() {
		editorUI = new UserInterface();
		editorUI.createMenu("TilePicker", 1280, 100, 192, 960, 2, 0);
		tilePickerMenu = editorUI.getMenu("TilePicker");
		tilePickerMenu.quickAdd("Grass", "grass");
		tilePickerMenu.quickAdd("Dirt", "dirt");
		tilePickerMenu.quickAdd("Water", "water");
		tilePickerMenu.quickAdd("Rock", "rock");
		menuUI = new UserInterface();
		menuUI.addButton("Back", "backButton", 1150, 850);
	}
	
	//Bouton retour
	private void updateButton() {
		if(Mouse.isButtonDown(0)) {//Si la souris est appuyée
			if(menuUI.isButtonClicked("Back"))//Si le bouton est cliqué
				StateManager.setState(GameState.MAINMENU); //Changer état
		}
	}
	
	//Actualisation
	public void update() {
		draw();
		//Suivit de la souris
		if(Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);//Savoir sur quoi la souris a cliqué
			if(mouseClicked) {
				if(tilePickerMenu.isButtonClicked("Grass")) {
					index = 0;
				}else if (tilePickerMenu.isButtonClicked("Dirt")) {
					index = 1;
				}else if (tilePickerMenu.isButtonClicked("Water")) {
					index = 2;
				}else if (tilePickerMenu.isButtonClicked("Rock")) {
					index = 3;
				}else {
					setTile();
				}
			}
		}
		
		//Texte à afficher
		editorUI.drawString(1302, 870, "Appuyez sur S");
		editorUI.drawString(1290, 900, "pour sauvegarder");

		//Suivre l'actualisation du menu
		menuUI.draw();
		
		//Actualiser bouton
		updateButton();
		
		//Suivit des actions du clavier //////////////////////OPTION ADMINISTRATEUR DEBUG/////////////////////////////////
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				moveIndex();
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState()) {
				SaveMap("newMap_1", grid);
			}
		}
	}
	
	//Dessiner fenêtre
	private void draw() {
		DrawQuadTex(menuBackground, 1280, 0, 192, 960);
		grid.draw();
		editorUI.draw();
	}

	//Localisation/Position de la tuile
	private void setTile() {
		grid.setTile((int) Math.floor(Mouse.getX() / TILE_SIZE), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / TILE_SIZE),
				types[index]);
	}

	
	//Permet de savoir quel type de tuiles est sélectionnée
	private void moveIndex() {
		index++;
		if (index > types.length - 1) { // Commence à 0
			index = 0;
		}
	}
}
