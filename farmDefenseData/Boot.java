package farmDefenseData;

import static assistant.Artist.BeginSession;

import org.lwjgl.opengl.Display;
import assistant.Clock;
import assistant.StateManager;

public class Boot {

	public Boot() { //Classe principale
			
		//Appeler la m�thode statique dans Artiste pour initialiser OpenGL
		BeginSession();

		
		//Nouveau thread pour la musique
		 Thread backgroundPlayer;
		 
		 //Option traquage probl�me
		    try {//Essayer de lancer la musique
		        backgroundPlayer = new Thread(new MusicBackground());
		        backgroundPlayer.start();
		    }
		    catch(Exception e)//Si il y a un probl�me, faire les actions suivantes :
		    {
		        System.out.println("Probl�me lancement du thread secondaire (= musique)");
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

	//M�thode principale
	public static void main(String[] args) {
		new Boot();
	}
}
