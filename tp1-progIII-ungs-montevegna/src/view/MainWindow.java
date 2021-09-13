package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class MainWindow {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		frame.setTitle("Lights out");
		frame.setBounds(100, 100, 693, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.EAST);

		String[] columnNames = { "1", "2", "3" };

		Boolean[][] data = { { true, false, true }, { false, true, false }, { false, true, false } };

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 30, 126, 35, 59, 0 };
		gbl_panel.rowHeights = new int[] { 14, 163, 48, 154, 23, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		table = new JTable(data, columnNames);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);

		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 3;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.gridx = 1;
		gbc_table.gridy = 1;

		JScrollPane scrollPanel = new JScrollPane(table);
		frame.getContentPane().add(scrollPanel, BorderLayout.CENTER);
		// Frame Size
		frame.setSize(658, 449);
		// Frame Visible = true
		frame.setVisible(true);

		JLabel lblScore = new JLabel("Score:");
		GridBagConstraints gbc_lblScore = new GridBagConstraints();
		gbc_lblScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblScore.gridx = 1;
		gbc_lblScore.gridy = 1;
		panel.add(lblScore, gbc_lblScore);

		JButton btnStart = new JButton("Start");
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 1;
		gbc_btnStart.gridy = 3;
		panel.add(btnStart, gbc_btnStart);
	}

}
