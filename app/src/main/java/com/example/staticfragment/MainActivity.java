package com.example.staticfragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentA.FragmentAListener{

    private Fragment fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Obtenemos el fragmentB de esta activity mediante el FragmentManager que obtenemos
        // con getSupportFragmentManager.
        fragmentB = getSupportFragmentManager().findFragmentById(R.id.fragmentB);
    }

    //La activity si conoce los fragment que la componen. La gestion de cambios de fragment de uno a otros,
    //y la comunicacion activity-fragment, entre otras cosas lo gestiona el FragmentManager.
    @Override
    public void onFragmentAEvent(String message, int size) {
        ((FragmentB)fragmentB).changeTextAndSize(message, size);
    }
}
