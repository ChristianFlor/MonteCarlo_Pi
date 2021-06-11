package interfazUsuario;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class Interfaz extends JFrame {

	private JLabel lblSeed, lblPoints, lblPi

	/**
	 * Create the frame
	 */
	public Interfaz() {

		setTitle("Simulador Pi Montecarlo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 430);
		setLocationRelativeTo(null);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(15, 30, 393, 145);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(true);

		lblSeed = new JLabel("Semilla");
		lblSeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeed.setBounds(10, 11, 118, 14);
		panel.add(lblSeed);

		lblPoints = new JLabel("Puntos");
		lblPoints.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoints.setBounds(138, 11, 245, 14);
		panel.add(lblPoints);

		lblPi = new JLabel("Pi");
		lblPi.setHorizontalAlignment(SwingConstants.CENTER);
		lblPi.setBounds(138, 67, 245, 14);
		panel.add(lblPi);
	}

	public void setInfo(String seed, String points, String pi){
		lblSeed.setText("Semilla: "+seed);
		lblPoints.setText("Puntos: "+points);
		lblPi.setText("Pi: "+pi);
	}
}
