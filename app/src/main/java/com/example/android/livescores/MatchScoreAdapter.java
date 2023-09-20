package com.example.android.livescores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


public class MatchScoreAdapter extends RecyclerView.Adapter<MatchScoreAdapter.ViewHolder> {

    ScoreData score;
    public MatchScoreAdapter(Context context, ScoreData score) {
        this.score=score;
    }

    @Override
    public MatchScoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scores_data,parent,false);
        return new MatchScoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatchScoreAdapter.ViewHolder holder, int position) {
        holder.match_layout_match_status.setText(score.getMatchReport());
        holder.matchDate.setText(score.getMatchDate());
        holder.matchName.setText(score.getMatchName());

        Picasso.get().load(score.getFlag_team_1()).into(holder.match_layout_team_1_flag);
        holder.match_layout_team_1_name.setText(score.getName_team_1());
        holder.match_layout_team_1score.setText(score.getScore_team_1());
        holder.match_layout_team_1_overs.setText(score.getOvers_team_1());

        Picasso.get().load(score.getFlag_team_2()).into(holder.match_layout_team_2_flag);
        holder.match_layout_team_2_name.setText(score.getName_team_2());
        holder.match_layout_team_2_score.setText(score.getScore_team_2());
        holder.match_layout_team_2_overs.setText(score.getOvers_team_2());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView matchDate;
        TextView matchName;


        ImageView match_layout_team_1_flag;
        TextView match_layout_team_1_name;
        TextView match_layout_team_1score;
        TextView match_layout_team_1_overs;

        TextView match_layout_team_2_name;
        TextView match_layout_team_2_score;
        TextView match_layout_team_2_overs;
        ImageView match_layout_team_2_flag;

        TextView match_layout_match_status;

        public ViewHolder(View itemView) {
            super(itemView);
            matchDate = itemView.findViewById(R.id.match_date);
            matchName = itemView.findViewById(R.id.match_name);

            match_layout_team_1_name = itemView.findViewById(R.id.match_layout_team_1_name);
            match_layout_team_1_flag = itemView.findViewById(R.id.match_layout_team_1_flag);
            match_layout_team_1score = itemView.findViewById(R.id.match_layout_team_1score);
            match_layout_team_1_overs = itemView.findViewById(R.id.match_layout_team_1_overs);

            match_layout_team_2_name = itemView.findViewById(R.id.match_layout_team_2_name);
            match_layout_team_2_score = itemView.findViewById(R.id.match_layout_team_2_score);
            match_layout_team_2_overs = itemView.findViewById(R.id.match_layout_team_2_overs);
            match_layout_team_2_flag = itemView.findViewById(R.id.match_layout_team_2_flag);

            match_layout_match_status = itemView.findViewById(R.id.match_layout_match_status);

        }
    }
}
