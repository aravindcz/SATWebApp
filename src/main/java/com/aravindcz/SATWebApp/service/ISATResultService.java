package com.aravindcz.SATWebApp.service;

import com.aravindcz.SATWebApp.dto.SATResultDTO;
import com.aravindcz.SATWebApp.model.SATResult;

import java.util.List;

public interface ISATResultService {

    public SATResult postResult(SATResultDTO satResultDTO);

    public List<SATResult> getResults();

    public long getRank(String name);

    public void updateScore(String name,int score);

    public void deleteRecord(String name);

}
