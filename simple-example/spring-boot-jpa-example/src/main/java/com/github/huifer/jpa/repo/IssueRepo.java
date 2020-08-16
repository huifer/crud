package com.github.huifer.jpa.repo;

import com.github.huifer.jpa.entity.IssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "issueRepo")
public interface IssueRepo extends JpaRepository<IssueEntity, Integer> {
}
