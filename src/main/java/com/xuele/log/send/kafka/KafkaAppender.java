package com.xuele.log.send.kafka;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.xuele.log.send.kafka.formatter.Formatter;
import com.xuele.log.send.kafka.formatter.JsonFormatter;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Created by QinDongLiang on 2017/1/9.
 */
public class KafkaAppender extends AppenderBase<ILoggingEvent> {

    private static final Logger log = LoggerFactory.getLogger(KafkaAppender.class);
    private Formatter formatter = new JsonFormatter();
    private boolean logToSystemOut = false;
    private String kafkaProducerProperties;
    private String topic;
    private String brokers;
    //    private String zkHost;
    private KafkaProducer producer;
    private String timeout;

    private boolean syncSend=true;

    @Override
    public void start() {
        super.start();
        log.info("Starting KafkaAppender...");
        Properties props = new Properties();
        try {
            props.put("bootstrap.servers", brokers);
            props.put("timeout.ms", timeout);
            props.put("request.timeout.ms",  timeout);
            props.put("metadata.fetch.timeout.ms",  timeout);
            props.put("network.request.timeout.ms",  timeout);

            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


//            props.put(ProducerConfig.ACKS_CONFIG, "-1");
//            props.put(ProducerConfig.BATCH_SIZE_CONFIG,1);

//            props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,0);
//            props.put(ProducerConfig.SEND_BUFFER_CONFIG, "false");

            producer = new KafkaProducer<String, String>(props);
        } catch (Exception e) {
            log.error("初始化KafkaAppender失败{}={}", e+"", e.getMessage());
        }
        log.info("kafkaProducerProperties = {}", kafkaProducerProperties);
        log.info("Kafka Producer Properties = {}", props);


    }

    @Override
    public void stop() {
        super.stop();
        log.info("Stopping KafkaAppender...");
        producer.close();
    }


    @Override
    protected void append(ILoggingEvent event) {
        String string = this.formatter.format(event);

        try {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, string);
            final Future send = producer.send(producerRecord);
            if(syncSend){
                send.get();
            }
        } catch (Exception e) {
            System.out.println("KafkaAppender: Exception sending message: '" + e + " : " + e.getMessage() + "'.");
            e.printStackTrace();
        }
    }

    public Formatter getFormatter() {
        return formatter;
    }

    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    public String getTopic() {
        return topic;
    }

    public boolean isSyncSend() {
        return syncSend;
    }

    public void setSyncSend(boolean syncSend) {
        this.syncSend = syncSend;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getLogToSystemOut() {
        return logToSystemOut + "";
    }

    public void setLogToSystemOut(String logToSystemOutString) {
        if ("true".equalsIgnoreCase(logToSystemOutString)) {
            this.logToSystemOut = true;
        }
    }


    public String getKafkaProducerProperties() {
        return kafkaProducerProperties;
    }

    public void setKafkaProducerProperties(String kafkaProducerProperties) {
        this.kafkaProducerProperties = kafkaProducerProperties;
    }

    public String getBrokers() {
        return brokers;
    }

    public void setBrokers(String brokers) {
        this.brokers = brokers;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }
}
