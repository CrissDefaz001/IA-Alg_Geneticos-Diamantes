package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ag.DiamondOptimizacion;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JSpinner;

public class AppGUI implements ActionListener {
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JTextField txtCarat;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JButton btnFind;
	private JLabel lblWhatIsYour;
	private JTextField txtBudget;
	private JTextArea txaDiamond;
	private JScrollPane scrollPane;
	private JComboBox<String> comboClarity, comboColor, comboCut;
	private String[] clarity = { "IF", "VVS1", "VVS2", "VS1", "VS2", "SI12", "SI2", "I1" },
			cut = { "Fair", "Good", "Very_Good", "Premium", "Ideal" }, color = { "D", "E", "F", "G", "H", "I", "J" };
	private JSpinner spinnerNum;
	private JLabel lblNumDeDiamantes;

	public AppGUI() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		JFrame jf = new JFrame(); // habilita una ventana
		jf.setTitle("Diamond explorer");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Quita recursos al cerrar la app
		jf.setSize(535, 500);
		jf.getContentPane().setLayout(null);

		/* ============== */
		label = new JLabel("Color:");
		label.setBounds(20, 47, 70, 25);
		jf.getContentPane().add(label);
		/* ============== */
		label_1 = new JLabel("Carat:");
		label_1.setBounds(20, 116, 70, 25);
		jf.getContentPane().add(label_1);
		/* ============== */
		label_2 = new JLabel("Clarity:");
		label_2.setBounds(20, 83, 67, 25);
		jf.getContentPane().add(label_2);
		/* ============== */
		label_3 = new JLabel("Cut: ");
		label_3.setBounds(20, 153, 70, 25);
		jf.getContentPane().add(label_3);
		/* ============== */
		comboClarity = new JComboBox<String>();
		comboClarity.setBounds(97, 83, 150, 25);
		jf.getContentPane().add(comboClarity);
		/* ============== */
		comboColor = new JComboBox<String>();
		comboColor.setBounds(97, 47, 150, 25);
		jf.getContentPane().add(comboColor);
		/* ============== */
		comboCut = new JComboBox<String>();
		comboCut.setBounds(97, 153, 150, 25);
		jf.getContentPane().add(comboCut);
		/* ============== */
		txtCarat = new JTextField();
		txtCarat.setColumns(10);
		txtCarat.setBounds(97, 118, 150, 25);
		jf.getContentPane().add(txtCarat);
		/* ============== */
		label_4 = new JLabel("4 - C", SwingConstants.CENTER);
		label_4.setOpaque(true);
		label_4.setBackground(Color.LIGHT_GRAY);
		label_4.setBounds(20, 16, 480, 20);
		jf.getContentPane().add(label_4);
		/* ============== */
		label_5 = new JLabel("D (Best)  --> E > F > G > I -->  J (Worst)");
		label_5.setBounds(277, 45, 220, 25);
		jf.getContentPane().add(label_5);
		/* ============== */
		label_6 = new JLabel("IF (Best)  -->  I1 (Worst)");
		label_6.setBounds(277, 81, 220, 25);
		jf.getContentPane().add(label_6);
		/* ============== */
		label_7 = new JLabel("From 0.2  -->  to 5.01");
		label_7.setBounds(277, 114, 220, 25);
		jf.getContentPane().add(label_7);
		/* ============== */
		btnFind = new JButton("Find best diamonds");
		btnFind.setBounds(370, 186, 130, 30);
		btnFind.addActionListener(this);
		jf.getContentPane().add(btnFind);
		/* ============== */
		lblWhatIsYour = new JLabel("What is your budget?    $");
		lblWhatIsYour.setBounds(20, 189, 130, 25);
		jf.getContentPane().add(lblWhatIsYour);
		/* ============== */
		txtBudget = new JTextField();
		txtBudget.setColumns(10);
		txtBudget.setBounds(150, 191, 100, 25);
		jf.getContentPane().add(txtBudget);
		/* ============== */
		/* ============== */
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 230, 480, 215);
		jf.getContentPane().add(scrollPane);
		txaDiamond = new JTextArea();
		txaDiamond.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txaDiamond.setEditable(false);
		scrollPane.setViewportView(txaDiamond);

		/* ============== */
		spinnerNum = new JSpinner();
		spinnerNum.setBounds(319, 190, 40, 25);
		jf.getContentPane().add(spinnerNum);
		/* ============== */
		lblNumDeDiamantes = new JLabel("Quantity:");
		lblNumDeDiamantes.setBounds(266, 191, 55, 25);
		jf.getContentPane().add(lblNumDeDiamantes);

		jf.setLocationRelativeTo(null);
		jf.setVisible(true);

		llenarCombo(comboClarity, clarity);
		llenarCombo(comboColor, color);
		llenarCombo(comboCut, cut);

	}

	public void llenarCombo(JComboBox<String> jc, String[] data) {
		// jc.removeAllItems();
		for (String datum : data) {
			jc.addItem(datum.toString());
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnFind) {
			int a = (int) spinnerNum.getValue();
			if (txtCarat.getText().equals("") || txtBudget.getText().equals("") || a <= 0) {
				JOptionPane.showMessageDialog(null, "Campos obligatorios vacíos", "No calculable",
						JOptionPane.WARNING_MESSAGE);
			} else {

				if (txtCarat.getText().matches("[0-9]+\\.*[0-9]*") && txtBudget.getText().matches("[0-9]+")) {

					double c = Double.parseDouble(txtCarat.getText());
					double b = Double.parseDouble(txtBudget.getText());

					if ((c >= 0.2 && c <= 5.01) && b >= 326) {
						int cantidad = (int) spinnerNum.getValue(); // numero de diamantes deseados
						ArrayList<String> criterios = new ArrayList<String>();
						criterios.add(comboClarity.getSelectedItem().toString()); // clarity
						criterios.add(comboColor.getSelectedItem().toString()); // color
						criterios.add(comboCut.getSelectedItem().toString()); // cut
						criterios.add(txtCarat.getText()); // carat
						criterios.add(txtBudget.getText()); // presupuesto
						// limpiar textarea
						txaDiamond.setText("");
						txaDiamond.append("Presupuesto: $ " + txtBudget.getText() + "\n");
						new DiamondOptimizacion().inicializar(10, criterios, cantidad, txaDiamond);
						// limpiar lista para otra iteracion
						criterios.clear();
					} else {
						JOptionPane.showMessageDialog(null, "Rango de carat: 0.2 - 5.01\nRango de budget: > $ 326",
								"Fuera de rango", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese solamente valores numéricos para 'carat' y 'budget' ",
							"Datos no válidos", JOptionPane.WARNING_MESSAGE);
				}
			}

		}
	}
}
