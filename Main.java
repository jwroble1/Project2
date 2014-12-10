Main.java

package com.example.spearre91.adviseme;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;

public class Main extends ActionBarActivity {

    private static final String url_test = "http://userpages.umbc.edu/~jwroble1/CMSC331/phpCode/getData.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class Course {
        private String classTitle;
        private String classNum;
        private int startTime;
        private int endTime;
        private boolean mon;
        private boolean tues;
        private boolean wed;
        private boolean thurs;
        private boolean fri;

        public Course(String title, String num) {
            classTitle = title;
            classNum = num;
            startTime = 0;
            endTime = 0;
            mon = false;
            tues = false;
            wed = false;
            thurs = false;
            fri = false;
        }

        /*public Course(String data) {
            String title;
            String num;
            String[] temp = data.split(",");
            title = temp[2].substring(14, 18);
            num = temp[3].substring(15, 17);
            if (!temp[3].substring(18).equals("\"")) {
                num.concat(temp[3].substring(18));
            }

            int start = Character.getNumericValue(temp[5].charAt(1)) * 100000 + Character.getNumericValue(temp[5].charAt(2)) * 100000 + Character.getNumericValue(temp[5].charAt(3)) * 1000 + Character.getNumericValue(temp[5].charAt(4)) * 100 + Character.getNumericValue(temp[5].charAt(5)) * 10 + Character.getNumericValue(temp[5].charAt(6));
            startTime = start;
            int end = Character.getNumericValue(temp[6].charAt(1)) * 100000 + Character.getNumericValue(temp[6].charAt(2)) * 100000 + Character.getNumericValue(temp[6].charAt(3)) * 1000 + Character.getNumericValue(temp[6].charAt(4)) * 100 + Character.getNumericValue(temp[6].charAt(5)) * 10 + Character.getNumericValue(temp[6].charAt(6));

            endTime = end;
        }


        public void setStartTime(int num) {
            startTime = num;
        }

        public void setEndTime(int num) {
            endTime = num;
        }
        */
        public boolean matchesTime(Course other) {
            boolean flag = false;
            if (this.mon && other.mon) {
                flag = true;
            } else if (this.tues && other.tues) {
                flag = true;
            } else if (this.wed && other.wed) {
                flag = true;
            } else if (this.thurs && other.thurs) {
                flag = true;
            } else if (this.fri && other.fri) {
                flag = true;
            }

            if (flag) {
                if (this.startTime == other.startTime || this.endTime == other.endTime || this.endTime > other.startTime || this.startTime < other.endTime) {
                }
                else {
                    flag = false;
                }
            }

            return flag;
        }

        public String getClassTitle() {
            return classTitle;
        }

        public String getClassNum() {
            return classNum;
        }

        public String toString() {
            String finalString = classTitle + classNum + " ";
            if(mon)
            {finalString += "M";}
            if(tues)
            {finalString += "T";}
            if(wed)
            {finalString += "W";}
            if(thurs)
            {finalString += "Th";}
            if(fri)
            {finalString += "F";}
            finalString += " ";
            finalString += startTime;
            finalString += endTime;
            return finalString;
        }
    }
    public String getClassTitle(String data)
    {
        String title;
        String[] temp = data.split(",");
        title = temp[2].substring(14, 18);
        return title;
    }

    public String getClassNum(String data)
    {
        String num;
        String[] temp = data.split(",");
        num = temp[3].substring(15, 17);
        if(!temp[3].substring(18).equals("\""))
        {
            num.concat(temp[3].substring(18));
        }
        return num;
    }


    public void Submit(View view) {
        EditText class1SubjField = (EditText) findViewById(R.id.editText);
        String class1Subj = class1SubjField.getText().toString();

        EditText class1NumField = (EditText) findViewById(R.id.editText2);
        String class1Num = class1NumField.getText().toString();

        EditText class2SubjField = (EditText) findViewById(R.id.editText3);
        String class2Subj = class2SubjField.getText().toString();

        EditText class2NumField = (EditText) findViewById(R.id.editText4);
        String class2Num = class2NumField.getText().toString();

        EditText class3SubjField = (EditText) findViewById(R.id.editText5);
        String class3Subj = class3SubjField.getText().toString();

        EditText class3NumField = (EditText) findViewById(R.id.editText6);
        String class3Num = class3NumField.getText().toString();

        EditText class4SubjField = (EditText) findViewById(R.id.editText7);
        String class4Subj = class4SubjField.getText().toString();

        EditText class4NumField = (EditText) findViewById(R.id.editText8);
        String class4Num = class4NumField.getText().toString();

        EditText class5SubjField = (EditText) findViewById(R.id.editText9);
        String class5Subj = class5SubjField.getText().toString();

        EditText class5NumField = (EditText) findViewById(R.id.editText10);
        String class5Num = class5NumField.getText().toString();

        EditText class6SubjField = (EditText) findViewById(R.id.editText11);
        String class6Subj = class6SubjField.getText().toString();

        EditText class6NumField = (EditText) findViewById(R.id.editText12);
        String class6Num = class6NumField.getText().toString();


        ArrayList<String> courseList = new ArrayList<String>();

        try {
            JSONArray jArray = new JSONArray(url_test);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                courseList.add(json_data.getString("$data"));
            }
        } catch (JSONException e) {
            System.out.print("error");
        }

        Course course1 = new Course(class1Subj, class1Num);
        Course course2 = new Course(class2Subj, class2Num);
        Course course3 = new Course(class3Subj, class3Num);
        Course course4 = new Course(class4Subj, class4Num);
        Course course5 = new Course(class5Subj, class5Num);
        Course course6 = new Course(class6Subj, class6Num);

        Course wants[] = {course1, course2, course3, course4, course5, course6};
        ArrayList<ArrayList<Course>> possibilities = new ArrayList<ArrayList<Course>>();
        Course temp = new Course("", "");
        for (int i = 0; i < wants.length; i++) {
            for (int j = 0; j < courseList.size(); j++) {
                if (getClassTitle(courseList.get(j)).equals(wants[i].getClassTitle())) {
                    if (getClassNum(courseList.get(j)).equals(wants[i].getClassNum())) {
                        temp = new Course(getClassTitle(courseList.get(j)), getClassNum(courseList.get(j)));
                    }
                }
            }
            for (int j = 0; j < possibilities.size(); j++) {
                for (int k = 0; k < possibilities.get(j).size(); k++) {
                    if (possibilities.get(j).get(k).matchesTime(temp)) {
                        if (j > 1) {
                            possibilities.remove(j);
                        }
                    } else {
                        possibilities.get(j).add(temp);
                    }
                }
            }

        }
        ArrayList<String> possibilities2 = new ArrayList<String>();
        String temp2;
        for (int i = 0; i < possibilities.size(); i++) {
            temp2 = "";
            for (int j = 0; j < possibilities.get(i).size(); j++) {
                temp2 += possibilities.get(i).get(j).toString();
                temp2 += " ";
            }
            possibilities2.add(temp2);
        }


        // link to results page
        Intent intent = new Intent(Main.this, ResultsPage.class);
        intent.putStringArrayListExtra("possibilities2", possibilities2);
        startActivity(intent);
    }

}
