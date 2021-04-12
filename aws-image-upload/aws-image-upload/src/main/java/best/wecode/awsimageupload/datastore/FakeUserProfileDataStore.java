package best.wecode.awsimageupload.datastore;

import best.wecode.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("2e63d1f9-9a55-459a-9b2c-1bdfaa34097f"), "Pippo", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("6b74abdb-9748-43a1-8471-4ffb1077063e"), "Bello", null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }
}
