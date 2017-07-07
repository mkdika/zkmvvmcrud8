package com.mkdika.zkmvvmcrud8.cli;

import com.mkdika.zkmvvmcrud8.config.SpringConfig;
import com.mkdika.zkmvvmcrud8.model.TbPerson;
import com.mkdika.zkmvvmcrud8.service.AppService;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public class TestData {

    private static ApplicationContext ctx;
    private static AppService svc;

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
        SpringConfig.WEB_MODE = false;
        
        // Init the Spring Application Context
        initAppContext();

        System.out.println("Total Person: " + svc.findAll(TbPerson.class).size());
        List<TbPerson> persons = svc.getTbPersonByGender(0);
        if (persons.size() > 0) {
            for (TbPerson p : persons) {
                System.out.println("Full Name: " + p.getFirstname() + " " + p.getLastname());
                System.out.println("\tTotal Experience: " + p.getExperiences().size());
            }
        }       
    }

    private static void initAppContext() {
        ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        svc = (AppService) ctx.getBean("appService");
    }
}
