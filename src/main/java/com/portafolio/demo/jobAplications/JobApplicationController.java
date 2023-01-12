package com.portafolio.demo.jobAplications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class JobApplicationController {
    private JobApplicationService jobApplicationService;

    @Autowired
    public JobApplicationController(JobApplicationService jobApplicationService){
        this.jobApplicationService = jobApplicationService;
    }
    @GetMapping("/applications")
    public @ResponseBody JobApplication getApplications(){
        return this.jobApplicationService.getApplications();
    }


}
