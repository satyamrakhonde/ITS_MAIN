package com.zensar.service;

import java.util.List;

import com.zensar.dto.Candidate;
import com.zensar.dto.InterviewSchedule;

public interface HrServices {
	
	public List<Candidate> viewInterviewMembers(String authToken);
	public InterviewSchedule giveRating(int id, InterviewSchedule interviewDto,String authToken);
	public Candidate viewCandidatesById(int id,String authToken);
	public boolean resignHrPanelMember(int id, String authToken);

}
