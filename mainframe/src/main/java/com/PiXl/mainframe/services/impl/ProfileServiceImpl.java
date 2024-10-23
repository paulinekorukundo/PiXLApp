package com.PiXl.mainframe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PiXl.mainframe.repositories.ProfileRepository;
import com.PiXl.mainframe.entities.ProfileEntity;
import com.PiXl.mainframe.services.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
    private ProfileRepository profileRepository;

    /**
     * Searches for profiles where the first name or last name contains the search term.
     *
     * @param searchTerm The search term to check against both first and last names
     * @return A list of matching profiles
     */
	@Override
    public List<ProfileEntity> searchByFirstNameOrLastNameContaining(String searchTerm) {
        return profileRepository.findByFirstNameContainingIgnoreCaseOfLastNameContainingIgnoreCase(searchTerm, searchTerm);
    }
}
