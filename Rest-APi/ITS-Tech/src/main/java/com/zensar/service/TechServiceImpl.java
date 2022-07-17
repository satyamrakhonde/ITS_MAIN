package com.zensar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.dto.Candidate;
import com.zensar.dto.InterviewSchedule;
import com.zensar.entity.CandidateEntity;
import com.zensar.entity.InterviewScheduleEntity;
import com.zensar.repo.CandidateRepo;
import com.zensar.repo.InterviewRepo;

@Service
public class TechServiceImpl implements TechService{
	
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	CandidateRepo candidateRepo;
	@Autowired
	InterviewRepo interviewRepo;
	@Autowired
	AdminServiceDelegate adminServiceDelgate;
	@Autowired
	UserServiceDelegate userServiceDelegate;

	@Override
	public List<Candidate> viewInterviewCandidates(String authToken) {
		List<CandidateEntity> candidateEntity = candidateRepo.findAll();
		List<Candidate> candidateList = new ArrayList<>();
			for(CandidateEntity candidates: candidateEntity)
				candidateList.add(convertCandidateEntityIntoDto(candidates));
		return candidateList;
	}

	@Override
	public InterviewSchedule giveTechRating(int id, InterviewSchedule interview, String authToken) {
		Optional<InterviewScheduleEntity> optionalInterviewEntity = interviewRepo.findById(id);
		if(optionalInterviewEntity.isPresent()) {
			InterviewScheduleEntity interviewEntity = optionalInterviewEntity.get();
			interviewEntity.setTechRating(interview.getTechRating());
			interviewRepo.save(interviewEntity);
			return convertInterviewEntityIntoDto(interviewEntity);
		}
		return null;
	}

	@Override
	public Candidate getCandidateById(int id, String authToken) {
		Optional<CandidateEntity> candidateId=candidateRepo.findById(id);
		if(candidateId.isPresent()) {
		return convertCandidateEntityIntoDto(candidateId.get());
		}
		return null;
	}

	@Override
	public boolean resignTechPanelMember(int id, String authToken) {
		if(userServiceDelegate.isTokenValid(authToken)) {
			return adminServiceDelgate.isDeleteSuccessful(id);
		}
		return false;
	}

	@Override
	public Candidate addCandidate(Candidate candidate) {
	CandidateEntity candidateEntity=convertCandidateDtoIntoEntity(candidate);
	candidateRepo.save(candidateEntity);
	return convertCandidateEntityIntoDto(candidateEntity);
	}



	@Override
	public InterviewSchedule scheduleInterview(InterviewSchedule interviewSchedule) {
	InterviewScheduleEntity interviewScheduleEntity=convertInterviewDtoIntoEntity(interviewSchedule);
	interviewRepo.save(interviewScheduleEntity);
	return convertInterviewEntityIntoDto(interviewScheduleEntity);
	}



	@Override
	public List<InterviewSchedule> viewInterviewSchedules() {
	List<InterviewScheduleEntity> interviewEntities=interviewRepo.findAll();
	//if(candidateEntities.isEmpty()) { throw new Exception; }
	List<InterviewSchedule> interviewSchedules=new ArrayList<InterviewSchedule>();
	for(InterviewScheduleEntity interviewScheduleEntity:interviewEntities) {
	interviewSchedules.add(convertInterviewEntityIntoDto(interviewScheduleEntity));
	}
	return interviewSchedules;
	}
	
	private CandidateEntity convertCandidateDtoIntoEntity(Candidate candidate) {
		CandidateEntity candidateEntity=modelMapper.map(candidate, CandidateEntity.class);
		return candidateEntity;
		}



		private Candidate convertCandidateEntityIntoDto(CandidateEntity candidateEntity) {
		Candidate candidate=modelMapper.map(candidateEntity, Candidate.class);
		return candidate;
		}
		
		private InterviewSchedule convertInterviewEntityIntoDto(InterviewScheduleEntity interviewEntity) {
			InterviewSchedule interview=modelMapper.map(interviewEntity, InterviewSchedule.class);
			return interview;
		}
		private InterviewScheduleEntity convertInterviewDtoIntoEntity(InterviewSchedule interview) {
			InterviewScheduleEntity interviewEntity=modelMapper.map(interview, InterviewScheduleEntity.class);
			return interviewEntity;
		}

}
