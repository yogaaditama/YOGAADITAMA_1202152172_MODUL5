package com.example.asusx453sa.YOGAADITAMA_1202152172_MODUL5.UI;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.asusx453sa.YOGAADITAMA_1202152172_MODUL5.NiceDatabase;
import com.example.asusx453sa.YOGAADITAMA_1202152172_MODUL5.R;
import com.example.asusx453sa.YOGAADITAMA_1202152172_MODUL5.RecyclerAdapter;
import com.example.asusx453sa.YOGAADITAMA_1202152172_MODUL5.SwipeHelper;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private LinkedList<com.example.asusx453sa.YOGAADITAMA_1202152172_MODUL5.Model.Activity> mActivity = new LinkedList<>();
    private int mCount = 0;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private NiceDatabase databaseHandler;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check DB
        databaseHandler = new NiceDatabase(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRecyclerView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AddToDoActivity.class);
                startActivityForResult(intent, 1);
            }
        });

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
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.custom_background);
            dialog.setTitle("Change");
            dialog.setCancelable(true);
            // there are a lot of settings, for dialog, check them all out!
            // set up radiobutton
            final RadioButton rdRed =  dialog.findViewById(R.id.rdRed);
            final RadioButton rdBlue = dialog.findViewById(R.id.rdBlue);
            final RadioButton rdGreen = dialog.findViewById(R.id.rdGreen);
            Button btnChange = dialog.findViewById(R.id.btnChange);
            btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rdRed.isChecked()){
                        mRecyclerView.setBackgroundResource(R.color.redBackgroud);
                        Toast.makeText(view.getContext(),"Red Choosen",Toast.LENGTH_SHORT).show();
                    }
                    if (rdBlue.isChecked()){
                        mRecyclerView.setBackgroundResource(R.color.blueBackgroud);
                        Toast.makeText(view.getContext(),"Blue Choosen",Toast.LENGTH_SHORT).show();
                    }
                    if (rdGreen.isChecked()){
                        mRecyclerView.setBackgroundResource(R.color.greenBackgroud);
                        Toast.makeText(view.getContext(),"Green Choosen",Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(view.getContext(),"Changed",Toast.LENGTH_SHORT).show();
                }
            });

            // now that the dialog is set up, it's time to show it
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setRecyclerView() {
        mActivity = databaseHandler.findAll();
        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recycler);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new RecyclerAdapter(MainActivity.this, mActivity);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.Callback callback = new SwipeHelper(mAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1){
            Log.d("new activity : ",data.getStringExtra("activity"));
            Log.d("new desc : ",data.getStringExtra("desc"));
            Log.d("new priority : ",data.getStringExtra("priority"));
            databaseHandler.save(new com.example.asusx453sa.YOGAADITAMA_1202152172_MODUL5.Model.Activity(data.getStringExtra("activity"), data.getStringExtra("desc"), data.getStringExtra("priority")));
        }
        setRecyclerView();
        mAdapter.notifyDataSetChanged();
    }
}
