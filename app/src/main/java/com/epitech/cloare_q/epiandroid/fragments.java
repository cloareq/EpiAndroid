package com.epitech.cloare_q.epiandroid;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import beans.HomeBeans;
import beans.GradesBeans;
import beans.MessagesBeans;
import beans.ModulesBeans;
import beans.PlanningBeans;
import beans.TokenBeans;

public class fragments {

    public static class FragHome extends Fragment {
        private String token;
        private String logTime;
        private String name;
        TextView nameText;
        TextView logText;
        TextView inputAlert;
        private HomeBeans hb;
        ImageView ppicture;
        private MessagesBeans mb;
        List<Map<String, String>> listMsg;
        List<Map<String, String>> listAlerts;

        ListView listElem;

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
            logText.setText(Integer.toString(finalLog) + " heure(s)");


            ppicture = (ImageView) homeView.findViewById(R.id.profilePicture);
            Bitmap p = hb.getImage();
            ppicture.setImageBitmap(p);

            mb = new MessagesBeans(token);
            listMsg = mb.getListMsg();

            listElem = (ListView) homeView.findViewById(R.id.latestMsg);
            SimpleAdapter mSchedule = new SimpleAdapter (getActivity().getApplicationContext(), listMsg, R.layout.list_single_line,
                    new String[] {"date", "title", "content"}, new int[] {R.id.dateMsg, R.id.titleMsg, R.id.contentMsg});

            listElem.setAdapter(mSchedule);

            listAlerts = hb.getListAlerts();

            String alerts = "";

            for (int i = 0;i<listAlerts.size(); i++)
            {alerts += listAlerts.get(i).get("title");}

            inputAlert = (TextView) homeView.findViewById(R.id.txtAlert);
            inputAlert.setText(alerts);

