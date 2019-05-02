package it.chutien.forextime.ui.list

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.chutien.forextime.BR
import it.chutien.forextime.R
import it.chutien.forextime.data.model.forex.ForexItem
import it.chutien.forextime.ui.base.BaseRefreshFragment
import kotlinx.android.synthetic.main.forex_price_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ForexPriceFragment : BaseRefreshFragment<ViewDataBinding,ForexPriceViewModel,ForexItem>() {

    companion object {
        const val TAG = "ForexPriceFragment"
        private const val DATA = "DATA_FOREX_PRICE"


        fun newInstance(data: String) = ForexPriceFragment().apply {
            arguments = Bundle().apply {
                putString(DATA, data)
            }
        }
    }

    override val bindingVariable: Int
        get() = BR.viewModel
    override val viewModel : ForexPriceViewModel  by viewModel()
    override val layoutId: Int
        get() = R.layout.forex_price_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG,"Fragment Start: onActivityCreated")
        val adapter = ForexPriceAdapter {
            Toast.makeText(context, "${it.name} has price is ${it.price} at ${it.time}", Toast.LENGTH_SHORT).show()
        }

        viewBinding.apply {
            root.setBackgroundColor(Color.WHITE)
            recycler_view.apply {
                layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
                this.adapter = adapter
            }
        }
        viewModel.apply {
            listItem.observe(this@ForexPriceFragment, Observer {
                adapter.submitList(it)
            })
            loadData(1)
        }
    }


    override fun onDetach() {
        super.onDetach()
        Log.d(TAG,"Fragment End: onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Fragment End: onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG,"Fragment End: onDestroyView")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"Fragment End: onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"Fragment End: onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"Fragment Start: onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"Fragment Start: onStart")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG,"Fragment Start: onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"Fragment Start: onCreate")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d(TAG,"Fragment Start: onAttach")
    }



}
