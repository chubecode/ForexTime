package it.chutien.forextime.ui.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import it.chutien.forextime.R
import org.koin.android.viewmodel.ext.android.viewModel

class ForexPriceFragment : Fragment() {

    companion object {
        fun newInstance() = ForexPriceFragment()
    }

    private  val viewModel by viewModel<ForexPriceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forex_price_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            //do something
        }
    }

}
