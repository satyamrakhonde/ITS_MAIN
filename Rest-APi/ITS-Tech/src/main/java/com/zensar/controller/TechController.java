package com.zensar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.dto.Candidate;
import com.zensar.dto.InterviewSchedule;
import com.zensar.service.TechService;

@RestController
@RequestMapping("/tech")
@CrossOrigin(origins = "*")
public class TechController {

	@Autowired
	TechService techService;

	@GetMapping(value = "/candidate", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Candidate> viewInterviewCandidates(@RequestHeader("Authorization") String authToken) {
      return techService.viewInterviewCandidates(authToken);
	}

	@PutMapping(value = "/interview/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public InterviewSchedule giveTechRating(@PathVariable("id") int id,
			@RequestBody InterviewSchedule interview, @RequestHeader("Authorization") String authToken) {
		return techService.giveTechRating(id, interview, authToken);
		
	}

	@GetMapping(value = "/candidate/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Candidate getCandidateById(@PathVariable("id") int id, @RequestHeader("Authorization") String authToken) {
		return techService.getCandidateById(id, authToken);

	}


	@DeleteMapping(value = "/tech/{id}")
	public boolean resignTechPanelMember(@PathVariable("id")  int id, @RequestHeader("Authorization") String authToken) {
		return techService.resignTechPanelMember(id, authToken);
		
	}


	@PostMapping(value = "/candidate/entry", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public Candidate addCandidate(@RequestBody Candidate candidate) {
		return techService.addCandidate(candidate);
		
	}

	
	@PostMapping(value = "/schedule/entry", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public InterviewSchedule scheduleInterview(@RequestBody InterviewSchedule interviewSchedule) {
		return techService.scheduleInterview(interviewSchedule);
		
	}

	//check

	@GetMapping(value = "/interview", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<InterviewSchedule> viewInterviewSchedules() {
		return techService.viewInterviewSchedules();
		
	}
}


