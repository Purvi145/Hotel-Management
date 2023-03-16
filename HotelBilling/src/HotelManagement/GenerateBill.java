package HotelManagement;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;
import java.awt.event.ActionEvent;

public class GenerateBill {

	private JFrame frame;
	JComboBox<String> dropdown = new JComboBox<String>();
	String selected;
	//String totalprice;
	int totalPrice=0;
	String line;
	Hashtable<String, Integer> orderprice = new Hashtable<String, Integer>();
	Hashtable<String, Integer> generatebill = new Hashtable<String, Integer>();
	String selectedTablenum = "";
	String key;
	String name;
	Hashtable<String, ArrayList<String>> tablesorder = null;
	public GenerateBill(Hashtable<String, ArrayList<String>> rv) {
		super();
		tablesorder = rv;
		frame = new JFrame();	
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Table Number");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(42, 52, 96, 22);
		frame.getContentPane().add(lblNewLabel);

	
		Enumeration<String> e = tablesorder.keys();
		 while(e.hasMoreElements())
		 {
			 String key = e.nextElement();
			 dropdown.addItem(key);
		 }

		String lines;
		try (BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\Purvi\\Documents\\JAVA Classes\\Itemprice.txt"))) {
			while ((lines = br.readLine()) != null) {
				String[] values = lines.split("-");
				name = values[0];
				int price = Integer.parseInt(values[1]);
				orderprice.put(name, price);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Enumeration<String> keys = orderprice.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			int value = orderprice.get(key);
		}
		
		//try {
//			BufferedReader reader = new BufferedReader(
//					new FileReader("C:\\Users\\Purvi\\Documents\\JAVA Classes\\GenerateBill.txt"));
//			while ((line = reader.readLine()) != null) {
//				cols = line.split("->");
//				String tablno = cols[0];
//				String[] dish = cols[1].replaceAll("[\\[\\]]", "").split(", ");
//				for (int i = 0; i < dish.length; i++) {
//					// System.out.print(dish[i]);
//				}
			Enumeration<String> e1 = tablesorder.keys();
			 while(e1.hasMoreElements())
			 {
				 String key = e1.nextElement();
				 System.out.println(key);
				 ArrayList<String> value = tablesorder.get(key);
				 System.out.println(value);
				//int totalPrice = 0;
				for (String itemlist:value) {
					totalPrice += orderprice.get(itemlist);//
				}
				// System.out.println("Total price for " + line + ": " + totalPrice);
				generatebill.put(key, totalPrice);
			}
			
	//}
//	catch (Exception e2) {
//
//		}
//		Enumeration<String> e1 = h.keys();
//		while (e1.hasMoreElements()) {
//			String key = e1.nextElement(); // key
//			Integer value = h.get(key); // value
//			// System.out.println(key +" : "+ value);// listing table number and totalprice
//			// associated with it

		//}
		dropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		dropdown.setBounds(144, 53, 137, 22);
		frame.getContentPane().add(dropdown);

		JButton gb = new JButton("Generate Bill");
		gb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedTablenum = (String) dropdown.getSelectedItem();
				Enumeration<String> k = generatebill.keys();
				while (k.hasMoreElements()) {
					key = k.nextElement();
					if (key.equalsIgnoreCase(selectedTablenum)) {
						totalPrice = generatebill.get(key);
						break;// checking which dropdown item is selected and comparing
								// with the table number in the hash table we got from itemprice.txt file
								// if matches the retrieve the total price of that particular table
					}

				}
				PrintWriter p;
				try {
					p = new PrintWriter(
							new FileWriter("C:\\Users\\Purvi\\Documents\\JAVA Classes\\" + selectedTablenum + ".txt"));
					JOptionPane.showMessageDialog(null, "Bill Generated");
					Calendar now = Calendar.getInstance();
					int year = now.get(Calendar.YEAR);
					int month = now.get(Calendar.MONTH) + 1;
					int day = now.get(Calendar.DAY_OF_MONTH);
					int hour = now.get(Calendar.HOUR_OF_DAY);
					int minute = now.get(Calendar.MINUTE);
					int second = now.get(Calendar.SECOND);

					p.println("--------------------------------------------------");
					p.println("Date: " + year + "-" + month + "-" + day);
					p.println("Time: " + hour + ":" + minute + ":" + second);
					p.println("Total bill amount for table " + selectedTablenum + " is " + totalPrice);
					p.println("--------------------------------------------------");
					p.println("            THANK YOU,Visit again :)         ");
					p.close();

				}

				catch (Exception e3) {

				}
			}
		});
		gb.setFont(new Font("Times New Roman", Font.BOLD, 14));
		gb.setBounds(144, 153, 137, 39);
		frame.getContentPane().add(gb);
		frame.setVisible(true);

	}

}