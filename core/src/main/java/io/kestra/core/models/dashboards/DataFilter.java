package io.kestra.core.models.dashboards;

import io.kestra.core.models.annotations.Plugin;
import io.kestra.core.models.dashboards.filters.AbstractFilter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@Plugin
@EqualsAndHashCode
public abstract class DataFilter<C extends ColumnDescriptor<? extends Enum<?>>> implements io.kestra.core.models.Plugin {
    @NotNull
    @NotBlank
    @Pattern(regexp = "\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*(\\.\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*)*")
    private String type;

    private Map<String, C> columns;

    private List<AbstractFilter> where;

    private Map<String, Order> orderBy;
}
