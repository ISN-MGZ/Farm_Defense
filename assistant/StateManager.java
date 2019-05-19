package assistant;

import static assistant.Leveler.LoadMap;

import farmDefenseData.Editor;
import farmDefenseData.Game;
import farmDefenseData.GameOver;
import farmDefenseData.MainMenu;
import farmDefenseData.TileGrid;

public class StateManager { //Différents status du jeu

	public static enum GameState{ //Classe enum pour définir les différents status du jeu comme constante
		MAINMENU, GAME, EDITOR, GAMEOVER
	}
	
	//Variables des status
	public static GameState gameState = GameState.MAINMENU ;
	public static MainMenu mainMenu;
	public static Game game;
	public static Editor editor;
	public static GameOver gameOver;
	
	
	//Variables pour le suivit de FPS
	public static long nextSecond = System.currentTimeMillis() + 1000;
	public static int framesInLastSecond = 0;
	public static int framesInCurrentSecond = 0;
	
	//Charger map
	static TileGrid map = LoadMap("newMap_1");
	
	//Actualisation des status
	public static void update() {
		switch(gameState) {
		case MAINMENU:
			if(mainMenu == null)//Si il n'existe pas encore = lance le jeu
				mainMenu = new MainMenu();
			mainMenu.update();
			break;
		case GAME:
			if(game == null)
				game = new Game(map);
			game.update();
			break;
		case EDITOR:
			if(editor == null)
				editor = new Editor();
			editor.update();
			break;
		case GAMEOVER:
			if(gameOver == null)
				gameOver = new GameOver();
			gameOver.update();
			break;
		}
		
		//Méthode suivit de FPS (Frames Per Second) (Nombre d'actualisation à la seconde)
		long currentTime = System.currentTimeMillis();
		if(currentTime > nextSecond) {
			nextSecond += 1000;//1000ms = 1s
			framesInLastSecond = framesInCurrentSecond;
			framesInCurrentSecond = 0;//Restart every second the tracker
		}
		framesInCurrentSecond ++;
	}
	
	//Changer status 
	public static void setState(GameState newState) {
		gameState = newState;
	}
}
