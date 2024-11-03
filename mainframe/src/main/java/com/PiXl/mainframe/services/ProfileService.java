package com.PiXl.mainframe.services;

import java.util.List;
import java.util.Optional;

import com.PiXl.mainframe.models.Profile;

public interface ProfileService {

    /**
     * Searches for profiles where the first name or last name contains the search term.
     *
     * @param name The search term to check against both first and last names
     * @return A list of matching profiles
     */
    List<Profile> searchProfilesByName(String name);

	/**
	 * Finds a profile by its profileId.
	 *
	 * @param profileId The ID of the profile to retrieve.
	 * @return Optional containing Profile object if found, otherwise empty.
	 */
	Optional<Profile> findByProfileId(Long profileId);

	/**
	 * Finds all profiles that contain a specified food preference.
	 *
	 * @param foodPreference The food preference to search for.
	 * @return List of Profile objects with the specified food preference.
	 */
	List<Profile> findByFoodPreferencesContaining(String foodPreference);

	/**
	 * Finds all profiles that contain a specified bio.
	 *
	 * @param bio The bio text to search for.
	 * @return List of Profile objects with the specified bio.
	 */
	List<Profile> findByBioContaining(String bio);

	/**
	 * Checks if a profile with the specified profileId exists.
	 *
	 * @param profileId The profile ID to check.
	 * @return true if a profile with the specified ID exists, false otherwise.
	 */
	boolean existsByProfileId(Long profileId);
    
    
    
    

}
