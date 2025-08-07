import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Set;

//public class schemaValidation {
//    public static void main(String[] args) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            InputStream schemaStream = schemaValidation.class.getResourceAsStream("/country-schema.json");
//            InputStream dataStream = schemaValidation.class.getResourceAsStream("/countries.json");
//
//            JsonSchemaFactory factory = JsonSchemaFactory.getInstance();
//            JsonSchema schema = factory.getSchema(schemaStream);
//            JsonNode data = mapper.readTree(dataStream);
//
//            Set<ValidationMessage> errors = schema.validate(data);
//            if (errors.isEmpty()) {
//                System.out.println("✅ JSON is valid.");
//            } else {
//                System.out.println("❌ Validation errors:");
//                errors.forEach(msg -> System.out.println(msg.getMessage()));
//            }
//        } catch (Exception e) {
//            System.err.println("❌ Error: " + e.getMessage());
//        }
//    }
//}

