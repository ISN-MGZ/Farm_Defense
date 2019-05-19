package farmDefenseData;

import static assistant.Artist.TILE_SIZE;
import static assistant.Clock.*;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Wave { //Classe vague ennemies

	//D�claration des variables priv�es
	private float timeSinceLastSpawn, spawnTime;
	private Enemy[] enemyTypes;
	private CopyOnWriteArrayList<Enemy> enemyList;
	private int enemiesPerWave, enemiesSpawned;
	private boolean waveCompleted;

	//Constructeur par d�faut + objets de la classe vague r�f�renc�s
	public Wave(Enemy[] enemyTypes, float spawnTime, int enemiesPerWave) {
		this.enemyTypes = enemyTypes;
		this.spawnTime = spawnTime;
		this.enemiesPerWave = enemiesPerWave;
		this.enemiesSpawned = 0;
		this.timeSinceLastSpawn = 0;
		this.enemyList = new CopyOnWriteArrayList<Enemy>();
		this.waveCompleted = false;

		spawn();
	}

	//Actualisation de la classe
	public void update() {
		//Assumer que tous les ennemies sont morts, jusqu'� ce que la boucle prouve le contraire
		boolean allEnemiesDead = true;
		if (enemiesSpawned < enemiesPerWave) {// Si le nombre d'ennemies qui spawn est moins grand que le nombre
												// d'ennemies qui doit �tre dans la vague = on continue � checker
			timeSinceLastSpawn += Delta();
			if (timeSinceLastSpawn > spawnTime) {
				spawn();
				timeSinceLastSpawn = 0;
			}
		}

		//Prendre liste ennemies
		for (Enemy e : enemyList) {
			if (e.isAlive()) {
				allEnemiesDead = false;
				e.update();
				e.draw();
			} else
				enemyList.remove(e);
		}
		if (allEnemiesDead)
			waveCompleted = true;
	}

	//Spawn ennemies al�atoirement
	private void spawn() {
		int enemyChosen = 0;
		Random random = new Random();
		enemyChosen = random.nextInt(enemyTypes.length);
		enemyList.add(new Enemy(enemyTypes[enemyChosen].getTexture(), enemyTypes[enemyChosen].getStartTile(), enemyTypes[enemyChosen].getTileGrid(), TILE_SIZE, TILE_SIZE,
				enemyTypes[enemyChosen].getSpeed(), enemyTypes[enemyChosen].getHealth()));
		enemiesSpawned ++;//Rajouter un ennemi � chque vague
	}

	//Vague ennemies compl�t�s
	public boolean isCompleted() {
		return waveCompleted;
	}

	//Retourner liste ennemies
	public CopyOnWriteArrayList<Enemy> getEnemyList() {
		return enemyList;
	}
}
