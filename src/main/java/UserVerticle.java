import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * Copyright by Intelin.
 * Creator: Nguyen Ngoc Chau
 * Date: 6/13/19
 * Time: 10:52 AM
 */
public class UserVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        System.out.println("Start user verticle success");
        HttpServer server = vertx.createHttpServer(new HttpServerOptions().setHost("localhost").setPort(9900));
        //User API declare
        Router router = Router.router(vertx);
        //Handle post API
        router.put().handler(context -> {
            context.request().bodyHandler(buffer -> {
                System.out.println("Receive by user" + buffer);
                System.out.println("Response: Hello this is user services");
                context.response()
                        .putHeader("content-type", "text/plain")
                        .end("Hello this is user services");
            });
        });
        server.requestHandler(router);
        // user services listen on port 9900
        server.listen(result->{
            if (result.succeeded()) {
                System.out.println("Listen success on port 9900");
            } else {
                System.out.println("Listen failed on port 9900 cause by " + result.cause().getMessage());
            }
        });
        startFuture.complete();
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        System.out.println("Stop user verticle success");
    }
}
