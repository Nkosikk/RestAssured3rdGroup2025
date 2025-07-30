package PayloadBuilder;

import com.github.javafaker.Faker;
import org.json.simple.JSONObject;

public class ReqresPayloadBuilder {

    public static JSONObject CreateReqresBody() {
        Faker faker = new Faker();

        String name = faker.name().firstName();
        String surname = faker.name().lastName();
        String job = faker.job().title();

        JSONObject Reqrespayload = new JSONObject();
        Reqrespayload.put("name", name);
        Reqrespayload.put("surname", surname);
        Reqrespayload.put("job", job);

        return Reqrespayload;


    }

    public  static  JSONObject updateReqresBody() {
        Faker faker = new Faker();

        String name = faker.name().firstName();
        String surname = faker.name().lastName();
        String job = faker.job().title();

        JSONObject Reqrespayload = new JSONObject();
        Reqrespayload.put("name", name);
        Reqrespayload.put("surname", surname);
        Reqrespayload.put("job", job);

        return Reqrespayload;
    }

    public static JSONObject deleteReqresBody() {
        // For DELETE requests, typically no body is required.
        // Returning an empty JSON object.
        return new JSONObject();
    }

}
