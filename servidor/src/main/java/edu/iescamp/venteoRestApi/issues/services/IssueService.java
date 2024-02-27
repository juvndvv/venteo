package edu.iescamp.venteoRestApi.issues.services;

import edu.iescamp.venteoRestApi.issues.models.dto.IssueDTO;
import edu.iescamp.venteoRestApi.issues.repositories.entities.Issue;

import java.util.List;

public interface IssueService {

    List<Issue> findAll();

    List<Issue> findSolved();

    List<Issue> findNoSolved();

    void save(Issue issue);

    void update(Issue issue);

    void patch(IssueDTO issueDTO);

    void delete(Long id);

}
