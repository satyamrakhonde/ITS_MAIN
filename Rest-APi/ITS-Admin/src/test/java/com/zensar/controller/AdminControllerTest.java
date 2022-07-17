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
import com.zensar.dto.Candidate;
import com.zensar.dto.InterviewSchedule;
import com.zensar.dto.PanelMember;
import com.zensar.service.AdminServices;

@WebMvcTest(AdminController.class)
class AdminControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	AdminController adminController;
	
	@MockBean
	AdminServices adminService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	
	//Service 1
		@Test
		public void testAddCandidate() throws Exception{
			
			Candidate candidate=new Candidate();
			candidate.setCandidateName("sample");
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "Tom");
			
			when(this.adminService.registerCandidate(candidate, "Tom")).thenReturn(candidate);
			
			MvcResult mvcResult=this.mockMvc.perform(post("http://localhost:8004/admin/candidate")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(candidate))
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
//					.andExpect(content().string(containsString("sample")))
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("sample"),true);
		}
		
		
		
		//Service 2.1
		@Test
		public void testViewCandidateById() throws Exception{
			
			Candidate candidate=new Candidate();
			candidate.setCandidateName("sample");
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "Tom");
			
			when(this.adminService.getCandidateById(1, "Tom")).thenReturn(candidate);
			
			MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:8004/admin/candidate/"+1)
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("sample")))
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("sample"),true);
		}
		
		//Service 2.2
		@Test
		public void testViewAllCandidates() throws Exception {
			List<Candidate> candidateList=new ArrayList<>();
			candidateList.add(new Candidate());
			candidateList.add(new Candidate());
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "Tom");
			
			when(this.adminService.getAllCandidates("Tom")).thenReturn(candidateList);
			
			MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:8004/admin/candidate")
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andReturn();
			
			String  response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("location"),true);
		}
		
		/*
		//Service 3
			@Test
			public void testShareData() throws Exception{
				
				Candidate candidate=new Candidate();
				candidate.setCandidateName("sample");
				HttpHeaders httpHeaders=new HttpHeaders();
				httpHeaders.set("Authorization", "Tom");
				
				when(this.adminService.shareData(1, "Tom")).thenReturn("success");
				
				MvcResult mvcResult=this.mockMvc.perform(post("http://localhost:8004/admin/candidate/share/"+1)
						.headers(httpHeaders)
						)
						.andExpect(status().isOk())
						.andReturn();
				
				String response=mvcResult.getResponse().getContentAsString();
				assertEquals(response.contains("success"),true);
			}
			
			*/
		
		/*
		//4
		@Test
		public void testScheduleInterview() throws Exception{
			
			InterviewSchedule interviewSchedule=new InterviewSchedule();
			interviewSchedule.setFinalStatus("PASSED");;
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "Tom");
			
			when(this.adminService.scheduleInterview(interviewSchedule, "Tom")).thenReturn(interviewSchedule);
			
			MvcResult mvcResult=this.mockMvc.perform(post("http://localhost:8004/admin/schedule")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(interviewSchedule))
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("PASSED")))
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("PASSED"),true);
		}
		
		*/
		
		/*
		//5
		@Test
		public void testUpdateSchedule() throws Exception{
			
			InterviewSchedule interviewSchedule=new InterviewSchedule();
			interviewSchedule.setFinalStatus("PASSED");
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "Tom");
			
			when(this.adminService.updateSchedule(1,interviewSchedule,"Tom")).thenReturn(interviewSchedule);
			
			MvcResult mvcResult=this.mockMvc.perform(put("http://localhost:8004/admin/schedule/"+1)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(interviewSchedule))
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("PASSED")))
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("PASSED"),true);
		}
		
		*/
		
		//5.1 - additrona;
