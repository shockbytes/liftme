package com.ooe.fh.liftme;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.List;

public class MainActivity extends WearableActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, MessageApi.MessageListener, DataApi.DataListener,
        SensorEventListener {


    private Vibrator vibrator;

    private String nodeId;
    private GoogleApiClient googleApiClient;

    private SensorManager sensorManager;
    private Sensor sensor;
    private int sensorReqCode = 103;

    private TextView textWorkout;
    private TextView textDuration;
    private TextView textHeartrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        textWorkout = (TextView) findViewById(R.id.text_workout);
        textDuration = (TextView) findViewById(R.id.text_duration);
        textHeartrate = (TextView) findViewById(R.id.text_heart_rate);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        initializeGoogleApiClient();
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    @Override
    protected void onResume() {
        super.onResume();
        googleApiClient.connect();
        getBestNode();
        initializeHeartRate();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (googleApiClient != null) {
            Wearable.DataApi.removeListener(googleApiClient, this);
            Wearable.MessageApi.removeListener(googleApiClient, this);
            googleApiClient.disconnect();
        }

        if (sensor != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Wearable.DataApi.addListener(googleApiClient, this);
        Wearable.MessageApi.addListener(googleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void initializeGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    private void initializeHeartRate() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS)
                == PackageManager.PERMISSION_GRANTED) {

            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
            if (sensor != null) {
                int interval = 1000000;
                sensorManager.registerListener(this, sensor, interval);
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.BODY_SENSORS}
                    , sensorReqCode);
        }

    }

    private void getBestNode() {

        // This must be an asynchronous call with callback
        // Otherwise .getConnectedNodes().await will return it immediately,
        // but must not called on the UI thread
        Wearable.NodeApi.getConnectedNodes(googleApiClient).setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
            @Override
            public void onResult(@NonNull NodeApi.GetConnectedNodesResult getConnectedNodesResult) {

                // Just get the first connected node
                // We assume that there is just 1 Wearable connected
                List<Node> nodes = getConnectedNodesResult.getNodes();
                if (nodes.size() > 0) {
                    nodeId = nodes.get(0).getId();
                }
            }
        });
    }

    private void updateDisplay() {
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

        // TODO Handle messages from handheld
        String path = messageEvent.getPath();

    }

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {

        // TODO Handle data synchronisation from handheld
        for (DataEvent event : dataEventBuffer) {
            if (event.getType() == DataEvent.TYPE_CHANGED) {
                // DataItem changed
                DataItem item = event.getDataItem();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == sensorReqCode && permissions[0].equals(Manifest.permission.BODY_SENSORS) &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initializeHeartRate();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        int heartrate = (int) event.values[0];
        String text = (heartrate > 0) ? heartrate + " bpm" : "--- pbm";
        textHeartrate.setText(text);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
