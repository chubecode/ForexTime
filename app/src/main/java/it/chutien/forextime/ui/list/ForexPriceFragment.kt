package it.chutien.forextime.ui.list

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import it.chutien.forextime.R
import it.chutien.forextime.ui.base.BaseFragment
import it.chutien.forextime.ui.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ForexPriceFragment : BaseFragment<ViewDataBinding,BaseViewModel>() {
    override val bindingVariable: Int = 0
     override val viewModel by viewModel<ForexPriceViewModel>()
    override val layoutId: Int
        get() = R.layout.forex_price_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {

        }
    }
}
