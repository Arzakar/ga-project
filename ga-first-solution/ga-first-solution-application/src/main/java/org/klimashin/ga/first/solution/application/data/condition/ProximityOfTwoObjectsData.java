package org.klimashin.ga.first.solution.application.data.condition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProximityOfTwoObjectsData implements TargetStateData {

    UUID firstObjectId;
    UUID secondObjectId;
    Double requiredDistance;

    @Override
    @JsonIgnore
    public TargetStateTypeData getType() {
        return TargetStateTypeData.PROXIMITY_OF_TWO_OBJECTS;
    }

    @Override
    @JsonIgnore
    public ProximityOfTwoObjectsData getState() {
        return this;
    }
}
