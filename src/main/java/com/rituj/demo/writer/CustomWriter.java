package com.rituj.demo.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> messages) throws Exception {
        log.info("In to writer....processing data: {}", messages    );
        for (String msg : messages) {
            System.out.println("Writing the data " + msg);
        }
    }

}
