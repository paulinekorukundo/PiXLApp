package com.PiXl.mainframe.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * Gets profiles by first name or last name
     *
     * @param searchTerm The search term to check against both first and last names
     * @return A list of matching profiles
     */
    @GetMapping("/{name}")
    public ResponseEntity<List<Profile>> searchProfilesByName(@PathVariable String name) {
        return ResponseEntity.ok(profileService.searchProfilesByName(name));
    }
    
    @GetMapping
    public ResponseEntity<List<Profile>> getProfiles(){
    	return ResponseEntity.ok(profileService.getProfiles());
    }
    
    /**
     * Gets a profile by profile ID.
     *
     * @param profileId The ID of the profile.
     * @return The profile if found, or 404 Not Found.
     */
    @GetMapping("/id/{profileId}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long profileId) {
        Optional<Profile> profile = profileService.getProfile(profileId);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Gets profiles by food preferences
     *
     * @param foodPreference The food preference to search for.
     * @return A list of profiles with the specified food preference.
     */
    @GetMapping("/food/{foodPreference}")
    public ResponseEntity<List<Profile>> searchProfilesByFoodPreference(@PathVariable String foodPreference) {
    	return ResponseEntity.ok(profileService.findByFoodPreferencesContaining(foodPreference));
    }

    /**
     * Gets profiles by bio.
     *
     * @param bio The bio text to search for.
     * @return A list of profiles with the specified bio.
     */
    @GetMapping("/search/bio/{bio}")
    public ResponseEntity<List<Profile>> searchProfilesByBio(@PathVariable String bio) {
    	return ResponseEntity.ok(profileService.findByBioContaining(bio));

    }

    /**
     * Checks if a profile with the specified ID exists.
     *
     * @param profileId The profile ID to check.
     * @return true if the profile exists, false otherwise.
     */
    @GetMapping("/exists/{profileId}")
    public ResponseEntity<Boolean> checkIfProfileExists(@PathVariable Long profileId) {
    	return ResponseEntity.ok(profileService.existsByProfileId(profileId));
    }
    
    /**
     * Creates a new profile and returns a response entity object
     * @param profile - the profile object to be added
     * @return A ResponseEntity indicating creation of the profile 
     */
    @PostMapping()
    public ResponseEntity<Profile> add(@RequestBody Profile profile) {
    	Profile profileToAdd = profileService.add(profile);
    	if(profileToAdd == null) {
    		return ResponseEntity.badRequest().build();
    	}
        return ResponseEntity.created(null).build();
    }
    
    /**
     * Updates an existing profile and 
     * returns a ResponseEntity object
     * @param profile - the profile object with the updated information
     * @return A ResponseEntity indicating the successful update 
     */
    @PutMapping()
    public ResponseEntity<Profile> update(@RequestBody Profile profile){
    	Profile updatedProfile = profileService.update(profile);
    	if (updatedProfile == null) {
            return ResponseEntity.notFound().build();
        }
    	return ResponseEntity.ok(updatedProfile);
    }
 
    /**
     * Deletes a profile resource and 
     * returns a ResponseEntity object
     * @param profile - The profile object to be deleted
     * @return  A ResponseEntity indicating that the resource has been deleted
     */
    @DeleteMapping()
    public ResponseEntity<Boolean> delete(@RequestBody Profile profile){
    	boolean deletedProfile = profileService.delete(profile);
    	if(deletedProfile == false) {
    		return ResponseEntity.notFound().build();
    	}
    	return ResponseEntity.ok(deletedProfile);
    }
}
