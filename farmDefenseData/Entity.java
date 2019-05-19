package farmDefenseData;

public interface Entity { //Classe interface (permet de d�finir le comportement d'une classe)(ensemble m�thode abstraites)

	//D�claration des variables/m�thodes
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
