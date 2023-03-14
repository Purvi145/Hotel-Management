package Hotel;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class TablesOrder extends JFrame {

	String selecteditem;
	
	JComboBox<String> comboBox;
	ArrayList<String> selectedOptions = new ArrayList<String>();
    JCheckBox [ ] c;
	private JFrame frame;
	int r=0;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablesOrder window = new TablesOrder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public TablesOrder() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {
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

		

//	    JScrollPane jsp = new JScrollPane(c);
//		Container con = getContentPane();
//		con.add(jsp);
//	    c.addItemListener(this);

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
		
			
			c = new JCheckBox[100];
			
			while ((eachline = reader.readLine()) != null) {
				String cols[] = eachline.split(",");
				// System.out.println(cols[0]);
				c[r] = new JCheckBox(cols[0]);
			
				c[r].setBounds(6, y, 100, 23);
				frame.getContentPane().add(c[r]);
				c[r].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JCheckBox c = (JCheckBox) e.getSource();
				 		if(c.isSelected()) {
				 			selectedOptions.add(c.getText());
				 			r++;
				 		}
				 			else 
				 			{
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
			public void actionPerformed(ActionEvent arg0) {
				Hashtable<String, ArrayList<String>> h = new Hashtable<String, ArrayList<String>>();
				{
					h.put(selecteditem, selectedOptions);

				}
				Enumeration<String> e = h.keys();
				while (e.hasMoreElements()) {
					String k = e.nextElement();
					ArrayList<String> v = h.get(k);
					System.out.println(k + v);
				}

			}
		});
		submit.setBounds(311, 202, 89, 23);
		frame.getContentPane().add(submit);
	}
}