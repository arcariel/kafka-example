package com.arcariel.springboot.kafka.service;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.StreamException;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    private static final Logger LOG = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    public void sendMessage() throws StreamException, InterruptedException {
        String topic = "wikimedia_recentchanges";

        BackgroundEventHandler eventHandler = new WikimediaChangesHandler(template, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(eventHandler, new EventSource.Builder(URI.create(url)));
        builder.build().start();

        TimeUnit.SECONDS.sleep(10);

    }
}
