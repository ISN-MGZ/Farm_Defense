package farmDefenseData;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.lang.Runnable;

public class MusicBackground implements Runnable {//Classe musique de fond (runnable)

		//Méthode de la musique
		public void run() {
			URL url = MusicBackground.class.getResource("backgroundMusic.wav");//Rechercher la musique
			AudioClip clip = Applet.newAudioClip(url);
			clip.play();//Jouer la musique
			
			try {
				Thread.sleep(1000);//Temps attente en millisecondes entre la fin et le début de la musique
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		clip.loop();//Boucle de la musique
	}
}