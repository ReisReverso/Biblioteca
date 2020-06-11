package gui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import bd.Database;

@SuppressWarnings("serial")
public class Biblioteca extends JFrame{
	
	private JLabel lblUser;
	private JLabel lblUsr;
	private JButton btnLogout;
	private JButton btnAutores;
	private JButton btnGeneros;
	private JButton btnLivros;
	private JButton btnAddAutor;
	private JButton btnAddGenero;
	private JButton btnAddLivro;
	private Database db;
	
	public Biblioteca(String usr, int logado) {
		this.setTitle("Biblioteca");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JMenuBar bar  = new JMenuBar();
		this.setJMenuBar(bar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		bar.add(mnArquivo);
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mnArquivo.add(mntmLogout);
		mntmLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login login = new Login();
				login.setVisible(true);
			}
		});
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);
		mntmSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenu mnUser = new JMenu("Usuário");
		bar.add(mnUser);
		JMenuItem mntmChangePass = new JMenuItem("Trocar senha");
		mnUser.add(mntmChangePass);
		mntmChangePass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePassword trocasenha = new ChangePassword(lblUsr.getText());
				trocasenha.setVisible(true);
			}
		});
		
		JMenu mnLivros = new JMenu("Livros");
		bar.add(mnLivros);
		JMenuItem mntmAddLivro = new JMenuItem("Novo Livro");
		mnLivros.add(mntmAddLivro);
		mntmAddLivro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddLivros newLivro = new AddLivros();
				newLivro.setVisible(true);
			}
		});
		JMenuItem mntmShowLivros = new JMenuItem("Listar Livros");
		mnLivros.add(mntmShowLivros);
		mntmShowLivros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowLivros livros = new ShowLivros();
				livros.setVisible(true);
			}
		});
		
		JMenu mnAutores = new JMenu("Autores");
		bar.add(mnAutores);
		JMenuItem mntmAddAutor = new JMenuItem("Novo Autor");
		mnAutores.add(mntmAddAutor);
		mntmAddAutor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddGeneros newautor = new AddGeneros();
				newautor.setVisible(true);
			}
		});
		JMenuItem mntmShowAutores = new JMenuItem("Listar Autores");
		mnAutores.add(mntmShowAutores);
		mntmShowAutores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowAutores autores = new ShowAutores();
				autores.setVisible(true);
			}
		});
		
		JMenu mnGeneros = new JMenu("Gêneros");
		bar.add(mnGeneros);
		JMenuItem mntmAddGenero = new JMenuItem("Novo Gênero");
		mnGeneros.add(mntmAddGenero);
		mntmAddGenero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddGeneros newgenero = new AddGeneros();
				newgenero.setVisible(true);
			}
		});
		JMenuItem mntmShowGeneros = new JMenuItem("Listar Gêneros");
		mnGeneros.add(mntmShowGeneros);
		mntmShowLivros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowGeneros generos = new ShowGeneros();
				generos.setVisible(true);
			}
		});
		
		JMenu mnAdmin = new JMenu("Admin");
		bar.add(mnAdmin);
		JMenuItem mntmNewUsr = new JMenuItem("Novo usuário");
		mnAdmin.add(mntmNewUsr);
		mntmNewUsr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddUsers novo = new AddUsers();
				novo.setVisible(true);
			}
		});
		JMenuItem mntmShowUsr = new JMenuItem("Listar Usuários");
		mnAdmin.add(mntmShowUsr);
		mntmShowUsr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowUsers users = new ShowUsers();
				users.setVisible(true);
			}
		});
		
		lblUser = new JLabel("Usuário atual: ");
		lblUser.setBounds(80, 10, 115, 30);
		lblUser.setFont(new Font("Times New Roman", Font.BOLD, 18));
		this.add(lblUser);
		
		lblUsr = new JLabel(usr);
		lblUsr.setBounds(200, 10, 100, 30);
		lblUsr.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		this.add(lblUsr);
		if (!lblUsr.getText().equals("admin")) {
			mnAdmin.setEnabled(false);
		}
		
		btnLogout = new JButton("LOGOUT");
		btnLogout.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnLogout.setBounds(280, 10, 125, 30);
		this.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login login = new Login();
				login.setVisible(true);
			}
		});
		
		btnAutores = new JButton("LISTAR AUTORES");
		btnAutores.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAutores.setBounds(40, 130, 200, 30);
		this.add(btnAutores);
		btnAutores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowAutores autores = new ShowAutores();
				autores.setVisible(true);
			}
		});
		
		btnGeneros = new JButton("LISTAR GÊNEROS");
		btnGeneros.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnGeneros.setBounds(40, 180, 200, 30);
		this.add(btnGeneros);
		btnGeneros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowGeneros generos = new ShowGeneros();
				generos.setVisible(true);
			}
		});
		
		btnLivros = new JButton("LISTAR LIVROS");
		btnLivros.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnLivros.setBounds(40, 230, 200, 30);
		this.add(btnLivros);
		btnLivros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowLivros livros = new ShowLivros();
				livros.setVisible(true);
			}
		});
		
		btnAddAutor = new JButton("NOVO AUTOR");
		btnAddAutor.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAddAutor.setBounds(260, 130, 200, 30);
		this.add(btnAddAutor);
		btnAddAutor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddAutores newAutor = new AddAutores();
				newAutor.setVisible(true);
			}
		});
		
		btnAddGenero = new JButton("NOVO GENERO");
		btnAddGenero.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAddGenero.setBounds(260, 180, 200, 30);
		this.add(btnAddGenero);
		btnAddGenero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddGeneros newgenero = new AddGeneros();
				newgenero.setVisible(true);
			}
		});
		
		btnAddLivro = new JButton("NOVO LIVRO");
		btnAddLivro.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAddLivro.setBounds(260, 230, 200, 30);
		this.add(btnAddLivro);
		btnAddLivro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddLivros newLivros = new AddLivros();
				newLivros.setVisible(true);
			}
		});
		
		verificaPrimeiroLogin(logado);
	}
	
	private void verificaPrimeiroLogin(int logado) {
		if (logado == 0) {
			JOptionPane.showMessageDialog(null, "O sistema identificou que esse é seu primeiro login.\n"
					+ "Para sua segurança recomendamos que altere sua senha através do menu Usuario > Senha.");
			db = new Database();
			db.atualizaFlagLogin(lblUsr.getText());
		}
	}
}
