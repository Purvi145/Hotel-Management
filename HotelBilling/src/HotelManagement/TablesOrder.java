package HotelManagement;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class TablesOrder extends JFrame {

	String selecteditem;
	JComboBox<String> comboBox;
	ArrayList<String> selectedOptions = new ArrayList<String>();
	JCheckBox[] c;
	private JFrame frame;
	int r = 0;

	Hashtable<String, ArrayList<String>> h = new Hashtable<String, ArrayList<String>>();

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TablesOrder window = new TablesOrder();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

//	public TablesOrder() throws Exception {
//		initialize();
//	}

	public TablesOrder() throws Exception {
		super("Tableorder");
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Table Number");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setBounds(10, 45, 117, 21);
		frame.getContentPane().add(lblNewLabel);

		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecteditem = comboBox.getSelectedItem().toString();
			}
		});

		String line;
		try {
			BufferedReader r = new BufferedReader(
					new FileReader("C:\\Users\\Purvi\\Documents\\JAVA Classes\\TableDetails.txt"));
			while ((line = r.readLine()) != null) {

				comboBox.addItem(line);
			}

			r.close();
			comboBox.setBounds(149, 45, 136, 22);
			frame.getContentPane().add(comboBox);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String eachline;
		int y = 85;
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\Purvi\\Documents\\JAVA Classes\\Itemprice.txt"));

			c = new JCheckBox[FileCounter.getCountRows()];
			while ((eachline = reader.readLine()) != null) {
				String cols[] = eachline.split("-");
				// System.out.println(cols[0]);
				c[r] = new JCheckBox(cols[0]);
				c[r].setBounds(6, y, 150, 23);
				frame.getContentPane().add(c[r]);
				c[r].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JCheckBox c = (JCheckBox) e.getSource();
						if (c.isSelected()) {
							selectedOptions.add(c.getText());
							r++;
						} else {
							selectedOptions.remove(c.getText());
						}

					}
				});
				y += 20;

			}
			reader.close();

		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {// hashtable

				h.put(selecteditem, selectedOptions);

				//System.out.println(h);
				Enumeration<String> e = h.keys();
				while (e.hasMoreElements()) {
					String k = e.nextElement();
					ArrayList<String> v = h.get(k);
					// System.out.println(c.getText());
					// System.out.println(k + v);
				 
					GenerateBill g = new GenerateBill(h);

				}

			}
		});
		submit.setBounds(311, 202, 89, 23);
		frame.getContentPane().add(submit);
		frame.setVisible(true);
	}

}