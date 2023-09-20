package com.example.android.livescores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    List<ScoreData> score;

    public ScoreAdapter(Context context, List<ScoreData> score) {
        this.score = score;
    }

    @NonNull
    @Override
    public ScoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scores_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScoreAdapter.ViewHolder holder, int position) {
        String dateString = score.get(position).getMatchDate();
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = inputDateFormat.parse(dateString);
            String formattedDate = outputDateFormat.format(date);
            dateString = formattedDate;
        } catch (Exception e) {
            dateString = dateString + "";
        }

        holder.matchDate.setText(dateString);
        holder.matchName.setText(score.get(position).getMatchName());
        holder.matchReport.setText(score.get(position).getMatchReport());

        Picasso.get().load(score.get(position).getFlag_team_1()).into(holder.team_1_flag);
        holder.team_1_name.setText(score.get(position).getName_team_1());
        holder.team_1_score.setText(score.get(position).getScore_team_1());
        holder.team_1_overs.setText(score.get(position).getOvers_team_1());
        holder.team_1_score_second_inning.setText(score.get(position).getScore_team_1_second_inning());
        holder.team_1_overs_second_inning.setText(score.get(position).getOvers_team_1_second_inning());

        Picasso.get().load(score.get(position).getFlag_team_2()).into(holder.team_2_flag);
        holder.team_2_name.setText(score.get(position).getName_team_2());
        holder.team_2_score.setText(score.get(position).getScore_team_2());
        holder.team_2_overs.setText(score.get(position).getOvers_team_2());
        holder.team_2_score_second_inning.setText(score.get(position).getScore_team_2_second_inning());
        holder.team_2_overs_second_inning.setText(score.get(position).getOvers_team_2_second_inning());

        ScoreData matchScore = new ScoreData(
                dateString,
                score.get(position).getFullMatchName(),
                score.get(position).getMatchName(),
                score.get(position).getMatchReport(),
                score.get(position).getName_team_1(),
                score.get(position).getScore_team_1(),
                score.get(position).getOvers_team_1(),
                score.get(position).getScore_team_1_second_inning(),
                score.get(position).getOvers_team_1_second_inning(),
                score.get(position).getFlag_team_1(),
                score.get(position).getName_team_2(),
                score.get(position).getScore_team_2(),
                score.get(position).getOvers_team_2(),
                score.get(position).getScore_team_2_second_inning(),
                score.get(position).getOvers_team_2_second_inning(),
                score.get(position).getFlag_team_2()
        );
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), MatchActivity.class);
            intent.putExtra("matchScore", matchScore);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return score.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView hitsToday;
        TextView matchDate;
        TextView matchName;
        TextView matchReport;
        TextView team_1_name;
        TextView team_1_score;
        TextView team_1_overs;
        TextView team_1_score_second_inning;
        TextView team_1_overs_second_inning;
        ImageView team_1_flag;
        TextView team_2_name;
        TextView team_2_score;
        TextView team_2_overs;
        TextView team_2_score_second_inning;
        TextView team_2_overs_second_inning;
        ImageView team_2_flag;


        public ViewHolder(View itemView) {
            super(itemView);
            hitsToday = itemView.findViewById(R.id.hits_used);
            matchDate = itemView.findViewById(R.id.match_date);
            matchName = itemView.findViewById(R.id.match_name);
            matchReport = itemView.findViewById(R.id.match_report);

            team_1_flag = itemView.findViewById(R.id.team_1_flag);
            team_1_name = itemView.findViewById(R.id.team_1_name);
            team_1_score = itemView.findViewById(R.id.team_1_score);
            team_1_overs = itemView.findViewById(R.id.team_1_overs);
            team_1_score_second_inning = itemView.findViewById(R.id.team_1_score_second_innings);
            team_1_overs_second_inning = itemView.findViewById(R.id.team_1_overs_second_innings);

            team_2_flag = itemView.findViewById(R.id.team_2_flag);
            team_2_name = itemView.findViewById(R.id.team_2_name);
            team_2_score = itemView.findViewById(R.id.team_2_score);
            team_2_overs = itemView.findViewById(R.id.team_2_overs);
            team_2_score_second_inning = itemView.findViewById(R.id.team_2_score_second_innings);
            team_2_overs_second_inning = itemView.findViewById(R.id.team_2_overs_second_innings);

        }
    }
}