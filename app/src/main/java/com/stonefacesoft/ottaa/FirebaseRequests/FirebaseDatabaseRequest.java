package com.stonefacesoft.ottaa.FirebaseRequests;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.stonefacesoft.ottaa.Interfaces.LoadUserInformation;
import com.stonefacesoft.ottaa.utils.constants.Constants;
import com.stonefacesoft.ottaa.utils.preferences.DataUser;

/*
 * usar esta clase solo para subir datos especificos como el pago , nombre y edad del usuario
 * */

public class FirebaseDatabaseRequest {
    private final DatabaseReference mDatabase;
    private StorageReference mStorageReference;
    private final FirebaseAuth mAuth;
    private final SharedPreferences sharedPrefsDefault;
    private final Context mContext;
    private final FirebaseUtils firebaseUtils;

    public FirebaseDatabaseRequest(Context mContext) {
        mAuth = FirebaseAuth.getInstance();
        this.mContext = mContext;
        firebaseUtils=FirebaseUtils.getInstance();
        //firebaseUtils.setmContext(this.mContext);
        firebaseUtils.initFirebaseAndEmulator();
        sharedPrefsDefault = PreferenceManager.getDefaultSharedPreferences(mContext);
        mDatabase =firebaseUtils.getmDatabase();
    }

    public void subirNombreUsuario(FirebaseAuth auth) {
        mDatabase.child(Constants.USERS).child(auth.getCurrentUser().getUid()).child(Constants.NAME).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists())
                    mDatabase.child(Constants.USERS).child(auth.getCurrentUser().getUid()).child(Constants.NAME).setValue(auth.getCurrentUser().getDisplayName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void subirEdadUsuario(String edad, FirebaseAuth auth) {
        mDatabase.child(Constants.EDAD.replaceFirst("e","E")).child(auth.getCurrentUser().getUid()).setValue(edad);
    }

    public void subirTipoUsuario(){

    }

    public void subirPago(FirebaseAuth auth) {
        mDatabase.child(Constants.PAYMENT).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(auth.getCurrentUser().getUid())) {
                    dataSnapshot.child(auth.getCurrentUser().getUid()).child(Constants.PAYMENT).getRef().setValue("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void subirConexion(){

    }

    public void subirEmail(FirebaseAuth auth) {
        mDatabase.child("email").child(auth.getCurrentUser().getUid()).child("email").setValue(auth.getCurrentUser().getEmail());
    }

    public void UploadUserData(DataUser userData){
        mDatabase.child(Constants.USERS).child(mAuth.getCurrentUser().getUid()).child(Constants.NAME).setValue(userData.getFirstAndLastName());
        mDatabase.child(Constants.USERS).child(mAuth.getCurrentUser().getUid()).child(Constants.BIRTH_DATE).setValue(userData.getBirthDate());
        mDatabase.child(Constants.USERS).child(mAuth.getCurrentUser().getUid()).child(Constants.GENDER).setValue(userData.getGender());
    }

   public void uploadUserAvatar(String name){
        mDatabase.child(Constants.AVATAR).child(mAuth.getCurrentUser().getUid()).child(name);
   }
   public void uploadUserAvatar(String name, String file){
       mDatabase.child(Constants.AVATAR).child(mAuth.getCurrentUser().getUid()).child(name);
       mDatabase.child(Constants.AVATAR).child(mAuth.getCurrentUser().getUid()).child(file);
   }

   public void FillUserInformation(LoadUserInformation loadUserInformation){
        DataUser user = new DataUser();
        String uid = "";
        try{
            uid = mAuth.getCurrentUser().getUid();
        }catch (Exception ex){
            uid = "";
        }
        if(!uid.isEmpty())
            mDatabase.child(Constants.USERS).child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChildren()){
                      if(snapshot.hasChild(Constants.NAME))
                        user.setFirstAndLastName(String.valueOf( snapshot.child(Constants.NAME).getValue()));
                      if(snapshot.hasChild(Constants.BIRTH_DATE))
                        user.setBirthDate(snapshot.child(Constants.BIRTH_DATE).getValue(Long.class));
                      if(snapshot.hasChild(Constants.GENDER))
                        user.setGender(snapshot.child(Constants.GENDER).getValue(String.class));
                      else
                       user.setGender("");
                    }
                    loadUserInformation.getUserInformation(user);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
   }




}
