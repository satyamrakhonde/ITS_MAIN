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
import com.zensar.exception.InvalidAuthTokenException;
import com.zensar.exception.InvalidIdException;
import com.zensar.repo.CandidateRepo;
import com.zensar.repo.InterviewRepo;

@Service
public class HrServiceImpl implements HrServices{

	@Autowired
	CandidateRepo candidateRepo;
	
	@Autowired
	InterviewRepo interviewRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	UserServiceDelegate userService;
	@Autowired
	AdminDelegateServiceImpl adminService;
	
	
	@Override
	public List<Candidate> viewInterviewMembers(String authToken) {
		if(userService.isTokenValid(authToken)) {
			List<CandidateEntity> candidateEntity= candidateRepo.findAll();
			List<Candidate> candidateDto= new ArrayList<>();
			for(CandidateEntity candidate: candidateEntity)
				candidateDto.add(convertCandidateEntityIntoDto(candidate));
			return candidateDto;
		}
		throw new InvalidAuthTokenException();
	}

	@Override
	public InterviewSchedule giveRating(int id, InterviewSchedule interviewDto, String authToken) {
		if(userService.isTokenValid(authToken)) {
			Optional<InterviewScheduleEntity> optionalInterviewEntity = interviewRepo.findById(id);
			if(optionalInterviewEntity.isPresent()) {
			InterviewScheduleEntity interviewEntity = optionalInterviewEntity.get();
			interviewEntity.setHrRating(interviewDto.getHrRating());
			if(interviewEntity.getHrRating()>5&& interviewEntity.getTechRating()>5) {
				interviewEntity.setFinalStatus("PASS");
			}
			if(interviewEntity.getHrRating()<5 || interviewEntity.getTechRating()<5) {
				interviewEntity.setFinalStatus("FAIL");
			}
			interviewRepo.save(interviewEntity);
			return convertInterviewEntityIntoDto(interviewEntity);
			}
			throw new InvalidIdException();
			}
		throw new InvalidAuthTokenException();
		}
			
		


	@Override
	public Candidate viewCandidatesById(int id, String authToken) {
		if(userService.isTokenValid(authToken)) {
			Optional<CandidateEntity> candidateId=candidateRepo.findById(id);
			if(candidateId.isPresent()) {
				return convertCandidateEntityIntoDto(candidateId.get());
				
			}
			throw new InvalidIdException();
		}
		throw new InvalidAuthTokenException();
		
	}
	@Override
	public boolean resignHrPanelMember(int id, String authToken) {
		if(userService.isTokenValid(authToken)) {
			return adminService.isDeleteSuccessful(id);
		}
		throw new InvalidAuthTokenException();
	}
	

	
	
	 
	private CandidateEntity convertCandidateDtoIntoEntity(Candidate candidate) {
		CandidateEntity candidateEntity=modelMapper.map(candidate, CandidateEntity.class);
		return candidateEntity;
		}

		private Candidate convertCandidateEntityIntoDto(CandidateEntity candidateEntity) {
		Candidate candidate=modelMapper.map(candidateEntity, Candidate.class);
		return candidate;
		}

		private InterviewScheduleEntity convertInterviewScheduleDtoIntoEntity(InterviewSchedule interviewSchedule) {
		InterviewScheduleEntity interviewScheduleEntity=modelMapper.map(interviewSchedule,InterviewScheduleEntity.class);
		return interviewScheduleEntity;
		}

		private InterviewSchedule convertInterviewEntityIntoDto(InterviewScheduleEntity interviewScheduleEntity) {
		InterviewSchedule interviewSchedule=modelMapper.map(interviewScheduleEntity, InterviewSchedule.class);
		return interviewSchedule;
		}

		
		

		
		
	
	

}
