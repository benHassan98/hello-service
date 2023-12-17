package com.example.helloservice;

//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
public class HelloServiceApplication {
    @Value("${test.url}")
    private String t;
//    @PersistenceContext
//    private EntityManager entityManager;
    @Autowired
    private RabbitAdmin rabbitAdmin;

    public static void main(String[] args) {
        SpringApplication.run(HelloServiceApplication.class, args);
    }

    @GetMapping("/create")
    public String create(){
        rabbitAdmin.declareQueue(new Queue("testQueue"));

        return "creating ....";
    }


    @GetMapping("/get")
    public String hello(){
//        List<Object[]> objects = entityManager.createNativeQuery("select * from accounts").getResultList();

        var x =  rabbitAdmin.getQueueInfo("testQueue");
//
//
//        return t+" "+objects.get(0)[0]+" "+x.getName();
        if(Objects.nonNull(x)){
            return t+" Hello "+x.getName();
        }
        else{
            return t+" Hello without x";
        }

    }

}
