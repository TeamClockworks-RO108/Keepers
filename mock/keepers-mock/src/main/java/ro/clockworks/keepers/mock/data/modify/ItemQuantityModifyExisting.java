package ro.clockworks.keepers.mock.data.modify;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;
import lombok.experimental.Accessors;

@Getter @Setter
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonPOJOBuilder(withPrefix = "")

public class ItemQuantityModifyExisting extends ItemQuantityModifyNew {

    private long id;

}
