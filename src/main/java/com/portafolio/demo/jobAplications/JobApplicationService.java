package com.portafolio.demo.jobAplications;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Component
public class JobApplicationService {
    public JobApplication getApplications(){
        return this.getProxyApplication();
    }

    private JobApplication getProxyApplication(){
        return new JobApplication(0,
                "Provexpress Sas",
                "https://www.provexpress.com.co/",
                "Rafael Novoa",
                LocalDate.of(2022,11,15),
                "Dismissed",
                LocalDate.of(2023,1,1),
                "IT Engineer",
                "Office",
                2000000L);
    }
}
