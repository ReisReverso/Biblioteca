package dao;

public class Autores {

	private int autorId;
	private String nome;
	
	public int getAutorId() {
		return autorId;
	}
	
	public void setAutorId(int autorId) {
		this.autorId = autorId;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
