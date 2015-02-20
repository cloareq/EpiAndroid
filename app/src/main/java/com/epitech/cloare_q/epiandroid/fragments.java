package com.epitech.cloare_q.epiandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import beans.HomeBeans;

/**
 * Created by Dodo on 20/02/2015.
 */
public class fragments {

    public static class FragHome extends Fragment {
        private String token;
        private String logTime;
        private String picture;
        private HomeBeans hb;
        public FragHome() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Bundle arg = this.getArguments();
            token = arg.getString("token");
            hb = new HomeBeans(token);

            return inflater.inflate(R.layout.fragment_home, container, false);
        }
    }

    public static class FragPlanning extends Fragment {

        public FragPlanning() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_planning, container, false);
        }
    }

    public static class FragToken extends Fragment {

        Button validate;
        EditText input_text;
        String text;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            final View tokenView = inflater.inflate(R.layout.fragment_token, container, false);
            validate = (Button) tokenView.findViewById(R.id.button_token);
            input_text = (EditText) tokenView.findViewById(R.id.text_token);
            validate.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Log.d("Test", "onClickListener ist gestartet");
                    Toast.makeText(getActivity().getApplicationContext(), "Test", Toast.LENGTH_LONG).show();
                    saveInString();

                }
            });
            return tokenView;
        }

        public void saveInString() {
            Toast.makeText(getActivity().getApplicationContext(), "copie dans chaine", Toast.LENGTH_LONG).show();
            text = input_text.getText().toString();
            Toast.makeText(getActivity().getApplicationContext(), "fini", Toast.LENGTH_SHORT).show();
        }

        /*public FragToken() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_token, container, false);
        }*/
    }

    public static class FragGrades extends Fragment {

        public FragGrades() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_grades, container, false);
        }
    }
}
