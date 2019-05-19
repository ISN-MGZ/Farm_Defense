package farmDefenseData;

public enum TileType { //Classe d�finition types de tuiles (enum)

	//Enum des tuiles
	Grass("grass", true), Dirt("dirt", false), Water("water", false), Rock("rock", true), NULL("water", false);

	//Variables d�clar�es
	String textureName;
	boolean buildable;

	//Constructeur types de tuiles
	TileType(String textureName, boolean buildable) {
		this.textureName = textureName;
		this.buildable = buildable;
	}
}
