package com.stonefacesoft.ottaa.FirebaseRequests.DownloadFile;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.stonefacesoft.ottaa.FirebaseRequests.FirebaseUtils;
import com.stonefacesoft.ottaa.utils.ObservableInteger;
import com.stonefacesoft.ottaa.utils.constants.Constants;
import com.stonefacesoft.ottaa.utils.exceptions.FiveMbException;

import org.json.JSONException;

import java.io.File;

public class DownloadPhrases extends DownloadFile{
    public DownloadPhrases(Context mContext, DatabaseReference mDatabase, StorageReference mStorageReference, SharedPreferences sharedPreferences, ObservableInteger observableInteger,String locale) {
        super(mContext, mDatabase, mStorageReference, sharedPreferences, observableInteger,locale);
        TAG ="DownloadPhrases";

    }

    public void syncPhrases(){
        mDatabase.child(Constants.Phrases).child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String child = "URL_frases_" + locale;
                if(snapshot.hasChild(child)){
                    Log.e(TAG, "bajarFrases: "+ child );

                    mStorageReference = FirebaseStorage.getInstance().getReference().child("Archivos_Usuarios").child(Constants.Phrases).child(Constants.Phrases.toLowerCase() + "_" + email + "_" + locale + "." + "txt");
                    final File frasesUsuarioFile = new File(rootPath, "frases.txt");
                    mStorageReference.getFile(frasesUsuarioFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Log.e(TAG, "bajarFrases: "+ child );
                            boolean areTheSameFile = false;
                            try{
                                 areTheSameFile = json.verifyFiles(Constants.PHRASES_FILE,frasesUsuarioFile);
                            }catch (Exception ex){

                            }
                            if(!areTheSameFile){
                                try {
                                    if (!getStringFromFile(frasesUsuarioFile.getAbsolutePath()).equals("[]") && frasesUsuarioFile.length() > 0) {

                                        json.setmJSONArrayTodasLasFrases(json.readJSONArrayFromFile(frasesUsuarioFile.getAbsolutePath()));
                                        json.guardarJson(Constants.PHRASES_FILE);
                                    }
                                } catch (JSONException | FiveMbException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    }
                            }

                            observableInteger.incrementValue();

                        }
                    });
                }else{
                    observableInteger.incrementValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    public void downloadPhrases() {
//
//        Log.e(TAG, "================ downloadPhrases() START ================");
//
//        // 1️⃣ Basic sanity checks
//        Log.e(TAG, "Constants.Phrases = " + Constants.Phrases);
//        Log.e(TAG, "Locale = " + locale);
//        Log.d(TAG, "UID = " + uid);
//        Log.d(TAG, "Email = " + email);
//        Log.d(TAG, "Database ref = " + mDatabase);
//        Log.d(TAG, "Database root = " + mDatabase.toString());
//
//        DatabaseReference testRef = FirebaseDatabase.getInstance().getReference("testNode");
//        testRef.setValue("hello");
//        testRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.e("TEST", "Got value: " + snapshot.getValue());
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.e("TEST", "Error: " + error.getMessage());
//            }
//        });
//
//
//        if (uid == null || uid.isEmpty()) {
//            Log.e(TAG, "❌ UID IS EMPTY – user not authenticated");
//            observableInteger.incrementValue();
//            return;
//        }
//
//        // 2️⃣ Check realtime DB connection to emulator
//        DatabaseReference connectedRef =
//                FirebaseDatabase.getInstance().getReference(".info/connected");
//
//        connectedRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Boolean connected = snapshot.getValue(Boolean.class);
//                Log.e(TAG, "🔥 Firebase DB connected = " + connected);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.e(TAG, "❌ Connection check cancelled: " + error.getMessage());
//            }
//        });
//
//        // 3️⃣ Log exact path we are about to read
//        String dbPath = Constants.Phrases + "/" + uid;
//        Log.d(TAG, "➡️ Reading path: /" + dbPath);
//
//        DatabaseReference frasesRef =
//                mDatabase.child(Constants.Phrases).child(uid);
//
//        // 4️⃣ Attach listener
//        frasesRef.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                Log.e(TAG, "✅ onDataChange FIRED");
//                Log.d(TAG, "Snapshot exists = " + snapshot.exists());
//                Log.d(TAG, "Snapshot value = " + snapshot.getValue());
//
//                String expectedChild = "URL_frases_" + locale;
//                Log.d(TAG, "Expected child key = " + expectedChild);
//
//                // 5️⃣ List ALL children (VERY IMPORTANT DEBUG)
//                for (DataSnapshot childSnap : snapshot.getChildren()) {
//                    Log.d(TAG, "Found child key = " + childSnap.getKey());
//                }
//
//                if (!snapshot.exists()) {
//                    Log.e(TAG, "❌ Snapshot does NOT exist at this path");
//                    observableInteger.incrementValue();
//                    return;
//                }
//
//                if (!snapshot.hasChild(expectedChild)) {
//                    Log.e(TAG, "❌ Child NOT found: " + expectedChild);
//                    observableInteger.incrementValue();
//                    return;
//                }
//
//                Log.e(TAG, "✅ Child FOUND: " + expectedChild);
//
//                // 6️⃣ Storage reference
//                mStorageReference = FirebaseStorage.getInstance()
//                        .getReference()
//                        .child("Archivos_Usuarios")
//                        .child(Constants.Phrases)
//                        .child(Constants.Phrases.toLowerCase()
//                                + "_" + email + "_" + locale + ".txt");
//
//                Log.d(TAG, "Storage reference = " + mStorageReference.getPath());
//
//                final File frasesUsuario =
//                        new File(rootPath, Constants.PHRASES_FILE);
//
//                Log.d(TAG, "Local file path = " + frasesUsuario.getAbsolutePath());
//
//                // 7️⃣ Download from Storage emulator
//                mStorageReference.getFile(frasesUsuario)
//                        .addOnSuccessListener(taskSnapshot -> {
//
//                            Log.e(TAG, "✅ STORAGE DOWNLOAD SUCCESS");
//                            Log.d(TAG, "Bytes downloaded = " +
//                                    taskSnapshot.getTotalByteCount());
//
//                            try {
//                                json.setmJSONArrayTodasLasFrases(
//                                        json.readJSONArrayFromFile(
//                                                frasesUsuario.getAbsolutePath()
//                                        )
//                                );
//
//                                if (json.guardarJson(Constants.PHRASES_FILE)) {
//                                    Log.e(TAG, "✅ Phrases JSON SAVED");
//                                } else {
//                                    Log.e(TAG, "❌ Failed to save JSON");
//                                }
//
//                            } catch (Exception e) {
//                                Log.e(TAG, "❌ Error parsing phrases file", e);
//                            }
//
//                            observableInteger.incrementValue();
//                        })
//                        .addOnFailureListener(e -> {
//                            Log.e(TAG, "❌ STORAGE DOWNLOAD FAILED", e);
//                            observableInteger.incrementValue();
//                        });
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.e(TAG, "❌ onCancelled FIRED");
//                Log.e(TAG, "Error code = " + error.getCode());
//                Log.e(TAG, "Error message = " + error.getMessage());
//                observableInteger.incrementValue();
//            }
//        });
//    }


    public void downloadPhrases(){
        Log.e(TAG, "bajarDownloadFrases: " );
        Log.e(TAG, "Phrases" + Constants.Phrases);
        Log.e(TAG, "Database" + mDatabase );
        Log.d(TAG, "mDatabase root: " + mDatabase.toString());
        Log.d(TAG, "UID: " + uid);
        Log.d(TAG, "Locale detected: " + locale);
        Log.d(TAG, "Attaching listener at: " + Constants.Phrases + "/" + uid);
        Log.d(TAG, "Database URL: " + FirebaseUtils.getInstance().database().getReference().toString());

//        FirebaseUtils.getInstance().database() .getReference("Frasess") .child(uid) .setValue("test phrase");
//
//        FirebaseUtils.getInstance().database().getReference()
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Log.d(TAG, "Root fired. Keys: " + snapshot.getChildren());
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Log.e(TAG, "Cancelled: " + error.getMessage());
//                    }
//                });


//        FirebaseUtils.getInstance().database().getReference("Phrases")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Log.d(TAG, "Phrases root fired. Children: " + snapshot.getChildrenCount());
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Log.e(TAG, "Cancelled: " + error.getMessage());
//                    }
//                });


//        mDatabase.child(Constants.Phrases).child(uid)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Log.d(TAG, "Listener fired. Exists? " + snapshot.exists());
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Log.e(TAG, "Listener cancelled: " + error.getMessage());
//                    }
//                });

        mDatabase.child(Constants.Phrases).child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String child = "URL_" + Constants.Phrases.toLowerCase() + "_" + locale;
                Log.e(TAG, "URL_: " + child);
                if (dataSnapshot.hasChild(child)) {
                    Log.e(TAG, "Has Child Frase");
                    mStorageReference = FirebaseStorage.getInstance().getReference().child("Archivos_Usuarios").child(Constants.Phrases).child(Constants.Phrases.toLowerCase() + "_" + email + "_" + locale + "." + "txt");
                    Log.e(TAG, "Storage Reference: " + mStorageReference);
                    final File frasesUsuario = new File(rootPath, Constants.PHRASES_FILE);
                    Log.e(TAG, "frasesUsuario: " + frasesUsuario);
                    mStorageReference.getFile(frasesUsuario).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            try {
                                json.setmJSONArrayTodasLasFrases(json.readJSONArrayFromFile(frasesUsuario.getAbsolutePath()));
                                if (!json.guardarJson(Constants.PHRASES_FILE)) {
                                    Log.e(TAG, "Fallo al guardar json");
                                }
                                else {
                                    Log.d(TAG, "Phrases Saved");
                                }

                            } catch (JSONException | FiveMbException e) {
                                e.printStackTrace();
                            }
                            observableInteger.set(observableInteger.get() + 1);
                        }
                    }).addOnFailureListener(DownloadPhrases.super::onFailure);
                } else {
                    observableInteger.set(observableInteger.get() + 1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: "+ databaseError.getMessage() );
                Log.e(TAG, "bajar Phrases: failure" );
            }
        });
    }



}
