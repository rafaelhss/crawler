package crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class OneOffProcess
{
    public static void main(String[] args)
    {
        SpringApplication.run(OneOffProcess.class);

//        new CrawlerRunner().crawl();

        System.out.println("crawler.OneOffProcess executed.");
    }
}