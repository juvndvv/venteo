package edu.iescamp.venteoRestApi.issues.web.controllers;

import edu.iescamp.venteoRestApi.issues.models.dto.IssueDTO;
import edu.iescamp.venteoRestApi.issues.repositories.entities.Issue;
import edu.iescamp.venteoRestApi.issues.services.IssueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @GetMapping("/issues")
    public List<Issue> findAll(@RequestParam(required = false) Boolean isSolved) {
        if (isSolved == null) {
            return issueService.findAll();
        }

        if (isSolved){
            return issueService.findSolved();
        } else {
            return issueService.findNoSolved();
        }

    }

    @PostMapping("/issues/save")
    public void save(@Valid @RequestBody Issue issue) {
        issueService.save(issue);
    }

    @PutMapping("/issue/{id}")
    public void update(@Valid @RequestBody Issue issue, @PathVariable Long id) {
        issue.setIssueId(id);
        issueService.update(issue);
    }

    @PatchMapping("/issue/{id}")
    public void patch(@Valid @RequestBody IssueDTO issueDTO, @PathVariable Long id) {
        issueDTO.setIssueId(id);
        issueService.patch(issueDTO);
    }

    @DeleteMapping("/issue/{id}")
    public void delete(@PathVariable Long id) {
        issueService.delete(id);
    }
}
