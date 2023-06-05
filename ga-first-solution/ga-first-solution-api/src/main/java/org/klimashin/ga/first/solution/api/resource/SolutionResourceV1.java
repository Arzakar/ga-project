package org.klimashin.ga.first.solution.api.resource;

import org.klimashin.ga.first.solution.api.dto.FixedDeviationCommandProfileRequestDtoV1;
import org.klimashin.ga.first.solution.api.dto.InitialStateRequestDtoV1;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@RequestMapping("/api/v1/solution")
@Tag(name = "SolutionResourceV1")
public interface SolutionResourceV1 {

    @PostMapping("/state/initial")
    void createInitialState(@RequestBody InitialStateRequestDtoV1 requestDtoV1);

    @PostMapping("/state/command-profile/fixed-vector-deviation/load")
    void loadFixedVectorDeviationCommandProfile(@RequestParam UUID initialStateId,
                                                @RequestBody FixedDeviationCommandProfileRequestDtoV1 requestDtoV1);
}
