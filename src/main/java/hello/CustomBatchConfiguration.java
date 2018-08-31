package hello;

//import common.Greeting;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Spring Batch Hello World job java configuration.
 */
@Configuration
@EnableBatchProcessing
public class CustomBatchConfiguration {


    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;




//    @Bean
//    public DataSource dataSource(){
//
//        return DataSourceBuilder.create()
//                .url(env.getProperty("db.url"))
//                .driverClassName(env.getProperty("db.driver"))
//                .username(env.getProperty("db.username"))
//                .password(env.getProperty("db.password"))
//                .build();
//    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
        return new MapJobRepositoryFactoryBean(transactionManager()).getJobRepository();
    }

    @Bean
    public JobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository());
        return jobLauncher;
    }

//    @Bean
//    @Scope("prototype")
//    public Greeting greeting() {
//        return new Greeting();
//    }
//
//    @Bean
//    public ItemProcessor greetingProcessor() {
//        return new GreetingProcessor();
//    }
//
//    @Bean
//    public ItemWriter greetingWriter() {
//        return new GreetingWriter();
//    }
//
//    @Bean
//    public ItemReader greetingReader() {
//
//        //configure line tokenizer
//        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
//        delimitedLineTokenizer.setNames(new String[]{"id","name"});
//
//        //configure field set mapper
//        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
//        fieldSetMapper.setPrototypeBeanName("greeting");
//
//        //configure line mapper
//        DefaultLineMapper defaultLineMapper = new DefaultLineMapper();
//        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
//        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
//
//        //configure item reader
//        FlatFileItemReader flatFileItemReader = new FlatFileItemReader();
//        flatFileItemReader.setResource(new ClassPathResource("greetings.csv"));
//        flatFileItemReader.setLineMapper(defaultLineMapper);
//        return flatFileItemReader;
//
//    }
//
//    @Bean
//    public Job job() {
//        return jobBuilders.get("helloWorldJob").start(step1()).build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilders.get("step1")
//                .<Greeting, Greeting> chunk(10)
//                .reader(greetingReader())
//                .processor(greetingProcessor())
//                .writer(greetingWriter()).build();
//    }

}