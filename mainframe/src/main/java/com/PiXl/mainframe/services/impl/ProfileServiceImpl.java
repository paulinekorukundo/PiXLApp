package com.PiXl.mainframe.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PiXl.mainframe.repositories.ProfileRepository;
import com.PiXl.mainframe.entities.ProfileEntity;
import com.PiXl.mainframe.models.Profile;
import com.PiXl.mainframe.services.ProfileService;


@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
    private ProfileRepository profileRepository;

    /**
     * Searches for profiles where the first name or last name is contained in the search term.
     *
     * @param name The search term to check against both first and last names
     * @return A list of matching profiles
     */
	@Override
    public List<Profile> searchProfilesByName(String name) {
        lombok.var profileEntities = profileRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
        return from(profileEntities);
    }
	
	/**
     * Finds a profile by its profileId.
     *
     * @param profileId The ID of the profile to retrieve.
     * @return Optional containing Profile object if found, otherwise empty.
     */
    @Override
    public Optional<Profile> findByProfileId(Long profileId) {
        Optional<ProfileEntity> profileEntity = profileRepository.findByProfileId(profileId);
        return profileEntity.map(Profile::new);
    }

    /**
     * Finds all profiles that contain a specified food preference.
     *
     * @param foodPreference The food preference to search for.
     * @return List of Profile objects with the specified food preference.
     */
    @Override
    public List<Profile> findByFoodPreferencesContaining(String foodPreference) {
        List<ProfileEntity> profileEntities = profileRepository.findByFoodPreferencesContainingIgnoreCase(foodPreference);
        return profileEntities.stream().map(Profile::new).collect(Collectors.toList());
    }
	
    
    /**
     * Finds all profiles that contain a specified bio.
     *
     * @param bio The bio text to search for.
     * @return List of Profile objects with the specified bio.
     */
    @Override
    public List<Profile> findByBioContaining(String bio) {
        List<ProfileEntity> profileEntities = profileRepository.findByBioContainingIgnoreCase(bio);
        return profileEntities.stream().map(Profile::new).collect(Collectors.toList());
    }

    /**
     * Checks if a profile with the specified profileId exists.
     *
     * @param profileId The profile ID to check.
     * @return true if a profile with the specified ID exists, false otherwise.
     */
    @Override
    public boolean existsByProfileId(Long profileId) {
        return profileRepository.existsByProfileId(profileId);
    }
	
	
	/**
	 * Convert a list of profile entities to  profile objects
	 */
	private List<Profile> from(List<ProfileEntity> profileEntities){
		
		lombok.var profiles = profileEntities.stream()
				.map(pe -> new Profile(pe))
				.collect(Collectors.toList());
		if(profiles.isEmpty()) {
			throw new NoSuchElementException();
		}
		return profiles;
	}
	
	
	
}
