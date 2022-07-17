/*
 * post
 * 	adding - candidate,interview,hr,tech
 * 
 * get
 * 	view - candidate,hr,tech
 * 	8.search rmployee by id or name[custom repo]
 * 	3.share the candidates
 * 		switch("tech")
 * 			[candidateId ---> techId]
 * 			hashmap[canId,techId];
 * 			
 * 
 * put
 * 	priority -2
 * 	
 * 
 * Delete
 *    cancel interview
 *    delete panel members
 *    
 *    
 *Priroity - 2
 *	listing all the interview list - get
 *	delete candidate - delete
 *	
 *
 *other endpoints 
 *	getByCandidateId(id)
 * 
 * 
 * 
 * 
 * 
 * 
 */

package com.zensar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.dto.Candidate;
import com.zensar.dto.InterviewSchedule;
import com.zensar.dto.PanelMember;
import com.zensar.service.AdminServices;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="*")
public class AdminController {

	@Autowired
	AdminServices adminServices;
	
	//satyam
	//1--Register a candidate


	@PostMapping(value="/candidate", consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Adding a candidate", notes = "This Rest API will add Candidate Info")
	public Candidate registerCandidate(@RequestBody Candidate candidate, @RequestHeader("Authorization") String authToken) {
		return adminServices.registerCandidate(candidate, authToken);
	}
	
	//2.1-- View All Candidate 
	@GetMapping(value="/candidate", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Get all candidate", notes = "This Rest API will help to view all candidate")
	public List<Candidate> getAllCandidates(@RequestHeader("Authorization") String authToken){
		return adminServices.getAllCandidates(authToken);
	}
	
	//2.2 -- View a candidate By Id
	@GetMapping(value="/candidate/{id}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Get a candidate by its Id", notes = "This Rest API will help you to view a candidate by ID")
	public Candidate getCandidateById(@PathVariable("id") int id, @RequestHeader("Authorization") String authToken){
		return adminServices.getCandidateById(id, authToken);
	}
	
	//7 -- Adding Panel Members
	@PostMapping(value="/panel", consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Adding a Panel Member", notes = "This Rest API will add Panel member")
	public PanelMember addPanelMember(@RequestBody PanelMember panelMember, @RequestHeader("Authorization") String authToken) {
		return adminServices.addPanelMember(panelMember, authToken);
	}

	
	
	
	//raja
    //services-3,4,6

	
    @PostMapping(value = "/share/tech/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
    	    MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "shareDataWithTech", notes = "This api will map the tech id with candidate id for the interview")

        public String shareCandidateWithTech(@PathVariable("id") int id,@RequestHeader("Authorization") String token) {
    	return adminServices.shareCandidateWithTech(id,token);
        }

        @PostMapping(value = "/interview", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @ApiOperation(value = "createInterview", notes = "This api will create a new interview")

        public InterviewSchedule createInterview(@RequestBody InterviewSchedule interviewDto,@RequestHeader("Authorization") String token) {
    	return adminServices.createInterviewSchedule(interviewDto,token);
        }

        @DeleteMapping(value = "/interview/{id}")
        @ApiOperation(value = "deleteInterviewByID", notes = "This REST API Deletes an interview by id")

        public boolean deleteInterviewScheduleByID(@PathVariable("id") int id,@RequestHeader("Authorization") String token) {
    	return adminServices.deleteInterviewScheduleByID(id,token);
        }
	
        //extra
    	@GetMapping(value="/interviews", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    	@ApiOperation(value = "Get all interviews", notes = "This Rest API will help to view all interviews")
    	public List<InterviewSchedule> getAllInterviews(){
    		return adminServices.getAllInterviews();
    	}
	      //8
        @GetMapping(value="/panel/search",consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})        
        @ApiOperation(value = "searchPanelMembers", notes = "This REST API saerches panel member ")

      		public List<PanelMember>searchEmployee(@RequestParam(name="id",required=false)Integer id,
      				@RequestParam(name="name",required=false)String name){
      			return adminServices.searchEmployee(id,name);
      		}
	
	//9.1
      	@DeleteMapping(value = "/panel/tech/{id}")
        @ApiOperation(value = "deletePanelByTechID", notes = "This REST API Deletes an panel member by id")

      	public boolean deleteTechMember(@PathVariable("id") int id) {
      		return adminServices.deleteTechMember(id);
      	}
      	//9.2
      	@DeleteMapping(value = "/panel/hr/{id}")
        @ApiOperation(value = "deletePanelByHRID", notes = "This REST API Deletes an panel member by id")

      	public boolean deleteHRMember(@PathVariable("id") int id) {
      		return adminServices.deleteHRMember(id);
      	}
      	
      	//10
      	@GetMapping(value = "/panel", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
        @ApiOperation(value = "getAllPanelMembers", notes = "This REST API returns all panel members")

      	public List<PanelMember> getAllPanelMembers(@RequestHeader("Authorization") String token){
      		return adminServices.getAllPanelMembers(token);
      	}
	
	
	
	
	
	
}
