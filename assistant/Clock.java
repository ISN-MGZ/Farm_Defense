package assistant;

import org.lwjgl.Sys;

public class Clock { /*Classe horloge pour notion du temps (+accès debug admin option)*/

	//Déclaration variables privées
	private static boolean paused = false;
	public static long LastFrame, totalTime;
	public static float d = 0, multiplier = 3;

	//Méthode reconnaissance du temps 
	public static long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();//Temps en millisecondes
	}

	//Actualisation fréquence pour éviter les bugs 
	public static float getDelta() { 
		long currentTime = getTime();//Temps présent
		int delta = (int) (currentTime - LastFrame);//Différence entre le temps présent et le temps passé
		LastFrame = getTime();
		if (delta * 0.001f > 0.05f)
			return 0.05f;
		return delta * 0.001f;
	}

	//Méthode temps delta
	public static float Delta() {
		if (paused)//Si le jeu est en pause
			return 0; //Pas d'actualisation donc delta = 0
		else
			return d * multiplier; //Sinon retourner la vitesse
	}

	//Déclaration TotalTime
	public static float TotalTime() {
		return totalTime;
	}

	//Déclaration Multiplier
	public static float Multiplier() {
		return multiplier;
	}

	//Actualisation
	public static void update() {
		d = getDelta();
		totalTime += d;
	}

	//Multiplicateur
	public static void ChangeMultiplier(float change) {
		if (multiplier + change < -1 && multiplier + change > 7) {
		} else {
			multiplier += change;
		}
	}

	//Méthode option pause
	public static void Pause() { 
		if (paused)
			paused = false;
		else
			paused = true;
	}
}
