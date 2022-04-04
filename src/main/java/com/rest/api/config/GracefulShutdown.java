package com.rest.api.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {
    // 해당 코드는 실행중인 작업을 마치고 종료하는 내용을 담고 있으며 30초안에 종료되지 않으면 강제로 작업을 종료하도록 처리하고 있습니다.
    private static final int TIMEOUT = 30;

    private volatile Connector connector;


    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        this.connector.pause();
        Executor executor = this.connector.getProtocolHandler().getExecutor();
        if(executor instanceof ThreadPoolExecutor){
            try {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                threadPoolExecutor.shutdown();
                if(!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)){
                    log.warn("Tomcat thread pool did not shut down graceully within "
                    + TIMEOUT + " seconds. Proceeding with forceful shutdown");
                    threadPoolExecutor.shutdownNow();

                    if(!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)){
                        log.error("Tomcat thread pool did not terminate");
                    }
                } else{
                    log.info("Tomcat thread pool has been gracefully shutdown");
                }
            }catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
    }
}
