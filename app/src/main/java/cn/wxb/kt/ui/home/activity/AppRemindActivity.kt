package cn.wxb.kt.ui.home.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import cn.wxb.kt.R
import cn.wxb.kt.ui.home.fragment.MedicFragment
import cn.wxb.kt.ui.home.fragment.MineFragment
import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.activity_app_remind.*

class AppRemindActivity : AppCompatActivity() {

    companion object {
        fun actionStart(context: Context){
            Intent().let {
                it.setClass(context, AppRemindActivity::class.java)
                context.startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_remind)
        init()
    }

    private fun init(){
        addFragment();
//        val vm = ViewModelProviders.of(this).get(AppRemindViewModel::class.java)
        val vm = ViewModelProvider(this)[AppRemindViewModel::class.java]

        LogUtils.e(this)
        btnModify.setOnClickListener {
            LogUtils.e(vm.name.value)
            vm.name.value = "python"
        }
        vm.name.observe(this, Observer {
            tvContent.text = vm.name.value
            LogUtils.e(">>>>>>>>>>> ${vm.name.value}")
        })

    }


    private fun addFragment(){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.flContainer, MedicFragment.newInstance())
        transaction.commit()
    }
}
