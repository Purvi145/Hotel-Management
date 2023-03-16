package HotelManagement;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileCounter {
	public static int getCountRows() {
		int c = 0;
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\Purvi\\Documents\\JAVA Classes\\Itemprice.txt"));
			String eachline = "";
			while ((eachline = reader.readLine()) != null) {
				c++;
			}
			//System.out.println(c);
			reader.close();
		} 
		catch (Exception e) {
		}
		return c;
	}
}
