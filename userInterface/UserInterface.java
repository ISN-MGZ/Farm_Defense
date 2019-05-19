package userInterface;

import static assistant.Artist.*;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;

public class UserInterface { //Classe interface utilisateur

	//Variables privées
	private ArrayList<Button> buttonList;//Stocker boutons
	private ArrayList<Menu> menuList;//Stocker menus
	private TrueTypeFont font;//Police écriture
	private Font awtFont;

	//Déclaration des éléments de l'interface utilisateur
	public UserInterface() {
		buttonList = new ArrayList<Button>();
		menuList = new ArrayList<Menu>();
		awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, false);
	}
	
	//Créer méthode ajouter police ériture
	public void drawString(int x, int y, String text) {
		font.drawString(x, y, text);
	}

	//Créer méthode ajouter bouton texture
	public void addButton(String name, String textureName, int x, int y) {
		buttonList.add(new Button(name, QuickLoad(textureName), x, y));
	}

	//Méthode pour savoir lorsque l'on a cliqué sur le bouton
	public boolean isButtonClicked(String buttonName) {
		Button b = getButton(buttonName);
		float mouseY = HEIGHT - Mouse.getY() - 1;
		if (Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() && mouseY > b.getY() && mouseY < b.getY() + b.getHeight()) {
			//Voir si la souris est sur le bouton
			return true;
		}
		return false;
	}

	//Liste boutons
	private Button getButton(String buttonName) {
		for (Button b : buttonList) {
			if (b.getName().equals(buttonName)) {
				return b;
			}
		}
		return null;
	}
	
	//Créer méthode pour menu
	public void createMenu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight) {
		menuList.add(new Menu(name, x, y, width, height, optionsWidth, optionsHeight));
	}
	
	//Cherche tous les menus en fonction de leur nom, et quand il le trouve, il le retourne pour l'utiliser
	public Menu getMenu(String name) { 
		for(Menu m: menuList)
			if(name.equals(m.getName()))
				return m;
		return null;
	}

	//Méthode pour afficher/dessiner les boutons et menus
	public void draw() {
		for (Button b: buttonList) 
			DrawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
		for (Menu m: menuList) {
			m.draw();
		}
	}
	
	public class Menu { ////////Classe Menu////////
		
		//variables classe menu
		String name;
		private ArrayList<Button> menuButtons;//Liste boutons
		private int x, y, width, height, buttonAmount, optionsWidth, optionsHeight, padding;
		
		//Référencer variables du menu
		public Menu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight) {
			this.name = name;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.optionsWidth = optionsWidth;
			this.optionsHeight = optionsHeight;
			this.padding = (width - (optionsWidth * TILE_SIZE)) / (optionsWidth + 1);
			this.buttonAmount = 0;
			this.menuButtons = new ArrayList<Button>();
		}
		
		//Méthode ajouter bouton
		public void addButton(Button b) {
			setButton(b);
		}
		
		//Méthode ajouter bouton raccourcie 
		public void quickAdd(String name, String buttonTextureName) {
			Button b = new Button(name, QuickLoad(buttonTextureName), 0, 0);
			setButton(b);
		}
		
		//Positionnement bouton
		private void setButton(Button b) {//
			if(optionsWidth != 0)
				b.setY(y + (buttonAmount / optionsWidth) * TILE_SIZE);
			b.setX(x + (buttonAmount % 2) * (padding + TILE_SIZE) + padding);// % = le reste
			buttonAmount++;//Ajouter boutton
			menuButtons.add(b);
		}
		
		//Méthode bouton cliqué boucle
		public boolean isButtonClicked(String buttonName) {
			Button b = getButton(buttonName);
			float mouseY = HEIGHT - Mouse.getY() - 1;
			if (Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() && mouseY > b.getY() && mouseY < b.getY() + b.getHeight()) {
				//Voir si la souris est sur le bouton
				return true;
			}
			return false;
		}
		
		//Chercher le bouton
		private Button getButton(String buttonName) {
			for (Button b : menuButtons) {
				if (b.getName().equals(buttonName)) {
					return b;
				}
			}
			return null;
		}
		
		//Méhode dessiner boutons
		public void draw() {
			for (Button b: menuButtons)
				DrawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
		
		
		
		/*Méthode déclarée publique*/
		public String getName() {
			return name;
		}
	}
}
