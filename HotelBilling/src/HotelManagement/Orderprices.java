package HotelManagement;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Orderprices {



		private JFrame frame;
		private JTextField item;
		private JTextField price;

		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Orderprices window = new Orderprices();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		public Orderprices() {
			initialize();
		}
		private void initialize() {
			frame = new JFrame();
			frame.setBounds(100, 100, 450, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JLabel lblNewLabel = new JLabel("ITEM");
			lblNewLabel.setBounds(21, 44, 70, 14);
			frame.getContentPane().add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("PRICE");
			lblNewLabel_1.setBounds(21, 89, 46, 14);
			frame.getContentPane().add(lblNewLabel_1);
			
			item = new JTextField();
			item.setBounds(133, 41, 164, 26);
			frame.getContentPane().add(item);
			item.setColumns(10);
			price = new JTextField();
			price.setBounds(133, 86, 164, 26);
			frame.getContentPane().add(price);
			price.setColumns(10);
			
			JButton btnNewButton = new JButton("SUBMIT");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String items = item.getText();
					String Prices= price.getText();
					
					PrintWriter p;
					try {
						p=new PrintWriter
								(new FileWriter("C:\\Users\\Purvi\\Documents\\JAVA Classes\\Itemprice.txt",true));
						p.println(items.trim() + "-" + Prices.trim());
						p.close();
					
						JOptionPane.showMessageDialog(null, "Submitted successfully");
					
						item.setText("");
						price.setText("");
						
					}
					
					catch (Exception e) {
						e.printStackTrace();
					}
			
				}

			});
			btnNewButton.setBounds(93, 162, 89, 23);
			frame.getContentPane().add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("NEXT");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						TablesOrder t = new TablesOrder();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				}
			});
			btnNewButton_1.setBounds(269, 162, 89, 23);
			frame.getContentPane().add(btnNewButton_1);
		}
	}
