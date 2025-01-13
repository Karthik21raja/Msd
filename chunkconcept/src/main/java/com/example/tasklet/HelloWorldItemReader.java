package com.example.tasklet;

import org.springframework.batch.item.ItemReader;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class HelloWorldItemReader implements ItemReader<String> {
private Iterator<String> dataIterator;
    @Override
    public String read() throws Exception {

    String data="hello";
        return data; 
    }
}