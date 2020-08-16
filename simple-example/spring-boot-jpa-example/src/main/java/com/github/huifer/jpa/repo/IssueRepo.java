package com.github.huifer.jpa.repo;

import com.github.huifer.jpa.entity.IssuesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepo extends CrudRepository<IssuesEntity, Integer> {
}
