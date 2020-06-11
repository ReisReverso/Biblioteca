package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.SwingConstants;

import bd.Database;


@SuppressWarnings("serial")
public class Login extends JFrame {
	
	private JLabel lblLogin;
	private JLabel lblInfo;
	private JLabel lblUsr;
	private JLabel lblPasswd;
	private JTextField tfUser;
	private JPasswordField tfPasswd;
	private JButton btnLogin;
	
	public Login() {
		this.setTitle("Login");
		this.setSize(300,200);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		lblLogin = new JLabel("LOGIN");
		lblLogin.setBounds(90, 7, 100, 30);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		this.add(lblLogin);
		
		lblInfo = new JLabel("?");
		lblInfo.setBounds(270, 7, 10, 30);
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblInfo.setToolTipText("ATENÇÃO: SEU NOME DE USUÁRIO DEVE SER DIGITADO EM MINÚSCULO.");
		this.add(lblInfo);
		
		lblUsr = new JLabel("Usuário:");
		lblUsr.setBounds(30, 38, 80, 30);
		lblUsr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsr.setFont(new Font("Times New Roman", Font.BOLD, 16));
		this.add(lblUsr);

		lblPasswd = new JLabel("Senha:");
		lblPasswd.setBounds(30, 76, 80, 30);
		lblPasswd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswd.setFont(new Font("Times New Roman", Font.BOLD, 16));
		this.add(lblPasswd);
		
		tfUser = new JTextField();
		tfUser.setBounds(130, 38, 100, 30);
		tfUser.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		this.add(tfUser);
		tfUser.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					verificaLogin(tfUser.getText(), new String(tfPasswd.getPassword()));
				}
			}
		});
		
		tfPasswd = new JPasswordField();
		tfPasswd.setBounds(130, 78, 100, 30);
		tfPasswd.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		this.add(tfPasswd);
		tfPasswd.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					verificaLogin(tfUser.getText(), new String(tfPasswd.getPassword()));
				}
			}
		});
		
		btnLogin =new JButton("LOGIN");
		btnLogin.setBounds(90, 117, 100, 32);
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		this.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verificaLogin(tfUser.getText(), new String(tfPasswd.getPassword()));
			}
		});
	}
	
	private void verificaLogin(String usuario, String senha) {
		Database db = new Database();
		int logado = db.validaLogin(usuario, senha);
		if (logado != -1) {
			Biblioteca biblio = new Biblioteca(usuario, logado);
			dispose();
			biblio.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "Login ou Senha incorretos.\nVerifique se a tecla CAPS LOCK está ativada e tente novamente.");
		}
	}
}
