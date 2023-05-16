package com.example.littlepaws;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.littlepaws.R;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapact);

        // Initialize the OpenStreetMap configuration
        Configuration.getInstance().load(getApplicationContext(), getPreferences(MODE_PRIVATE));

        // Get the MapView from the layout
        mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        // Set the default map position and zoom level
        GeoPoint defaultLocation = new GeoPoint(14.595553749525399, 120.98799843453843);
        mapView.getController().setCenter(defaultLocation);
        mapView.getController().setZoom(15);

        // Add pinned locations
        addMarker(14.601679160081622, 120.98804129807459, "PSPCA");
        addMarker(14.584817636754284, 120.98984374240997, "E. A. Garcia Veterinary Clinic");
        addMarker(14.604378545045629, 120.99636687429037, "Cayco Animal Clinic");
    }

    private void addMarker(double latitude, double longitude, String title) {
        Marker marker = new Marker(mapView);
        marker.setPosition(new GeoPoint(latitude, longitude));
        marker.setIcon(getResources().getDrawable(R.drawable.baseline_location_on_24));
        marker.setTitle(title);
        mapView.getOverlays().add(marker);
        mapView.invalidate(); // Refresh the map view
    }
}
