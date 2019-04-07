package dipesh.com.emergencyalertsystem.bor;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

class Injection {
    private Injection() {
    }

    public static FirebaseAuth provideFireBaseAuth() {
        return FirebaseAuth.getInstance();
    }

    public static FirebaseDatabase provideFireBaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    public static SharedPreferenceManager getSharedPreference() {
        return SharedPreferenceManager.getInstance();
    }

    public static DonationDataSource provideLocalDataSource() {
        return DonationLocalDataSource.getInstance();
    }

    public static DonationDataSource provideRemoteDataSource() {
        return DonationRemoteDataSource.getInstance(Injection.provideFireBaseDatabase());
    }

    public static DonationDataSource providesDataRepo() {
        return DonationDataRepository.getInstance(Injection.provideLocalDataSource(),
                Injection.provideRemoteDataSource());
    }
}
