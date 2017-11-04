package com.example.android.cryptoapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.cryptoapp.R;
import com.example.android.cryptoapp.Results;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by OZMA NIG COM LTD on 13-Oct-17.
 */

public class RatesAdapter extends RecyclerView.Adapter<RatesAdapter.Rates_ViewHoler>  {

    final private ListItemClickListiner mOnClickedListiner;
    private List<Results> mResults;
    private Context context;
    private int clickedPosition;
    ImageView currencyImage;
    TextView currencyAbr;
    TextView exchangeRate_1;
    TextView currencyName;
    TextView exchangeRate_2;
    TextView currencySymbol_1;
    TextView currencySymbol_2;
    DecimalFormat format;


        public RatesAdapter(Context context, List<Results> results, ListItemClickListiner listiner) {

            mResults = results;
            this.context = context;
            mOnClickedListiner = listiner;


        }

        @Override
        public RatesAdapter.Rates_ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
            int layoutResourceId = R.layout.rates_row_items;
            Context getCurrentContext = parent.getContext();
            LayoutInflater layoutInflater = LayoutInflater.from(getCurrentContext);


            View newViewHolder = layoutInflater.inflate(layoutResourceId, parent, false);

            return new Rates_ViewHoler(newViewHolder);


        }

        @Override
        public void onBindViewHolder(Rates_ViewHoler holder, int position) {
            holder.bind(position);


        }



        @Override
        public int getItemCount() {
            return mResults.size();
        }

        public interface ListItemClickListiner {
            void onListItemClicked(int clickditemindex);
        }




        public class Rates_ViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {

            private Rates_ViewHoler(View itemView) {
                super(itemView);

                itemView.setOnClickListener(this);


                exchangeRate_1 = (TextView)itemView.findViewById(R.id.conversion_result);
                currencyAbr = (TextView)itemView.findViewById(R.id.currency_abr);
                currencyImage=(ImageView)itemView.findViewById(R.id.currency_image);
                currencyName =  (TextView)itemView.findViewById(R.id.currency_name);
                currencySymbol_1 =(TextView)itemView.findViewById(R.id.currency_symbol);
                exchangeRate_2 = (TextView)itemView.findViewById(R.id.conversion_result_1);
                currencySymbol_2=(TextView)itemView.findViewById(R.id.currency_symbol_1);


            }


            void bind(int listIndex) {

                format = new DecimalFormat();
                format.setGroupingUsed(true);
                format.setMaximumIntegerDigits(10);
                format.setMaximumFractionDigits(3);

                exchangeRate_1.setText(format.format(mResults.get(listIndex).getFirstExRate()));
                currencySymbol_1.setText(mResults.get(listIndex).getSymbol());
                exchangeRate_2.setText(format.format(mResults.get(listIndex).getSecondExRate()));
                currencySymbol_2.setText(mResults.get(listIndex).getSymbol());
                currencyAbr.setText(mResults.get(listIndex).getAbbrivation());
                currencyImage.setImageResource(mResults.get(listIndex).getImage());
                currencyName.setText(mResults.get(listIndex).getName());

            }




            @Override
            public void onClick(View view) {

//             Set the body of the function to get the position which is the item that was clicked
                clickedPosition = getAdapterPosition();


//             This invokes the onclick listener of the other class by passing clickedPosition value
                mOnClickedListiner.onListItemClicked(clickedPosition);
            }
        }

        //adds data for the adapter to utilize
    public void add (Results results){

        mResults.add(results);
    }

    }

