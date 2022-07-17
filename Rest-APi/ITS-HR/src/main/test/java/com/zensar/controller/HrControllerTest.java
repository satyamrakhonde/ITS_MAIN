package com.zensar.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.dto.CandidateDto;
import com.zensar.dto.InterviewDto;
import com.zensar.service.HrServices;

@WebMvcTest(HrController.class)
class HRControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	HrController hrController;

	@MockBean
	HrServices hrService;

	@Autowired
	ObjectMapper objectMapper;

//Service 1
	@Test
	public void testviewInterviewCandidates() throws Exception {
		List<CandidateDto> candidateList = new ArrayList<>();
		candidateList.add(new CandidateDto());
		candidateList.add(new CandidateDto());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "AG66200");

		when(this.hrService.viewInterviewMembers("AG66200")).thenReturn(candidateList);

		MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8006/its-hr/candidate").headers(httpHeaders))
				.andExpect(status().isOk()).andReturn();

		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("location"), true);
	}

//Service 2
	/*
	 * @Test public void testGiveHRRating() throws Exception{
	 * 
	 * InterviewSchedule interviewSchedule=new InterviewSchedule();
	 * interviewSchedule.setFinalStatus("PASSED"); HttpHeaders httpHeaders=new
	 * HttpHeaders(); httpHeaders.set("Authorization", "AG66200");
	 * 
	 * when(this.hrService.giveHRRating(1,interviewSchedule,"AG66200")).thenReturn(
	 * interviewSchedule);
	 * 
	 * MvcResult
	 * mvcResult=this.mockMvc.perform(put("http://localhost:7002/its-hr/interview/"+
	 * 1) .contentType("application/json")
	 * .content(objectMapper.writeValueAsString(interviewSchedule))
	 * .headers(httpHeaders) ) .andExpect(status().isOk())
	 * .andExpect(content().string(containsString("PASSED"))) .andReturn();
	 * 
	 * String response=mvcResult.getResponse().getContentAsString();
	 * assertEquals(response.contains("PASSED"),true); }
	 * 
	 */

//Service 3
	@Test
	public void testGetCandidateById() throws Exception {

		CandidateDto candidate = new CandidateDto();
		candidate.setLocation("Hyderabad");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "AG66200");

		when(this.hrService.viewCandidatesById(1, "AG66200")).thenReturn(candidate);

		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8006/its-hr/candidate/" + 1).headers(httpHeaders))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Hyderabad"))).andReturn();

		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Hyderabad"), true);
	}

//Service 4
	/*
	 * @Test public void testResignHRPanelMember() throws Exception {
	 * 
	 * HttpHeaders httpHeaders = new HttpHeaders(); httpHeaders.set("Authorization",
	 * "AG66200");
	 * 
	 * when(this.hrService.resignHRPanelMember(1, "AG66200")).thenReturn(true);
	 * 
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(delete("http://localhost:7002/its-hr/hr/" +
	 * 1).headers(httpHeaders)) .andExpect(status().isOk()).andReturn();
	 * 
	 * String response = mvcResult.getResponse().getContentAsString();
	 * assertEquals(response.contains("true"), true); }
	 */

}
