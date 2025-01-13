package com.example.tasklet;

import java.util.Random;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class MainTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
    	Random rn = new Random();
    	int answer = rn.nextInt(10) + 1;
    	System.out.println(answer);
        return RepeatStatus.FINISHED;
    }
}
