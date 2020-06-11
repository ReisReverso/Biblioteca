package dao;

public class Generos {

	private int idGenero;
	private String genero;
	
	public int getIdGenero() {
		return idGenero;
	}
	
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	@Override
	public String toString() {
		return getGenero();
	}
}
