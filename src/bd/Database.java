package bd;
import java.sql.*;
import java.util.ArrayList;
import dao.Autores;
import dao.Generos;
import dao.Livros;
import dao.Users;

public class Database {
	private Connection conn;
	
	public Database(){
		Conectar();
	}
	
	public void Conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.conn = DriverManager.getConnection("jdbc:sqlite:biblioteca.s3db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Função para validar o login do usuário.
	 * @param usr
	 * @param passwd
	 * @return
	 */
	public int validaLogin(String usr, String passwd) {
		String query = "SELECT LOGADO FROM USERS WHERE USERNAME = ? AND PASSWD = ?";
		int log = -1;
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setString(1, usr);
			st.setString(2, passwd);
			ResultSet rs = st.executeQuery();
			log  = rs.getInt(1);
			if (log != -1 )  {
				st.close();
				return log;
			}
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Função que atualiza um campo do banco de dados no primeiro login do usuário.
	 * @param usuario
	 */
	public void atualizaFlagLogin(String usuario) {
		String query = "UPDATE USERS SET LOGADO = 1 WHERE USERNAME = ?";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setString(1, usuario);
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Função para cadastrar um novo usuário no banco de dados.
	 * @param usr
	 * @return true caso o cadastro seja bem sucedido, ou false caso ocorra algum erro.
	 */
	public boolean cadastraUser(String usr) {
		String query = "INSERT INTO USERS (USERID, USERNAME, PASSWD, LOGADO) VALUES (null, ?, ?, 0)";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setString(1, usr.toLowerCase());
			st.setString(2, usr.toLowerCase());
			st.executeUpdate();
			st.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Função para remover um usuário do banco.
	 * @param userid
	 * @return true caso a remoção do usuário seja bem sucedida, ou false caso algum erro ocorra.
	 */
	public boolean removeUser(int userid) {
		String query = "DELETE FROM USERS WHERE USERID = ?";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setLong(1, userid);
			st.executeUpdate();
			st.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Função que altera a senha do usuário.
	 * @param usuario
	 * @param password
	 * @param newPass
	 * @return true caso o update da senha seja bem sucedido, ou false caso algum erro ocorra.
	 */
	public boolean alteraSenha(String usuario, String password, String newPass) {
		String query = "UPDATE USERS SET PASSWD = ? WHERE USERNAME = ? AND PASSWD = ?";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setString(1, newPass);
			st.setString(2, usuario);
			st.setString(3, password);
			int rs = st.executeUpdate();
			if (rs != 0) {
				st.close();
				return true;
			}
			st.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Função para resetar a senha do usuário para a padrão.
	 * @param usuario
	 * @return true se o reset foi bem sucedido, ou false, caso algum erro ocorra.
	 */
	public boolean resetaPass(String usuario) {
		String query = "UPDATE USERS SET PASSWD = ? WHERE USERNAME = ?";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setString(1, usuario);
			st.setString(2, usuario);
			int rs = st.executeUpdate();
			if (rs != 0) {
				st.close();
				return true;
			}
			st.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Função que retorna a lista de usuários existentes no banco.
	 * @return retorna uma lista de usuários cadastrados no banco de dados.
	 */
	public ArrayList<Users> listaUsers() {
		String query = "SELECT * FROM USERS";
		ArrayList<Users> usuarios = new ArrayList<Users>();
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Users usuario = new Users();
				usuario.setUserid(rs.getInt("USERID"));
				usuario.setUsername(rs.getString("USERNAME"));
				usuario.setPassword(rs.getString("PASSWD"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	/**
	 * Função que retorna uma lista de Autores cadastrados
	 * @return ArrayList<Autores>
	 */
	public ArrayList<Autores> listaAutores() {
		String query = "SELECT * FROM AUTORES";
		ArrayList<Autores> autores = new ArrayList<Autores>();
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Autores autor = new Autores();
				autor.setAutorId(rs.getInt("AUTORID"));
				autor.setNome(rs.getString("AUTOR"));
				autores.add(autor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return autores;
	}
	
	/**
	 * Retorna uma lista de generos cadastrados
	 * @return ArrayList<Generos>
	 */
	public ArrayList<Generos> listaGeneros() {
		String query = "SELECT * FROM GENEROS";
		ArrayList<Generos> generos = new ArrayList<Generos>();
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Generos genero = new Generos();
				genero.setIdGenero(rs.getInt("GENEROID"));
				genero.setGenero(rs.getString("GENERO"));
				generos.add(genero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return generos;
	}
	
	/**
	 * Retorna uma lista de livros cadastrados.
	 * @return ArrayList<Livros>
	 */
	public ArrayList<Livros> listaLivros() {
		String query = "SELECT * FROM LIVROS";
		ArrayList<Livros> livros = new ArrayList<Livros>();
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Livros livro = new Livros();
				livro.setIdLivro(rs.getInt("LIVROID"));
				livro.setTitulo(rs.getString("TITULO"));
				livro.setAutor(rs.getString("AUTOR"));
				livro.setGenero(rs.getString("GENERO"));
				livro.setPaginas(rs.getInt("PAGINAS"));
				livro.setPublicacao(rs.getString("PUBLICACAO"));
				livro.setSinopse(rs.getString("SINOPSE"));
				livros.add(livro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;
	}
	
	/**
	 * Função para remover um autor do banco.
	 * @param userid
	 * @return true caso a remoção do autor seja bem sucedida, ou false caso algum erro ocorra.
	 */
	public boolean removeAutor(int autorid) {
		String query = "DELETE FROM AUTORES WHERE AUTORID = ?";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setLong(1, autorid);
			st.executeUpdate();
			st.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Função para remover um genero do banco.
	 * @param userid
	 * @return true caso a remoção do genero seja bem sucedida, ou false caso algum erro ocorra.
	 */
	public boolean removeGenero(int generoid) {
		String query = "DELETE FROM GENEROS WHERE GENEROID = ?";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setLong(1, generoid);
			st.executeUpdate();
			st.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Função para remover um livro do banco.
	 * @param userid
	 * @return true caso a remoção do livro seja bem sucedida, ou false caso algum erro ocorra.
	 */
	public boolean removeLivro(int livroid) {
		String query = "DELETE FROM LIVROS WHERE LIVROID = ?";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setLong(1, livroid);
			st.executeUpdate();
			st.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Função para cadastrar um novo livro no banco de dados.
	 * @param usr
	 * @return true caso o cadastro seja bem sucedido, ou false caso ocorra algum erro.
	 */
	public boolean cadastraLivro(String titulo, String autor, String genero, int paginas, String publicacao, String sinopse) {
		String query = "INSERT INTO LIVROS (LIVROID, TITULO, AUTOR, GENERO, PAGINAS, PUBLICACAO, SINOPSE) VALUES (null, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setString(1, titulo.toUpperCase());
			st.setString(2, autor.toUpperCase());
			st.setString(3, genero.toUpperCase());
			st.setInt(4, paginas);
			st.setString(5, publicacao);
			st.setString(6, sinopse.toUpperCase());
			st.executeUpdate();
			st.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Função para cadastrar um novo autor no banco de dados.
	 * @param usr
	 * @return true caso o cadastro seja bem sucedido, ou false caso ocorra algum erro.
	 */
	public boolean cadastraAutor(String autor) {
		String query = "INSERT INTO AUTORES (AUTORID, AUTOR) VALUES (null, ?)";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setString(1, autor.toUpperCase());
			st.executeUpdate();
			st.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Função para cadastrar um novo genero no banco de dados.
	 * @param usr
	 * @return true caso o cadastro seja bem sucedido, ou false caso ocorra algum erro.
	 */
	public boolean cadastraGenero(String genero) {
		String query = "INSERT INTO GENEROS (GENEROID, GENERO) VALUES (null, ?)";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setString(1, genero.toUpperCase());
			st.executeUpdate();
			st.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}