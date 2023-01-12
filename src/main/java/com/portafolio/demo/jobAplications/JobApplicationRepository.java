package com.portafolio.demo.jobAplications;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer>  {

}
