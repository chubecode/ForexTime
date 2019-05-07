package it.chutien.forextime.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import it.chutien.forextime.BR
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by ChuTien on ${1/25/2017}.
 */
abstract class BaseRecyclerAdapter<Item,ViewBinding: ViewDataBinding>(
    callback:DiffUtil.ItemCallback<Item>
) : ListAdapter<Item,BaseViewHolder<ViewBinding>>(
    AsyncDifferConfig.Builder<Item>(callback)
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewBinding>{
        return BaseViewHolder(
            DataBindingUtil.inflate<ViewBinding>(
                LayoutInflater.from(parent.context),
                getLayoutRes(viewType),
                parent,false
            ).apply {
                bindFirstTime(this)
            }
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding>, position: Int) {
        val item: Item = getItem(position)
        holder.binding.setVariable(BR.item,item)
        bindView(holder.binding,item)
        holder.binding.executePendingBindings()
    }

    /**
     * get layout res based on view type
     */
    protected abstract fun getLayoutRes(viewType: Int):Int

    /**
     * overide if need
     * bind first time
     * use for set item onClickListener, something only set on item
     */
    protected open fun bindFirstTime(binding: ViewBinding){}

    /**
     * override if need
     * bind view
     */
    protected open fun bindView(binding: ViewBinding,item: Item){}

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}
open class BaseViewHolder<ViewBinding: ViewDataBinding> constructor(
    val binding: ViewBinding
): RecyclerView.ViewHolder(binding.root)