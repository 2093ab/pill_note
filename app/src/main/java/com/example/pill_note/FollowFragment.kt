package com.example.pill_note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.example.pill_note.databinding.FragmentFollowBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FollowFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FollowFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val db = Firebase.database
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        // Initialize Firebase Auth
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
        val binding = FragmentFollowBinding.inflate(inflater, container, false)
        db.getReference("users").get().addOnSuccessListener {
            val followings = mutableListOf<User>()

            Log.d("pill_note", "followers: ${it.value}")
            it.value?.let { it1 ->
                for (it2 in it1 as HashMap<String, HashMap<String, Any>>) {
                    Log.d("pill_note", "follower: ${it2.value["nickname"]}")
                    followings.add(
                        User(
                            it2.value["id"].toString(),
                            it2.value["email"].toString(),
                            it2.value["nickname"].toString()
                        )
                    )

                }

            }
            val adapter = FollowAdapter(followings, auth.currentUser!!.uid)
            binding.followerRecyclerView.adapter = adapter

        }.addOnFailureListener {
            Log.d("pill_note", "failed to get followers")
        }

        val layoutManager = LinearLayoutManager(activity)
        binding.followerRecyclerView.layoutManager = layoutManager

        binding.followerRecyclerView.addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val child = rv.findChildViewUnder(e.x, e.y)
                if (child != null) {
                    val position = rv.getChildAdapterPosition(child)
                    Log.d("pill_note", "position: $position")
                    /*val intent = Intent(activity, ProfileActivity::class.java)
                    intent.putExtra("nickname", followers[position])
                    startActivity(intent)*/
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                Log.d("pill_note", "onTouchEvent")
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                Log.d("pill_note", "onRequestDisallowInterceptTouchEvent")
            }


        })
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FollowFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FollowFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}