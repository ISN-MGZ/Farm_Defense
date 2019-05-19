package farmDefenseData;

public class ProjectileNail extends Projectile{//Classe projectile clou

		public ProjectileNail(ProjectileType type, Enemy target, float x, float y, int width, int height) {
			super(type, target, x, y, width, height);
		}
		
		public void damage() {
			super.damage();
		}
	}
