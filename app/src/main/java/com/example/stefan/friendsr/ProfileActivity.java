package com.example.stefan.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");
        Log.d("Friendsr", "onCreate: " + retrievedFriend.getName());



        TextView name = findViewById(R.id.textViewName);
        TextView bio = findViewById(R.id.textViewBio);
        ImageView avatar = findViewById(R.id.imageViewAvatar);

        name.setText(retrievedFriend.getName());
        bio.setText(retrievedFriend.getBio());
        avatar.setImageDrawable(getDrawable(retrievedFriend.getDrawableId()));
    }
}
