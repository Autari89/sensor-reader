package com.sensor.functions;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sensor.phone_sensors.R;

import java.util.ArrayList;
import java.util.List;

import com.sensor.elements.SensorComponent;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {
    RecyclerViewAdapter myAdapter;
    DrawerLayout drawerLayout;
    public static final String EXTRA_MESSAGE = "MESSAGE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* From the activity_main.xml we firstly retrieve the navigation drawer object. It is
        possible then to apply a listener so that once a button is pressed the corresponding view is
        shown */
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        CharSequence title = item.getTitle();
                        Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(Gravity.START, true);
                        return true;
                    }
                }
        );

        /* Compact Activity already has a navigation drawer toolbar, after disabling it from manifest
        it is possible to insert the custom one in the view
         */
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        /* After the custom toolbar has been introduced it is possible to add the home button ad a listener
           to it */
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<SensorComponent> sensors = buildSensorList();
        myAdapter = new RecyclerViewAdapter(this, sensors);
        myAdapter.setClickListener(this);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getApplicationContext(), LaneDetection.class);
        String message = myAdapter.getItem(position);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<SensorComponent> buildSensorList() {
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<SensorComponent> sensorComponentList = new ArrayList<>();

        if (mSensorManager != null) {
            List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
            SensorComponent sensorComponent = new SensorComponent(this, sensorList.get(0), sensorList.get(0).getName(), R.drawable.accelerometer);
            sensorComponentList.add(sensorComponent);

            sensorList = mSensorManager.getSensorList(Sensor.TYPE_GRAVITY);
            sensorComponent = new SensorComponent(this, sensorList.get(0), sensorList.get(0).getName(), R.drawable.gravity);
            sensorComponentList.add(sensorComponent);

            sensorList = mSensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);
            sensorComponent = new SensorComponent(this, sensorList.get(0), sensorList.get(0).getName(), R.drawable.gyroscope);
            sensorComponentList.add(sensorComponent);

            sensorList = mSensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
            sensorComponent = new SensorComponent(this, sensorList.get(0), sensorList.get(0).getName(), R.drawable.proximity);
            sensorComponentList.add(sensorComponent);

            sensorList = mSensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
            sensorComponent = new SensorComponent(this, sensorList.get(0), sensorList.get(0).getName(), R.drawable.magnetic_field);
            sensorComponentList.add(sensorComponent);
        }

        return sensorComponentList;
    }

}
