package farmDefenseData;

import static assistant.Artist.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import assistant.StateManager;
import assistant.StateManager.GameState;
import userInterface.UserInterface;

public class GameOver {//Classe défaite dans le jeu = Game Over

	private Texture gameOverBackground;
	private UserInterface menuUI;
	
	//Constructeur par défaut de l'écran Game Over
	public GameOver() {
		/*menuUI.addButton("Menu", "playButton", WIDTH / 2 - 256, (int) (HEIGHT * 0.4f));
		menuUI.addButton("Quit", "quitButton", WIDTH / 2 -256, (int) (HEIGHT * 0.8f));*/
		this.gameOverBackground = QuickLoad("gameOverBackground");
	}
	
	/*private void updateButton() {
		if(Mouse.isButtonDown(0)) {
			if(menuUI.isButtonClicked("Menu"))
				StateManager.setState(GameState.GAME);
			if(menuUI.isButtonClicked("Quit"))
				System.exit(0);
		}
	}*/
	
	//Afficher le fond
	public void update() {
		DrawQuadTex(gameOverBackground, 0, 0, 1900, 1800);
		/*menuUI.draw();
		updateButton();*/
	}
}
