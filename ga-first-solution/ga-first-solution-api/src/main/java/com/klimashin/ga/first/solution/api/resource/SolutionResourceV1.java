package com.klimashin.ga.first.solution.api.resource;

import com.klimashin.ga.first.solution.api.dto.InitialStatesWithFixedVectorDeviationRequestDtoV1;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/solution")
@Tag(name = "SolutionResourceV1kek")
public interface SolutionResourceV1 {

    @PostMapping("/initiate/fixed-vector-deviation")
    void createInitialStatesWithFixedVectorDeviation(@RequestBody InitialStatesWithFixedVectorDeviationRequestDtoV1 requestDtoV1);
}
