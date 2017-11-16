package com.example.staticfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by usuario on 16/11/17.
 */

public class FragmentB extends Fragment {
    private TextView txvMessage;

    //No necesita comunicacion por lo que no necesita contrato, simplemente recibira los datos del otro fragment a traves de
    //la activity. Solo implementa el onCreateView para que infle la vista que va a mostrar.

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentb, container, false);
        txvMessage = view.findViewById(R.id.txvMessage);
        Log.d("onCreateViewB", "FragmentB create view");
        return view;
    }

    public void changeTextAndSize(String message, int size) {
        txvMessage.setText(message);
        txvMessage.setTextSize(size);
    }

    //Guardamos el estado dinámico para que al girar la pantalla no se borren los datos al destruirse y crearse de nuevo.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message", txvMessage.getText().toString());
        outState.putFloat("size", txvMessage.getTextSize());
    }

    //Restauramos el estado dinamico, o tambien se puede hacer en onViewCreated.
    //Aumentan las dimensiones automaticamente, por lo que hay que arreglarlo: https://stackoverflow.com/questions/22935490/orientation-change-increases-textviews-text-size
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            txvMessage.setText(savedInstanceState.getString("message"));
            txvMessage.setTextSize(savedInstanceState.getFloat("size"));
        }
        Log.d("onActivityCreatedB", "FragmentB activity created");
    }

    //MENSAJES DE LOG DE TODOS LOS ESTADOS

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreateB", "FragmentB created");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStartB", "FragmentB started");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResumeB", "FragmentB resumed");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("onPauseB", "FragmentB paused");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("onStopB", "FragmentB stopped");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("onDestroyViewB", "FragmentB view destroyed");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroyB", "FragmentB destroyed");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("onDetachB", "FragmentB detached");
    }


}
