package hometask.one;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.net.URLConnection;

public class WriteLinkFromURLtoFile {

	String spec;
	String coding = "UTF-8";

	public WriteLinkFromURLtoFile() {
		super();
	}

	public WriteLinkFromURLtoFile(URL url, String spec, String coding) {
		super();
		this.spec = spec;
		this.coding = coding;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}

	public static void getLinkList(String spec, String coding) throws IOException {
		URL url = new URL(spec);
		URLConnection connection = url.openConnection();
		File file = new File("WebAddresses.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), coding));
				FileWriter fw = new FileWriter(file, true);) {
			String tempString = "";
			while (true) {
				tempString = br.readLine();
				if (tempString == null) {
					break;
				} else {
					if (tempString.contains("http:") || tempString.contains("https:")) {
						String subString = tempString.substring(tempString.indexOf("http"), tempString.length());

						if (subString.indexOf("\"") > -1) {
							subString = subString.substring(0, subString.indexOf("\""));
						}
						if (subString.indexOf("\'") > -1) {
							subString = subString.substring(0, subString.indexOf("\'"));
						}
						if (!subString.contains("#") && !subString.contains("javascript")) {
							fw.write(subString + System.lineSeparator());
						}
					}
				}
			}
		}
	}

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