package io.kestra.plugin.core.flow;

import io.kestra.core.models.executions.Execution;
import io.kestra.core.models.flows.State;
import io.kestra.core.queues.QueueException;
import io.kestra.core.runners.AbstractMemoryRunnerTest;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class IfTest  extends AbstractMemoryRunnerTest {
    @Test
    void ifTruthy() throws TimeoutException, QueueException {
        Execution execution = runnerUtils.runOne(null, "io.kestra.tests", "if-condition", null,
            (f, e) -> Map.of("param", true) , Duration.ofSeconds(120));

        assertThat(execution.getTaskRunList(), hasSize(2));
        assertThat(execution.findTaskRunsByTaskId("when-true").getFirst().getState().getCurrent(), is(State.Type.SUCCESS));
        assertThat(execution.getState().getCurrent(), is(State.Type.SUCCESS));

        execution = runnerUtils.runOne(null, "io.kestra.tests", "if-condition", null,
            (f, e) -> Map.of("param", "true") , Duration.ofSeconds(120));

        assertThat(execution.getTaskRunList(), hasSize(2));
        assertThat(execution.findTaskRunsByTaskId("when-true").getFirst().getState().getCurrent(), is(State.Type.SUCCESS));
        assertThat(execution.getState().getCurrent(), is(State.Type.SUCCESS));

        execution = runnerUtils.runOne(null, "io.kestra.tests", "if-condition", null,
            (f, e) -> Map.of("param", 1) , Duration.ofSeconds(120));

        assertThat(execution.getTaskRunList(), hasSize(2));
        assertThat(execution.findTaskRunsByTaskId("when-true").getFirst().getState().getCurrent(), is(State.Type.SUCCESS));
        assertThat(execution.getState().getCurrent(), is(State.Type.SUCCESS));
    }

    @Test
    void ifFalsy() throws TimeoutException, QueueException {
        Execution execution = runnerUtils.runOne(null, "io.kestra.tests", "if-condition", null,
            (f, e) -> Map.of("param", false) , Duration.ofSeconds(120));

        assertThat(execution.getTaskRunList(), hasSize(2));
        assertThat(execution.findTaskRunsByTaskId("when-false").getFirst().getState().getCurrent(), is(State.Type.SUCCESS));
        assertThat(execution.getState().getCurrent(), is(State.Type.SUCCESS));

        execution = runnerUtils.runOne(null, "io.kestra.tests", "if-condition", null,
            (f, e) -> Map.of("param", "false") , Duration.ofSeconds(120));

        assertThat(execution.getTaskRunList(), hasSize(2));
        assertThat(execution.findTaskRunsByTaskId("when-false").getFirst().getState().getCurrent(), is(State.Type.SUCCESS));
        assertThat(execution.getState().getCurrent(), is(State.Type.SUCCESS));

        execution = runnerUtils.runOne(null, "io.kestra.tests", "if-condition", null,
            (f, e) -> Map.of("param", 0) , Duration.ofSeconds(120));

        assertThat(execution.getTaskRunList(), hasSize(2));
        assertThat(execution.findTaskRunsByTaskId("when-false").getFirst().getState().getCurrent(), is(State.Type.SUCCESS));
        assertThat(execution.getState().getCurrent(), is(State.Type.SUCCESS));

        execution = runnerUtils.runOne(null, "io.kestra.tests", "if-condition", null,
            (f, e) -> Map.of("param", -0) , Duration.ofSeconds(120));

        assertThat(execution.getTaskRunList(), hasSize(2));
        assertThat(execution.findTaskRunsByTaskId("when-false").getFirst().getState().getCurrent(), is(State.Type.SUCCESS));
        assertThat(execution.getState().getCurrent(), is(State.Type.SUCCESS));

        // We cannot test null as inputs cannot be null
    }

    @Test
    void ifWithoutElse() throws TimeoutException, QueueException {
        Execution execution = runnerUtils.runOne(null, "io.kestra.tests", "if-without-else", null,
            (f, e) -> Map.of("param", true) , Duration.ofSeconds(120));

        assertThat(execution.getTaskRunList(), hasSize(2));
        assertThat(execution.findTaskRunsByTaskId("when-true").getFirst().getState().getCurrent(), is(State.Type.SUCCESS));
        assertThat(execution.getState().getCurrent(), is(State.Type.SUCCESS));

        execution = runnerUtils.runOne(null, "io.kestra.tests", "if-without-else", null,
            (f, e) -> Map.of("param", false) , Duration.ofSeconds(120));
        assertThat(execution.getTaskRunList(), hasSize(1));
        assertThat(execution.findTaskRunsByTaskId("when-true").isEmpty(), is(true));
        assertThat(execution.getState().getCurrent(), is(State.Type.SUCCESS));
    }

    @Test
    void ifInFlowable() throws TimeoutException, QueueException {
        Execution execution = runnerUtils.runOne(null, "io.kestra.tests", "if-in-flowable", null,
            (f, e) -> Map.of("param", true) , Duration.ofSeconds(120));

        assertThat(execution.getTaskRunList(), hasSize(8));
        assertThat(execution.findTaskRunsByTaskId("after_if").getFirst().getState().getCurrent(), is(State.Type.SUCCESS));
        assertThat(execution.getState().getCurrent(), is(State.Type.SUCCESS));
    }
}