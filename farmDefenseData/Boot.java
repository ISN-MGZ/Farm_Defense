package farmDefenseData;

import static assistant.Artist.BeginSession;

import org.lwjgl.opengl.Display;
import assistant.Clock;
import assistant.StateManager;

public class Boot {

	public Boot() { //Classe principale
			
		//Appeler la méthode statique dans Artiste pour initialiser OpenGL
		BeginSession();

		
		//Nouveau thread pour la musique
		 Thread backgroundPlayer;
		 
		 //Option traquage problème
		    try {//Essayer de lancer la musique
		        backgroundPlayer = new Thread(new MusicBackground());
		        backgroundPlayer.start();
		    }
		    catch(Exception e)//Si il y a un problème, faire les actions suivantes :
		    {
		        System.out.println("Problème lancement du thread secondaire (= musique)");
		        e.printStackTrace();
		    }

		    
		//Boucle principale du jeu
		while (!Display.isCloseRequested()) {
			Clock.update();
			StateManager.update();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}

	//Méthode principale
	public static void main(String[] args) {
		new Boot();
	}
}
