package com.example.android.cryptoapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.Results
import com.example.android.cryptoapp.adapter.RatesAdapter.Rates_ViewHoler
import com.google.gson.Gson
import java.text.DecimalFormat


//class RatesAdapter(private val mContext: Context, private val mResults: MutableList<Results>, private val mOnClickedListiner: ListItemClickListiner) : RecyclerView.Adapter<Rates_ViewHoler>() {
class RatesAdapter( private val mResults: MutableList<Results>, private  val mListItemClickListiner :ListItemClickListiner) : RecyclerView.Adapter<Rates_ViewHoler>() {
    private var clickedPosition = 0
    private lateinit var currencyImage: ImageView
    private lateinit var currencyAbr: TextView
    private lateinit var exchangeRate_1: TextView
    private lateinit var currencyName: TextView
    private lateinit var exchangeRate_2: TextView
    private lateinit var currencySymbol_1: TextView
    private lateinit var currencySymbol_2: TextView
    private lateinit var mOnClickedListiner: ListItemClickListiner
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Rates_ViewHoler {
        Log.e("onCreateViewHolder",  "Here ")

        val layoutResourceId = R.layout.rates_row_items
        val getCurrentContext = parent.context
        val layoutInflater = LayoutInflater.from(getCurrentContext)
        val newViewHolder = layoutInflater.inflate(layoutResourceId, parent, false)
        return Rates_ViewHoler(newViewHolder)
    }

    override fun onBindViewHolder(holder: Rates_ViewHoler, position: Int) {
        Log.e("position",  "${position}")

        holder.bind(position)
    }

    override fun getItemCount(): Int {

        Log.e(" mResults.size",  "${mResults.size}")
        return mResults.size
    }

    interface ListItemClickListiner {
        fun onListItemClicked(clickditemindex: Int)
    }

    inner class Rates_ViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        fun bind(listIndex: Int) {
            var format: DecimalFormat? = null
            format = DecimalFormat()
            format.isGroupingUsed = true
            format.maximumIntegerDigits = 10
            format.maximumFractionDigits = 3

            exchangeRate_1.setText(format.format(mResults[listIndex].firstExRate))
            currencySymbol_1.setText(mResults[listIndex].symbol)
            exchangeRate_2.setText(format.format(mResults[listIndex].secondExRate))
            currencySymbol_2.setText(mResults[listIndex].symbol)
            currencyAbr.setText(mResults[listIndex].abbrivation)
            currencyImage?.setImageResource(mResults[listIndex].image)
            currencyName.setText(mResults[listIndex].name)
        }

        override fun onClick(view: View) {

//             Set the body of the function to get the position which is the item that was clicked
            clickedPosition = adapterPosition


//             This invokes the onclick listener of the other class by passing clickedPosition value
            mOnClickedListiner.onListItemClicked(clickedPosition)
        }

        init {
            Log.e("Init",  "Here ")

            itemView.setOnClickListener(this)
            exchangeRate_1 = itemView.findViewById<View>(R.id.btc_result) as TextView
            currencyAbr = itemView.findViewById<View>(R.id.currency_abr) as TextView
            currencyImage = itemView.findViewById<View>(R.id.currency_image) as ImageView
            currencyName = itemView.findViewById<View>(R.id.currency_name) as TextView
            currencySymbol_1 = itemView.findViewById<View>(R.id.currency_symbol) as TextView
            exchangeRate_2 = itemView.findViewById<View>(R.id.eth_result) as TextView
            currencySymbol_2 = itemView.findViewById<View>(R.id.currency_symbol_1) as TextView


//            if (mListItemClickListiner is ListItemClickListiner) {
                mOnClickedListiner =mListItemClickListiner
//            } else {
//                throw ClassCastException(mContext.toString()
//                        + mContext.resources.getString(R.string.exception_message))
//            }


        }
    }

    //adds data for the adapter to utilize
    fun add(results: Results) {
        mResults.add(results)
        Log.e("results",Gson().toJson(results))
        Log.e("resultAdapter size",Gson().toJson(mResults.size))
//        notifyDataSetChanged()
    }

}