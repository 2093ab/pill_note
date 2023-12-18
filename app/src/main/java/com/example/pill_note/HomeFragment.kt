package com.example.pill_note

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pill_note.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var auth: FirebaseAuth
    var datas: MutableList<String>? = null
    lateinit var adapter: MainActivity.MyFragmentPagerAdapter
    lateinit var requestLauncher: ActivityResultLauncher<Intent>
    private val db = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


        requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            it.data!!.getStringExtra("result")?.let {
                datas?.add(it)
                adapter.notifyDataSetChanged()
            }
        }

        datas = savedInstanceState?.let {
            it.getStringArrayList("datas")?.toMutableList()
        } ?: let {
            mutableListOf<String>()
        }

        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        db.getReference("pill").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.value?.let { it: Any ->
                    Log.d("pill_note", "pill info: $it")
                    val medi_name = mutableListOf<String>()
                    for (it2 in it as HashMap<String, Any>) {
                        Log.d("pill_note", "pill name: ${it2.key}")
                        medi_name.add(it2.key)
                    }

                    val adapter = HomeAdapter(medi_name)
                    binding.homeRecycler.adapter = adapter
                }
                /*
                val pillInfo = snapshot.getValue<PillInfo>()
                if (pillInfo == null) {
                    Log.d("pill_note", "pill info is null")
                    return
                }
                Log.d("pill_note", "pill info: ${pillInfo.pill}")
                for (pill in pillInfo.pill) {
                    Log.d("pill_note", "pill name: ${pill.name}")
                    medi_name.add(pill.name)
                }*/
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("pill_note", "pill info cancelled")
            }
        })
                /*
        db.getReference("pill").get().addOnSuccessListener {
            val pillInfo = it.getValue(PillInfo::class.java)
            if (pillInfo == null) {
                Log.d("pill_note", "pill info is null")
                return@addOnSuccessListener
            }
            Log.d("pill_note", "pill info: ${pillInfo.pill}")
            for (pill in pillInfo.pill) {
                Log.d("pill_note", "pill name: ${pill.name}")
                medi_name.add(pill.name)
            }
        }.addOnFailureListener {
            Log.d("pill_note", "failed to get pill db")
        }*/

        if (auth.currentUser != null) {
            Log.d("pill_note", "current user: ${auth.currentUser!!.displayName}")
            var curDate = LocalDate.now()

            var strNow = curDate.format(
                DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
            )

            binding.homeMessage.text =
                "${auth.currentUser!!.displayName}님, \n${strNow} \n복약을 진행해주세요!"
        }

        val layoutManager = LinearLayoutManager(activity)
        binding.homeRecycler.layoutManager = layoutManager

        val adapter = HomeAdapter(mutableListOf<String>())
        binding.homeRecycler.adapter = adapter

        //binding.addBtn.setOnClickListener(this)
        //binding.homeMorningBtn.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        when (v.id) {
            /*R.id.home_morning_btn -> {
                Log.d("pill_note", "morning button clicked")
            }*/

            /*R.id.add_btn -> {
                Log.d("pill_note", "add button clicked")

                val intent = Intent(activity, PillAddFragment::class.java)
                requestLauncher.launch(intent)
            }*/
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}