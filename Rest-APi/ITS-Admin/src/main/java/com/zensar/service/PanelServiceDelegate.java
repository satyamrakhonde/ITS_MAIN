package com.zensar.service;

import com.zensar.dto.Candidate;
import com.zensar.dto.InterviewSchedule;

public interface PanelServiceDelegate {

	public Candidate shareCandidateWithPanel(Candidate candidate);
	
	public InterviewSchedule shareScheduleWithPanel(InterviewSchedule interviewSchedule);
}
