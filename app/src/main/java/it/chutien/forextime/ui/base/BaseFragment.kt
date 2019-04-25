package it.chutien.forextime.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import it.chutien.forextime.R
import it.chutien.forextime.utils.DialogUtils
import it.chutien.forextime.utils.DialogUtils.showMessage

/**
 * Created by ChuTien on ${1/25/2017}.
 */
abstract class BaseFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {

    abstract val bindingVariable: Int

    lateinit var viewBinding: ViewBinding

    abstract val viewModel: ViewModel

    @get:LayoutRes
    abstract val layoutId: Int

    var mAlertDialog: AlertDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            setVariable(bindingVariable, viewModel)
            root.isClickable = true
            lifecycleOwner = this@BaseFragment
            executePendingBindings()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mAlertDialog = DialogUtils.createLoadingDialog(context, false)
        viewModel.apply {
            isLoading.observe(this@BaseFragment, Observer {
                handleShowLoading(it)
            })

            errorMessage.observe(this@BaseFragment, Observer {
                handleShowErrorMessage(it)
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.isLoading.removeObservers(this)
        viewModel.errorMessage.removeObservers(this)
        viewModel.onActivityDestroyed()
    }


    private fun handleShowErrorMessage(message: String) {
        showMessage(context, message = message, textPositive = getString(R.string.ok))
    }

    open fun handleShowLoading(isLoading: Boolean) {
        if (isLoading) showLoading() else hideLoading()
    }

    fun showLoading() {
        hideLoading()
        mAlertDialog?.show()
    }

    fun hideLoading() {
        if (mAlertDialog != null && mAlertDialog!!.isShowing) {
            mAlertDialog?.cancel()
        }
    }

    /**
     * fragment transaction
     */

    fun findFragment(TAG: String): Fragment? {
        return activity?.supportFragmentManager?.findFragmentByTag(TAG)
    }

    fun findChildFragment(parentFragment: Fragment = this, TAG: String): Fragment? {
        return parentFragment.childFragmentManager.findFragmentByTag(TAG)
    }

    fun replaceFragment(
        fragment: Fragment, TAG: String?, addToBackStack: Boolean = false,
        transit: Int = -1
    ) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, fragment, TAG)?.apply {
                commitTransaction(this, addToBackStack, transit)
            }?.commit()
    }

    fun replaceChildFragment(
        parentFragment: Fragment = this, containerViewId: Int,
        fragment: Fragment, TAG: String?, addToBackStack: Boolean = false, transit: Int = -1
    ) {
        val transaction = parentFragment.childFragmentManager.beginTransaction().replace(
            containerViewId, fragment, TAG
        )
        commitTransaction(transaction, addToBackStack, transit)
    }

    fun addChildFragment(
        parentFragment: Fragment = this, containerViewId: Int,
        targetFragment: Fragment, TAG: String?, addToBackStack: Boolean = false,
        transit: Int = -1
    ) {
        val transaction = parentFragment.childFragmentManager.beginTransaction().add(
            containerViewId, targetFragment, TAG
        )
        commitTransaction(transaction, addToBackStack, transit)
    }

    @SuppressLint("WrongConstant")
    fun showDialogFragment(
        dialogFragment: DialogFragment, TAG: String?,
        addToBackStack: Boolean = false, transit: Int = -1
    ) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if (addToBackStack) transaction?.addToBackStack(TAG)
        if (transit != -1) transaction?.setTransition(transit)
        dialogFragment.show(transaction!!, TAG)
    }

    @SuppressLint("WrongConstant")
    private fun commitTransaction(
        transaction: FragmentTransaction, addToBackStack: Boolean = false,
        transit: Int = -1
    ) {
        if (addToBackStack) transaction.addToBackStack(null)
        if (transit != -1) transaction.setTransition(transit)
        transaction.commit()
    }

    fun popChildFragment(parentFragment: Fragment = this) {
        parentFragment.childFragmentManager.popBackStack()
    }

    open fun onBack(): Boolean {
        return false
    }
}