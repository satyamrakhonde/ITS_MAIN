package com.zensar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.entity.PanelMemberEntity;

public interface PanelMemberRepo extends JpaRepository<PanelMemberEntity, Integer>{

}
