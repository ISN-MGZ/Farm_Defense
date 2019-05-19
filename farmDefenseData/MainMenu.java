package farmDefenseData;

import static assistant.Artist.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import assistant.StateManager;
import assistant.StateManager.GameState;
import userInterface.UserInterface;

public class MainMenu {//Classe du menu principal

	//Déclaration de variables privées
	private Texture background;
	private UserInterface menuUI;
	private Texture mainMenuRightBackground;
	
	//Constructeur par défaut + objet du menu principal référencé
	public MainMenu() {
		background = QuickLoad("mainmenu");
		menuUI = new UserInterface();
		menuUI.addButton("Play", "playButton", WIDTH / 2 - 256, (int) (HEIGHT * 0.4f));
		menuUI.addButton("Editor", "editorButton", WIDTH / 2 - 256, (int) (HEIGHT * 0.6f));
		menuUI.addButton("Quit", "quitButton", WIDTH / 2 -256, (int) (HEIGHT * 0.8f));
		
		this.mainMenuRightBackground = QuickLoad("menu_background3");
	}
	
	//Checker si un bouton est cliqué par l'utilisateur, si oui effectuer une action
	private void updateButton() {
		if(Mouse.isButtonDown(0)) {
			if(menuUI.isButtonClicked("Play"))//Si le bouton "Jouer" est cliqué
				StateManager.setState(GameState.GAME);//Changer le status en mode jeu
			if(menuUI.isButtonClicked("Editor"))//Si le bouton "éditeur" est cliqué
				StateManager.setState(GameState.EDITOR);//Changer le status en mode éditeur
			if(menuUI.isButtonClicked("Quit"))//Si le bouton "Quitter" est cliqué
				System.exit(0);//Quitter le jeu
		}
	}
	
	
	//Actualisation de la fenêtre
	public void update() {
		DrawQuadTex(mainMenuRightBackground, 1280, 0, 192, 960);
		DrawQuadTex(background, 0, 0, 2048, 1024);
		menuUI.draw();
		updateButton();
	}
}
