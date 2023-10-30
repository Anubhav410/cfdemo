package connectors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import demo.Configs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BaseConnector {
    HttpServer server;

    String workflowId;
    String taskId;
    String integrationID;
    Configs configs;

    ScheduledExecutorService executor;

    public BaseConnector(String workflowId, String taskId, String integrationID, Configs configs) {
        this.workflowId = workflowId;
        this.taskId = taskId;
        this.integrationID = integrationID;
        this.configs = configs;
    }

    public void run() throws Exception {
        System.out.printf("Processing Workflow ID: %s%n", workflowId);
        System.out.printf("Processing Integration ID: %s%n", integrationID);
        System.out.printf("Configs Passed: %s%n", configs);


        // 1. Start a Server to expose Health
        server = HttpServer.create(new InetSocketAddress("0.0.0.0", 8111), 0);

        // Create a context for the "/health" endpoint


        // Start the server
        System.out.println("Server started on port 8000.");

        startHeatBeatService(configs);

        // 2. Start a Server to listen to STOP Signal
        startStopSignalServer(server);

        server.start();

        // 3. Start working based on the configurations
        // Use the configs object for processing

        // 4. Send back the final result to the Orchestrator Server
        dataPullStart();

        signalOrchestratorWorkComplete();

        stopConnector();

    }

    public void dataPullStart() throws Exception {
        throw new Exception("missing implementation");
    }

    private void signalOrchestratorWorkComplete() {
        System.out.println("Sending Signal to Orchestrator, about completion of work");
        String endpoint = String.format("%s/taskflow/%s/complete", configs.getOrchestratorConfig().getUrl(), workflowId);
        makeApiCall(endpoint, "POST");
    }

    private void startHeatBeatService(Configs configs) {
        // Implement logic to start the health server
        System.out.println("Heart Beat Started.");
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(this::heartbeater, 0, configs.getHeartBeatConfig().getHeartBeatFrequencyMils(), TimeUnit.MILLISECONDS);
    }

    private void heartbeater() {
        System.out.println("Sending HeartBeat");
        String heartBeatEndpoint = String.format("%s/%s/%s/heartbeat", configs.getHeartBeatConfig().getEndpoint(), workflowId, taskId);
        makeApiCall(heartBeatEndpoint, "PUT");
    }

    private void makeApiCall(String urlString, String method) {
        try {
            //
            URL url = new URL(urlString); // Replace with your API endpoint
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
            } else {
                System.out.println("Request not worked");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startStopSignalServer(HttpServer server) {
        // Implement logic to start the server listening to the STOP signal
        System.out.println("Server to listen to STOP signal started.");
        server.createContext("/stop", new StopConnector());
    }

    class StopConnector implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            System.out.println("Exiting Connector");
            String response = "{\"success\": \"ok\"}";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
            stopConnector();
        }
    }

    public void stopConnector() throws IOException {
        executor.shutdown();
        server.stop(0);
    }

}
