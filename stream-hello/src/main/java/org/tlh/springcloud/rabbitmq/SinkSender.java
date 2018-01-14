package org.tlh.springcloud.rabbitmq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.Date;

@EnableBinding(SinkSender.SinkOutput.class)
public class SinkSender {

    @Bean
    @InboundChannelAdapter(value = SinkOutput.OUTPUT,poller = @Poller(fixedDelay = "2000"))
    public MessageSource<Date> timerMessageSource(){
        return ()->new GenericMessage<Date>(new Date());
    }

    public interface SinkOutput{
        String OUTPUT="input";

        @Output(SinkOutput.OUTPUT)
        MessageChannel output();
    }

}
