package com.PiXl.mainframe.services;

import java.util.List;


import com.PiXl.mainframe.entities.ProfileEntity;

public interface ProfileService {

    /**
     * Searches for profiles where the first name or last name contains the search term.
     *
     * @param searchTerm The search term to check against both first and last names
     * @return A list of matching profiles
     */
    List<ProfileEntity> searchByFirstNameOrLastNameContaining(String searchTerm);
    
    

}
