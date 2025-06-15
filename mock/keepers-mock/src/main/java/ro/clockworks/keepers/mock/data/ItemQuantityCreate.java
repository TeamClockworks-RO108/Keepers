package ro.clockworks.keepers.mock.data;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;

@Getter @Setter
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonPOJOBuilder(withPrefix = "")
public class ItemQuantityCreate {

    @Min(1)
    @NotNull
    private Integer quantity;

    @NotNull
    private Boolean allocated;

    @Size(min = 0, max = 2500)
    @NotNull
    private String comments;

}
