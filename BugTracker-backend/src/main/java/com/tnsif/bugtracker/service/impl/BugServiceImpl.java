package com.tnsif.bugtracker.service.impl;

import com.tnsif.bugtracker.entity.Bug;
import com.tnsif.bugtracker.repository.BugRepository;
import com.tnsif.bugtracker.service.BugService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugServiceImpl implements BugService {

    private final BugRepository bugRepository;

    public BugServiceImpl(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    @Override
    public Bug addBug(Bug bug) {
        return bugRepository.save(bug);
    }

    @Override
    public Bug updateBug(Long id, Bug updatedBug) {
        Bug existing = bugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bug not found with ID: " + id));

        existing.setTitle(updatedBug.getTitle());
        existing.setDescription(updatedBug.getDescription());
        existing.setSeverity(updatedBug.getSeverity());
        existing.setPriority(updatedBug.getPriority());
        existing.setAssignedTo(updatedBug.getAssignedTo());
        existing.setProjectName(updatedBug.getProjectName());

        return bugRepository.save(existing);
    }

    @Override
    public Bug updateStatus(Long id, String status) {
        Bug bug = bugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bug not found with ID: " + id));

        bug.setStatus(status);
        return bugRepository.save(bug);
    }

    @Override
    public Bug assignBug(Long id, String assignedTo) {
        Bug bug = bugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bug not found with ID: " + id));

        bug.setAssignedTo(assignedTo);
        return bugRepository.save(bug);
    }

    @Override
    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    @Override
    public Bug getBugById(Long id) {
        return bugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bug not found with ID: " + id));
    }

    @Override
    public void deleteBug(Long id) {
        if (!bugRepository.existsById(id)) {
            throw new RuntimeException("Bug not found with ID: " + id);
        }
        bugRepository.deleteById(id);
    }
}
