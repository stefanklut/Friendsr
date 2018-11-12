package com.example.stefan.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<Friend> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String, String> hmap = new HashMap<String, String>(){{
            put("Arya", "");
            put("Cersei","");
            put("Daenerys", "");
            put("Jaime","");
            put("Jon","");
            put("Jorah","");
            put("Margaery","");
            put("Melisandre","");
            put("Sansa","");
            put("Tyrion","");
        }};
        for (HashMap.Entry<String, String> entry : hmap.entrySet()) {
            String name = entry.getKey();
            String bio = entry.getValue();
            int drawableId = getResources().getIdentifier(name.toLowerCase(),
                                                          "drawable",
                                                          getPackageName());
            friends.add(new Friend(name, bio, drawableId));
        }
        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        GridView gv = findViewById(R.id.GridViewList);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new OnItemClickListener());
    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Friend clickedFriend = (Friend) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);
            Log.d("Friendsr", "onItemClick: "+ clickedFriend.getName());
        }
    }
}
