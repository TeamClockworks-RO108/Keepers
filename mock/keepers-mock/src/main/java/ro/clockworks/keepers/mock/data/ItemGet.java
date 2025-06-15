package ro.clockworks.keepers.mock.data;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonPOJOBuilder(withPrefix = "")
public class ItemGet {

    private long id;


    private String name;

    private List<ItemQuantityGet> quantities;

}
