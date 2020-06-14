package com.example;


import com.example.config.SpringConfig;
import com.example.service.AsyncTaskService;
import com.example.service.DemoAnnotationService;
import com.example.service.DemoMethodService;
import com.example.service.ScheduledTaskService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
 //       DemoAnnotationService demoAnnotationService = ctx.getBean(DemoAnnotationService.class);

//        DemoMethodService demoMethodService = ctx.getBean(DemoMethodService.class);

//        demoAnnotationService.add();
//        demoMethodService.add();
//        AsyncTaskService asyncTaskService = ctx.getBean(AsyncTaskService.class);
//        for(int i = 0 ;i <10 ;i++){
//             asyncTaskService.executeAsyncTask(i);
//             asyncTaskService.executeAsyncTaskPlus(i);
//        }


        ctx.close();
    }
}
