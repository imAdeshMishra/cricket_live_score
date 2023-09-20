package com.example.android.livescores;

import android.os.Parcel;
import android.os.Parcelable;

public class ScoreData implements Parcelable{

    private  final String matchDate;
    private final String matchName;
    private final String matchReport;
    private String fullMatchName;

    private final String name_team_1;
    private final String score_team_1;
    private final String overs_team_1;
    private final String flag_team_1;
    private final String score_team_1_second_inning;
    private final String overs_team_1_second_inning;

    private final String name_team_2;
    private final String score_team_2;
    private final String overs_team_2;
    private final String flag_team_2;
    private final String score_team_2_second_inning;
    private final String overs_team_2_second_inning;


    public ScoreData(String matchDate,String fullMatchName,String matchName,String matchReport, String name_team_1, String score_team_1,String overs_team_1,String score_team_1_second_inning,String overs_team_1_second_inning, String flag_team_1, String name_team_2, String score_team_2,String overs_team_2,String score_team_2_second_inning,String overs_team_2_second_inning, String flag_team_2) {

        this.matchDate=matchDate;
        this.fullMatchName=fullMatchName;
        this.matchName=matchName;
        this.matchReport = matchReport;

        this.flag_team_1 = flag_team_1;
        this.name_team_1 = name_team_1;
        this.score_team_1 = score_team_1;
        this.overs_team_1 = overs_team_1;
        this.score_team_1_second_inning = score_team_1_second_inning;
        this.overs_team_1_second_inning = overs_team_1_second_inning;

        this.flag_team_2 = flag_team_2;
        this.name_team_2 = name_team_2;
        this.score_team_2 = score_team_2;
        this.overs_team_2 = overs_team_2;
        this.score_team_2_second_inning = score_team_2_second_inning;
        this.overs_team_2_second_inning = overs_team_2_second_inning;
    }

    public String getMatchReport() {
        return matchReport;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public String getFullMatchName() {return fullMatchName;}

    public String getMatchName() {
        return matchName;
    }

    public String getName_team_1() {
        return name_team_1;
    }

    public String getScore_team_1() {
        return score_team_1;
    }

    public String getOvers_team_1() {return overs_team_1;}

    public String getScore_team_1_second_inning() {return score_team_1_second_inning;}

    public String getOvers_team_1_second_inning() {return overs_team_1_second_inning;}

    public String getFlag_team_1() {
        return flag_team_1;
    }

    public String getName_team_2() {
        return name_team_2;
    }

    public String getScore_team_2() {
        return score_team_2;
    }

    public String getOvers_team_2() {return overs_team_2; }

    public String getScore_team_2_second_inning() {return score_team_2_second_inning;}

    public String getOvers_team_2_second_inning() {return overs_team_2_second_inning;}

    public String getFlag_team_2() {
        return flag_team_2;
    }

    protected ScoreData(Parcel in) {

        matchDate = in.readString();
        matchName = in.readString();
        matchReport = in.readString();

        name_team_1 = in.readString();
        score_team_1 = in.readString();
        overs_team_1 = in.readString();
        flag_team_1 = in.readString();
        score_team_1_second_inning = in.readString();
        overs_team_1_second_inning = in.readString();

        name_team_2 = in.readString();
        score_team_2 = in.readString();
        overs_team_2 = in.readString();
        flag_team_2 = in.readString();
        score_team_2_second_inning = in.readString();
        overs_team_2_second_inning = in.readString();
    }

    public static final Creator<ScoreData> CREATOR = new Creator<ScoreData>() {
        @Override
        public ScoreData createFromParcel(Parcel in) {
            return new ScoreData(in);
        }

        @Override
        public ScoreData[] newArray(int size) {

            return new ScoreData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(matchDate);
        dest.writeString(matchName);
        dest.writeString(matchReport);
        dest.writeString(name_team_1);
        dest.writeString(score_team_1);
        dest.writeString(overs_team_1);
        dest.writeString(flag_team_1);
        dest.writeString(score_team_1_second_inning);
        dest.writeString(overs_team_1_second_inning);
        dest.writeString(name_team_2);
        dest.writeString(score_team_2);
        dest.writeString(overs_team_2);
        dest.writeString(flag_team_2);
        dest.writeString(score_team_2_second_inning);
        dest.writeString(overs_team_2_second_inning);

    }

}