            return homeView;
        }
    }

    public static class FragToken extends Fragment {

        Button validate;
        EditText input_text;
        String text;
        List<Map<String, String>> listToken = new ArrayList<>();
        TokenBeans tkn;

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
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

            // Print dates of the current week starting on Monday
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String firstDay = df.format(c.getTime());
            c.add(Calendar.DATE, 6);
            String lastDay = df.format(c.getTime());

            Bundle arg = this.getArguments();
            String token = arg.getString("token");
            tkn = new TokenBeans(token);
            tkn.requestToken(firstDay, lastDay);

            listToken = tkn.getListToken();

            System.out.println("LIST TOKEN =>" + listToken);

            //sendtokenrequest

        //tkn.validateToken(String t, Map<String, String> event, String token_validation) {


            return tokenView;
        }

        public void saveInString() {
            Toast.makeText(getActivity().getApplicationContext(), "copie dans chaine", Toast.LENGTH_LONG).show();
            text = input_text.getText().toString();
            Toast.makeText(getActivity().getApplicationContext(), "fini", Toast.LENGTH_SHORT).show();
        }
    }

    public static class FragPlanning extends Fragment {
        private String token;
        List<Map<String,String>> planning;
        View planningView;
        ListView listPlanning;
        Button prev;
        Button next;
        Calendar c = Calendar.getInstance();

        public FragPlanning() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            planningView = inflater.inflate(R.layout.fragment_planning, container, false);

            // Get calendar set to current date and time

            // Set the calendar to monday of the current week
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

            // Print dates of the current week starting on Monday
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String firstDay = df.format(c.getTime());
            c.add(Calendar.DATE, 6);
            String lastDay = df.format(c.getTime());


            Bundle arg = this.getArguments();
            token = arg.getString("token");


            prev = (Button) planningView.findViewById(R.id.prev_week);
            next = (Button) planningView.findViewById(R.id.next_week);

            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    prevWeek();
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nextWeek();
                }
            });

            fillPlanning(firstDay, lastDay);

            return planningView;
        }
        public void prevWeek() {

            // Print dates of the current week starting on Monday
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            c.add(Calendar.DATE, -13);
            String firstDay = df.format(c.getTime());
            c.add(Calendar.DATE, 6);
            String lastDay = df.format(c.getTime());
            fillPlanning(firstDay, lastDay);
        }

        public void nextWeek() {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            c.add(Calendar.DATE, 1);
            String firstDay = df.format(c.getTime());
            c.add(Calendar.DATE, 6);
            String lastDay = df.format(c.getTime());
            fillPlanning(firstDay, lastDay);
        }

        public void fillPlanning (String firstDay, String lastDay){
            PlanningBeans pb = new PlanningBeans(token);
            pb.requestPlanning(firstDay, lastDay);
            planning = pb.getListPlanning();
            System.out.println("PLAANING ___ > " + planning);
            listPlanning = (ListView) planningView.findViewById(R.id.planningList);
            SimpleAdapter mSchedule = new SimpleAdapter (getActivity().getApplicationContext(), planning, R.layout.simple_line_planning,
                    new String[] {"start", "end", "acti_title", "titlemodule"}, new int[] {R.id.start_date, R.id.end_date, R.id.titleCourse, R.id.planningModuleTitle});

            listPlanning.setAdapter(mSchedule);
        }
    }

    public static class FragGrades extends Fragment {

        private GradesBeans gb;
        private String token;
        TextView note;
        TextView title;
        TextView year;
        TextView comment;
        Button prev;
        Button next;
        int gradePosition;
        View gradeView;
        List<Map<String, String>> grades;

        public FragGrades() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            gradeView = inflater.inflate(R.layout.fragment_grades, container, false);
            Bundle arg = this.getArguments();
            token = arg.getString("token");
            gb = new GradesBeans(token);
            grades = gb.getGrades();
            gradePosition = grades.size() - 1;
            prev = (Button) gradeView.findViewById(R.id.prev_button);
            next = (Button) gradeView.findViewById(R.id.next_button);
            fillGradePage();

            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    prevGrade();
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nextGrade();
                }
            });
            return gradeView;
        }

        public void prevGrade() {
            gradePosition -= 1;
            if (gradePosition < 0) {
                Toast.makeText(getActivity().getApplicationContext(), R.string.next_grade_msg, Toast.LENGTH_LONG).show();
                gradePosition = 0;
                return ;
            }
            fillGradePage();
        }

        public void nextGrade() {
            gradePosition += 1;
            if (gradePosition == grades.size()) {
                Toast.makeText(getActivity().getApplicationContext(), R.string.next_grade_msg, Toast.LENGTH_LONG).show();
                gradePosition -= 1;
                return ;
            }
            fillGradePage();
        }

        public void fillGradePage() {
            note = (TextView) gradeView.findViewById(R.id.final_note);
            note.setText(grades.get(gradePosition).get("final_note"));
            title = (TextView) gradeView.findViewById(R.id.title_grade);
            title.setText(grades.get(gradePosition).get("title"));
            year = (TextView) gradeView.findViewById(R.id.year);
            year.setText(grades.get(gradePosition).get("scolaryear"));
            comment = (TextView) gradeView.findViewById(R.id.commentContent);
            comment.setText(grades.get(gradePosition).get("comment"));
            comment.setMovementMethod(new ScrollingMovementMethod());
        }
    }

    public static class FragModules extends Fragment {

        private ModulesBeans mb;
        private String token;
        List<Map<String, String>> modules;
        ListView listModules;

        public FragModules() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            final View modulesView = inflater.inflate(R.layout.fragment_modules, container, false);
            Bundle arg = this.getArguments();
            token = arg.getString("token");
            mb = new ModulesBeans(token);
            modules = mb.getModules();

            listModules = (ListView) modulesView.findViewById(R.id.listModules);
            SimpleAdapter mSchedule = new SimpleAdapter (getActivity().getApplicationContext(), modules, R.layout.simple_line_module,
                    new String[] {"semester", "title", "grade"}, new int[] {R.id.semester, R.id.titleModule, R.id.gradeModule});

            listModules.setAdapter(mSchedule);

            return modulesView;
        }
    }
}
