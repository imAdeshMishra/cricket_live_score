package com.example.android.livescores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.AppBarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url = "https://api.cricapi.com/v1/currentMatches?apikey=b5e05cec-cd33-4597-8037-2cd038e3b905&offset=0";
    RecyclerView recyclerView;
    List<ScoreData> scores = new ArrayList<>();
    List<ScoreData> filteredScore = new ArrayList<>();
    ScoreAdapter adapter;
    EditText searchView;
    TextView nothingFound;
    TextView hitsToday;
    AppBarLayout searchBarAppView;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        searchBarAppView = findViewById(R.id.search_bar_app_view);
        searchView = findViewById(R.id.search_bar);
        nothingFound = findViewById(R.id.nothing_found);
        nothingFound.setVisibility(View.INVISIBLE);

        hitsToday = findViewById(R.id.hits_used);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filteredScore.clear();
                if (editable.toString().isEmpty()) {
                    searchView.clearFocus();
                    recyclerView.setAdapter(new ScoreAdapter(getApplicationContext(), scores));
                    adapter.notifyDataSetChanged();
                    nothingFound.setVisibility(View.INVISIBLE);

                } else {
                    filter(editable.toString());
                }
            }
        });

        getData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scores.clear();
                // Refresh your data here
                getData();
                // Once the data has been refreshed, call setRefreshing(false) to hide the progress indicator
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void filter(String text) {
        for (ScoreData data : scores) {
            if (data.getName_team_1().toLowerCase().contains(text.toLowerCase()) || data.getName_team_2().toLowerCase().contains(text.toLowerCase())) {
                filteredScore.add(data);
                nothingFound.setVisibility(View.INVISIBLE);
            }
        }
        recyclerView.setAdapter(new ScoreAdapter(getApplicationContext(), filteredScore));
        adapter.notifyDataSetChanged();
        if (filteredScore.size() == 0) {
            nothingFound.setVisibility(View.VISIBLE);
        }
    }

    private void getData() {


        View loadingIndicator = findViewById(R.id.loading_indicator);


        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    loadingIndicator.setVisibility(View.GONE);
                    JSONArray data = response.getJSONArray("data");
                    JSONObject info = response.getJSONObject("info");
                    String apiHitsToday = info.getString("hitsToday");

                    hitsToday.setText(apiHitsToday);

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject current = data.getJSONObject(i);


                        String matchDate = current.getString("date");
                        String status = current.getString("status");

                        JSONArray teamInfo = current.getJSONArray("teamInfo");
                        JSONArray score = current.getJSONArray("score");
                        JSONObject team1;
                        JSONObject team2;

                        JSONObject teamA = teamInfo.getJSONObject(0);
                        String teamAname = teamA.getString("name");


                        JSONObject tA;
                        String tAinning;

                        String scoreOfTeam1 = "Yet to Bat";
                        String scoreOfTeam2 = "Yet to Bat";

                        String team1overs = "";
                        String team2overs = "";

                        String scoreOfTeam1SecondInning = "";
                        String scoreOfTeam2SecondInning = "";

                        String team1oversSecondInning = "";
                        String team2oversSecondInning = "";

                        if (!status.equals("Match not started")) {
                            tA = score.getJSONObject(0);
                            tAinning = tA.getString("inning");

                            if (tAinning.contains(teamAname)) {
                                team1 = teamInfo.getJSONObject(0);
                                team2 = teamInfo.getJSONObject(1);
                            } else {
                                team1 = teamInfo.getJSONObject(1);
                                team2 = teamInfo.getJSONObject(0);
                            }

                            for (int j = 0; j < score.length(); j++) {
                                if (j == 0) {
                                    JSONObject team1score = score.getJSONObject(j);
                                    String team1runs = team1score.getString("r");
                                    String team1wickets = team1score.getString("w");
                                    scoreOfTeam1 = team1runs + "/" + team1wickets;
                                    team1overs = team1score.getString("o");
                                    team1overs = " (" + team1overs + ")";
                                }
                                if (j == 1) {
                                    JSONObject team2score = score.getJSONObject(j);
                                    String team2runs = team2score.getString("r");
                                    String team2wickets = team2score.getString("w");
                                    scoreOfTeam2 = team2runs + "/" + team2wickets;
                                    team2overs = team2score.getString("o");
                                    team2overs = " (" + team2overs + ")";
                                }

                                if (j == 2) {
                                    JSONObject team2score = score.getJSONObject(j);
                                    String team2runs = team2score.getString("r");
                                    String team2wickets = team2score.getString("w");
                                    scoreOfTeam1SecondInning += "&" + team2runs + "/" + team2wickets;
                                    team1oversSecondInning = team2score.getString("o");
                                    team1oversSecondInning = " (" + team1oversSecondInning + ")";
                                }

                                if (j == 3) {
                                    JSONObject team2score = score.getJSONObject(j);
                                    String team2runs = team2score.getString("r");
                                    String team2wickets = team2score.getString("w");
                                    scoreOfTeam2SecondInning += "&" + team2runs + "/" + team2wickets;
                                    team2oversSecondInning = team2score.getString("o");
                                    team2oversSecondInning = " (" + team2oversSecondInning + ")";
                                }
                            }
                        } else {
                            team1 = teamInfo.getJSONObject(0);
                            team2 = teamInfo.getJSONObject(1);
                        }

                        String nameOfTeam1;
                        try {
                            nameOfTeam1 = team1.getString("shortname");
                        } catch (Exception e) {
                            nameOfTeam1 = team1.getString("name");
                        }

                        String team1FlagURL = team1.getString("img");

                        String nameOfTeam2;
                        try {
                            nameOfTeam2 = team2.getString("shortname");
                        } catch (Exception e) {
                            nameOfTeam2 = team2.getString("name");
                        }
                        String team2FlagURL = team2.getString("img");

                        String matchType;
                        try {
                            matchType = current.getString("matchType");
                            matchType = matchType.toUpperCase();
                        } catch (JSONException e) {
                            matchType = " ";
                        }


                        String matchName = current.getString("name");
                        String fullMatchName = matchType + ", " + matchName;

                        nameOfTeam1 += "-";
                        nameOfTeam2 += "-";

                        scores.add(new ScoreData(matchDate,
                                fullMatchName,
                                matchName,
                                status,
                                nameOfTeam1,
                                scoreOfTeam1,
                                team1overs,
                                scoreOfTeam1SecondInning,
                                team1oversSecondInning,
                                team1FlagURL,
                                nameOfTeam2,
                                scoreOfTeam2,
                                team2overs,
                                scoreOfTeam2SecondInning,
                                team2oversSecondInning,
                                team2FlagURL)
                        );

                        adapter = new ScoreAdapter(getApplicationContext(), scores);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }


                } catch (JSONException e) {
                    loadingIndicator.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "JSON Error", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loadingIndicator.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonObjectRequest);
    }
}
