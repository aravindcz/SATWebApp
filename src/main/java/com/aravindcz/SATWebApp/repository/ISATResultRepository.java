package com.aravindcz.SATWebApp.repository;

import com.aravindcz.SATWebApp.model.SATResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ISATResultRepository extends JpaRepository<SATResult, String> {

}
