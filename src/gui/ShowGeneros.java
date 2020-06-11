package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import bd.Database;
import dao.Generos;

@SuppressWarnings("serial")
public class ShowGeneros extends JDialog {


	private JLabel lblGeneros;
	private JTable tblGeneros;
	private JButton btnDelGenero;
	private JButton btnAddGenero;
	private DefaultTableModel model = new DefaultTableModel();
	private Database db;
	
	public ShowGeneros() {
		this.setTitle("Lista de Generos");
		this.setLayout(null);
		this.setSize(500, 450);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		
		lblGeneros = new JLabel("LISTA DE GENEROS");
		lblGeneros.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblGeneros.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneros.setBounds(140, 10, 180, 30);
		this.add(lblGeneros);
		
		tblGeneros = new JTable(model);
		model.addColumn("GENEROID");
		model.addColumn("GENERO");
		
		db = new Database();
		atualizaTabela();
		
		JScrollPane scroll = new JScrollPane(tblGeneros);
		scroll.setBounds(50, 50, 400, 250);
		this.add(scroll);
		
		btnDelGenero = new JButton("REMOVER GENERO");
		btnDelGenero.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnDelGenero.setBounds(130, 310, 250, 30);
		this.add(btnDelGenero);
		btnDelGenero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int linha = -1; 
				linha = tblGeneros.getSelectedRow();
				if (linha >= 0) {
					int idAutor = (int)tblGeneros.getValueAt(linha, 0);
					boolean removeu = db.removeGenero(idAutor);
					if (removeu) {
						model.removeRow(linha);
						JOptionPane.showMessageDialog(null, "Genero removido com sucesso.");
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
				}
			}
		});
		
		btnAddGenero = new JButton("NOVO GENERO");
		btnAddGenero.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAddGenero.setBounds(130, 350, 250, 30);
		this.add(btnAddGenero);
		btnAddGenero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddGeneros addgenero = new AddGeneros();
				addgenero.setVisible(true);
				atualizaTabela();
			}
		});
	}
	
	public void atualizaTabela() {
		model.setNumRows(0);
		for (Generos u : db.listaGeneros()) {
			model.addRow(new Object[]{u.getIdGenero(), u.getGenero()});
		}
	}
}
