package com.world.jasonloh95.evironmentreader;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PM25 extends Fragment {

    static TextView north;
    static TextView east;
    static TextView west;
    static TextView south;
    static TextView national;
    static TextView result;
    Button refresh;

    PM25 pm25;

    private static final String TAG = "MyActivity";

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);

        View view=inflater.inflate(R.layout.activity_pm25,container,false);

        //link between class and UI. call the textView and button to use in the class.
        result = (TextView) view.findViewById(R.id.textView);
        north = (TextView) view.findViewById(R.id.north1);
        east = (TextView) view.findViewById(R.id.east1);
        west = (TextView) view.findViewById(R.id.west1);
        south = (TextView) view.findViewById(R.id.south1);
        national = (TextView) view.findViewById(R.id.national1);
        refresh = (Button) view.findViewById(R.id.button) ;


        // On click the refresh button and then the system will execute the class again.
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(PM25.this).attach(PM25.this).commit();

            }
        });

        //call a class which call DataAdapter
        DataAdapter i = new DataAdapter(getActivity());
        i.getProcess("PM25");
        i.execute();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}
