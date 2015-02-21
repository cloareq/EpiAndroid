package com.epitech.cloare_q.epiandroid;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import beans.HomeBeans;
import beans.GradesBeans;

/**
 * Created by Dodo on 20/02/2015.
 */
public class fragments {

    public static class FragHome extends Fragment {
        private String token;
        private String logTime;
        private String picture;
        private String name;
        TextView nameText;
        TextView logText;
        private HomeBeans hb;
        ImageView ppicture;

        public FragHome() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View homeView = inflater.inflate(R.layout.fragment_home, container, false);
            Bundle arg = this.getArguments();
            token = arg.getString("token");
            hb = new HomeBeans(token);
            name = hb.getName();
            logTime = hb.getLogTime();
            nameText = (TextView) homeView.findViewById(R.id.txtName);
            nameText.setText(name);
            logText = (TextView) homeView.findViewById(R.id.txtLog);
            Double time = Double.parseDouble(logTime);
            int finalLog = (int) Math.round(time);
            logText.setText(Integer.toString(finalLog));


            ppicture = (ImageView) homeView.findViewById(R.id.profilePicture);
            Bitmap p = hb.getImage();
            ppicture.setImageBitmap(p);

            return homeView;
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

        private GradesBeans hb;
        private String token;

        public FragGrades() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View gradeView = inflater.inflate(R.layout.fragment_grades, container, false);
            Bundle arg = this.getArguments();
            token = arg.getString("token");
            hb = new GradesBeans(token);

            return gradeView;
        }
    }
}
