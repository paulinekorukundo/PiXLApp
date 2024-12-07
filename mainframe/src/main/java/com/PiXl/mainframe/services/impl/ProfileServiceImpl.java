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

import jakarta.validation.Valid;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository pr;

    /**
     * Searches for profiles where the first name or last name is contained in the
     * search term.
     *
     * @param name The search term to check against both first and last names
     * @return A list of matching profiles
     */
    @Override
    public List<Profile> searchProfilesByName(String name) {
        var profileEntities = pr.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
        return from(profileEntities);
    }

    /**
     * Finds a profile by its profileId.
     *
     * @param profileId The ID of the profile to retrieve.
     * @return Optional containing Profile object if found, otherwise empty.
     */
    @Override
    public Optional<Profile> getProfile(Long profileId) {
        Optional<ProfileEntity> profileEntity = pr.findByProfileId(profileId);
        return profileEntity.map(Profile::new);
    }

    /**
     * Finds a profile by its userId.
     *
     * @param userId The ID of the profile to retrieve.
     * @return Optional containing Profile object if found, otherwise empty.
     */
    @Override
    public Optional<Profile> getProfile(String userId) {
        Optional<ProfileEntity> profileEntity = pr.findByUserId(userId);
        return profileEntity.map(Profile::new);
    }

    /**
     * Get all profiles
     * 
     * @return a list of all profiles
     */
    @Override
    public List<Profile> getProfiles() {
        var profileEntities = (List<ProfileEntity>) pr.findAll();
        return from(profileEntities);
    }

    /**
     * Finds all profiles that contain a specified food preference.
     *
     * @param foodPreference The food preference to search for.
     * @return List of Profile objects with the specified food preference.
     */
    @Override
    public List<Profile> findByFoodPreferencesContaining(String foodPreference) {
        List<ProfileEntity> profileEntities = pr.findByFoodPreferencesContainingIgnoreCase(foodPreference);
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
        List<ProfileEntity> profileEntities = pr.findByBioContainingIgnoreCase(bio);
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
        return pr.existsByProfileId(profileId);
    }

    /**
     * Adds a new profile object
     * 
     * @param profile - the profile to be added
     * @return profile - the added profile object
     */
    @Override
    public Profile add(@Valid Profile profile) {
        Profile addedProfile = null;
        ProfileEntity profileEntityToAdd = new ProfileEntity(profile);
        if (!pr.existsByProfileId(profileEntityToAdd.getProfileId())) {
            var profileEntityVar = pr.save(profileEntityToAdd);
            addedProfile = new Profile(profileEntityVar);
        }
        return addedProfile;
    }

    /**
     * Updates an existing profile in the repository
     * 
     * @param profile - The profile to be updated
     * @return profile - the updated profile object
     */
    @Override
    public Profile update(@Valid Profile profile) {
        Profile updatedProfile = null;
        ProfileEntity profileEntityToUpdate = new ProfileEntity(profile);
        if (pr.existsByProfileId(profileEntityToUpdate.getProfileId())) {
            var updatedProfileEntityVar = pr.save(new ProfileEntity(profile));
            updatedProfile = new Profile(updatedProfileEntityVar);
        }
        return updatedProfile;
    }

    /**
     * Deletes an existing profile from the repository
     * 
     * @param profile - The profile to be deleted
     * @return true - the profile is successfully deleted
     */
    @Override
    public boolean delete(@Valid Profile profile) {
        boolean isDeleted = false;
        ProfileEntity profileEntityToDeletEntity = new ProfileEntity(profile);
        if (pr.existsByProfileId(profileEntityToDeletEntity.getProfileId())) {
            pr.delete(new ProfileEntity(profile));
            isDeleted = true;
        }
        return isDeleted;
    }

    /**
     * Convert a list of profile entities to profile objects
     */
    private List<Profile> from(List<ProfileEntity> profileEntities) {

        var profiles = profileEntities.stream()
                .map(pe -> new Profile(pe))
                .collect(Collectors.toList());
        if (profiles.isEmpty()) {
            throw new NoSuchElementException();
        }
        return profiles;
    }

}
