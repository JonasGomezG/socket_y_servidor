import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

public class Interfaz_Cliente {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz_Cliente window = new Interfaz_Cliente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz_Cliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Entrada Normal = 10€");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 121, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEntradaExtraVip = new JLabel("Entrada Extra VIP = 50€");
		lblEntradaExtraVip.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntradaExtraVip.setBounds(272, 10, 154, 25);
		frame.getContentPane().add(lblEntradaExtraVip);
		
		JLabel lblEntradaVip = new JLabel("Entrada VIP = 30€");
		lblEntradaVip.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntradaVip.setBounds(156, 10, 121, 25);
		frame.getContentPane().add(lblEntradaVip);
		
		JRadioButton entradaNormal = new JRadioButton("");
		buttonGroup.add(entradaNormal);
		entradaNormal.setBounds(52, 37, 21, 25);
		frame.getContentPane().add(entradaNormal);
		
		JRadioButton entradaVIP = new JRadioButton("");
		buttonGroup.add(entradaVIP);
		entradaVIP.setBounds(205, 37, 21, 25);
		frame.getContentPane().add(entradaVIP);
		
		JRadioButton entradaExtraVIP = new JRadioButton("");
		buttonGroup.add(entradaExtraVIP);
		entradaExtraVIP.setBounds(317, 37, 21, 25);
		frame.getContentPane().add(entradaExtraVIP);
		
		JLabel lblCantidad = new JLabel("0");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setBounds(182, 118, 79, 30);
		frame.getContentPane().add(lblCantidad);
		
		JButton btnMenos = new JButton("-");
		btnMenos.setBounds(129, 118, 50, 30);
		frame.getContentPane().add(btnMenos);
		
		JButton btnMas = new JButton("+");
		btnMas.setBounds(259, 118, 50, 30);
		frame.getContentPane().add(btnMas);
		
		JButton btnNewButton_2 = new JButton("Comprar");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(156, 182, 121, 36);
		frame.getContentPane().add(btnNewButton_2);
		
		btnMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numero = Integer.parseInt(lblCantidad.getText());
				int resultado = numero - 1;
				if (resultado < 0) {
					lblCantidad.setText("0");
				} else {
					lblCantidad.setText(resultado+"");
				}
			}
		});
		
		btnMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numero = Integer.parseInt(lblCantidad.getText());
				int resultado = numero + 1;
				if (resultado > 3) {
					lblCantidad.setText("3");
				} else {
					lblCantidad.setText(resultado+"");
				}
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(entradaNormal.isSelected()) {
					try {
						Socket socket = new Socket("localhost",123);
						OutputStream outputStream = socket.getOutputStream();
						outputStream.write(("Normal "+ lblCantidad +"\n").getBytes());
						outputStream.flush();
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else if (entradaExtraVIP.isSelected()) {
					try {
						Socket socket = new Socket("localhost",123);
						OutputStream outputStream = socket.getOutputStream();
						outputStream.write(("VIP "+ lblCantidad +"\n").getBytes());
						outputStream.flush();
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else if (entradaExtraVIP.isSelected()) {
					try {
						Socket socket = new Socket("localhost",123);
						OutputStream outputStream = socket.getOutputStream();
						outputStream.write(("ExtraVP "+ lblCantidad +"\n").getBytes());
						outputStream.flush();
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		/*
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					Socket socket = new Socket("localhost",123);
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write((textField.getText()+"\n").getBytes());
					outputStream.flush();
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});*/
	}
}
