package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import bd.Database;
import dao.Autores;
import dao.Generos;

@SuppressWarnings("serial")
public class AddLivros extends JDialog{

	private JLabel lblAdd;
	private JLabel lblTitulo;
	private JTextField tfTitulo;
	private JLabel lblAutor;
	private JComboBox<String> cbAutor;
	private JLabel lblGenero;
	private JComboBox<String> cbGenero;
	private JLabel lblPaginas;
	private JFormattedTextField tfPag;
	private JLabel lblPublicacao;
	private JFormattedTextField tfPublicacao;
	private JButton btnAdd;
	private Database db = new Database();
	
	public AddLivros() {
		this.setModal(true);
		this.setTitle("Novo Livro");
		this.setSize(500, 280);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		lblAdd = new JLabel("NOVO LIVRO");
		lblAdd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setBounds(160, 10, 150, 30);
		this.add(lblAdd);
		
		lblTitulo = new JLabel("Livro: ");
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitulo.setBounds(20, 50, 60, 30);
		this.add(lblTitulo);
		
		tfTitulo = new JTextField();
		tfTitulo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tfTitulo.setBounds(90, 50, 150, 30);
		this.add(tfTitulo);
		
		lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAutor.setBounds(260, 50, 50, 30);
		this.add(lblAutor);
		
		cbAutor = new JComboBox<String>();
		cbAutor.setBounds(320, 50, 150, 30);
		cbAutor.setBackground(Color.WHITE);
		listaAutores();
		this.add(cbAutor);
		
		lblGenero = new JLabel("Gênero");
		lblGenero.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblGenero.setBounds(20, 100, 60, 30);
		this.add(lblGenero);
		
		cbGenero = new JComboBox<String>();
		cbGenero.setBounds(100, 100, 150, 30);
		cbGenero.setBackground(Color.WHITE);
		listaGeneros();
		this.add(cbGenero);
		
		lblPaginas = new JLabel("Paginas");
		lblPaginas.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPaginas.setBounds(270, 100, 70, 30);
		this.add(lblPaginas);
		
		try {
			MaskFormatter formatpag = new MaskFormatter("###");
			tfPag = new JFormattedTextField(formatpag);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tfPag.setBounds(350, 100, 100, 30);
		this.add(tfPag);
		
		lblPublicacao = new JLabel("Publicação");
		lblPublicacao.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPublicacao.setBounds(20, 150, 100, 30);
		this.add(lblPublicacao);
		
		try {
			MaskFormatter format = new MaskFormatter("##/##/####");
			tfPublicacao = new JFormattedTextField(format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tfPublicacao.setBounds(150, 150, 100, 30);
		this.add(tfPublicacao);
		
		
		btnAdd = new JButton("NOVO LIVRO");
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAdd.setBounds(160, 200, 200, 30);
		this.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tfTitulo.getText().length() >= 5) {
					cadastraLivro(tfTitulo.getText(), cbAutor.getSelectedItem().toString(), cbGenero.getSelectedItem().toString(),
							Integer.parseInt(tfPag.getText()), tfPublicacao.getText());
				}
				else {
					JOptionPane.showMessageDialog(null, "O nome do livro deve ter no mínimo 5 caracteres.");
				}
			}
		});
	}
	
	public void cadastraLivro(String titulo, String autor, String genero, int paginas, String publicacao) {
		Database db = new Database();
		Boolean cadastrou = db.cadastraLivro(titulo, autor, genero, paginas, publicacao);
		if (cadastrou) {
			JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
			dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "Esse livro já está cadastrado.");
		}
	}
	
	public void listaGeneros() {
		for (Generos  g: db.listaGeneros()) {
			cbGenero.addItem(g.getGenero());
		}
	}
	
	public void listaAutores() {
		for (Autores a : db.listaAutores()) {
			cbAutor.addItem(a.getNome());
		}
	}
}
