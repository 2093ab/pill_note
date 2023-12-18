package com.example.pill_note

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pill_note.databinding.FollowerRecyclerviewBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.MutableData
import com.google.firebase.database.Transaction
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FollowViewHolder(val binding: FollowerRecyclerviewBinding) :
    RecyclerView.ViewHolder(binding.root)

class FollowAdapter(
    private val datas: MutableList<User>,
    private val isFollow: MutableList<Boolean>,
    private val currentUserId: String
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val db = Firebase.database
    override fun getItemCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder = FollowViewHolder(
        FollowerRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as FollowViewHolder).binding
        binding.followerImage.setImageResource(R.drawable.profile_icon)
        binding.followerName.text = datas[position].nickname
        if (isFollow[position]) binding.followerInfoButton.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
        else binding.followerInfoButton.setImageResource(R.drawable.arrow)
        binding.followerInfoButton.setOnClickListener {
            Log.d("pill_note", "follower info button clicked")
            updateFollowInfo(datas[position].id!!, currentUserId)
        }
    }

    private fun updateFollowInfo(followingId: String, currentUser: String) {
        Log.d("pill_note", "update follow info $followingId $currentUser")
        db.getReference("follow").child(followingId).runTransaction(object : Transaction.Handler {
            override fun doTransaction(currentData: MutableData): Transaction.Result {
                Log.d("pill_note", currentData.value.toString())
                val followInfo = currentData.getValue(FollowInfo::class.java)
                if (followInfo == null) {
                    Log.d("pill_note", "follow info is null")
                    return Transaction.abort()
                }
                if (followInfo.followers.containsKey(currentUser))
                    followInfo.followers.remove(currentUser)
                else
                    followInfo.followers[currentUser] = true

                currentData.value = followInfo
                return Transaction.success(currentData)
            }

            override fun onComplete(
                error: DatabaseError?,
                committed: Boolean,
                currentData: DataSnapshot?
            ) {
                Log.d("pill_note", "update follow info transaction completed")
            }
        })
        db.getReference("follow").child(currentUser).runTransaction(object : Transaction.Handler {
            override fun doTransaction(currentData: MutableData): Transaction.Result {
                Log.d("pill_note", currentData.value.toString())
                val followInfo = currentData.getValue(FollowInfo::class.java)
                if (followInfo == null) {
                    Log.d("pill_note", "follow info is null")
                    return Transaction.abort()
                }
                if (followInfo.followings.containsKey(followingId))
                    followInfo.followings.remove(followingId)
                else
                    followInfo.followings[followingId] = true

                currentData.value = followInfo
                return Transaction.success(currentData)
            }

            override fun onComplete(
                error: DatabaseError?,
                committed: Boolean,
                currentData: DataSnapshot?
            ) {
                Log.d("pill_note", "update follow info transaction completed")
            }
        })
        /*
        db.getReference("follow").child(currentUser).updateChildren(
            mapOf(
                "followings" to mapOf(followingId to true)
            )
        )*/
        /*
        db.getReference("follow").child(currentUser).get().addOnSuccessListener {
            Log.d("pill_note", "followers: ${it.value}")
            val followInfo = it.getValue(FollowInfo::class.java)
            if (followInfo != null) {
                if (followInfo.followings.containsKey(followingId))
                    followInfo.followings.remove(followingId)
                else
                    followInfo.followings[followingId] = true
                db.getReference("follow").child(currentUser).setValue(followInfo)
            }

        }.addOnFailureListener {
            Log.d("pill_note", "failed to get followers")
        }*/
    }
}