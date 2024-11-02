package com.PiXl.mainframe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PiXl.mainframe.models.Profile;
import com.PiXl.mainframe.services.ProfileService;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/v1/profiles")
@NoArgsConstructor
public class ProfilesController {
	
	@Autowired
    private ProfileService profileService;

    /**
     * API endpoint to search profiles by first name or last name containing the search term.
     *
     * @param searchTerm The search term to check against both first and last names
     * @return A list of matching profiles
     */
    @GetMapping("/{name}")
    public ResponseEntity<List<Profile>> searchProfilesByName(@PathVariable String name) {
        return ResponseEntity.ok(profileService.searchProfilesByName(name));
    }

}
