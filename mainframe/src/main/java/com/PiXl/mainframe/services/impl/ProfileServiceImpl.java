package com.PiXl.mainframe.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PiXl.mainframe.repositories.ProfileRepository;
import com.PiXl.mainframe.entities.ProfileEntity;
import com.PiXl.mainframe.models.Profile;
import com.PiXl.mainframe.services.ProfileService;

import lombok.experimental.var;

@SuppressWarnings("deprecation")
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
        var profileEntities = profileRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
        return from(profileEntities);
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
