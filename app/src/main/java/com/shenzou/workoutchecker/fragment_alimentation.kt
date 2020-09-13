package com.shenzou.workoutchecker

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class fragment_alimentation : Fragment() {

    private lateinit var viewOfLayout: View

    companion object{
        fun newInstance(): fragment_alimentation{
            return fragment_alimentation()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewOfLayout =  inflater.inflate(R.layout.fragment_alimentation, container, false)
        return viewOfLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAdd: Button = view.findViewById(R.id.button_add)
        buttonAdd.setOnClickListener(){
            val intent = Intent(this.context, BarcodeScanActivity::class.java)
            startActivity(intent)
        }
    }
}