package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import bd.Database;
import dao.Livros;

@SuppressWarnings("serial")
public class ShowLivros  extends JDialog {


	private JLabel lblLivros;
	private JTable tblLivros;
	private JButton btnDelLivro;
	private JButton btnAddLivro;
	private DefaultTableModel model = new DefaultTableModel();
	private Database db;
	
	public ShowLivros() {
		this.setTitle("Lista de Autores");
		this.setLayout(null);
		this.setSize(500, 450);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		
		lblLivros = new JLabel("LISTA DE LIVROS");
		lblLivros.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblLivros.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivros.setBounds(140, 10, 180, 30);
		this.add(lblLivros);
		
		tblLivros = new JTable(model);
		model.addColumn("LIVROID");
		model.addColumn("TITULO");
		model.addColumn("AUTOR");
		model.addColumn("GENERO");
		model.addColumn("PAGINAS");
		model.addColumn("PUBLICACAO");
		
		db = new Database();
		atualizaTabela();
		
		JScrollPane scroll = new JScrollPane(tblLivros);
		scroll.setBounds(50, 50, 400, 250);
		this.add(scroll);
		
		btnDelLivro = new JButton("REMOVER LIVRO");
		btnDelLivro.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnDelLivro.setBounds(130, 310, 250, 30);
		this.add(btnDelLivro);
		btnDelLivro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int linha = -1; 
				linha = tblLivros.getSelectedRow();
				if (linha >= 0) {
					int idLivro = (int)tblLivros.getValueAt(linha, 0);
					boolean removeu = db.removeLivro(idLivro);
					if (removeu) {
						model.removeRow(linha);
						JOptionPane.showMessageDialog(null, "Autor removido com sucesso.");
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
				}
			}
		});
		
		btnAddLivro = new JButton("NOVO LIVRO");
		btnAddLivro.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAddLivro.setBounds(130, 350, 250, 30);
		this.add(btnAddLivro);
		btnAddLivro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddLivros addlivro = new AddLivros();
				addlivro.setVisible(true);
				atualizaTabela();
			}
		});
	}
	
	public void atualizaTabela() {
		model.setNumRows(0);
		for (Livros u : db.listaLivros()) {
			model.addRow(new Object[]{u.getIdLivro(), u.getTitulo(), u.getAutor(), u.getGenero(), u.getPaginas(), u.getPublicacao()});
		}
	}
}
