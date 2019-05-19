package farmDefenseData;

public class ProjectileIceball extends Projectile{//Classe projectile glace

	public ProjectileIceball(ProjectileType type, Enemy target, float x, float y, int width, int height) {
		super(type, target, x, y, width, height);
	}
	
	public void damage() {
		super.getTarget().setSpeed(4f);
		super.damage();
	}
}
