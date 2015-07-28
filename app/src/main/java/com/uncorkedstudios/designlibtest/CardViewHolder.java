package com.uncorkedstudios.designlibtest;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Kat on 7/27/15.
 */
public class CardViewHolder extends RecyclerView.ViewHolder {

    private TextView label;

    private TextView textView;

    public CardViewHolder(View itemView) {
        super(itemView);

        CardView cardView = (CardView) itemView.findViewById(R.id.card);
        textView = (TextView) itemView.findViewById(R.id.textview);

    }

    public void populate(int position) {
        textView.setText("" + position);
    }
}
