package com.example.staticfragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

/**
 * Created by usuario on 16/11/17.
 */

public class FragmentA extends Fragment {
    private FragmentAListener mCallBack;
    private EditText edtMessage;
    private Button btnSize;
    private SeekBar skbSize;

    //Este metodo solo funciona desde la API 23 en adelante. Si se ejecuta en una API inferior no
    //da error pero no funciona la comunicacion entre activity y fragment.
    //@Override
    //public void onAttach(Context context) {
    //    super.onAttach(context);
    //}

    /**
     * Se define la interfaz que servirá de contrato entre el fragment y la activity
     */

    public interface FragmentAListener {
        //Se deberá pasar el mensaje y el tamaño de dicho mensaje.
        void onFragmentAEvent(String message, int size);
    }

    //Usaremos este que funciona en todas.
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            //SI realmente ha implementado dicha interfaz, no debere tener ningun problema
            //Para esto sirve la interfaz que hace de "contrato" con este fragment.
            mCallBack = (FragmentAListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement FragmentAListener");
        }
        Log.d("onAttachA", "FragmentA attached");
    }

    //Debemos sobreescribir este metodo para que infle la vista al llamar al fragment, porque si lo
    //podemos visualizar en el layout es por el tools, pero no esta realmente inflada todavia el layout
    //en el fragment, la vista en el fragment. Aqui tambien al inflar la vista cogemos de ella los elementos
    //que necesitemos.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenta, container, false);
        edtMessage = view.findViewById(R.id.edtMessage);
        btnSize = view.findViewById(R.id.btnSize);
        skbSize = view.findViewById(R.id.skbSize);
        Log.d("onCreateViewA", "FragmentA on create view");
        return view;
    }

    //Se lanza cuando ya la vista se ha inflado y creado. Aqui comunicamos a la activity que se ha pulsado
    //el boton y le pasamos los datos mediante nuestro contrato.
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.onFragmentAEvent(edtMessage.getText().toString(), skbSize.getProgress());
            }
        });
        Log.d("onViewCreatedA", "FragmentA on view created");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //Aqui es cuando el fragment ya termina por completo, por lo que liberamos la memoria de mCallBack.
        mCallBack = null;
        Log.d("onDetachA", "FragmentA detached");
    }

    //MENSAJES DE LOG DE TODOS LOS ESTADOS

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreateA", "FragmentA created");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStartA", "FragmentA started");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResumeA", "FragmentA resumed");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("onPauseA", "FragmentA paused");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("onStopA", "FragmentA stopped");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("onDestroyViewA", "FragmentA view destroyed");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroyA", "FragmentA destroyed");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("onActivityCreatedA", "FragmentA activity created");
    }
}
