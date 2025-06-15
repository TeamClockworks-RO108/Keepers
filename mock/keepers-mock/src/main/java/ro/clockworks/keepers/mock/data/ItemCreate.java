package ro.clockworks.keepers.mock.data;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ItemCreate {

    @Size(min = 3, max = 250)
    @NotNull
    private String name;

    @NotNull
    @Valid
    private List<@Valid ItemQuantityCreate> quantities;

}
