package com.pingcage.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    HashMap<String, List<String>> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = new HashMap<>();
        map.put("Fruits", Arrays.asList("Apple", "Banana", "Peach"));
        map.put("Vegetables", Arrays.asList("Cucumber", "Potato", "Tomato"));
        ExpandableListView elvList = findViewById(R.id.elvList);
        List<String> titles = new ArrayList<>(map.keySet());
        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(MainActivity.this, titles, map);
        elvList.setAdapter(adapter);
    }
}
