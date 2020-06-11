package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import bd.Database;
import dao.Autores;

@SuppressWarnings("serial")
public class ShowAutores extends JDialog {

	private JLabel lblAutores;
	private JTable tblAutores;
	private JButton btnDelAutor;
	private JButton btnAddAutor;
	private DefaultTableModel model = new DefaultTableModel();
	private Database db;
	
	public ShowAutores() {
		this.setTitle("Lista de Autores");
		this.setLayout(null);
		this.setSize(500, 450);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		
		lblAutores = new JLabel("LISTA DE AUTORES");
		lblAutores.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAutores.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutores.setBounds(140, 10, 180, 30);
		this.add(lblAutores);
		
		tblAutores = new JTable(model);
		model.addColumn("AUTORID");
		model.addColumn("AUTOR");
		
		db = new Database();
		atualizaTabela();
		
		JScrollPane scroll = new JScrollPane(tblAutores);
		scroll.setBounds(50, 50, 400, 250);
		this.add(scroll);
		
		btnDelAutor = new JButton("REMOVER AUTOR");
		btnDelAutor.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnDelAutor.setBounds(130, 310, 250, 30);
		this.add(btnDelAutor);
		btnDelAutor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int linha = -1; 
				linha = tblAutores.getSelectedRow();
				if (linha >= 0) {
					int idAutor = (int)tblAutores.getValueAt(linha, 0);
					boolean removeu = db.removeAutor(idAutor);
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
		
		btnAddAutor = new JButton("NOVO AUTOR");
		btnAddAutor.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAddAutor.setBounds(130, 350, 250, 30);
		this.add(btnAddAutor);
		btnAddAutor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddAutores addautor = new AddAutores();
				addautor.setVisible(true);
				atualizaTabela();
			}
		});
	}
	
	public void atualizaTabela() {
		model.setNumRows(0);
		for (Autores u : db.listaAutores()) {
			model.addRow(new Object[]{u.getAutorId(), u.getNome()});
		}
	}
}
