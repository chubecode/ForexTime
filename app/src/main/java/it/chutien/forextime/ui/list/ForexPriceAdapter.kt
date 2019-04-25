package it.chutien.forextime.ui.list

import android.annotation.SuppressLint
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import it.chutien.forextime.R
import it.chutien.forextime.data.model.forex.ForexItem
import it.chutien.forextime.databinding.ItemForexBinding
import it.chutien.forextime.ui.base.BaseRecyclerAdapter

/**
 * Created by ChuTien on ${1/25/2017}.
 */
class ForexPriceAdapter(
    val itemClickListener: ((ForexItem) -> Unit)? = null
) : BaseRecyclerAdapter<ForexItem, ItemForexBinding>(object : DiffUtil.ItemCallback<ForexItem>() {
    override fun areItemsTheSame(oldItem: ForexItem, newItem: ForexItem): Boolean {
        return oldItem.name == newItem.name
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ForexItem, newItem: ForexItem): Boolean {
        return oldItem == newItem
    }
}) {
    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_forex
    }

    override fun bindFirstTime(binding: ItemForexBinding) {
        binding.apply {
            root.setOnClickListener {
                item?.apply {
                    itemClickListener?.invoke(this)
                }
            }
        }
    }

    override fun bindView(binding: ItemForexBinding, item: ForexItem) {
        binding.apply { this.item = item }
    }
}