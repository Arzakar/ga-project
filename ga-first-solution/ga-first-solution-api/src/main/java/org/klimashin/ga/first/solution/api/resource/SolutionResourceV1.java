package org.klimashin.ga.first.solution.api.resource;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/solution")
@Tag(name = "SolutionResourceV1")
public interface SolutionResourceV1 {

    @PostMapping("/start")
    void startResolveProcess();
}
