package ro.clockworks.keepers.mock.data.modify;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ItemQuantityModifyDeserializer extends JsonDeserializer<ItemQuantityModifyBase> {

    @Override
    public ItemQuantityModifyNew deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = jsonParser.readValueAsTree();

        if (node.has("id")) {
            return mapper.readerFor(ItemQuantityModifyExisting.class).readValue(node);
        } else {
            return mapper.readerFor(ItemQuantityModifyNew.class).readValue(node);
        }
    }
}
