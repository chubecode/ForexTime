package it.chutien.forextime.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.databinding.DataBindingUtil



/**
 * Created by ChuTien on ${1/25/2017}.
 */
abstract class BaseActivity<ActivityBinding : ViewDataBinding, ViewModel : BaseViewModel> : AppCompatActivity(){

    abstract val bindingVariable: Int

    lateinit var  binding: ActivityBinding

    abstract val viewModel: ViewModel

    protected fun bindView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.apply {
            setVariable(bindingVariable,viewModel)
        }
    }

    override fun onDestroy() {
        viewModel.onActivityDestroyed()
        super.onDestroy()
    }

}