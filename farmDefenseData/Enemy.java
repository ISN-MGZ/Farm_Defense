package farmDefenseData;

import org.newdawn.slick.opengl.Texture;

import static assistant.Artist.*;
import static assistant.Clock.*;

import java.util.ArrayList;

public class Enemy implements Entity { //Classe ennemies (avec interface Entity)
	
	//Déclaration variables privées
	private int width, height, currentCheckpoint;
	private float speed, x, y, health, startHealth, hiddenHealth;
	private Texture texture, healthBackground, healthForeground, healthBorder;
	private Tile startTile;
	private boolean first, alive;
	private TileGrid grid;
	private ArrayList<Checkpoint> checkpoints;//Stocker checkpoints
	private int[] directions;

	//Constructeur par défaut + objets ennemies référencés
	public Enemy(int tileX, int tileY, TileGrid grid) {
		this.texture = QuickLoad("enemy_s_rabbit");
		this.healthBackground = QuickLoad("healthBackground");
		this.healthForeground = QuickLoad("healthForeground");
		this.healthBorder = QuickLoad("healthBorder");
		this.startTile = grid.getTile(tileX, tileY);
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = TILE_SIZE;
		this.height = TILE_SIZE;
		this.speed = 50;
		this.health = 50;
		this.startHealth = health;
		this.hiddenHealth = health;
		this.grid = grid;
		this.first = true;
		this.alive = true;
		this.checkpoints = new ArrayList<Checkpoint>();
		this.directions = new int[2];
		this.directions[0] = 0; // X-Direction
		this.directions[1] = 0; // Y-Direction
		this.directions = findNextD(startTile);
		this.currentCheckpoint = 0;
		populateCheckpointList();
	}
	
	//Constructeur ennemies texture + objets ennemies référencés
	public Enemy(Texture texture, Tile startTile, TileGrid grid, int width, int height, float speed, float health) {
		this.texture = texture;
		this.healthBackground = QuickLoad("healthBackground");
		this.healthForeground = QuickLoad("healthForeground");
		this.healthBorder = QuickLoad("healthBorder");
		this.startTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.health = health;
		this.startHealth = health;
		this.hiddenHealth = health;
		this.grid = grid;
		this.first = true;
		this.alive = true;
		this.checkpoints = new ArrayList<Checkpoint>();
		this.directions = new int[2];
		this.directions[0] = 0; // X Direction
		this.directions[1] = 0; // Y Direction
		this.directions = findNextD(startTile);
		this.currentCheckpoint = 0;
		populateCheckpointList();
	}

	//Mouvement des ennemies
	public void update() {
		//Voir si c'est la première fois que cette classe est actulisée
		if (first)//Si c'est la première fois, ignorer
			first = false;
		else { //Sinon si le checkpoint est atteint
			if (checkpointReached()) {
				//Checker si il y a d'autres checkpoints avant de bouger
				if (currentCheckpoint + 1 == checkpoints.size())// Prévenir crache quand on arrive au bout
					endOfMazeReached();//Arrivé au bout du chemin
				else
					currentCheckpoint++;//Sinon checkpoint ajoutée
			} else {
				//Si le checkpoint n'est pas atteint, continuer dans la même direction
				x += Delta() * checkpoints.get(currentCheckpoint).getxDirection() * speed;
				y += Delta() * checkpoints.get(currentCheckpoint).getyDirection() * speed;
			}
		}
	}
	
	//Action quand l'ennemi a atteint le dernier checkpoint
	private void endOfMazeReached() {
		Player.modifyLives(-1);//Enlever une vie au joueur
		die();//Faire mourrir l'ennemi
	}

	//Boucle checkpoint atteint
	private boolean checkpointReached() {
		boolean reached = false;
		Tile t = checkpoints.get(currentCheckpoint).getTile();
		//Checker si la position atteinte est dans la variance de 3 (arbitraire) 
		if (x > t.getX() - 3 && x < t.getX() + 3 && y > t.getY() - 3 && y < t.getY() + 3) {
			reached = true;
			x = t.getX();
			y = t.getY();
		}
		return reached;
	}

