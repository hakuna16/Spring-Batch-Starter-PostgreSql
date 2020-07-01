package com.rituj.demo.reader;

import org.springframework.batch.item.ItemReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomReader implements ItemReader<String> {

    private String[] messages = { "hello From reader", "hello From reader 2nd data", "hello From reader 3rd data",
            "hello From reader 4th data" ,"hello From reader 5th data"};

    int count = 0;

    @Override
    public String read() {
        log.info("In to reader....reading data");
        
        if (count < messages.length) {
            return messages[count++];
        } else {
            count = 0;
        }
        return null;
    }

}
