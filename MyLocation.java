package com.example.adrianomerodack.promofind;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import model.bean.Promocoes;

/**
 * Created by Adriano Merodack on 28/10/2015.
 */
public class MyLocation implements LocationListener {
    private Context ctx;
    LocationManager locationManager;
    String provedor;
    private boolean networkOn;
    Promocoes promocoes= new Promocoes();



    public MyLocation(Context ctx) {
        this.ctx = ctx;
        locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        provedor = LocationManager.NETWORK_PROVIDER;
        networkOn = locationManager.isProviderEnabled(provedor);
        locationManager.requestLocationUpdates(provedor, 5000, 100, this);
        getLocation();

    }


    private void getLocation(){
        if(networkOn){
            Location lc= locationManager.getLastKnownLocation(provedor);
            if(lc!=null){
                StringBuilder builder=new StringBuilder();
                builder.append("geo:").append(lc.getLatitude())
                        .append(",").append(lc.getLongitude());

                Toast.makeText(ctx, builder.toString(), Toast.LENGTH_LONG).show();
                //setLoc(builder.toString());





            }
        }

    }



    @Override
    public void onLocationChanged(Location location) {
        getLocation();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

