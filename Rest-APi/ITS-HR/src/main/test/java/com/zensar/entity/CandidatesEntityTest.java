package com.zensar.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CandidatesEntityTest {

	@Test
	public void test1() {
		CandidatesEntity testCandidate=new CandidatesEntity();
		testCandidate.setCandidateId(123);
		testCandidate.setCandidateName("Ram Kumar");
		testCandidate.setExperience(5);
		testCandidate.setLocation("Nagpur");
		testCandidate.setDesignation("clerk");
		testCandidate.setNoticePeriod(3);
		testCandidate.setQualification("BBA");
		assertEquals(null, testCandidate.getPrimarySkills());
		assertEquals(5, testCandidate.getExperience());
		assertNotEquals(121,testCandidate.getCandidateId());
		assertNotEquals(100, testCandidate.getExperience());
		assertEquals("BBA", testCandidate.getQualification());
		assertEquals("Ram Kumar",testCandidate.getCandidateName());
		testCandidate.setPrimarySkills("Java");
		assertEquals(null, testCandidate.getSecondarySkills());
		testCandidate.setSecondarySkills("React");
		assertEquals(3, testCandidate.getNoticePeriod());
		/* */
		assertEquals("Nagpur", testCandidate.getLocation());
		assertEquals("clerk", testCandidate.getDesignation());
	}
	
	@Test
	public void test2() {
		CandidatesEntity testCandi = new CandidatesEntity(123, "Yash", "Java Python", "Angular",
				12, "BE", "SDE 1", 5, "mumbai");
		testCandi.setCandidateId(12344);
		testCandi.toString();
	}

}
