package org.klimashin.ga.first.solution.application.data.condition;

public interface TargetStateData {

    TargetStateTypeData getType();

    <T extends TargetStateData> T getState();
}
