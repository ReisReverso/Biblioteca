package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import bd.Database;

@SuppressWarnings("serial")
public class AddAutores extends JDialog {

	private JLabel lblAdd;
	private JLabel lblAutor;
	private JTextField tfAutor;
	private JButton btnAdd;
	
	public AddAutores() {
		this.setModal(true);
		this.setTitle("Novo Autor");
		this.setSize(500,180);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		lblAdd = new JLabel("NOVO AUTOR");
		lblAdd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setBounds(160, 10, 150, 30);
		this.add(lblAdd);
		
		lblAutor = new JLabel("Autor: ");
		lblAutor.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutor.setBounds(80, 50, 100, 30);
		this.add(lblAutor);
		
		tfAutor = new JTextField();
		tfAutor.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tfAutor.setBounds(190, 50, 180, 30);
		this.add(tfAutor);
		
		btnAdd = new JButton("NOVO AUTOR");
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAdd.setBounds(160, 100, 200, 30);
		this.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tfAutor.getText().length() >= 5) {
					cadastraAutor(tfAutor.getText());
				}
				else {
					JOptionPane.showMessageDialog(null, "O nome do autor deve ter no mínimo 5 caracteres.");
				}
			}
		});
	}
	
	public void cadastraAutor(String autor) {
		Database db = new Database();
		Boolean cadastrou = db.cadastraAutor(autor);
		if (cadastrou) {
			JOptionPane.showMessageDialog(null, "Autor cadastrado com sucesso!");
			dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "Esse autor já está cadastrado.");
		}
	}
}
