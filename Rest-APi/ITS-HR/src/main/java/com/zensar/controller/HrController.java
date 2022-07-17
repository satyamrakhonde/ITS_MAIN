package com.zensar.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.dto.Candidate;
import com.zensar.dto.InterviewSchedule;
import com.zensar.service.HrServices;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/hr")
@CrossOrigin(origins="*")
public class HrController {
	
	@Autowired
	HrServices hrService;
	
	//15
	@GetMapping(value="/candidates",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Reads all status", notes="Returns status")
	public ResponseEntity<List<Candidate>> viewInterviewMembers(@RequestHeader("Authorization") String authToken){
		return new ResponseEntity<List<Candidate>>(hrService.viewInterviewMembers(authToken), HttpStatus.OK);
		
	}
	
	//16
	@PutMapping(value="/interview/{id}",consumes=MediaType.APPLICATION_JSON_VALUE, produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Reads all status", notes="Returns status")
	public ResponseEntity<InterviewSchedule> giveRating(@PathVariable("id") int id, @RequestBody InterviewSchedule interviewDto, @RequestHeader("Authorization") String authToken) {
		return new ResponseEntity<InterviewSchedule>(hrService.giveRating( id, interviewDto, authToken), HttpStatus.OK);
	}
	
	//17
	@GetMapping(value="/candidates/{id}",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Reads all status", notes="Returns status")
	public ResponseEntity<Candidate> viewCandidatesById(@PathVariable("id") int id, @RequestHeader("Authorization") String authToken) {
		return new ResponseEntity<Candidate>(hrService.viewCandidatesById(id, authToken), HttpStatus.OK);
		
	}
	
	
  //4
	@DeleteMapping(value = "/hr/{id}")
	public boolean resignHrPanelMember(@PathVariable("id") int id, @RequestHeader("Authorization") String authToken) {
	return hrService.resignHrPanelMember(id, authToken);

	}
}
