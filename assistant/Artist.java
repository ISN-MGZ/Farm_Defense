package assistant;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glTranslatef;

public class Artist { /*Classe openGl qui donne accès à une librairie graphique 2D*/

	/*Définition des variables pour l'écran (+"final" pour ne pas être modifiées)*/
	public static final int WIDTH = 1472, HEIGHT = 960;
	public static final int TILE_SIZE = 64; 

	public static void BeginSession() { //Lancement jeu
		Display.setTitle("Farm Defense _ By ISN_MGZ"); //Titre projet (barre du haut)
		/*Regroupement des instructions à exécuter pour définir une réponse si l'une de ces instructions provoque une exception*/
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		//Projection 2D de la librairie
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);//Caméra (0, largeur, hauteur, 3 Dimension(1, -1))
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);// Image png transparence
	}

	//Anticiper collision 
	public static boolean CheckCollision(float x1, float y1, float width1, float height1, float x2, float y2,
			float width2, float height2) {// Coordonnée pour 2 objets
		if (x1 + width1 > x2 && x1 < x2 + width2 && y1 + height1 > y2 && y1 < y2 + height2)
			return true;
		return false;
	}

	//Dessiner vortex pour les tuiles
	public static void DrawQuad(float x, float y, float width, float height) {
		glBegin(GL_QUADS);
		glVertex2f(x, y);// Coin haut gauche
		glVertex2f(x + width, y);// Coin haut droit
		glVertex2f(x + width, y + height);// Coin bas droite
		glVertex2f(x, y + height);// Coin bas gauche
		glEnd();
	}

	//Dessiner les vortex pour texture
	public static void DrawQuadTex(Texture tex, float x, float y, float width, float height) {
		tex.bind();
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}

	//Dessiner les vortex avec rotation
	public static void DrawQuadTexRot(Texture tex, float x, float y, float width, float height, float angle) {
		tex.bind();
		glTranslatef(x + width / 2, y + height / 2, 0);// Rotation par rapport au centre d'un carré
		glRotatef(angle, 0, 0, 1);
		glTranslatef(-width / 2, -height / 2, 0);// Translation angle haut gauche
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}

	//Méthode pour charger texture
	public static Texture LoadTexture(String path, String fileType) {
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);//Chemin des images
		/*Regroupement des instructions à exécuter pour définir une réponse si l'une de ces instructions provoque une exception*/
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}

	// Méthode raccourcie pour appeler texture PNG
	public static Texture QuickLoad(String name) { 
		Texture tex = null;
		tex = LoadTexture("res/" + name + ".PNG", "PNG");
		return tex;
	}
}
