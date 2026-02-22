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
import com.stonefacesoft.ottaa.utils.ObservableInteger;
import com.stonefacesoft.ottaa.utils.constants.Constants;
import com.stonefacesoft.ottaa.utils.exceptions.FiveMbException;

import org.json.JSONException;

import java.io.File;

public class DownloadFavoritePhrases extends DownloadFile{
    public DownloadFavoritePhrases(Context mContext, DatabaseReference mDatabase, StorageReference mStorageReference, SharedPreferences sharedPreferences, ObservableInteger observableInteger, String locale) {
        super(mContext, mDatabase, mStorageReference, sharedPreferences, observableInteger,locale);
        TAG ="DownloadFavoritePhrase";
    }

    public void DownloadFavoritePhrases(){
        Log.e(TAG, "bajarFavoriteFrases: " );
        mDatabase.child(Constants.FavoritePhrases).child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, "FavoritePhrases exists" );
                String child = "URL_" + Constants.FavoritePhrases.toLowerCase() + "_" + locale;
                if (dataSnapshot.hasChild(child)) {
                    Log.e(TAG, "Favorite has child: " );
                    mStorageReference = FirebaseStorage.getInstance().getReference().child("Archivos_Usuarios").child(Constants.FavoritePhrases).child(Constants.FavoritePhrases.toLowerCase() + "_" + email + "_" + locale + "." + "txt");
                    final File frasesUsuario = new File(rootPath, Constants.FAVORITE_PHRASES_FILE);
                    mStorageReference.getFile(frasesUsuario).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Log.e(TAG, "Favorite frase downloaded" );
                            try {
                                json.setmJSonArrayFrasesFavoritas(json.readJSONArrayFromFile(frasesUsuario.getAbsolutePath()));
                                if (!json.guardarJson(Constants.FAVORITE_PHRASES_FILE)) {

                                    Log.e(TAG, "Fallo al guardar json");
                                }
                            } catch (JSONException | FiveMbException e) {
                                Log.e(TAG, "Favorite frase NOT downloaded" );
                                e.printStackTrace();
                            }
                            if(observableInteger!=null)
                                observableInteger.incrementValue();
                        }
                    }).addOnFailureListener(DownloadFavoritePhrases.super::onFailure);
                }else{
                    if(observableInteger!=null)
                        observableInteger.incrementValue();
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
