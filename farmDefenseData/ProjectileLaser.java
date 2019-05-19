package farmDefenseData;

public class ProjectileLaser extends Projectile{//Classe projectile laser

	public ProjectileLaser(ProjectileType type, Enemy target, float x, float y, int width, int height) {
		super(type, target, x, y, width, height);
	}
	
	public void damage() {
		super.getTarget().setSpeed(8f);
		super.damage();
	}
}
