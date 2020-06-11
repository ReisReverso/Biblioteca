package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import bd.Database;

@SuppressWarnings("serial")
public class ChangePassword extends JDialog {

	private JLabel lblChangePass;
	private JLabel lblSenhaAtual;
	private JPasswordField tfPassAtual;
	private JLabel lblNewPass;
	private JPasswordField tfNewPass;
	private JLabel lblConfNewPass;
	private JPasswordField tfConfNewPass;
	private JButton btnChange;
	
	public ChangePassword(String username) {
		this.setModal(true);
		this.setLayout(null);
		this.setSize(400, 280);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		lblChangePass = new JLabel("MUDAR SENHA");
		lblChangePass.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblChangePass.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangePass.setBounds(125, 10, 150, 30);
		this.add(lblChangePass);
		
		lblSenhaAtual = new JLabel("Senha Atual:");
		lblSenhaAtual.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblSenhaAtual.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenhaAtual.setBounds(30, 50, 150, 30);
		this.add(lblSenhaAtual);
		
		tfPassAtual = new JPasswordField();
		tfPassAtual.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tfPassAtual.setBounds(200, 50, 150, 30);
		this.add(tfPassAtual);
		
		lblNewPass = new JLabel("Nova Senha:");
		lblNewPass.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewPass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewPass.setBounds(30, 100, 150, 30);
		this.add(lblNewPass);
		
		tfNewPass = new JPasswordField();
		tfNewPass.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tfNewPass.setBounds(200, 100, 150, 30);
		this.add(tfNewPass);
		
		lblConfNewPass = new JLabel("Confirme a senha:");
		lblConfNewPass.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblConfNewPass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfNewPass.setBounds(30, 150, 150, 30);
		this.add(lblConfNewPass);
		
		tfConfNewPass = new JPasswordField();
		tfConfNewPass.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tfConfNewPass.setBounds(200, 150, 150, 30);
		this.add(tfConfNewPass);
		
		btnChange = new JButton("ALTERAR SENHA");
		btnChange.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnChange.setBounds(85, 200, 200, 30);
		this.add(btnChange);
		btnChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (new String(tfPassAtual.getPassword()).isEmpty() || new String(tfNewPass.getPassword()).isEmpty() || new String(tfConfNewPass.getPassword()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "As senhas não podem estar vazias.");
				}
				else if (new String(tfNewPass.getPassword()).length() < 5 || new String(tfConfNewPass.getPassword()).length() < 5) {
					JOptionPane.showMessageDialog(null, "A nova senha deve ter pelo menos 5 caracteres.");
					tfNewPass.setText("");
					tfConfNewPass.setText("");
					tfNewPass.requestFocus();
				}
				else if (new String(tfNewPass.getPassword()).equals(new String(tfConfNewPass.getPassword()))) {
					Database db = new Database();
					boolean alterou = db.alteraSenha(username, new String(tfPassAtual.getPassword()), new String(tfNewPass.getPassword()));
					if (alterou) {
						JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "A senha atual inserida não está correta.");
						tfPassAtual.setText("");
						tfPassAtual.requestFocus();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "A senha e a confirmação devem ser iguais.");
					tfNewPass.setText("");
					tfConfNewPass.setText("");
					tfNewPass.requestFocus();
				}
			}
		});
	}
}
