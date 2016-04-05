package cx.lehmann.vertx;

import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.logging.Logger;

import io.vertx.core.Vertx;

public class RestUrl {

  private static final Logger log = LoggerFactory.getLogger(RestUrl.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    String restAPIActualEndPoint = "http://services.groupkt.com/country/get/iso2code/IN";

    HttpClient client = vertx.createHttpClient();

    HttpClientRequest c_req = client.requestAbs(HttpMethod.GET, restAPIActualEndPoint, restApiResponse -> {

      log.info("API response: " + restApiResponse.statusCode());

      restApiResponse.handler(data -> {
        log.info("Response Data : " + data.toString());
      });

    });

    c_req.end();
  }

}
