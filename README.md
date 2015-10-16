HTTPRequests is a small project with the objective to make HTTP requests way easier for a Java user when connecting to, for example, a REST server. It is basically a jar file that can be imported to a Java project providing a function capable of sending a [GET, POST, PUT, DELETE] requests.

## Usage

To use the jar file, it is only required to import it to the project as a referenced library. The function it provides are:

- sendRequest(String url, Map<String, String> textArgs, Map<String, String> fileArgs, String httpMethod);

	- String url: The url to where the request will be sent;
    - Map<String, String> textArgs: A map with the text parameters to send. Keys are the name of the parameters, values are their value;
    - Map<String, String> textArgs: A map with the file parameters to send. Keys are the name of the parameters, values are their value;
    - String httpMethod: The HTTP Method desired. GET, POST, PUT and DELETE are the possible choices;


## History

The idea appeared when I had the need to test a REST API using a OOP language. Considering the development of HTTP Requests not as simple as I think it should be, the project tries to solve it.

## Credits

Nuno Vieira <nunovieira220>