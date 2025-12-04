package com.tnsif.bugtracker.controller;

import com.tnsif.bugtracker.entity.Bug;
import com.tnsif.bugtracker.service.BugService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bugs")
@CrossOrigin("*") // Allow frontend React or Postman
public class BugController {

    private final BugService bugService;

    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    // ----------------- CREATE BUG -----------------
    @PostMapping
    public Bug addBug(@RequestBody Bug bug) {
        return bugService.addBug(bug);
    }

    // ----------------- UPDATE BUG -----------------
    @PutMapping("/{id}")
    public Bug updateBug(@PathVariable Long id, @RequestBody Bug bug) {
        return bugService.updateBug(id, bug);
    }

    // ----------------- UPDATE BUG STATUS -----------------
    @PatchMapping("/{id}/status")
    public Bug updateStatus(@PathVariable Long id, @RequestParam String status) {
        return bugService.updateStatus(id, status);
    }

    // ----------------- ASSIGN BUG -----------------
    @PatchMapping("/{id}/assign")
    public Bug assignBug(@PathVariable Long id, @RequestParam String assignedTo) {
        return bugService.assignBug(id, assignedTo);
    }

    // ----------------- GET ALL BUGS -----------------
    @GetMapping
    public List<Bug> getAllBugs() {
        return bugService.getAllBugs();
    }

    // ----------------- GET BUG BY ID -----------------
    @GetMapping("/{id}")
    public Bug getBugById(@PathVariable Long id) {
        return bugService.getBugById(id);
    }

    // ----------------- DELETE BUG -----------------
    @DeleteMapping("/{id}")
    public void deleteBug(@PathVariable Long id) {
        bugService.deleteBug(id);
    }
}
