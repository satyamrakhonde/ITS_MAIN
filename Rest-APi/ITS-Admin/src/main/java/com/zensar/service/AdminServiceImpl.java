package com.zensar.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.dto.Candidate;
import com.zensar.dto.InterviewSchedule;
import com.zensar.dto.PanelMember;
import com.zensar.entity.CandidateEntity;
import com.zensar.entity.InterviewScheduleEntity;
import com.zensar.entity.PanelMemberEntity;
import com.zensar.exception.InvalidDataShared;
import com.zensar.exception.InvalidIdException;
import com.zensar.exception.InvalidAuthTokenException;

import com.zensar.repo.CandidateRepo;
import com.zensar.repo.InterviewScheduleRepo;
import com.zensar.repo.PanelMemberRepo;

import com.zensar.security.*;

@Service
public class AdminServiceImpl implements AdminServices {

	@Autowired
	EntityManager entityManager;
	@Autowired
	CandidateRepo candidateRepo;

	@Autowired
	PanelMemberRepo panelMemberRepo;

	@Autowired
	InterviewScheduleRepo interviewScheduleRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	UserServiceDelegate userServiceDelegate;
	@Autowired
	PanelServiceDelegate panelServiceDelegate;

	// utils - methods
	private Candidate convertEntityintoDTOForCandidate(CandidateEntity candidateEntity) {

		Candidate candidate = modelMapper.map(candidateEntity, Candidate.class);
		return candidate;
	}

	private CandidateEntity convertDTOintoEntityForCandidate(Candidate candidate) {

		CandidateEntity candidateEntity = modelMapper.map(candidate, CandidateEntity.class);
		return candidateEntity;
	}

	// utils
	private InterviewScheduleEntity convertDTOIntoEntityForInterviewSchedule(InterviewSchedule interviewScheduleDto) {
		TypeMap<InterviewSchedule, InterviewScheduleEntity> tMap = modelMapper.typeMap(InterviewSchedule.class,
				InterviewScheduleEntity.class);
		InterviewScheduleEntity interviewScheduleEntity = modelMapper.map(interviewScheduleDto,
				InterviewScheduleEntity.class);
		return interviewScheduleEntity;
	}

	private InterviewSchedule convertEntityIntoDTOForInterviewScheduleSchedule(
			InterviewScheduleEntity interviewScheduleEntity) {
		TypeMap<InterviewScheduleEntity, InterviewSchedule> tMap = modelMapper.typeMap(InterviewScheduleEntity.class,
				InterviewSchedule.class);
		InterviewSchedule interviewScheduleDto = modelMapper.map(interviewScheduleEntity, InterviewSchedule.class);
		return interviewScheduleDto;
	}

	@Override
	public Candidate registerCandidate(Candidate candidate) {
		CandidateEntity candidateEntity = convertDTOintoEntityForCandidate(candidate);
		candidateEntity = candidateRepo.save(candidateEntity);

		return convertEntityintoDTOForCandidate(candidateEntity);
	}

	// logic
	
	//extra
	@Override
	public List<InterviewSchedule> getAllInterviews() {
		List<InterviewScheduleEntity> interviewScheduleEntityList = interviewScheduleRepo.findAll();
		List<InterviewSchedule> interviewScheduleList= new ArrayList<InterviewSchedule>();
		for (InterviewScheduleEntity interviewScheduleEntity : interviewScheduleEntityList) {

			InterviewSchedule interviewSchedule = convertEntityIntoDTOForInterviewScheduleSchedule(interviewScheduleEntity);
			interviewScheduleList.add(interviewSchedule);
		}
		return interviewScheduleList;
	}

	// 6- delete interview by id
	
	

	@Override
	public boolean deleteInterviewScheduleByID(int id, String token) {
		if (userServiceDelegate.isTokenValid(token)) {
			if (interviewScheduleRepo.existsById(id)) {
				interviewScheduleRepo.deleteById(id);
				return true;
			}
			throw new InvalidIdException();

		}
		throw new InvalidAuthTokenException();
	}

//    private List<InterviewScheduleEntity> checkUser(String token) {
//
//	token = token.substring(7);
//	String uname = jwtUtil.extractUsername(token);
//	List<InterviewScheduleEntity> adList = InterviewScheduleRepo.findByUsername(uname);
//	if (adList == null) {
//	    throw new InvalidAuthTokenException(uname);
//	}
//	return adList;
//    }

