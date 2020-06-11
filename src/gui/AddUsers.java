package gui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import bd.Database;

@SuppressWarnings("serial")
public class AddUsers extends JDialog {
	
	private JLabel lblAdd;
	private JLabel lblUser;
	private JTextField tfUser;
	private JButton btnAdd;
	
	public AddUsers() {
		this.setModal(true);
		this.setTitle("Criar Usuário");
		this.setSize(500,180);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		lblAdd = new JLabel("CRIAR USUÁRIO");
		lblAdd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setBounds(160, 10, 150, 30);
		this.add(lblAdd);
		
		lblUser = new JLabel("Usuário: ");
		lblUser.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUser.setBounds(80, 50, 100, 30);
		this.add(lblUser);
		
		tfUser = new JTextField();
		tfUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tfUser.setBounds(190, 50, 180, 30);
		this.add(tfUser);
		
		btnAdd = new JButton("CRIAR USUÁRIO");
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAdd.setBounds(160, 100, 200, 30);
		this.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tfUser.getText().length() >= 5) {
					cadastraUser(tfUser.getText());
				}
				else {
					JOptionPane.showMessageDialog(null, "O nome de usuário deve ter no mínimo 5 caracteres.");
				}
			}
		});
	}
	
	public void cadastraUser(String usuario) {
		Database db = new Database();
		Boolean cadastrou = db.cadastraUser(usuario);
		if (cadastrou) {
			JOptionPane.showMessageDialog(null, "Usuário Cadastrado com sucesso!");
			dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "Já existe alguém com esse usuário");
		}
	}
}
