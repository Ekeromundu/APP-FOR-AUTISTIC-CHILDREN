package com.stonefacesoft.ottaa.FirebaseRequests;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.stonefacesoft.ottaa.BuildConfig;

public final class FirebaseUtils {

    private static FirebaseUtils instance;

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private DatabaseReference rootRef;

    private boolean initialized = false;

    private String Uid = "";
    private String email = "";

    // Set UID
    public void setUid(String uid) {
        this.Uid = uid;
    }

    // Get UID
    public String getUid() {
        return Uid;
    }

    // Set email
    public void setEmail(String email) {
        this.email = email;
    }

    // Get email
    public String getEmail() {
        return email;
    }


    // Set FirebaseAuth manually
    public FirebaseUtils setAuth(FirebaseAuth auth) {
        this.auth = auth;
        return this;
    }

    // Set FirebaseStorage manually
    public FirebaseUtils setStorage(FirebaseStorage storage) {
        this.storage = storage;
        return this;
    }

    // If you want, you can also add setDatabase(FirebaseDatabase db)
// similar to old code, though usually initFirebaseAndEmulator() handles it.
    public FirebaseUtils setDatabase(FirebaseDatabase db) {
        this.database = db;
        this.rootRef = db.getReference();
        return this;
    }

    // 🔒 Private constructor
    private FirebaseUtils() {}

    // ✅ Thread-safe singleton
    public static synchronized FirebaseUtils getInstance() {
        if (instance == null) {
            instance = new FirebaseUtils();
        }
        return instance;
    }

    /**
     * MUST be called ONCE before any Firebase usage
     */
    public synchronized void initFirebaseAndEmulator() {
        if (initialized) return;

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        if (BuildConfig.DEBUG) {
            Log.d("FirebaseUtils", "🔥 Using Firebase Emulators");

            auth.useEmulator("172.25.64.183", 9099);
            database.useEmulator("172.25.64.183", 9000);
            storage.useEmulator("172.25.64.183", 9198);
        }

        rootRef = database.getReference();
        initialized = true;
    }

    public DatabaseReference checkFirebaseNetworkConnection(){
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        return connectedRef;
    }

    public FirebaseAuth auth() {
        ensureInitialized();
        return auth;
    }

    public FirebaseDatabase database() {
        ensureInitialized();
        return database;
    }

    public FirebaseStorage storage() {
        ensureInitialized();
        return storage;
    }

    public DatabaseReference root() {
        ensureInitialized();
        return rootRef;
    }

    public DatabaseReference getmDatabase() {
        return root();
    }

    private void ensureInitialized() {
        if (!initialized) {
            throw new IllegalStateException(
                    "FirebaseUtils not initialized. Call initialize() first."
            );
        }
    }
}
