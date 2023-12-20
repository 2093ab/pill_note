package com.example.pill_note

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pill_note.databinding.FragmentPillAddBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.MutableData
import com.google.firebase.database.Transaction
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PillAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PillAddFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentPillAddBinding
    private val db = Firebase.database
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        auth = Firebase.auth
        if (auth.currentUser == null) {
            val intent = Intent(activity, AuthActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPillAddBinding.inflate(inflater, container, false)
        binding.mediAdd.setOnClickListener(this)
        binding.plusButton.setOnClickListener {
            Log.d("pill_note", "plus button clicked")
            binding.mediDose.setText((binding.mediDose.text.toString().toInt() + 1).toString())
        }
        binding.minusButton.setOnClickListener {
            Log.d("pill_note", "minus button clicked")
            if (binding.mediDose.text.toString().toInt() > 0) {
                binding.mediDose.setText((binding.mediDose.text.toString().toInt() - 1).toString())
            }
        }
        binding.mediDose.setText("0")
        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.medi_add -> {
                Log.d("pill_note", "medi add button clicked")
                val mediName = binding.mediName.text.toString()
                val mediDose = binding.mediDose.text.toString()

                db.getReference("pill").child(auth.currentUser!!.uid).child(mediName)
                    .setValue(Pill(mediName, mediDose))
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PillAddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PillAddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}