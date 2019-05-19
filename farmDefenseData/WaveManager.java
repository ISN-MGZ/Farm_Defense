package farmDefenseData;

public class WaveManager {//Classe gestion des vagues ennemies

	//Déclaration des variables privées
	private float timeSinceLastWave, timeBetweenEnemies;;
	private int waveNumber, enemiesPerWave;
	private Enemy[] enemyTypes;
	private Wave currentWave;

	//Constructeur par défaut + objets de la gestion des vagues référencés
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
		if (!currentWave.isCompleted())//Si la vague n'est pas fini continué de l'actualiser
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

	/*Variables publiques déclarées*/
	public Wave getCurrentWave() {
		return currentWave;
	}
	
	public int getWaveNumber() {
		return waveNumber;
	}
}
