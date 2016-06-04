package crawler.batch;

/**
 * Created by rafa on 04/06/2016.
 */

import javax.sql.DataSource;

import crawler.crawlers.FakeCrawler;
import crawler.noticia.Noticia;
import crawler.noticia.repository.NoticiaRepository;
import crawler.noticia.repository.NoticiaWriter;
import crawler.processor.NoticiaProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    //@Autowired
   // public DataSource dataSource;

    @Autowired
    public NoticiaWriter noticiaWriter;


    // tag::readerwriterprocessor[]
    @Bean
    public ItemReader<Noticia> reader() {
        FakeCrawler reader = new FakeCrawler();
        return reader;
    }

    @Bean
    public NoticiaProcessor processor() {
        return new NoticiaProcessor();
    }

    @Bean
    public ItemWriter<Noticia> writer() {
     /*   JdbcBatchItemWriter<Noticia> writer = new JdbcBatchItemWriter<Noticia>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Noticia>());
        writer.setSql("INSERT INTO noticia (titulo, texto) VALUES (:titulo, :texto)");
        writer.setDataSource(dataSource);*/
        return noticiaWriter;
    }
    // end::readerwriterprocessor[]

    // tag::listener[]

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionNotificationListener();
    }

    // end::listener[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Noticia, Noticia>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    // end::jobstep[]
}