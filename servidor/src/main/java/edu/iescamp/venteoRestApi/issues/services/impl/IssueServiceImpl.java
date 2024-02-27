package edu.iescamp.venteoRestApi.issues.services.impl;

import edu.iescamp.venteoRestApi.issues.exceptions.IssueNotFoundException;
import edu.iescamp.venteoRestApi.issues.models.dto.IssueDTO;
import edu.iescamp.venteoRestApi.issues.repositories.dao.IssueRepository;
import edu.iescamp.venteoRestApi.issues.repositories.entities.Issue;
import edu.iescamp.venteoRestApi.issues.services.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private static final Logger log = LoggerFactory.getLogger(IssueServiceImpl.class);

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public List<Issue> findAll() {
        log.info("IssueServiceImpl - findAll: listamos todas las incidencias: ");

        return issueRepository.findAll();
    }

    @Override
    public List<Issue> findSolved() {
        log.info("IssueServiceImpl - findSolved: listamos las incidencias resueltas: ");
        return issueRepository.findSolved();
    }

    @Override
    public List<Issue> findNoSolved() {
        log.info("IssueServiceImpl - findNoSolved: listamos las incidencias no resueltas: ");
        return issueRepository.findNoSolved();
    }


    @Override
    public void save(Issue issue) {
        log.info("IssueServiceImpl - create: creamos una incidencia: " + issue.toString());

        issueRepository.save(issue);
    }

    @Override
    public void update(Issue issue) {
        if(issueRepository.findById(issue.getIssueId()).isEmpty()) {
            throw new IssueNotFoundException(issue.getIssueId());
        }
        issueRepository.save(issue);
    }

    @Override
    public void patch(IssueDTO issueDTO) {

        Issue issue = issueRepository.findById(issueDTO.getIssueId()).orElseThrow(() -> new IssueNotFoundException(issueDTO.getIssueId()));
        if (issueDTO.getSubject() != null) {
            issue.setSubject(issueDTO.getSubject());
        }
        if (issueDTO.getMessage() != null) {
            issue.setMessage(issueDTO.getMessage());
        }
        if (issueDTO.getCreatedAt() != null){
            issue.setCreatedAt(issueDTO.getCreatedAt());
        }
        if (issueDTO.getIsSolved() != null) {
            issue.setIsSolved(issueDTO.getIsSolved());
        }
        if (issueDTO.getUserId() != null) {
            issue.setUserId(issueDTO.getUserId());
        }
        issueRepository.save(issue);

    }

    @Override
    public void delete(Long id) {
        log.info("IssueServiceImpl - delete: borramos una incidencia: " + id.toString());

        issueRepository.deleteById(id);
    }

}