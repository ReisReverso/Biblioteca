package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import bd.Database;

@SuppressWarnings("serial")
public class AddGeneros extends JDialog {

	private JLabel lblAdd;
	private JLabel lblGenero;
	private JTextField tfGenero;
	private JButton btnAdd;
	
	public AddGeneros() {
		this.setModal(true);
		this.setTitle("Criar Genero");
		this.setSize(500,180);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		lblAdd = new JLabel("NOVO GÊNERO");
		lblAdd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setBounds(160, 10, 150, 30);
		this.add(lblAdd);
		
		lblGenero = new JLabel("Gênero: ");
		lblGenero.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblGenero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGenero.setBounds(80, 50, 100, 30);
		this.add(lblGenero);
		
		tfGenero = new JTextField();
		tfGenero.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tfGenero.setBounds(190, 50, 180, 30);
		this.add(tfGenero);
		
		btnAdd = new JButton("NOVO GÊNERO");
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAdd.setBounds(160, 100, 200, 30);
		this.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tfGenero.getText().length() >= 5) {
					cadastraUser(tfGenero.getText());
				}
				else {
					JOptionPane.showMessageDialog(null, "O gênero deve ter no mínimo 5 caracteres.");
				}
			}
		});
	}
	
	public void cadastraUser(String genero) {
		Database db = new Database();
		Boolean cadastrou = db.cadastraGenero(genero);
		if (cadastrou) {
			JOptionPane.showMessageDialog(null, "Gênero cadastrado com sucesso!");
			dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "Esse gênero já existe.");
		}
	}
}
