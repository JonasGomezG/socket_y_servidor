import java.awt.Color;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Interfaz_Servidor {
	
	private int entradasnormal = 50;
	
	private int entradasvip = 50;
	
	private int entradasextravip = 50;

	private JFrame frame;
	private JTextField cajaNormal;
	private JTextField cajaExtraVIP;
	private JTextField cajaVip;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz_Servidor window = new Interfaz_Servidor();
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
	public Interfaz_Servidor() {
		initialize();
		new Thread(()->{
			try {
				startServer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();;
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		cajaNormal = new JTextField();
		cajaNormal.setBounds(10, 10, 100, 100);
		frame.getContentPane().add(cajaNormal);
		cajaNormal.setColumns(10);
		
		cajaExtraVIP = new JTextField();
		cajaExtraVIP.setColumns(10);
		cajaExtraVIP.setBounds(326, 10, 100, 100);
		frame.getContentPane().add(cajaExtraVIP);
		
		cajaVip = new JTextField();
		cajaVip.setColumns(10);
		cajaVip.setBounds(170, 10, 100, 100);
		frame.getContentPane().add(cajaVip);
		
		JLabel lblNewLabel = new JLabel("Entrada Normal");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 120, 100, 19);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEntradaVip = new JLabel("Entrada VIP");
		lblEntradaVip.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntradaVip.setBounds(170, 120, 100, 19);
		frame.getContentPane().add(lblEntradaVip);
		
		JLabel lblEntradaExtraVip = new JLabel("Entrada Extra VIP");
		lblEntradaExtraVip.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntradaExtraVip.setBounds(326, 120, 100, 19);
		frame.getContentPane().add(lblEntradaExtraVip);
		
		JLabel lblNewLabel_1 = new JLabel("Restantes:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 149, 100, 19);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Restantes:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(170, 149, 100, 19);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Restantes:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(326, 149, 100, 19);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel entradasNormal = new JLabel("");
		entradasNormal.setHorizontalAlignment(SwingConstants.CENTER);
		entradasNormal.setBounds(10, 178, 100, 19);
		frame.getContentPane().add(entradasNormal);
		
		JLabel entradasVIP = new JLabel("");
		entradasVIP.setHorizontalAlignment(SwingConstants.CENTER);
		entradasVIP.setBounds(170, 178, 100, 19);
		frame.getContentPane().add(entradasVIP);
		
		JLabel entradasExtraVIP = new JLabel("");
		entradasExtraVIP.setHorizontalAlignment(SwingConstants.CENTER);
		entradasExtraVIP.setBounds(326, 178, 100, 19);
		frame.getContentPane().add(entradasExtraVIP);
		
		JLabel facturacionTotal = new JLabel("");
		facturacionTotal.setHorizontalAlignment(SwingConstants.CENTER);
		facturacionTotal.setBounds(177, 234, 93, 19);
		frame.getContentPane().add(facturacionTotal);
		
		JLabel lblFacturacin = new JLabel("Facturación:");
		lblFacturacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblFacturacin.setBounds(177, 207, 93, 19);
		frame.getContentPane().add(lblFacturacin);
		

		while(true) {
			entradasNormal.setText(entradasnormal+"");
			entradasVIP.setText(entradasvip+"");
			entradasExtraVIP.setText(entradasExtraVIP+"");
		}
		
	}
	
	private void startServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(123);
		int indiceEspacio;
		int entradas;
		while(true) {
			Socket socket = serverSocket.accept();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String cadena = bufferedReader.readLine();
			if (cadena.contains("Normal")) 
			{
				indiceEspacio = cadena.indexOf(" ");
				String numeroStr = cadena.substring(indiceEspacio + 1);
				entradas = Integer.parseInt(numeroStr);
				cajaNormal.setBackground(Color.RED);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				cajaNormal.setBackground(Color.WHITE);
				entradasnormal = entradasnormal - entradas;
			} 
			else if (cadena.contains("VIP")) 
			{
				indiceEspacio = cadena.indexOf(" ");
				String numeroStr = cadena.substring(indiceEspacio + 1);
				entradas = Integer.parseInt(numeroStr);
				cajaVip.setBackground(Color.RED);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				cajaVip.setBackground(Color.WHITE);
				entradasvip = entradasvip - entradas;
			}
			else if (cadena.contains("ExtraVP")) 
			{
				indiceEspacio = cadena.indexOf(" ");
				String numeroStr = cadena.substring(indiceEspacio + 1);
				entradas = Integer.parseInt(numeroStr);
				cajaExtraVIP.setBackground(Color.RED);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				cajaExtraVIP.setBackground(Color.WHITE);
				entradasextravip = entradasextravip - entradas;
			}
		}
	}
}
