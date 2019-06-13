import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

import java.io.IOException;
import java.util.Scanner;

/**
 * Copyright by Intelin.
 * Creator: Nguyen Ngoc Chau
 * Date: 6/13/19
 * Time: 10:44 AM
 */
public class MainApp {

    public static void main(String[] args) throws IOException, InterruptedException {
        Vertx vertx = Vertx.vertx();
        while (true) {
            Scanner input = new Scanner(System.in);
            DeploymentOptions options = new DeploymentOptions()
                    .setWorker(true)
                    .setInstances(1)
                    .setWorkerPoolSize(4)
                    .setWorkerPoolName("vertx-demo-default-pool");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Please input command: ");
            String command = input.nextLine();
            switch (command) {
                case "1":
                    System.out.println("Set pool name");
                    options.setWorkerPoolName(input.nextLine());
                    break;
                case "2":
                    System.out.println("Start verticle: ");
                    vertx.deployVerticle(input.nextLine(), options, asyncResult -> {
                        System.out.println("Deploy done");
                        if (asyncResult.succeeded()) {
                            System.out.println("Deployment id " + asyncResult.result());
                        } else {
                            System.out.println("Failed to deploy verticle cause by " + asyncResult.cause().getMessage());
                        }
                    });
                    break;
                case "3":
                    System.out.println("Stop verticle: ");
                    vertx.undeploy(input.nextLine());
                    break;
            }
        }
    }

}
