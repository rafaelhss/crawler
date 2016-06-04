package crawler.batch;

/**
 * Created by rafa on 04/06/2016.
 */

import crawler.batch.crawlers.FakeCrawler;
import crawler.batch.crawlers.GloboEsporteCrawler;
import model.noticia.Noticia;
import crawler.batch.processor.NoticiaProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
/*    @Bean
    public ItemReader<Noticia> reader() {
        FakeCrawler reader = new FakeCrawler();
        return reader;
    }
*/
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
                .next(step2())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1 - globo")
                .<Noticia, Noticia>chunk(10)
                .reader(new GloboEsporteCrawler())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2 - fake")
                .<Noticia, Noticia>chunk(10)
                .reader(new FakeCrawler())
                .processor(processor())
                .writer(writer())
                .build();
    }

    // end::jobstep[]
}