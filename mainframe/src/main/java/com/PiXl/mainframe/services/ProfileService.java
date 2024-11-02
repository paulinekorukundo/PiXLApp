package com.PiXl.mainframe.services;

import java.util.List;


import com.PiXl.mainframe.models.Profile;

public interface ProfileService {

    /**
     * Searches for profiles where the first name or last name contains the search term.
     *
     * @param name The search term to check against both first and last names
     * @return A list of matching profiles
     */
    List<Profile> searchProfilesByName(String name);
    
    

}
