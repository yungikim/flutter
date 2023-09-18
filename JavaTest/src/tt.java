import java.util.HashMap;
import java.util.Map;

import com.dit.jco.ConncetorSapManager;
import com.dit.jco.JCoService;

public class tt {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated constructor stub
				tt tt = new tt();
				tt.test2();
				
//				String st = "20220107";
//				String et = "20221101";
//				
//				Date time = new Date();	 //현재 날짜				
//				SimpleDateFormat simpleDate2 = new SimpleDateFormat("yyyyMMdd"); //날짜 포멧	
//				String start = simpleDate2.format(time.getTime()); 
//				System.out.println(start);
//				
//				if (Integer.parseInt(start) >= Integer.parseInt(st) && Integer.parseInt(start) <= Integer.parseInt(et)) {
//					System.out.println("OK");
//				}
	}


	
	private void test2() {
		try {
	        String jcoClient = "100";
	        String jcoGroup = "PES";
	        String jcoLang = "KO";
	        String jcoMShost = "192.168.14.200";
	        String jcoMSserv = "";
	        String jcoR3name = "PES";
	        
	        String jcoUser = "epjco";
	        String jcoPasswd = "daesang1!";
	        
	        String functionName = "ZFI_GEP_CARD_GET_ZFITCD01_CNT";    
	        
	        ConncetorSapManager sapManager = new ConncetorSapManager(jcoClient, jcoGroup, jcoLang, jcoMShost, jcoMSserv, jcoR3name, jcoUser, jcoPasswd);
	        JCoService jcoService = new JCoService();
	        jcoService.setSapMan(sapManager);
	        
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("I_BUKRS", "1000");
	        params.put("I_PERNR", "958166");
	        Map<String, Object> result = jcoService.execute(functionName, params);

	        String count = "";
	        if (result != null && result.size() > 0) {      	
				Map<String, Object> map = (Map<String, Object>)result.get("initialized");		
				count =  map.get("E_CNT").toString();
				String subrc = map.get("E_SUBRC").toString();
				if (subrc.equals("4")) {
					count = "0";
				}
					        	
	        }
	        System.out.println(count);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
