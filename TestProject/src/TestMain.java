import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nunovieira.HTTPRequest;

public class TestMain {

	public static void main(String[] args) {
		String url = "http://192.168.1.3:8080/nat-create";
		Map<String, String> textArgs = new HashMap<String, String>();
		textArgs.put("api_token", "MAIlGopQUt");
		Map<String, String> fileArgs = new HashMap<String, String>();
		textArgs.put("file", "/Users/nunovieira/Desktop/Tese/Constituicao-pt.es.tmx");
		System.out.println(HTTPRequest.sendRequest(url, textArgs, fileArgs, "POST"));
	}

}
