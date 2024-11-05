package io.kestra.plugin.core.dashboard.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.kestra.core.models.annotations.Plugin;
import io.kestra.core.models.dashboards.DataFilter;
import io.kestra.core.models.dashboards.charts.Chart;
import io.kestra.plugin.core.dashboard.chart.timeseries.TimeseriesColumnDescriptor;
import io.kestra.plugin.core.dashboard.chart.timeseries.TimeseriesOption;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@Plugin
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@EqualsAndHashCode
public class TimeSeries<D extends DataFilter<? extends TimeseriesColumnDescriptor<?>>> extends Chart<TimeseriesOption, D> {
}
