package com.ruijun.characterview.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    RecyclerView mRecyclerView;
    CharacterViewAdapter mCharacterViewAdapter;
    LinearLayoutManager mLayoutManager;

    private ArrayList<String> mNameLists = new ArrayList<String>();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getNameData();
        mContext = this;

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mCharacterViewAdapter = new CharacterViewAdapter(mContext, mNameLists);
        mRecyclerView.setAdapter(mCharacterViewAdapter);
    }

    private void getNameData(){
        this.mNameLists.add("John");
        this.mNameLists.add("Michelle");
        this.mNameLists.add("Amy");
        this.mNameLists.add("Kim");
        this.mNameLists.add("Android");
        this.mNameLists.add("Window");
        this.mNameLists.add("Sunny");
        this.mNameLists.add("Python");
        this.mNameLists.add("Java");
        this.mNameLists.add("Michael");
        this.mNameLists.add("Sarah");
        this.mNameLists.add("Robert");
        this.mNameLists.add("Lily");
        this.mNameLists.add("William");
        this.mNameLists.add("Jessica");
        this.mNameLists.add("Test123");
        this.mNameLists.add("12345");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
