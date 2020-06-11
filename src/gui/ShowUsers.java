package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import bd.Database;
import dao.Users;

@SuppressWarnings("serial")
public class ShowUsers extends JDialog {
	
	private JLabel lblUsers;
	private JTable tblUsers;
	private JButton btnDelUser;
	private JButton btnResetPass;
	private JButton btnAddUser;
	private DefaultTableModel model = new DefaultTableModel();
	private Database db;
	
	public ShowUsers() {
		this.setTitle("Lista de Usuários");
		this.setLayout(null);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		
		lblUsers = new JLabel("LISTA DE USUÁRIOS");
		lblUsers.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsers.setBounds(140, 10, 180, 30);
		this.add(lblUsers);
		
		tblUsers = new JTable(model);
		model.addColumn("USERID");
		model.addColumn("USERNAME");
		
		db = new Database();
		atualizaTabela();
		
		JScrollPane scroll = new JScrollPane(tblUsers);
		scroll.setBounds(50, 50, 400, 250);
		this.add(scroll);
		
		btnDelUser = new JButton("REMOVER USUARIO");
		btnDelUser.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnDelUser.setBounds(130, 310, 250, 30);
		this.add(btnDelUser);
		btnDelUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int linha = -1; 
				linha = tblUsers.getSelectedRow();
				if (linha >= 0) {
					int idUser = (int)tblUsers.getValueAt(linha, 0);
					boolean removeu = db.removeUser(idUser);
					if (removeu) {
						model.removeRow(linha);
						JOptionPane.showMessageDialog(null, "Usuário removido com sucesso.");
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
				}
			}
		});
		
		btnResetPass = new JButton("RESETAR SENHA");
		btnResetPass.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnResetPass.setBounds(130, 350, 250, 30);
		this.add(btnResetPass);
		btnResetPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = -1; 
				linha = tblUsers.getSelectedRow();
				if (linha >= 0) {
					String username = (String)tblUsers.getValueAt(linha, 1);
					boolean reset = db.resetaPass(username);
					if (reset) {
						JOptionPane.showMessageDialog(null, "A senha foi resetada para a padrão.");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha");
				}
			}
		});
		
		btnAddUser = new JButton("NOVO USUÁRIO");
		btnAddUser.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAddUser.setBounds(130, 390, 250, 30);
		this.add(btnAddUser);
		btnAddUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddUsers adduser = new AddUsers();
				adduser.setVisible(true);
				atualizaTabela();
			}
		});
	}
	
	public void atualizaTabela() {
		model.setNumRows(0);
		for (Users u : db.listaUsers()) {
			model.addRow(new Object[]{u.getUserid(), u.getUsername()});
		}
	}
}
