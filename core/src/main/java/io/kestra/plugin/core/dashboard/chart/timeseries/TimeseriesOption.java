package io.kestra.plugin.core.dashboard.chart.timeseries;

import io.kestra.core.models.dashboards.PanelOption;
import io.kestra.core.models.dashboards.charts.LegendOption;
import io.kestra.core.models.dashboards.charts.TooltipBehaviour;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class TimeseriesOption extends PanelOption {
    @Builder.Default
    private TooltipBehaviour tooltip = TooltipBehaviour.ALL;

    @Builder.Default
    private LegendOption legend = LegendOption.builder().enabled(true).build();
}
