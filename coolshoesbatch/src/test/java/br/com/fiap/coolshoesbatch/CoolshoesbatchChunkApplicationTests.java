package br.com.fiap.coolshoesbatch;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CoolshoesbatchChunkApplication.class, CoolshoesBatchConfig.class })
class CoolshoesbatchChunkApplicationTests {

	@Autowired
	private Job job;

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private DataSource datasource;

	@Test
	void processPessoaToDatabase() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, SQLException {
//		JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher()
//				.run(job, jobLauncherTestUtils.getUniqueJobParameters());
//		Assertions.assertNotNull(jobExecution);
//		Assertions.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

		ResultSet resultSet = datasource.getConnection()
				.prepareStatement("select count(*) from TB_USER")
				.executeQuery();

		Awaitility.await()
				.atMost(10, TimeUnit.SECONDS)
				.until(() -> {
					resultSet.last();
					int countPessoas = resultSet.getInt(1);
					return countPessoas == 3;
				} );

		resultSet.last();
		Assertions.assertEquals(3, resultSet.getInt(1));
	}

}
