package com.rituj.demo.processor;

import org.springframework.batch.item.ItemProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String item) throws Exception {
        log.info("In to processor....processing data: {}", item);
        return item.toUpperCase();
    }

}
