package com.treinamento.adenilson.myretrofitapplication.domain.entity;

import android.graphics.Color;

import com.google.gson.annotations.SerializedName;

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
        @SerializedName("good")
        GOOD(Color.GREEN),

        @SerializedName("minor")
        MINOR(Color.YELLOW),

        @SerializedName("major")
        MAJOR(Color.RED);

        private int color;

        Type(int color) {
            this.color = color;
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
