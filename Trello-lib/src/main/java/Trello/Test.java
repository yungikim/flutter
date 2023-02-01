package Trello;

import java.util.List;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class Test {
	public static void main(String[] args) {
		Test tr = new Test();
		
		tr.vv();
		
		
	}
	
	private void vv() {
		try {
			String trelloKey = "8f3b25d839f4d1f5949a7672c7e522a2";
			String trelloAccessToken = "011f6a706f524e33cdab2f27db6215573fd038a9ce4331f04e06fb385759e24c";
			Trello trelloApi = new TrelloImpl(trelloKey, trelloAccessToken, new ApacheHttpClient());
			
			String trelloBoardForAddingCardsId = "qcyxfbFD";
			Board board = trelloApi.getBoard(trelloBoardForAddingCardsId);
			
			List<TList> lists = board.fetchLists();
			
//			System.out.println(trelloApi.toString());
			
//			HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/tokens/{token}")
//					  .header("Accept", "application/json")
//					  .queryString("key", "0471642aefef5fa1fa76530ce1ba4c85")
//					  .queryString("token", "9eb76d9a9d02b8dd40c2f3e5df18556c831d4d1fadbe2c45f8310e6c93b5c548")
//					  .asJson();
//
//					System.out.println(response.getBody());
					
//			
//			HttpResponse<String> response = Unirest.get("https://api.trello.com/1/actions/dosa777@gmail.com")
//					  .queryString("key", trelloKey)
//					  .queryString("token", trelloAccessToken)
//					  .asString();
//
//					System.out.println(response.getBody());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
