package hometask.one;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			WriteLinkFromURLtoFile.getLinkList("https://www.w3schools.com/", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			CheckSiteAvailability.checkLinks("WebAddressesTemp.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}