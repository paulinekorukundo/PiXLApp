package com.PiXl.mainframe.controllers;

import java.util.List;
import java.util.Optional;

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
    
    /**
     * API endpoint to get a profile by profile ID.
     *
     * @param profileId The ID of the profile.
     * @return The profile if found, or 404 Not Found.
     */
    @GetMapping("/id/{profileId}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long profileId) {
        Optional<Profile> profile = profileService.findByProfileId(profileId);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * API endpoint to search profiles by food preference.
     *
     * @param foodPreference The food preference to search for.
     * @return A list of profiles with the specified food preference.
     */
    @GetMapping("/food/{foodPreference}")
    public ResponseEntity<List<Profile>> searchProfilesByFoodPreference(@PathVariable String foodPreference) {
        List<Profile> profiles = profileService.findByFoodPreferencesContaining(foodPreference);
        if (profiles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profiles);
    }

    /**
     * API endpoint to search profiles by bio.
     *
     * @param bio The bio text to search for.
     * @return A list of profiles with the specified bio.
     */
    @GetMapping("/search/bio/{bio}")
    public ResponseEntity<List<Profile>> searchProfilesByBio(@PathVariable String bio) {
        List<Profile> profiles = profileService.findByBioContaining(bio);
        if (profiles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profiles);
    }

    /**
     * API endpoint to check if a profile with the specified ID exists.
     *
     * @param profileId The profile ID to check.
     * @return true if the profile exists, false otherwise.
     */
    @GetMapping("/exists/{profileId}")
    public ResponseEntity<Boolean> checkIfProfileExists(@PathVariable Long profileId) {
        boolean exists = profileService.existsByProfileId(profileId);
        return ResponseEntity.ok(exists);
    }
    
}
