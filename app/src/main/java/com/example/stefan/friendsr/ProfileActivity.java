package com.example.stefan.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    Friend retrievedFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Get the intent and retrieve the friend that was passed along
        Intent intent = getIntent();
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        // Variables for the views in the profile activity
        TextView name = findViewById(R.id.textViewName);
        TextView bio = findViewById(R.id.textViewBio);
        ImageView avatar = findViewById(R.id.imageViewAvatar);

        RatingBar ratingBar = findViewById(R.id.ratingBar);

        // Set the values of the views based on the values of the friend
        name.setText(retrievedFriend.getName());
        bio.setText(retrievedFriend.getBio());
        avatar.setImageDrawable(getDrawable(retrievedFriend.getDrawableId()));

        // Check SharedPreferences for any saved value for the rating and set the value if found
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        float retrievedRating = prefs.getFloat(retrievedFriend.getName(),0);
        ratingBar.setRating(retrievedRating);

        // Add the OnRatingBarChangeListener to the rating bar
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener());
    }

    // Inner class that listens for changes in the rating bar
    private class OnRatingBarChangeListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            // Create or retrieve the SharedPreferences editor
            SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();

            // Add the rating that was set to the rating bar to the SharedPreferences
            editor.putFloat(retrievedFriend.getName(), rating);
            editor.apply();
        }
    }
}
