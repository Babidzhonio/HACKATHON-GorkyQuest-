package com.example.GorkyQuest.quests.repository;

import com.example.GorkyQuest.quests.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest,Long> {

}
