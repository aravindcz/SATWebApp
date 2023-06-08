package com.aravindcz.SATWebApp.service;

import com.aravindcz.SATWebApp.dto.SATResultDTO;
import com.aravindcz.SATWebApp.exception.NameAlreadyExistsException;
import com.aravindcz.SATWebApp.exception.ResultNotFoundException;
import com.aravindcz.SATWebApp.model.SATResult;
import com.aravindcz.SATWebApp.repository.ISATResultRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class SATResultService implements ISATResultService {

    @Autowired
    private ISATResultRepository satResultRepository;

    @Autowired
    private ObjectMapper objectMapper;

    //thread safe list for calculating ranks
    private CopyOnWriteArrayList<SATResult> satResultCopyOnWriteArrayList;

    public SATResultService(){
        this.satResultCopyOnWriteArrayList = new CopyOnWriteArrayList<SATResult>();
    }


    public SATResult postResult(SATResultDTO satResultDTO){

        try{
            SATResult satResult = getSATResultFromSATResultDTO(satResultDTO);
            if(satResultRepository.existsById(satResult.getName()))
                throw new NameAlreadyExistsException();
            satResultCopyOnWriteArrayList.add(satResult);
            satResultRepository.save(satResult);
            return satResult;
        } catch (Exception e){
            log.error("Some kind of exception occured",e);
            throw e;
        }


    }


    public List<SATResult> getResults(){

        try{
            List<SATResult> satResultList = satResultRepository.findAll();
            return satResultList;
        } catch (Exception e){
            log.error("Some kind of exception occured",e);
            throw e;
        }


    }

    public long getRank(String name){

        try{
            if(!satResultRepository.existsById(name))
                throw new ResultNotFoundException();
            long rank = getRankFromThreadSafeList(name);
            return rank;
        } catch (Exception e){
            log.error("Some kind of exception occured",e);
            throw e;
        }

    }

    public void updateScore(String name,int score){

        SATResult satResult;

        try{
            if(!satResultRepository.existsById(name))
                throw  new ResultNotFoundException();
            Optional<SATResult> optionalSATResult = satResultRepository.findById(name);
            if(optionalSATResult.get() != null){
                satResult = optionalSATResult.get();
                satResult.setScore(score);
                satResult=setPassOrFailBasedOnScore(satResult);
                satResultRepository.save(satResult);
                updateScoreInThreadSafeList(name,score);
            }
            else throw new ResultNotFoundException();
        } catch (Exception e){
            log.error("Some kind of exception occured",e);
            throw e;
        }

    }

    public void deleteRecord(String name){

        try{
            if(!satResultRepository.existsById(name))
                throw new ResultNotFoundException();
            satResultRepository.deleteById(name);
            deleteRecordFromThreadSafeList(name);

        } catch (Exception e){
            log.error("Some kind of exception occured",e);
            throw e;
        }

    }




    private SATResult getSATResultFromSATResultDTO(SATResultDTO satResultDTO){

        SATResult satResult = objectMapper.convertValue(satResultDTO,SATResult.class);
        satResult = setPassOrFailBasedOnScore(satResult);
        return satResult;

    }

    private SATResult setPassOrFailBasedOnScore(SATResult satResult){

        int score = satResult.getScore();
        if((((float)score/1600)*100)>30)
            satResult.setPassed(true);
        else satResult.setPassed(false);

        return satResult;

    }


    public long getRankFromThreadSafeList(String name){

        //sorting the thread safe collection based on the score
        //in such a way that examinee with highes score comes first in the list
        Collections.sort(satResultCopyOnWriteArrayList, new Comparator<SATResult>() {
            @Override
            public int compare(SATResult o1, SATResult o2) {
                if(o1.getScore() >= o2.getScore())
                    return -1;
                else return 1;
            }
        });

        //calculating rank from the list based on the name

        long rank = 0,previousScore = -1;

        for(SATResult satResult : satResultCopyOnWriteArrayList){

            if(satResult.getScore() == previousScore){
                if(satResult.getName().equals(name))
                    return rank;

            }
            else {
                ++rank;
                if(satResult.getName().equals(name))
                    return rank;
                previousScore = satResult.getScore();
            }

        }

        return rank;

    }

    public void updateScoreInThreadSafeList(String name,int score){

        for(SATResult satResult : satResultCopyOnWriteArrayList){
            if(satResult.getName().equals(name))
                satResult.setScore(score);
        }

    }

    public void deleteRecordFromThreadSafeList(String name){

        for(SATResult satResult : satResultCopyOnWriteArrayList){
            if(satResult.getName().equals(name))
                satResultCopyOnWriteArrayList.remove(satResult);

        }

    }


}
