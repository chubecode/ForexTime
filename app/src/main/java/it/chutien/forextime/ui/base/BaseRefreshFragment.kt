package it.chutien.forextime.ui.base

import androidx.databinding.ViewDataBinding
import it.chutien.forextime.BR
import it.chutien.forextime.R

/**
 * Created by ChuTien on ${1/25/2017}.
 */
abstract class BaseRefreshFragment<View: ViewDataBinding, ViewModel: BaseRefreshViewModel<Item>,Item> : BaseFragment<View,ViewModel>(){
    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_refresh

    override fun handleShowLoading(isLoading: Boolean) {
        // use progress bar
    }
}