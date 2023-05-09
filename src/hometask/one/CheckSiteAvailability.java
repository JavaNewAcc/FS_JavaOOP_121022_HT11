package hometask.one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class CheckSiteAvailability {
	public static void checkLinks(String fileName) throws IOException {
		File fileIn = new File(fileName);
		File fileOut = new File("SiteAvailability.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(fileIn));
				FileWriter fw = new FileWriter(fileOut, true)) {
			while (true) {
				String spec = br.readLine();
				if (spec != null) {
					URL url = new URL(spec);
					URLConnection connection = url.openConnection();
					fw.write(connection.getHeaderField(0) + " for " + spec + System.lineSeparator());

				} else {
					break;
				}
			}
		}
	}
}
