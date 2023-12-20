package com.example.pill_note

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private var hour = "12"
    private var minute = "00"
    private var timezone = "오전"

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
        // set onClickListener for all buttons
        binding.hour1.setOnClickListener(this)
        binding.hour2.setOnClickListener(this)
        binding.hour3.setOnClickListener(this)
        binding.hour4.setOnClickListener(this)
        binding.hour5.setOnClickListener(this)
        binding.hour6.setOnClickListener(this)
        binding.hour7.setOnClickListener(this)
        binding.hour8.setOnClickListener(this)
        binding.hour9.setOnClickListener(this)
        binding.hour10.setOnClickListener(this)
        binding.hour11.setOnClickListener(this)
        binding.hour12.setOnClickListener(this)
        binding.minute0.setOnClickListener(this)
        binding.minute5.setOnClickListener(this)
        binding.minute10.setOnClickListener(this)
        binding.minute15.setOnClickListener(this)
        binding.minute20.setOnClickListener(this)
        binding.minute25.setOnClickListener(this)
        binding.minute30.setOnClickListener(this)
        binding.minute35.setOnClickListener(this)
        binding.minute40.setOnClickListener(this)
        binding.minute45.setOnClickListener(this)
        binding.minute50.setOnClickListener(this)
        binding.minute55.setOnClickListener(this)
        binding.buttonAm.setOnClickListener(this)
        binding.buttonPm.setOnClickListener(this)
        binding.mediDose.setText("0")
        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.hour1 -> {
                Log.d("pill_note", "hour1 button clicked")
                hour = binding.hour1.text.toString()
            }

            R.id.hour2 -> {
                Log.d("pill_note", "hour2 button clicked")
                hour = binding.hour2.text.toString()
            }

            R.id.hour3 -> {
                Log.d("pill_note", "hour3 button clicked")
                hour = binding.hour3.text.toString()
            }

            R.id.hour4 -> {
                Log.d("pill_note", "hour4 button clicked")
                hour = binding.hour4.text.toString()
            }

            R.id.hour5 -> {
                Log.d("pill_note", "hour5 button clicked")
                hour = binding.hour5.text.toString()
            }

            R.id.hour6 -> {
                Log.d("pill_note", "hour6 button clicked")
                hour = binding.hour6.text.toString()
            }

            R.id.hour7 -> {
                Log.d("pill_note", "hour7 button clicked")
                hour = binding.hour7.text.toString()
            }

            R.id.hour8 -> {
                Log.d("pill_note", "hour8 button clicked")
                hour = binding.hour8.text.toString()
            }

            R.id.hour9 -> {
                Log.d("pill_note", "hour9 button clicked")
                hour = binding.hour9.text.toString()
            }

            R.id.hour10 -> {
                Log.d("pill_note", "hour10 button clicked")
                hour = binding.hour10.text.toString()
            }

            R.id.hour11 -> {
                Log.d("pill_note", "hour11 button clicked")
                hour = binding.hour11.text.toString()
            }

            R.id.hour12 -> {
                Log.d("pill_note", "hour12 button clicked")
                hour = binding.hour12.text.toString()
            }

            R.id.minute0 -> {
                Log.d("pill_note", "minute0 button clicked")
                minute = binding.minute0.text.toString()
            }

            R.id.minute5 -> {
                Log.d("pill_note", "minute5 button clicked")
                minute = binding.minute5.text.toString()
            }

            R.id.minute10 -> {
                Log.d("pill_note", "minute10 button clicked")
                minute = binding.minute10.text.toString()
            }

            R.id.minute15 -> {
                Log.d("pill_note", "minute15 button clicked")
                minute = binding.minute15.text.toString()
            }

            R.id.minute20 -> {
                Log.d("pill_note", "minute20 button clicked")
                minute = binding.minute20.text.toString()
            }

            R.id.minute25 -> {
                Log.d("pill_note", "minute25 button clicked")
                minute = binding.minute25.text.toString()
            }

            R.id.minute30 -> {
                Log.d("pill_note", "minute30 button clicked")
                minute = binding.minute30.text.toString()
            }

            R.id.minute35 -> {
                Log.d("pill_note", "minute35 button clicked")
                minute = binding.minute35.text.toString()
            }

            R.id.minute40 -> {
                Log.d("pill_note", "minute40 button clicked")
                minute = binding.minute40.text.toString()
            }

            R.id.minute45 -> {
                Log.d("pill_note", "minute45 button clicked")
                minute = binding.minute45.text.toString()
            }

            R.id.minute50 -> {
                Log.d("pill_note", "minute50 button clicked")
                minute = binding.minute50.text.toString()
            }

            R.id.minute55 -> {
                Log.d("pill_note", "minute55 button clicked")
                minute = binding.minute55.text.toString()
            }

            R.id.button_am -> {
                timezone = "오전"
            }

            R.id.button_pm -> {
                timezone = "오후"
            }

            R.id.medi_add -> {
                Log.d("pill_note", "medi add button clicked")
                val mediName = binding.mediName.text.toString()
                val mediDose = binding.mediDose.text.toString()

                db.getReference("pill").child(auth.currentUser!!.uid).child(mediName)
                    .setValue(Pill(mediName, mediDose, timezone, hour + "시 " + minute + "분"))
                    .addOnSuccessListener {
                        Toast.makeText(activity, "약 정보가 추가되었습니다.", Toast.LENGTH_SHORT).show()
                    }
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