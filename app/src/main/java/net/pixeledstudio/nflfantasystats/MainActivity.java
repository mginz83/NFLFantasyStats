package net.pixeledstudio.nflfantasystats;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class MainActivity extends AppCompatActivity {

    Spinner chooseWeek;
    ListView scoresListView;
    String weekPickedBySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chooseWeek = findViewById(R.id.chooseWeek);
        scoresListView = findViewById(R.id.scoresListView);

        ArrayAdapter<String> chooseWeekAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.choose_week_array));
        chooseWeek.setAdapter(chooseWeekAdapter);

        chooseWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                weekPickedBySpinner = Constants.convertWeekFromSpinner(adapterView.getItemAtPosition(i).toString());
                new GetGameScoresFromESPN_XML().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //if user is not logged in, go to login screen.....

    }


    private class GetGameScoresFromESPN_XML extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //BEFORE THE EXECUTE

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            //EXECUTE
            HttpHandler sh = new HttpHandler();

            String apiURL = Constants.NFL_SCORES_BY_WEEK_API + weekPickedBySpinner;

            String jsonStr = sh.makeServiceCall(apiURL);

            if (jsonStr != null) {

                try {
                    JSONObject xmlJSONObj = XML.toJSONObject(jsonStr);
                    System.out.println(xmlJSONObj);


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }

            return null;
        }


        @Override
        protected void onPostExecute (Void result){
            super.onPostExecute(result);
            //AFTER EXECUTE
        }

    }
}
