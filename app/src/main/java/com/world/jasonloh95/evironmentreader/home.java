package com.world.jasonloh95.evironmentreader;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class home extends Fragment {

     static TextView result;
     static TextView psi;
     static TextView pm25;
    Button refresh;

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        View view=inflater.inflate(R.layout.activity_home,container,false);

        //link between class and UI. call the textView and button to use in the class.
        result = (TextView) view.findViewById(R.id.textView);
        psi = (TextView) view.findViewById(R.id.psi1);
        pm25 = (TextView) view.findViewById(R.id.pm251);
        refresh = (Button) view.findViewById(R.id.button) ;


        // On click the refresh button and then the system will execute the class again.
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(home.this).attach(home.this).commit();

            }
        });

        //call a class which call DataAdapter
        DataAdapter i = new DataAdapter(getActivity());
        i.getProcess("home");
        i.execute();

            return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}

