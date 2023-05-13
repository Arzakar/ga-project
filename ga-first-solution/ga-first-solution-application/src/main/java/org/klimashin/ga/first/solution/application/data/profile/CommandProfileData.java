package org.klimashin.ga.first.solution.application.data.profile;

public interface CommandProfileData {

    CommandProfileTypeData getType();

    <T extends CommandProfileData> T getProfile();
}
