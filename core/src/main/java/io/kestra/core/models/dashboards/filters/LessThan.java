package io.kestra.core.models.dashboards.filters;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@SuperBuilder
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class LessThan extends AbstractFilter {
    @NotNull
    @JsonInclude
    @Builder.Default
    protected String type = "LESS_THAN";

    @NotNull
    @Schema(anyOf = {Number.class, ZonedDateTime.class})
    private Object value;
}