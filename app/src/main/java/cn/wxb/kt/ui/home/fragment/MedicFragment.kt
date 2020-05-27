package cn.wxb.kt.ui.home.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import cn.wxb.kt.R
import cn.wxb.kt.ui.home.activity.AppRemindActivity
import cn.wxb.kt.ui.home.activity.AppRemindViewModel
import cn.wxb.kt.ui.home.viewmodel.MainViewModel
import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MedicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MedicFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_medic, container, false)
        init(view)
        // Inflate the layout for this fragment
        return view
    }

    fun init(view:View){
//       val vm = ViewModelProviders.of(activity!!).get(AppRemindViewModel::class.java)
//        val vm = ViewModelProvider(activity!!)[AppRemindViewModel::class.java]
        val vm = ViewModelProvider(activity!!)[MainViewModel::class.java]
        LogUtils.e(vm.name.value)
        val btn = view.findViewById<Button>(R.id.btnModifyVm)
        btn.setOnClickListener {
            vm.name.value = "kotlin"
        }
        val tvContent = view.findViewById<TextView>(R.id.tvContent)
        vm.name.observe(activity!!, Observer {
            tvContent.text = it
        })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MedicFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            MedicFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
