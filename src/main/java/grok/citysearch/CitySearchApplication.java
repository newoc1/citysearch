package grok.citysearch;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CitySearchApplication 
{
    public static void main( String[] args )
    {
        ApplicationContext applicationContext = SpringApplication.run(CitySearchApplication.class, args);
        
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for(String beanName: beanNames) {
        	System.out.println(beanName);
        }
    }
}
