package com.zensar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.entity.InterviewScheduleEntity;

public interface InterviewRepo extends JpaRepository<InterviewScheduleEntity, Integer> {

}