	@Override
	public List<Candidate> getAllCandidates() {

		List<CandidateEntity> candidateEntityList = candidateRepo.findAll();
		List<Candidate> candidateDtoList = new ArrayList<Candidate>();
		for (CandidateEntity candidateEntity : candidateEntityList) {

			Candidate candidate = convertEntityintoDTOForCandidate(candidateEntity);
			candidateDtoList.add(candidate);
		}
		return candidateDtoList;
	}

	@Override
	public Candidate getCandidateById(int id) {

		Optional<CandidateEntity> opCandidateEntity = candidateRepo.findById(id);
		if (opCandidateEntity.isPresent()) {
			CandidateEntity candidateEntity = opCandidateEntity.get();
			return convertEntityintoDTOForCandidate(candidateEntity);
		}
		throw new InvalidIdException();
	}

	@Override
	public PanelMember addPanelMember(PanelMember panelMember) {
		PanelMemberEntity panelMemberEntity = convertDTOIntoEntity(panelMember);
		panelMemberEntity = panelMemberRepo.save(panelMemberEntity);

		return convertEntityIntoDTO(panelMemberEntity);
	}

	// nagaraj
//	private PanelMember convertEntityIntoDTO(PanelMemberEntity panelMemberEntity) {
//
//		PanelMember panelMember = modelMapper.map(panelMemberEntity, PanelMember.class);
//		return panelMember;
//	}

//	private PanelMemberEntity convertDTOIntoEntity(PanelMember panelMember) {
//
//		PanelMemberEntity panelMemberEntity = modelMapper.map(panelMember, PanelMemberEntity.class);
//		return panelMemberEntity;
//	}

	// change method name
	@Override
	public String shareCandidateWithTech(int interviewScheduleId, String token) {
		if (!userServiceDelegate.isTokenValid(token)) {
			throw new InvalidAuthTokenException();

		}

		InterviewScheduleEntity interviewScheduleEntity2 = interviewScheduleRepo.getById(interviewScheduleId);

		int candidateId = interviewScheduleEntity2.getCandidateId();
		CandidateEntity candidateEntity2 = candidateRepo.getById(candidateId);

		if (candidateEntity2 != null && interviewScheduleEntity2 != null) {
			// delegate
			panelServiceDelegate.shareCandidateWithPanel(convertEntityintoDTOForCandidate(candidateEntity2));

			panelServiceDelegate
					.shareScheduleWithPanel(convertEntityIntoDTOForInterviewScheduleSchedule(interviewScheduleEntity2));
			return "data shared successfully";

		} 
		
		else {
			throw new InvalidDataShared();

		}

	}



	@Override
	public InterviewSchedule createInterviewSchedule(InterviewSchedule interviewScheduleDto, String token) {
		if (userServiceDelegate.isTokenValid(token)) {
			interviewScheduleRepo.save(convertDTOIntoEntityForInterviewSchedule(interviewScheduleDto));
			return interviewScheduleDto;
		}
		throw new InvalidAuthTokenException();
	}

//	@Override
//	public boolean deleteInterviewScheduleByID(int id) {
//		if (interviewScheduleRepo.existsById(id)) {
//			interviewScheduleRepo.deleteById(id);
//			return true;
//		}
//		throw new InvalidIdException();
//	}

	public List<PanelMember> getAllPanelMembers(String token) {

		if (userServiceDelegate.isTokenValid(token)) {
			List<PanelMemberEntity> panelMemberEntity = panelMemberRepo.findAll();
			List<PanelMember> panelMember = new ArrayList<PanelMember>();
			Iterator<PanelMemberEntity> itrPanelEntities = panelMemberEntity.iterator();
			while (itrPanelEntities.hasNext()) {
				PanelMember panels = convertEntityIntoDTO(itrPanelEntities.next());
				panelMember.add(panels);
				return panelMember;

			}
		}
		throw new InvalidAuthTokenException();

	}

	@Override
	public boolean deleteTechMember(int id) {

		if (panelMemberRepo.existsById(id)) {
			if (panelMemberRepo.getById(id).getType().equalsIgnoreCase("Tech")) {
				panelMemberRepo.deleteById(id);
				return true;
			}
		}
		throw new InvalidIdException();
	}

