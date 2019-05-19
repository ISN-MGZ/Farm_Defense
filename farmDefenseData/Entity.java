package farmDefenseData;

public interface Entity { //Classe interface (permet de définir le comportement d'une classe)(ensemble méthode abstraites)

	//Déclaration des variables/méthodes
	public float getX();
	public float getY();
	public int getWidth();
	public int getHeight();
	public void setX(float x);
	public void setY(float y);
	public void setWidth(int width);
	public void setHeight(int height);
	public void update();
	public void draw();
}
