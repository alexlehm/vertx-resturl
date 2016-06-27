package cx.lehmann.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.ProxyOptions;

public class ProxyRequestVerticle extends AbstractVerticle {

  private static final Logger log = LoggerFactory.getLogger(ProxyRequestVerticle.class);

  private Vertx vertx = Vertx.vertx();

  public void start() {
    log.info("test starting");

    NetClientOptions options = new NetClientOptions().setSsl(true)
        .setProxyOptions(new ProxyOptions().setHost("localhost").setPort(3128));

    NetClient client = vertx.createNetClient(options);

    client.connect(443, "www.google.de", ar -> {
      if (ar.succeeded()) {
        log.info("succeeded");
      } else {
        log.error("error", ar.cause());
      }
    });
  }

//  public void start() {
//    log.info("test starting");
//
//    HttpClientOptions options = new HttpClientOptions().setSsl(true).setDefaultPort(443).setDefaultHost("www.google.de")
//        .setProxyOptions(new ProxyOptions().setHost("localhost").setPort(3128));
//
//    HttpClient client = vertx.createHttpClient(options);
//
//    client.get("/", resp -> {
//      log.info("response code: " + resp.statusCode());
//      resp.bodyHandler(data -> {
//        log.info("body text: " + data.toString());
//      });
//      resp.exceptionHandler(th -> log.error("Exception", th));
//      resp.endHandler(v -> log.info("request finished"));
//    }).exceptionHandler(th -> log.error("Exception", th)).end();
//  }

}
