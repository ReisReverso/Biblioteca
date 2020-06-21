package bd;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import dao.Autores;
import dao.Generos;
import dao.Livros;
//import dao.Users;

public class Database {
	private Connection conn;
	private String diretorio = System.getenv("AppData") + "\\Biblioteca Pessoal";
	
	public Database(){
		Conectar();
		CriaBase();
		Desconectar();
	}
	
	private void Conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			File file = new File(diretorio);
			file.mkdirs();
			this.conn = DriverManager.getConnection("jdbc:sqlite:"+diretorio+"\\biblioteca.s3db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void CriaBase() {
		/*String criaUsers = "CREATE TABLE IF NOT EXISTS USERS ("
				+ "USERID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "USERNAME VARCHAR(255) NOT NULL UNIQUE,"
				+ "PASSWD VARCHAR(255) NOT NULL,"
				+ "LOGADO INTEGER NOT NULL)";

		String insereAdmin = "INSERT OR IGNORE INTO USERS (USERID, USERNAME, PASSWD, LOGADO) VALUES (null, admin, admin, 0)";*/

		String criaAutores = "CREATE TABLE IF NOT EXISTS AUTORES ("
				+ "AUTORID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "AUTOR VARCHAR(255) NOT NULL UNIQUE)";

		String criaGeneros = "CREATE TABLE IF NOT EXISTS GENEROS ("
				+ "GENEROID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "GENERO VARCHAR(255) NOT NULL UNIQUE)";

		String criaLivros = "CREATE TABLE IF NOT EXISTS LIVROS ("
				+ "LIVROID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "TITULO VARCHAR(255) NOT NULL,"
				+ "AUTOR VARCHAR(255) NOT NULL REFERENCES AUTORES(AUTORID),"
				+ "GENERO VARCHAR(255) NOT NULL REFERENCES GENEROS(GENEROID),"
				+ "PAGINAS INTEGER NOT NULL,"
				+ "PUBLICACAO TEXT NOT NULL,"
				+ "UNIQUE(TITULO, AUTOR))";

		try {
			/*PreparedStatement st = this.conn.prepareStatement(criaUsers);
			st.execute();
			st.getConnection().prepareStatement(insereAdmin);
			st.executeUpdate();*/
			PreparedStatement st = this.conn.prepareStatement(criaAutores);
			st.execute();
			st.close();
			st = this.conn.prepareStatement(criaGeneros);
			st.execute();
			st.close();
			st = this.conn.prepareStatement(criaLivros);
			st.execute();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void Desconectar() {
		try {
			this.conn.close();
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
	/*public int validaLogin(String usr, String passwd) {
		Conectar();
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
				Desconectar();
				return log;
			}
			Desconectar();
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			Desconectar();
			return -1;
		}
	}*/

	/**
	 * Função que atualiza um campo do banco de dados no primeiro login do usuário.
	 * @param usuario
	 */
	/*public void atualizaFlagLogin(String usuario) {
		Conectar();
		String query = "UPDATE USERS SET LOGADO = 1 WHERE USERNAME = ?";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setString(1, usuario);
			st.executeUpdate();
			st.close();
			Desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
			Desconectar();
		}
	}*/
	
	/**
	 * Função para cadastrar um novo usuário no banco de dados.
	 * @param usr
	 * @return true caso o cadastro seja bem sucedido, ou false caso ocorra algum erro.
	 */
	/*public boolean cadastraUser(String usr) {
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
	}*/

	/**
	 * Função para remover um usuário do banco.
	 * @param userid
	 * @return true caso a remoção do usuário seja bem sucedida, ou false caso algum erro ocorra.
	 */
	/*public boolean removeUser(int userid) {
		Conectar();
		String query = "DELETE FROM USERS WHERE USERID = ?";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setLong(1, userid);
			st.executeUpdate();
			st.close();
			Desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			Desconectar();
			return false;
		}
	}*/

	/**
	 * Função que altera a senha do usuário.
	 * @param usuario
	 * @param password
	 * @param newPass
	 * @return true caso o update da senha seja bem sucedido, ou false caso algum erro ocorra.
	 */
	/*public boolean alteraSenha(String usuario, String password, String newPass) {
		Conectar();
		String query = "UPDATE USERS SET PASSWD = ? WHERE USERNAME = ? AND PASSWD = ?";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setString(1, newPass);
			st.setString(2, usuario);
			st.setString(3, password);
			int rs = st.executeUpdate();
			if (rs != 0) {
				st.close();
				Desconectar();
				return true;
			}
			st.close();
			Desconectar();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			Desconectar();
			return false;
		}
	}*/
	
	/**
	 * Função para resetar a senha do usuário para a padrão.
	 * @param usuario
	 * @return true se o reset foi bem sucedido, ou false, caso algum erro ocorra.
	 */
	/*public boolean resetaPass(String usuario) {
		Conectar();
		String query = "UPDATE USERS SET PASSWD = ? WHERE USERNAME = ?";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setString(1, usuario);
			st.setString(2, usuario);
			int rs = st.executeUpdate();
			if (rs != 0) {
				st.close();
				Desconectar();
				return true;
			}
			st.close();
			Desconectar();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			Desconectar();
			return false;
		}
	}*/

	/**
	 * Função que retorna a lista de usuários existentes no banco.
	 * @return retorna uma lista de usuários cadastrados no banco de dados.
	 */
	/*public ArrayList<Users> listaUsers() {
		Conectar();
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
			Desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
			Desconectar();
		}
		Desconectar();
		return usuarios;
	}*/

	/**
	 * Função que retorna uma lista de Autores cadastrados
	 * @return ArrayList<Autores>
	 */
	public ArrayList<Autores> listaAutores() {
		Conectar();
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
			Desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
			Desconectar();
		}
		return autores;
	}
	
	/**
	 * Retorna uma lista de generos cadastrados
	 * @return ArrayList<Generos>
	 */
	public ArrayList<Generos> listaGeneros() {
		Conectar();
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
			Desconectar();
		} catch (SQLException e) {
			Desconectar();
			e.printStackTrace();
		}
		return generos;
	}
	
	/**
	 * Retorna uma lista de livros cadastrados.
	 * @return ArrayList<Livros>
	 */
	public ArrayList<Livros> listaLivros() {
		Conectar();
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
				livros.add(livro);
			}
			Desconectar();
		} catch (SQLException e) {
			Desconectar();
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
		Conectar();
		String query = "DELETE FROM GENEROS WHERE GENEROID = ?";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setLong(1, generoid);
			st.executeUpdate();
			st.close();
			Desconectar();
			return true;
		} catch (SQLException e) {
			Desconectar();
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
		Conectar();
		String query = "DELETE FROM LIVROS WHERE LIVROID = ?";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setLong(1, livroid);
			st.executeUpdate();
			st.close();
			Desconectar();
			return true;
		} catch (SQLException e) {
			Desconectar();
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Função para cadastrar um novo livro no banco de dados.
	 * @param usr
	 * @return true caso o cadastro seja bem sucedido, ou false caso ocorra algum erro.
	 */
	public boolean cadastraLivro(String titulo, String autor, String genero, int paginas, String publicacao) {
		Conectar();
		String query = "INSERT INTO LIVROS (LIVROID, TITULO, AUTOR, GENERO, PAGINAS, PUBLICACAO) VALUES (null, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setString(1, titulo.toUpperCase());
			st.setString(2, autor.toUpperCase());
			st.setString(3, genero.toUpperCase());
			st.setInt(4, paginas);
			st.setString(5, publicacao);
			st.executeUpdate();
			st.close();
			Desconectar();
			return true;
		} catch (SQLException e) {
			Desconectar();
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
		Conectar();
		String query = "INSERT INTO AUTORES (AUTORID, AUTOR) VALUES (null, ?)";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setString(1, autor.toUpperCase());
			st.executeUpdate();
			st.close();
			Desconectar();
			return true;
		} catch (SQLException e) {
			Desconectar();
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
		Conectar();
		String query = "INSERT INTO GENEROS (GENEROID, GENERO) VALUES (null, ?)";
		try {
			PreparedStatement st = this.conn.prepareStatement(query);
			st.setString(1, genero.toUpperCase());
			st.executeUpdate();
			st.close();
			Desconectar();
			return true;
		} catch (SQLException e) {
			Desconectar();
			e.printStackTrace();
			return false;
		}
	}
}