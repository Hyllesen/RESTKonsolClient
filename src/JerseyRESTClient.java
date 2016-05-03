import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import com.google.gson.Gson;

public class JerseyRESTClient {	
	
	public ArrayList<Item> itemList;
	
	public JerseyRESTClient() throws JSONException, IOException {
		URL url = new URL("http://fbballin.com/v1/items/");
		JSONTokener tokener = new JSONTokener(url.openStream());
		JSONObject root = new JSONObject(tokener);
		Gson gson = new Gson();

		JSONObject object1 = root.getJSONObject("data");
		JSONArray array = object1.getJSONArray("default");
		
		itemList = new ArrayList<Item>();
		
		//Opretter liste med items fra JSONArrayet
		for (int i = 0; i < array.length(); i++) {
			Item item = gson.fromJson(array.get(i).toString(), Item.class);
			itemList.add(item);
		}
	}
	
	public static void main(String[] args) {		
		try {
			JerseyRESTClient client = new JerseyRESTClient();
			client.printMenu();		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void printMenu() {
		System.out.println("Velkommen til Super Simple DDHF Java REST klient!");
		System.out.println("Vælg en kommando:");
		System.out.println("1. List alle genstande");
		
		Scanner scan = new Scanner(System.in);
		String userInput = scan.nextLine();
		switch(userInput) {
		case "1":
			listAllItems();
			break;
		}
		
	}


	private void listAllItems() {
		System.out.println(itemList.size());
		for (int i = 0; i < itemList.size(); i++) {
			System.out.println("---------------------------------");
			System.out.println(itemList.get(i).toString());
		}		
	}


	public class Item {
		private int id;
		private String headline;
		private String description;
		
		public String getDescription() { return description; }
		
		public String getHeadline() { return headline; }
		
		public int getId() { return id; }
		
		public String toString() {
			return "Titel: " + headline + "\n" + 
					"ID: " + id;
		}
		
	}

}
