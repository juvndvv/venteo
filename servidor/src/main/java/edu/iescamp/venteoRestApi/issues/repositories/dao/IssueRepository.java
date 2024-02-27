package edu.iescamp.venteoRestApi.issues.repositories.dao;

import edu.iescamp.venteoRestApi.issues.repositories.entities.Issue;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query("SELECT i FROM Issue i WHERE i.isSolved = true")
    List<Issue> findSolved();

    @Query("SELECT i FROM Issue i WHERE i.isSolved = false")
    List<Issue> findNoSolved();

}
