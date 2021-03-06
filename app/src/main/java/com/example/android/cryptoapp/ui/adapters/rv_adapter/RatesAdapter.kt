package com.example.android.cryptoapp.ui.adapters.rv_adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.domain.model.CryptoCurrencyRates
import com.example.android.cryptoapp.ui.adapters.rv_adapter.RatesAdapter.RatesViewHolder
import com.example.android.cryptoapp.databinding.RatesRowItemsBinding
import com.example.android.cryptoapp.util.Listeners.*
import com.google.gson.Gson
import timber.log.Timber


//class RatesAdapter(private val mContext: Context, private val mResults: MutableList<Results>, private val mOnClickedListiner: ListItemClickListiner) : RecyclerView.Adapter<Rates_ViewHoler>() {
class RatesAdapter(private val mResults: MutableList<CryptoCurrencyRates>, private  val listItemClickListener :ListItemClickListener, private val longClickListener : LongItemClickedListener,val dataBindingComponent: DataBindingComponent) : RecyclerView.Adapter<RatesViewHolder>() {
    private var clickedPosition = 0
    private lateinit var onClickedListiner: ListItemClickListener
    private lateinit var onLonglickListener: LongItemClickedListener
    init {
        onClickedListiner =listItemClickListener
        onLonglickListener =longClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {

//        val layoutResourceBinding = RatesRowItemsBinding.
        val getCurrentContext = parent.context
        val layoutInflater = LayoutInflater.from(getCurrentContext)
        val newViewHolder:RatesRowItemsBinding  = DataBindingUtil.inflate(layoutInflater,R.layout.rates_row_items, parent, false,dataBindingComponent)
        return RatesViewHolder(newViewHolder)

    }

    @SuppressLint("TimberArgCount")
    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
//        Log.e("position onBindViewHolder ",  "${position}")

        holder.bind(position)
//
//        Timber.e("Results onBind onBindViewHolder " ,mResults)
//
//        Timber.e("Results[listIndex] onBindViewHolder " ,mResults[clickedPosition])
//        Timber.e("Results Gson().toJson(mResults) getItemCount " ,Gson().toJson(mResults))


    }

    override fun getItemViewType(position: Int): Int {
//        Log.e("getItemViewType position",  "${position}")

        return super.getItemViewType(position)
    }

    @SuppressLint("TimberArgCount")
    override fun getItemCount(): Int {

//        Log.e(" mResults.size",  "${mResults.size}")
//
        Timber.e("Results onBind getItemCount " ,Gson().toJson(mResults))

        return mResults.size
    }



//    inner class RatesViewHolder(val binding: RatesRowItemsBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener,View.OnLongClickListener {
    inner class RatesViewHolder(val binding: RatesRowItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        init {

          /*  binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)*/
        }

        @SuppressLint("TimberArgCount")
        fun bind(listIndex: Int) {

            Log.e("position bind ",  "${position}")

            binding.cryptoData=mResults[listIndex]
            Timber.e("Results onBind" ,mResults)
            Timber.e("Results[listIndex] onBind" ,mResults[clickedPosition])
            binding.clickListener=onClickedListiner

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

     /*   @SuppressLint("TimberArgCount")
        override fun onClick(view: View) {

//         Set the function [getAdapterPosition] to get the position which is the item that was clicked
            clickedPosition = adapterPosition

           Timber.e("mResults[clickedPosition] in onclick" ,mResults[clickedPosition])
//        This invokes the onclick listener of the other class by passing clickedPosition value
//            onClickedListiner.onListItemClicked(mResults[clickedPosition])
            binding.cryptoData=mResults[clickedPosition]
        }

        override fun onLongClick(v: View?): Boolean {
            Log.e(" mResults.size",  " >>>>>>>>")


//         Set the function [getAdapterPosition] to get the position which is the item that was clicked
            clickedPosition = adapterPosition

            //  This invokes the onclick listener of the other class by passing clickedPosition value
            onLonglickListener.onLongItemClickedListener(mResults[clickedPosition])

            //Instructs the listener to listen no more
           return true
        }
*/

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