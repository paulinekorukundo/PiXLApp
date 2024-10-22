package com.PiXl.mainframe.repositories;

import com.PiXl.mainframe.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

	 /**
     * Find a profile by profile Id.
     *
     * @param profileId the profile ID to search for
     * @return an Optional containing the ProfileEntity if found, otherwise empty
     */
    Optional<ProfileEntity> findByProfileId(Long profileId);
    
    /**
     * Find all profiles with a specific food preference.
     *
     * @param foodPreference the food preference to search for
     * @return a list of ProfileEntity objects with the specified food preference
     */
    List<ProfileEntity> findByFoodPreferencesContainingIgnoreCase(String foodPreference);
    
    /**
     * Find all profiles by first name or last name
     *
     * @param firstName the first name to search for
     * @param lastName the last name to search for
     * @return a list of ProfileEntity objects containing specified first name or last name
     */
    List<ProfileEntity> findByFirstNameContainingIgnoreCaseOfLastNameContainingIgnoreCase(String firstName, String lastName);
    
    /**
     * Find all profile with a specific bio
     * @param bio the bio to search for
     * @return a list of Profile Entity objects containing the specified bio
     */
    List<ProfileEntity> findByBioContainingIgnoreCase(String bio);
    
    /**
     * Check if a profile with the given profile Id exists.
     *
     * @param profile Id the profile Id to check
     * @return true if the profile exists, false otherwise
     */
    boolean existsByProfileId(Long profileId);
    
}
