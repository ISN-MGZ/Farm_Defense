package farmDefenseData;

public class WaveManager {//Classe gestion des vagues ennemies

	//D�claration des variables priv�es
	private float timeSinceLastWave, timeBetweenEnemies;;
	private int waveNumber, enemiesPerWave;
	private Enemy[] enemyTypes;
	private Wave currentWave;

	//Constructeur par d�faut + objets de la gestion des vagues r�f�renc�s
	public WaveManager(Enemy[] enemyTypes, float timeBetweenEnemies, int enemiesPerWave) {
		this.enemyTypes = enemyTypes;
		this.timeBetweenEnemies = timeBetweenEnemies;
		this.enemiesPerWave = enemiesPerWave;
		this.timeSinceLastWave = 0;
		this.waveNumber = 0;
		this.currentWave = null;

		newWave();
	}

	//Actualisation de la classe
	public void update() {
		if (!currentWave.isCompleted())//Si la vague n'est pas fini continu� de l'actualiser
			currentWave.update();
		else //Sinon nouvelle vague
			newWave();
	}

	//Nouvelle vague
	private void newWave() {
		currentWave = new Wave(enemyTypes, timeBetweenEnemies, enemiesPerWave);
		enemiesPerWave++;
		waveNumber++;
		System.out.println("Beginning Wave " + waveNumber);
	}

	/*Variables publiques d�clar�es*/
	public Wave getCurrentWave() {
		return currentWave;
	}
	
	public int getWaveNumber() {
		return waveNumber;
	}
}