	//Checkpointlist méthode
	private void populateCheckpointList() {
		//Ajouter le premier checkpoint manuellement en fonction de la tuile de départ
		checkpoints.add(findNextC(startTile, directions = findNextD(startTile)));
		int counter = 0;
		boolean cont = true;
		while (cont) {
			int[] currentD = findNextD(checkpoints.get(counter).getTile());
			if (currentD[0] == 2 || counter == 20) { //Checker si une prochaine direction existe, et après 20 checkpoints
				cont = false;
			} else {
				checkpoints.add(findNextC(checkpoints.get(counter).getTile(),
						directions = findNextD(checkpoints.get(counter).getTile())));
			}
			counter++;
		}
	}

	//Trouver prochain checkpoint
	private Checkpoint findNextC(Tile s, int[] dir) {
		Tile next = null;
		Checkpoint c = null;

		//Boucle pour savoir si le prochain checkpoint a été trouvé
		boolean found = false;

		//Intégrale pour chaque boucle
		int counter = 1;

		while (!found) {
			if (s.getXPlace() + dir[0] * counter == grid.getTilesWide()
					|| s.getYPlace() + dir[1] * counter == grid.getTilesHigh() || s.getType() != grid.getTile(s.getXPlace() + dir[0] * counter, s.getYPlace() + dir[1] * counter).getType()) {
				found = true;
				//Faire diminuer le compteur de 1 pour trouver la tuile avant le nouveau type de tuile
				counter -= 1;
				next = grid.getTile(s.getXPlace() + dir[0] * counter, s.getYPlace() + dir[1] * counter);
			}

			counter++;
		}
		c = new Checkpoint(next, dir[0], dir[1]);//Trouver le prochain checkpoint
		return c;
	}

	//Trouber la prochaine direction
	private int[] findNextD(Tile s) {
		int[] dir = new int[2];
		Tile u = grid.getTile(s.getXPlace(), s.getYPlace() - 1); // Haut
		Tile r = grid.getTile(s.getXPlace() + 1, s.getYPlace()); // Droite
		Tile d = grid.getTile(s.getXPlace(), s.getYPlace() + 1); // Bas
		Tile l = grid.getTile(s.getXPlace() - 1, s.getYPlace()); // Gauche

		
		//Checker si le type de tuile actuel inhabité est le même que celui en haut, droite, bas, gauche
		if (s.getType() == u.getType() && directions[1] != 1) { // Si la tuile au dessus est le même : aller (x=0, y=-1)
			dir[0] = 0;
			dir[1] = -1;
		} else if (s.getType() == r.getType() && directions[0] != -1) { // Si Tile à droite est le même : aller (x=1,
																		// y=0)
			dir[0] = 1;
			dir[1] = 0;
		} else if (s.getType() == d.getType() && directions[1] != -1) { // Si Tile en dessous est le même : aller (x=0,
																		// y=1)
			dir[0] = 0;
			dir[1] = 1;
		} else if (s.getType() == l.getType() && directions[0] != 1) {// Si Tile à gauche est le même : aller (x=-1,
																		// y=0)
			dir[0] = -1;
			dir[1] = 0;
		} else {
			dir[0] = 2;
			dir[1] = 2;
			// System.out.println("Aucune direction trouvée");
		}
		return dir;
	}

	
	//Prendre des dégats d'une source extérieure
	public void damage(int amount) {
		health -= amount;
		if (health <= 0) {
			die();
			Player.modifyCash(5); //Méhode statique de la classe Player
		}
	}

	//Méthode mort
	private void die() {
		alive = false;
	}

	//Dessiner fenêtre
	public void draw() {
		float healthPercentage = health / startHealth;
		//Texture ennemi
		DrawQuadTex(texture, x, y, width, height);
		//Texture vie de l'ennemi
		DrawQuadTex(healthBackground, x, y - 16, width, 8);
		DrawQuadTex(healthForeground, x, y - 16, TILE_SIZE * healthPercentage, 8);
		DrawQuadTex(healthBorder, x, y - 16, width, 8);
	}
	
	
	/*Variables publiques déclarées*/
	public void reduceHiddenHealth (float amount) {
		hiddenHealth -= amount;
	}

	public float getHiddenHealth() {
		return hiddenHealth;
	}
	
	public int getWidth() {// METTRE PUBLIC POUR LES AUTRES CLASSES ↓
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	//Méthode Quickload pour toutes les classes ennemies
	public void setTexture(String textureName) {
		this.texture = QuickLoad(textureName);
	}

	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public TileGrid getTileGrid() {
		return grid;
	}

	public boolean isAlive() {
		return alive;
	}
}
