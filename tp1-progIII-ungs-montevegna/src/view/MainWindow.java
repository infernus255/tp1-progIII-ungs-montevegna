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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.BoardController;
import dto.UpdateLightBoardDto;
import service.BoardService;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JTable table;
	private BoardController boardController;
	private BoardService boardService;
	private int movesCount = 0;

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

		// TODO : dynamic matrix
		boardService = new BoardService(4);
		boardController = new BoardController(boardService);

		String[] columnNames = { "0", "1", "2", "3" };

		Boolean[][] data = boardController.get();

		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 30, 126, 35, 59, 0 };
		gbl_panel.rowHeights = new int[] { 14, 163, 48, 154, 23, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblMoves = new JLabel("Moves: " + movesCount);
		lblMoves.setEnabled(false);
		GridBagConstraints gbc_lblMoves = new GridBagConstraints();
		gbc_lblMoves.insets = new Insets(0, 0, 5, 5);
		gbc_lblMoves.gridx = 1;
		gbc_lblMoves.gridy = 1;
		panel.add(lblMoves, gbc_lblMoves);

		table = new JTable(tableModel);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateLightBoardDto light = new UpdateLightBoardDto();
				light.row = table.getSelectedRow();
				light.col = table.getSelectedColumn();

				if (light.row > -1 && light.col > -1) {
					boardController.updateLight(light);
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel = new DefaultTableModel(boardController.get(), columnNames) {
						private static final long serialVersionUID = 1L;

						@Override
						public boolean isCellEditable(int row, int col) {
							return false;
						}
					};
					table.setModel(tableModel);
					lblMoves.setText("Moves: " + ++movesCount);
					if (boardController.win()) {
						table.setEnabled(false);
						String respuesta = JOptionPane.showInputDialog("Felicidades, ganaste, escribe tu nombre: ");
					}
				}
			}
		});
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

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(true);
				btnStart.setEnabled(false);
			}
		});
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 1;
		gbc_btnStart.gridy = 3;
		panel.add(btnStart, gbc_btnStart);
	}

}
