package org.klimashin.ga.first.solution.api.resource;

import org.klimashin.ga.first.solution.api.dto.InitialStateGeneratorRequestDtoV1;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/initial-state")
@Tag(name = "InitialStateResourceV1")
public interface InitialStateResourceV1 {

    @PostMapping("/generate")
    void generateInitialState(@RequestBody InitialStateGeneratorRequestDtoV1 requestDtoV1);
}
