package com.example.android.livescores;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class MatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_layout);


        Intent intent = getIntent();
        ScoreData score = intent.getParcelableExtra("matchScore");

        setTitle(score.getMatchName());
        setMatchData(score);

    }

    private void setMatchData(ScoreData score) {
        TextView match_layout_matchDate = findViewById(R.id.match_layout_match_date);
//        TextView match_layout_matchName = findViewById(R.id.match_layout_match_name);

        TextView match_layout_team_1_name = findViewById(R.id.match_layout_team_1_name);
        ImageView match_layout_team_1_flag = findViewById(R.id.match_layout_team_1_flag);
        TextView match_layout_team_1score = findViewById(R.id.match_layout_team_1score);
        TextView match_layout_team_1_overs = findViewById(R.id.match_layout_team_1_overs);

        TextView match_layout_team_2_name = findViewById(R.id.match_layout_team_2_name);
        TextView match_layout_team_2_score = findViewById(R.id.match_layout_team_2_score);
        TextView match_layout_team_2_overs = findViewById(R.id.match_layout_team_2_overs);
        ImageView match_layout_team_2_flag = findViewById(R.id.match_layout_team_2_flag);

        TextView match_layout_match_status = findViewById(R.id.match_layout_match_status);

        match_layout_match_status.setText(score.getMatchReport());
        match_layout_matchDate.setText(score.getMatchDate());
//        match_layout_matchName.setText(score.getMatchName());

        Picasso.get().load(score.getFlag_team_1()).into(match_layout_team_1_flag);
        String team_1_name = score.getName_team_1();
        team_1_name=team_1_name.substring(0, team_1_name.length() - 1);
        match_layout_team_1_name.setText(team_1_name);
        match_layout_team_1score.setText(score.getScore_team_1());
        match_layout_team_1_overs.setText(score.getOvers_team_1());

        Picasso.get().load(score.getFlag_team_2()).into(match_layout_team_2_flag);
        String team_2_name = score.getName_team_2();
        team_2_name=team_2_name.substring(0, team_2_name.length() - 1);
        match_layout_team_2_name.setText(team_2_name);
        match_layout_team_2_score.setText(score.getScore_team_2());
        match_layout_team_2_overs.setText(score.getOvers_team_2());
    }
}