	@Override
	public boolean deleteHRMember(int id) {

		if (panelMemberRepo.existsById(id)) {
			if (panelMemberRepo.getById(id).getType().equalsIgnoreCase("HR")) {
				panelMemberRepo.deleteById(id);
				return true;
			}
			throw new InvalidIdException();

		}
		throw new InvalidIdException();
	}

	@Override
	public List<PanelMember> searchEmployee(Integer id, String name) {
		CriteriaBuilder critertiaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PanelMemberEntity> criteriaQuery = critertiaBuilder.createQuery(PanelMemberEntity.class);
		Root<PanelMemberEntity> rootEntity = criteriaQuery.from(PanelMemberEntity.class);

		Predicate predicateId = critertiaBuilder.and();
		Predicate predicateName = critertiaBuilder.and();
		Predicate predicateFinal = critertiaBuilder.and();

		if (name != null && !"".equalsIgnoreCase(name)) {
			predicateName = critertiaBuilder.like(rootEntity.get("name"), "%" + name + "%");

		}
		if (id != null) {
			predicateId = critertiaBuilder.equal(rootEntity.get("employeeId"), id);
		}

		predicateFinal = critertiaBuilder.and(predicateId, predicateName);
		criteriaQuery.where(predicateFinal);

		TypedQuery<PanelMemberEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		List<PanelMemberEntity> panelEntityList = typedQuery.getResultList();
		// write a convert and return InterviewScheduleertise list here
		List<PanelMember> panelList = new ArrayList<>();
		for (PanelMemberEntity p : panelEntityList)
			panelList.add(convertEntityIntoDTO(p));

		return panelList;
	}
//comment

//	@Override
//	public List<Candidate> getAllCandidates(String token) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	// satyam

	@Override
	public Candidate registerCandidate(Candidate candidate, String authToken) {
		if (userServiceDelegate.isTokenValid(authToken)) {
			CandidateEntity candidateEntity = convertDTOintoEntity(candidate);
			candidateEntity = candidateRepo.save(candidateEntity);

			return convertEntityintoDTO(candidateEntity);
		}
		throw new InvalidAuthTokenException();
	}

	private Candidate convertEntityintoDTO(CandidateEntity candidateEntity) {

		Candidate candidate = modelMapper.map(candidateEntity, Candidate.class);
		return candidate;
	}

	private CandidateEntity convertDTOintoEntity(Candidate candidate) {

		CandidateEntity candidateEntity = modelMapper.map(candidate, CandidateEntity.class);
		return candidateEntity;
	}

	@Override
	public List<Candidate> getAllCandidates(String authToken) {
		if (userServiceDelegate.isTokenValid(authToken)) {
			List<CandidateEntity> candidateEntityList = candidateRepo.findAll();
			List<Candidate> candidateDtoList = new ArrayList<Candidate>();
			for (CandidateEntity candidateEntity : candidateEntityList) {

				Candidate candidate = convertEntityintoDTO(candidateEntity);
				candidateDtoList.add(candidate);
			}
			return candidateDtoList;
		}
		throw new InvalidAuthTokenException();
	}

	@Override
	public Candidate getCandidateById(int id, String authToken) {
		if (userServiceDelegate.isTokenValid(authToken)) {
			Optional<CandidateEntity> opCandidateEntity = candidateRepo.findById(id);
			if (opCandidateEntity.isPresent()) {
				CandidateEntity candidateEntity = opCandidateEntity.get();
				return convertEntityintoDTO(candidateEntity);
			}
		}
		throw new InvalidIdException();
	}

	@Override
	public PanelMember addPanelMember(PanelMember panelMember, String authToken) {
		if (userServiceDelegate.isTokenValid(authToken)) {
			PanelMemberEntity panelMemberEntity = convertDTOIntoEntity(panelMember);
			panelMemberEntity = panelMemberRepo.save(panelMemberEntity);

			return convertEntityIntoDTO(panelMemberEntity);
		}
		throw new InvalidAuthTokenException();
	}

	private PanelMember convertEntityIntoDTO(PanelMemberEntity panelMemberEntity) {

		PanelMember panelMember = modelMapper.map(panelMemberEntity, PanelMember.class);
		return panelMember;
	}

	private PanelMemberEntity convertDTOIntoEntity(PanelMember panelMember) {

		PanelMemberEntity panelMemberEntity = modelMapper.map(panelMember, PanelMemberEntity.class);
		return panelMemberEntity;
	}



}
