package com.task.bottomnavigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter (var list:ArrayList<Info>): BaseAdapter(){
    override fun getCount(): Int {
        return list.size
    }
    override fun getItem(position: Int): Any {
        return list[position]
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_adapter,parent,false)
        val name=view.findViewById<TextView>(R.id.tvName)
        name.setText(list[position].name.toString())
        val number=view.findViewById<TextView>(R.id.tvNumber)
        number.setText(list[position].number.toString())
        return view
    }
}