//		@Test
//		public void testGetInterviewScheduleById() throws Exception{
//			
//			InterviewSchedule interviewSchedule=new InterviewSchedule();
//			interviewSchedule.setFinalStatus("PASSED");
//			HttpHeaders httpHeaders=new HttpHeaders();
//			httpHeaders.set("Authorization", "Tom");
//			
//			when(this.adminService.getInterviewScheduleById(1, "Tom")).thenReturn(interviewSchedule);
//			
//			MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:8004/admin/schedule/"+1)
//					.headers(httpHeaders)
//					)
//					.andExpect(status().isOk())
//					.andExpect(content().string(containsString("PASSED")))
//					.andReturn();
//			
//			String response=mvcResult.getResponse().getContentAsString();
//			assertEquals(response.contains("PASSED"),true);
//		}
		
		
		//6
		@Test
		public void testDeleteSchedule() throws Exception{
			
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "Tom");
			
			when(this.adminService.deleteInterviewScheduleByID(1, "Tom")).thenReturn(true);
			
			MvcResult mvcResult=this.mockMvc.perform(delete("http://localhost:8004/admin/interview/"+1)
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("true"),true);
		}
		
		
		
		//7
		@Test
		public void testAddPanelMember() throws Exception{
			
			PanelMember panelMember=new PanelMember();
			panelMember.setName("sample");
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "Tom");
			
			when(this.adminService.addPanelMember(panelMember, "Tom")).thenReturn(panelMember);
			
			MvcResult mvcResult=this.mockMvc.perform(post("http://localhost:8004/admin/panel")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(panelMember))
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
//					.andExpect(content().string(containsString("sample")))
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("sample"),true);
		}
		
		@Test
		public void testViewInterviewSchedules() throws Exception {
		List<InterviewSchedule> interviewList=new ArrayList<>();
		interviewList.add(new InterviewSchedule());
		interviewList.add(new InterviewSchedule());


		when(this.adminService.getAllInterviews()).thenReturn(interviewList);

		MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:8004/admin/interviews")



		)
		.andExpect(status().isOk())
		.andReturn();

		String response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("finalStatus"),true);
		}
		
		
		
		//Service 8
		
		//need to check
//		@Test
//		public void testSearchPanelByText() throws Exception {
//			List<PanelMember> panelList=new ArrayList<>();
//			panelList.add(new PanelMember());
//			panelList.add(new PanelMember());
//			when(this.adminService.searchEmployee(null, "hr")).thenReturn(panelList);
//			
//			MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:8004/admin/panel/search")
//					.param("name", "hr")
//					)
//					.andExpect(status().isOk())
//					.andReturn();
//			
//			String  response=mvcResult.getResponse().getContentAsString();
//			assertEquals(response.contains("name"),true);
//		}
		
		
		//9.1
		@Test
		public void testDeleteTechMember() throws Exception{
			
			//HttpHeaders httpHeaders=new HttpHeaders();
			//httpHeaders.set("Authorization", "Tom");
			
			when(this.adminService.deleteTechMember(1)).thenReturn(true);
			
			MvcResult mvcResult=this.mockMvc.perform(delete("http://localhost:8004/admin/panel/tech/"+1)
					)
					.andExpect(status().isOk())
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("true"),true);
		}
		
		//9.2
		@Test
		public void testDeleteHRMember() throws Exception{
			//HttpHeaders httpHeaders=new HttpHeaders();
			//httpHeaders.set("Authorization", "Tom");
			
			when(this.adminService.deleteHRMember(1)).thenReturn(true);
			
			MvcResult mvcResult=this.mockMvc.perform(delete("http://localhost:8004/admin/panel/hr/"+1)
					)
					.andExpect(status().isOk())
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("true"),true);
		}
		
		
		//10
		@Test
		public void testGetAllPanelMembers() throws Exception {
			List<PanelMember> panelList=new ArrayList<>();
			panelList.add(new PanelMember());
			panelList.add(new PanelMember());
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "Tom");
			
			when(this.adminService.getAllPanelMembers("Tom")).thenReturn(panelList);
			
			MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:8004/admin/panel")
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andReturn();
			
			String  response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("type"),true);
		}
		
		

}
