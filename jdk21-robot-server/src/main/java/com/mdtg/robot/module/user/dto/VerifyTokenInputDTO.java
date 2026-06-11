package com.mdtg.robot.module.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-19]
 */
@Getter
@Setter
public class VerifyTokenInputDTO {

    @Schema(description = "JWT-Token",example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3NzM5MTE4MjIsInN1YiI6ImFkbWluIiwiZXhwIjoxNzczOTExODgyLCJ1c2VybmFtZSI6ImFkbWluIn0.kw3hKLeC95v8NjO-zPvoNuh9VGYxIVRYJmFSwj0vJLXfA8LHKnuBRzpJi5cF1KLXg4vetolLAZ5ZkJsLe0bf7A")
    @NotBlank
    private String token;
}
