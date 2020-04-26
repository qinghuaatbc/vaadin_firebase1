package com.goldknight;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Consumer;


@Service
public class MyFirebase implements Serializable {



    private FileInputStream serviceAccount =
            new FileInputStream("/Users/mac/Downloads/myaccount.json");
    private FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://customer-f29a1.firebaseio.com")
            .build();

    static private FirebaseDatabase database;
    private DatabaseReference ref;


    public MyFirebase() throws IOException {

        FirebaseApp.initializeApp(options);

        database = FirebaseDatabase.getInstance();

      //  ref = database.getReference("customer");
        ;
    }
// Attach a listener to read the data at our posts reference

    public ArrayList<Customer> getCustomer() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        ref = database.getReference("customer");

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String k = ds.getKey();
                    Customer customer = ds.getValue(Customer.class);
                    customers.add(customer);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        return customers;
    }

    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        ref = database.getReference("movies/movie");

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String k = ds.getKey();
                    System.out.println(k);
                   Movie movie = ds.getValue(Movie.class);
                    System.out.println(movie.country);

                   movies.add(movie);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        return movies;
    }


    //getZones
    public  ArrayList<Zone> getZoneList(String node) {
         ArrayList<Zone> zonesList = new ArrayList<>();
//
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.child(node).child("alarm").child("zone").getChildren()) {
                    String zid = ds.getKey();
                    String zname = ds.getValue(String.class);
                    Zone zone = new Zone(zid,zname);
                  zonesList.add(zone );
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


      return zonesList;

    }
}