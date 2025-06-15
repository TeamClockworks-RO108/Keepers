package ro.clockworks.keepers.mock.data;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonPOJOBuilder(withPrefix = "")
public class ItemQuantityGet {

    private long id;

    @Min(1)
    private int quantity;

    private boolean allocated;

    private String comments;

}
