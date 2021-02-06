package com.example.android.cryptoapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.domain.model.CryptoCurrencyRates
import com.example.android.cryptoapp.adapter.RatesAdapter.RatesViewHolder
import com.example.android.cryptoapp.databinding.RatesRowItemsBinding
import com.example.android.cryptoapp.util.Helpers.formatAndReturnString
import com.google.gson.Gson
import java.text.DecimalFormat


//class RatesAdapter(private val mContext: Context, private val mResults: MutableList<Results>, private val mOnClickedListiner: ListItemClickListiner) : RecyclerView.Adapter<Rates_ViewHoler>() {
class RatesAdapter(private val mResults: MutableList<CryptoCurrencyRates>, private  val mListItemClickListiner :ListItemClickListiner) : RecyclerView.Adapter<RatesViewHolder>() {
    private var clickedPosition = 0
    private lateinit var mOnClickedListiner: ListItemClickListiner


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {

//        val layoutResourceBinding = RatesRowItemsBinding.
        val getCurrentContext = parent.context
        val layoutInflater = LayoutInflater.from(getCurrentContext)
        val newViewHolder:RatesRowItemsBinding  = DataBindingUtil.inflate(layoutInflater,R.layout.rates_row_items, parent, false)
        return RatesViewHolder(newViewHolder)

    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
//        Log.e("position",  "${position}")

        holder.bind(position)
    }

    override fun getItemViewType(position: Int): Int {
//        Log.e("getItemViewType position",  "${position}")

        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {

        Log.e(" mResults.size",  "${mResults.size}")
        return mResults.size
    }

    interface ListItemClickListiner {
        fun onListItemClicked(result: CryptoCurrencyRates)
    }

    inner class RatesViewHolder(val binding: RatesRowItemsBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(listIndex: Int) {


            binding.cryptoData=mResults[listIndex]

//            binding.btcResult.text=(format.format(mResults[listIndex].firstExRate))
//            binding.currencySymbol.text=(mResults[listIndex].symbol)
//            binding.ethResult.text=(formatAndReturnString((mResults[listIndex].secondExRate)))
//            binding.ethResult.text=((mResults[listIndex].secondExRate).formatAndReturnString())
//            binding.currencySymbol1.text=(mResults[listIndex].symbol)
//            binding.currencyAbr.text=(mResults[listIndex].abbrivation)
//            binding.currencyImage.setImageResource(mResults[listIndex].image)
//            binding.currencyName.text=(mResults[listIndex].name)
           binding.executePendingBindings()
        }

        override fun onClick(view: View) {

//         Set the body of the function to get the position which is the item that was clicked
            clickedPosition = adapterPosition


//        This invokes the onclick listener of the other class by passing clickedPosition value
            mOnClickedListiner.onListItemClicked(mResults[clickedPosition])
        }

        init {

            var format: DecimalFormat = DecimalFormat()
            format.isGroupingUsed = true
            format.maximumIntegerDigits = 10
            format.maximumFractionDigits = 3

            itemView.setOnClickListener(this)

             mOnClickedListiner =mListItemClickListiner
        }
    }

    //adds data for the adapter to utilize
    fun add(results: List<CryptoCurrencyRates>) {
        mResults.clear()
        mResults.addAll(results)
        Log.e("results",Gson().toJson(results))
        notifyDataSetChanged()
//      notifyItemChanged(mResults.size)

    }

}