package com.zensar.service;

import java.util.List;

import com.zensar.dto.Candidate;
import com.zensar.dto.InterviewSchedule;


public interface TechService {
	public List<Candidate> viewInterviewCandidates(String authToken);
	public InterviewSchedule giveTechRating(int id, InterviewSchedule interview, String authToken);
	public Candidate getCandidateById(int id, String authToken);
	public boolean resignTechPanelMember(int id, String authToken);
	public Candidate addCandidate(Candidate candidate);
	public InterviewSchedule scheduleInterview(InterviewSchedule interviewSchedule);
	public List<InterviewSchedule> viewInterviewSchedules();
	

}
