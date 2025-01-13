package com.example.tasklet;


import org.springframework.batch.item.ItemProcessor;

public class HelloWorldItemProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String item) throws Exception {
// System.out.println(item);
// System.out.println(item.toUpperCase());
        return item.toUpperCase();
    }
}
