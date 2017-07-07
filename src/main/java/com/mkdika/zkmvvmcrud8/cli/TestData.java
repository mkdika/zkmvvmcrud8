package com.mkdika.zkmvvmcrud8.cli;

import com.mkdika.zkmvvmcrud8.config.SpringConfig;
import com.mkdika.zkmvvmcrud8.model.TbPerson;
import com.mkdika.zkmvvmcrud8.service.AppService;
import java.util.Date;
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
        // Init the Spring Application Context
        initAppContext();

        System.out.println("Total Person: " + svc.findAll(TbPerson.class).size());
//        List<TbPerson> persons = (List<TbPerson>) svc.findAll(TbPerson.class, "firstname desc");
        List<TbPerson> persons = svc.getTbPersonByGender(0);
        if (persons.size() > 0) {
            for (TbPerson p : persons) {
                System.out.println("Full Name: " + p.getFirstname() + " " + p.getLastname());
                System.out.println("\tTotal Experience: " + p.getExperiences().size());
            }
        }

        TbPerson p = new TbPerson();
        p.setFirstname("GUNDAM");
        p.setLastname("UNICORN");
        p.setGender(0);
        p.setBirthday(new Date());
        p.setIdtype(0);
        p.setIdnumber("12312312312");
        p.setPhonenumber("08119892312");
        p.setEmail("gundamuni@gmail.com");
        p.setAddress("Jl.Bunga Melati no.123, Jakarta Utara, Indonesia");
        
        svc.save(p);
        System.out.println("GENDER FEMALE SIZE: " + svc.getTbPersonByGender(0).size());
        
        TbPerson a = (TbPerson) svc.findById(TbPerson.class, "ff8081815d173e5e015d173e621400ca");
        System.out.println("A FIRST NAME: " + a.getFirstname());
        System.out.println("A LAST NAME: "+a.getLastname());

    }

    private static void initAppContext() {
        ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        svc = (AppService) ctx.getBean("appService");
    }
}
