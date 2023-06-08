package com.aravindcz.SATWebApp.controller;

import com.aravindcz.SATWebApp.dto.MenuDTO;
import com.aravindcz.SATWebApp.dto.ResponseDTO;
import com.aravindcz.SATWebApp.dto.SATResultDTO;
import com.aravindcz.SATWebApp.dto.ScoreDTO;
import com.aravindcz.SATWebApp.model.SATResult;
import com.aravindcz.SATWebApp.service.ISATResultService;
import com.aravindcz.SATWebApp.service.SATResultService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Validated
public class SATResultController {

    @Autowired
    private MenuDTO menuDTO;

    @Autowired
    ISATResultService satResultService;

    @GetMapping("/menu")
    public ResponseEntity<ResponseDTO> getMenu(){
        ResponseEntity<ResponseDTO> responseEntity;

        try {
           responseEntity  = new ResponseEntity<ResponseDTO>(new ResponseDTO("success",200,"Successfully got menu options",menuDTO), HttpStatus.OK);
        } catch (Exception e){
            log.error("Some kind of exception occured: ",e);
            throw e;
        }
        return responseEntity;
    }

    @PostMapping("/results")
    public ResponseEntity<ResponseDTO> postResult(@Valid@RequestBody SATResultDTO satResultDTO){
        ResponseEntity<ResponseDTO> responseEntity;

        try {
            SATResult satResult = satResultService.postResult(satResultDTO);
            responseEntity  = new ResponseEntity<ResponseDTO>(new ResponseDTO("success",201,"Successfully saved the results",satResult), HttpStatus.CREATED);
        } catch (Exception e){
            log.error("Some kind of exception occured: ",e);
            throw e;
        }
        return responseEntity;
    }


    @GetMapping("/results")
    public ResponseEntity<ResponseDTO> getResults(){
        ResponseEntity<ResponseDTO> responseEntity;

        try {
            List<SATResult> satResultList = satResultService.getResults();
            responseEntity  = new ResponseEntity<ResponseDTO>(new ResponseDTO("success",200,"Successfully retrieved the results",satResultList), HttpStatus.OK);
        } catch (Exception e){
            log.error("Some kind of exception occured: ",e);
            throw e;
        }
        return responseEntity;
    }


    @GetMapping("/results/{name}/rank")
    public ResponseEntity<ResponseDTO> getRank(@PathVariable@NotBlank String name){
        ResponseEntity<ResponseDTO> responseEntity;

        try {
            long rank = satResultService.getRank(name);
            responseEntity  = new ResponseEntity<ResponseDTO>(new ResponseDTO("success",200,"Successfully retrieved the rank",rank), HttpStatus.OK);
        } catch (Exception e){
            log.error("Some kind of exception occured: ",e);
            throw e;
        }
        return responseEntity;
    }


    @PutMapping("/results/{name}")
    public ResponseEntity<ResponseDTO> updateScore(@PathVariable@NotBlank String name, @RequestBody ScoreDTO scoreDTO){

        int score = scoreDTO.getScore();

        ResponseEntity<ResponseDTO> responseEntity;

        try {
            satResultService.updateScore(name,score);
            responseEntity  = new ResponseEntity<ResponseDTO>(new ResponseDTO("success",204,"Successfully updated the score",null), HttpStatus.NO_CONTENT);
        } catch (Exception e){
            log.error("Some kind of exception occured: ",e);
            throw e;
        }
        return responseEntity;
    }


    @DeleteMapping("/results/{name}")
    public ResponseEntity<ResponseDTO> deleteRecord(@PathVariable@NotBlank String name){
        ResponseEntity<ResponseDTO> responseEntity;

        try {
            satResultService.deleteRecord(name);
            responseEntity  = new ResponseEntity<ResponseDTO>(new ResponseDTO("success",204,"Successfully deleted the record",null), HttpStatus.NO_CONTENT);
        } catch (Exception e){
            log.error("Some kind of exception occured: ",e);
            throw e;
        }
        return responseEntity;
    }



}
