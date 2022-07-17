package com.zensar.service;

import java.util.List;

import com.zensar.dto.Candidate;
import com.zensar.dto.InterviewSchedule;
import com.zensar.dto.PanelMember;

public interface AdminServices {

	public Candidate registerCandidate(Candidate candidate);

//	public List<Candidate> getAllCandidates(String token);

	public Candidate getCandidateById(int id);

	public PanelMember addPanelMember(PanelMember panelMember);

	public String shareCandidateWithTech(int id,String token);



	InterviewSchedule createInterviewSchedule(InterviewSchedule interviewScheduleDto,String token);

//	boolean deleteInterviewScheduleByID(int id);
        
	public List<PanelMember> searchEmployee(Integer id, String name);

	public boolean deleteHRMember(int id);

	public List<PanelMember> getAllPanelMembers(String token);

	boolean deleteTechMember(int id);

	public boolean deleteInterviewScheduleByID(int id, String token);

	public List<Candidate> getAllCandidates();

//<<<<<<< HEAD
//	boolean deleteHRMember(int id);
//
//	boolean deleteTechMember(int id);
//
//	public List<PanelMember> getAllPanelMembers();
//
//=======
//	public List<PanelMember> getAllPanelMembers();
//>>>>>>> branch 'main' of https://github.com/Rajamr550/ITS.git
//
//	public boolean deleteTechMember(int id);
//
//	public boolean deleteHRMember(int id);
	
	
	//satyam
	
	public Candidate registerCandidate(Candidate candidate, String authToken);

	public List<Candidate> getAllCandidates(String authToken);

	public Candidate getCandidateById(int id,String authToken);

	public PanelMember addPanelMember(PanelMember panelMember, String authToken);

	public List<InterviewSchedule> getAllInterviews();
	
}
