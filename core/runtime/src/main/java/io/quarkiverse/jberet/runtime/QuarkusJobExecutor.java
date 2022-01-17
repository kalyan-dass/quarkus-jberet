package io.quarkiverse.jberet.runtime;

import io.quarkus.runtime.ThreadPoolConfig;
import org.jberet.spi.JobExecutor;
import org.wildfly.common.cpu.ProcessorInfo;

import java.util.concurrent.Executor;

class QuarkusJobExecutor extends JobExecutor {
    private final int cpuPoolSize;

    public QuarkusJobExecutor(Executor delegate, final ThreadPoolConfig threadPoolConfig) {
        super(delegate);
        this.cpuPoolSize = threadPoolConfig.maxThreads.orElse(Math.max(8 * ProcessorInfo.availableProcessors(), 200));
    }

    @Override
    protected int getMaximumPoolSize() {
        return cpuPoolSize;
    }
}