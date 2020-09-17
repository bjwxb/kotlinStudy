package cn.wxb.kt.ui.mine.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.wxb.kt.R
import cn.wxb.kt.mvvm.base.BaseActivity
import cn.wxb.kt.network.entity.OrderApiInfo
import cn.wxb.kt.ui.mine.adapter.OrderListAdapter
import cn.wxb.kt.ui.mine.viewmodel.MineOrderViewModel
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_mine_order.*

class MineOrderActivity : BaseActivity<MineOrderViewModel, ViewDataBinding>(), SwipeRefreshLayout.OnRefreshListener {
    private var list = mutableListOf<OrderApiInfo>()
    private var page = 1
    private val pageSize = "10"

    private val adapter by lazy {
        OrderListAdapter(list)
    }

    companion object{
        fun actionStart(context: Context){
            context.startActivity(Intent().apply {
                setClass(context, MineOrderActivity::class.java)
            })
        }
    }

    override fun layoutId() = R.layout.activity_mine_order

    override fun initView(savedInstanceState: Bundle?) {
        swipeRefresh.setOnRefreshListener(this)
        initRv()
    }

    private fun initRv(){
        adapter.loadMoreModule.setOnLoadMoreListener {
            getData()
        }
        //adapter.loadMoreModule.loadMoreView = SimpleLoadMoreView()
        rvOrderList.adapter = adapter
        rvOrderList.layoutManager = LinearLayoutManager(this)


        adapter.setOnItemClickListener { adapter, view, position ->
            run {
                ToastUtils.showShort("***** click $position *****")
            }
        }

        viewModel.mOrderBean.observe(this, Observer {
            it.orderApiInfo?.let {
                LogUtils.e(Gson().toJson(it))
                list.addAll(it)
            }

            swipeRefresh.isRefreshing = false
            if (it.total == list.size){
                adapter.loadMoreModule.loadMoreEnd()
            } else {
                page++
                adapter.loadMoreModule.loadMoreComplete()
            }
        })

        getData()

    }

    //获取数据
    private fun getData(){
        val map = HashMap<String, String>()
        map.put("pageAt", page.toString())
        map.put("pageSize", pageSize)
        viewModel.getOrderBean(map)
    }

    override fun onRefresh() {
        page = 1
        list.clear()
        adapter.notifyDataSetChanged()
        getData()
    }

    override fun initData() {
    }


}
