package br.com.fiap.coolshoesbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.nio.file.Paths;

@SpringBootApplication
@EnableBatchProcessing
public class CoolshoesbatchTaskletApplication {

	Logger logger = LoggerFactory.getLogger(CoolshoesbatchTaskletApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CoolshoesbatchTaskletApplication.class, args);
	}

	@Bean
	public Tasklet clearFile(@Value("${file.path}") String filePath){
		return (contribution, chunkContext) -> {
			File file = Paths.get(filePath).toFile();
			if(file.delete()) {
				logger.info("Arquivo deletado com sucesso");
			}else{
				logger.error("Erro ao deletar arquivo");
			}
			return RepeatStatus.FINISHED;
		};
	}

	@Bean
	public Step deleteStep(StepBuilderFactory stepBuilderFactory,
						   Tasklet tasklet){
		return stepBuilderFactory.get("Delete file Step")
				.tasklet(tasklet)
				.allowStartIfComplete(true)
				.build();
	}

	@Bean
	public Job deleteJob(JobBuilderFactory jobBuilderFactory,
						 Step step){
		return jobBuilderFactory.get("Delete file Job")
				.start(step)
				.build();
	}

}
