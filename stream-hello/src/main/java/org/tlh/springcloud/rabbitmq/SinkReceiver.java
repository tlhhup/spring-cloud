package org.tlh.springcloud.rabbitmq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

//绑定到相应的通道上(输入或输出)
@EnableBinding(Sink.class)
public class SinkReceiver {

    //转换为消息中间件上的数据流的事件监听器
    @StreamListener(Sink.INPUT)
    public void receive(Object payload){
        System.out.println(payload);
    }

}
