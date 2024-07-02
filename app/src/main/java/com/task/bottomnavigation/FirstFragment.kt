package com.task.bottomnavigation

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.task.bottomnavigation.databinding.CustomdialogBinding
import com.task.bottomnavigation.databinding.FragmentFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding:FragmentFirstBinding?=null
    var mainActivity: MainActivity?=null
    lateinit var listAdapter:ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity=activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentFirstBinding.inflate(layoutInflater)
        listAdapter=ListAdapter(mainActivity!!.infoList)
        return binding?.root
        //return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listView?.adapter=listAdapter
        binding?.btnAdd?.setOnClickListener {
            var dialogBinding= CustomdialogBinding.inflate(layoutInflater)
            var dialog= Dialog(requireContext()).apply{
                setContentView(dialogBinding.root)
                show()
            }
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialogBinding.btnSubmit.setOnClickListener {
                if (dialogBinding.etName.text?.toString()?.trim().isNullOrEmpty()){
                    dialogBinding.etName.error="Enter Name"
                }else if (dialogBinding.etQty.text?.toString()?.trim().isNullOrEmpty()){
                    dialogBinding.etQty.error="Enter Quantity"
                }else{
                    mainActivity?.infoList?.add(Info(dialogBinding.etName.text.toString(),dialogBinding.etQty.text.toString().toInt()))
                    listAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}