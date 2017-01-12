package com.treinamento.adenilson.myretrofitapplication.domain.entity;

import android.graphics.Color;

import com.google.gson.annotations.SerializedName;
import com.treinamento.adenilson.myretrofitapplication.R;

import java.util.Date;

/**
 * Entidade da API Status
 *
 * @see <a href="https://status.github.com/api/last-message.json"> </a>
 *
 * Created by adenilson on 09/01/17.
 */

public class Status {

    @SerializedName("status")
    public Type type;
    public String body;
    public Date created_on;


    public enum Type{
        NONE(Color.BLACK, R.string.txt_loading),

        @SerializedName("good")
        GOOD(Color.GREEN, R.string.txt_enable),

        @SerializedName("minor")
        MINOR(Color.YELLOW, R.string.txt_alert),

        @SerializedName("major")
        MAJOR(Color.RED, R.string.txt_disable);

        private int message;
        private int color;

        Type(int color, int message) {
            this.color = color;
            this.message= message;
        }

        public int getMessage() {
            return message;
        }

        public int getColor() {
            return color;
        }
    }

    public Type getType() {
        return type;
    }


    public String getBody() {
        return body;
    }

}
