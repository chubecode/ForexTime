package it.chutien.forextime.ui.list

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.chutien.forextime.BR
import it.chutien.forextime.R
import it.chutien.forextime.data.model.forex.ForexItem
import it.chutien.forextime.ui.base.BaseFragment
import it.chutien.forextime.ui.base.BaseRefreshFragment
import it.chutien.forextime.ui.base.BaseViewModel
import it.chutien.forextime.utils.SpacingItemDecoration
import kotlinx.android.synthetic.main.forex_price_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ForexPriceFragment : BaseRefreshFragment<ViewDataBinding,ForexPriceViewModel,ForexItem>() {

    companion object {
        const val TAG = "ForexPriceFragment"
        const val DATA = "DATA_FOREX_PRICE"


        fun newInstance(data: String) = ForexPriceFragment().apply {
            arguments = Bundle().apply {
                putString(DATA, data)
            }
        }
    }

    override val bindingVariable: Int
        get() = BR.viewModel
    override val viewModel by viewModel<ForexPriceViewModel>()
    override val layoutId: Int
        get() = R.layout.forex_price_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = ForexPriceAdapter({
            Toast.makeText(context, "${it.name} has price is ${it.price} at ${it.time}", Toast.LENGTH_SHORT).show()
        })

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
}
