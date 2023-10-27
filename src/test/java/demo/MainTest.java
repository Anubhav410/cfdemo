package demo;

import org.junit.Test;

import java.io.IOException;

public class MainTest {

    @Test
    public void testMain() throws InterruptedException, IOException {
        String[] args = {"workflowID", "integrationID", "{\"URL\":\"example.com\",\"PORT\":8080,\"Username\":\"user\",\"Password\":\"pass\", \"HeartBeatFrequencyMils\" : 1000}", "1000"};
//        Main.main(args);
        // Add your assertions here to verify the expected behavior of your main method
    }


    @Test
    public void testConfigLoading() {


        String configStr = "{\"orchestratorConfig\" : {\"url\":\"your_url\",\"authConfig\":{\"username\":\"your_username\",\"password\":\"your_password\"}} ,\"heartBeatConfig\" : {\"endpoint\":\"your_endpoint\",\"authConfig\":{\"username\":\"your_username\",\"password\":\"your_password\"},\"heartBeatFrequencyMils\":5000}}";
        Configs config = Main.parseJsonToConfigs(configStr);
        System.out.println(config);
    }
}