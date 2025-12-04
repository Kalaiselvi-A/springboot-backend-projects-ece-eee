package com.tnsif.bugtracker.service;

import com.tnsif.bugtracker.entity.Bug;
import java.util.List;

public interface BugService {

    Bug addBug(Bug bug);

    Bug updateBug(Long id, Bug bug);

    Bug updateStatus(Long id, String status);

    Bug assignBug(Long id, String assignedTo);

    List<Bug> getAllBugs();

    Bug getBugById(Long id);

    void deleteBug(Long id);
}
