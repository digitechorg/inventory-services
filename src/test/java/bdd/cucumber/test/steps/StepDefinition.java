package bdd.cucumber.test.steps;


import com.interviewrepl.inventory.model.Inventory;
import com.interviewrepl.inventory.utililty.Helper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static bdd.cucumber.test.utils.JavaProcessBuilder.launchApplication;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@CucumberContextConfiguration
public class StepDefinition {


    private static Logger logger = LoggerFactory.getLogger(StepDefinition.class);
    private static boolean firstTime = true;
    private String apiServiceUrl = "http://localhost:8080";

    private HttpResponse<String> response;

    private synchronized static void setFirstTime() {
        firstTime = false;
    }

    private Scenario scenario;

    @Before
    public void before(Scenario scenario) throws IOException, InterruptedException {
        this.scenario = scenario;
        if(firstTime){
            setFirstTime();
            addShutDownHook(launchApplication());
        }
    }

    private void addShutDownHook(Process process){
        // kill running process
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            process.destroy();
        }));
    }

    @Given("^API Service is started$")
    public void api_service_is_started() throws IOException {

        // ping if application is up and running
        int appPort = Integer.parseInt("8080");

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", appPort), 1000);
        socket.close();

    }

    @When("User sends a POST request to insert inventory records then it receives a valid response")
    public void userSendsAPOSTRequestToInsertInventoryRecordsThenItReceivesAValidResponse() throws URISyntaxException, IOException, InterruptedException, JSONException {
        Helper helper = getHelper();
        Inventory inventory = helper.buildInventory1();
        String jsonBody = helper.mapToJson(inventory);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(new URI(apiServiceUrl + "/api/inventory"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        scenario.log(String.format("Request: %1$s", request.toString()));
        this.response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String outputInJson = response.body();
        JSONAssert.assertEquals(outputInJson, jsonBody,
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("Delivery[ItemId=728392017342].id", (o1, o2) -> true),  // skipping comparison of Json fields which are not static
                        new Customization("Refund[ItemId=728392017342].id", (o1, o2) -> true),
                        new Customization("Sale[ItemId=728392017342].id", (o1, o2) -> true)));
        Assert.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
    }


    private Helper getHelper() {
        return new Helper();
    }


    @When("User sends a GET request to fetch the inventory records by storeId {string} then it receives a valid response")
    public void userSendsAGETRequestToFetchTheInventoryRecordsByStoreIdThenItReceivesAValidResponse(String storeId) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(new URI(apiServiceUrl + "/api/inventory/" + storeId))
                .header("accept", "application/json")
                .build();
        scenario.log(String.format("Request: %1$s", request.toString()));
        this.response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Assert.assertEquals(HttpStatus.OK.value(), response.statusCode());
    }
}
