package com.example.stefan.friendsr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ArrayList<Friend> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // All friends in a map
        HashMap<String, String> hmap = new HashMap<String, String>(){{
            put("Arya", "Arya is a small northern sassy little girl that now kills people!");
            put("Cersei","Mean, likes her brother ;)");
            put("Daenerys", "Dragons in GoT!");
            put("Jaime","Nice, likes his sister D: she is mean stop it...");
            put("Jon","Wears black, died...");
            put("Jorah","Not relevant for the story, not even a little bit.");
            put("Margaery","Pretty, sweet, but exploded in a big church explosion.");
            put("Melisandre","You're a wizard harry.");
            put("Sansa","Bigger than Arya northern sassy queen of the northlike girl.");
            put("Tyrion","Giant dwarf inhabiting a spaceforge near a dying star.");
        }};

        // Loop over friends to add them to the ArrayList
        for (HashMap.Entry<String, String> entry : hmap.entrySet()) {
            String name = entry.getKey();
            String bio = entry.getValue();
            int drawableId = getResources().getIdentifier(name.toLowerCase(),
                                                          "drawable",
                                                          getPackageName());
            friends.add(new Friend(name, bio, drawableId));
        }

        // Create the friendsAdapter
        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);

        // Find the grid view and add the Adapter and OnItemClickListener
        GridView gv = findViewById(R.id.GridViewList);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new OnItemClickListener());
    }

    // Inner class that listens for clicks on items in the grid view
    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Retrieve what friend was clicked
            Friend clickedFriend = (Friend) parent.getItemAtPosition(position);

            // Open new activity with the intent passing on the friend
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);
        }
    }
}
