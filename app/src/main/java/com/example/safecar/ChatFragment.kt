package com.example.safecar

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.safecar.model.Message
import com.example.safecar.userID
//import com.example.safecar.userID
import com.example.safecar.model.ChatAdapter
import com.example.safecar.databinding.FragmentChatBinding
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "ChatFragment"

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        setup()
        loadMessages()
    }

    private fun setup() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.stackFromEnd = true

        binding.rvMessages.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = ChatAdapter()
        }

        binding.btnSend.setOnClickListener {
            val message = binding.etContent.text.toString()
            sendMessage(message)

            binding.etContent.text.clear()
        }
    }

    private fun loadMessages() {
        println(FirebaseAuth.getInstance().currentUser?.uid.toString())
        val docs = Firebase.firestore.collection("message").whereEqualTo("dono", FirebaseAuth.getInstance().currentUser?.uid.toString()).orderBy("timestamp")
        docs.addSnapshotListener { snapshot, e ->
            if (e != null || snapshot == null) {
                Log.w(TAG, "Unable to retrieve data. Error=$e, snapshot=$snapshot")
                return@addSnapshotListener
            }

            Log.d(TAG, "New data retrieved:${snapshot.documents.size}")

            val messages = mutableListOf<Message>()
            for (document in snapshot.documents) {
                val message = Message(
                    document.id,
                    "${document.data?.get("user")}",
                    "${document.data?.get("content")}",
                    "${document.data?.get("timestamp")}",
                    document.data?.get("user") == userID()
                )
                println(document.data?.get("user"))
                println("${Build.MODEL}-${Build.MANUFACTURER}")
                println(FirebaseAuth.getInstance().currentUser?.uid.toString())

                messages += message
            }

            for (message in messages) {
                println(message.toString())
            }

            val adapter = binding.rvMessages.adapter as ChatAdapter

            adapter.submitList(messages)
            adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {

                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    super.onItemRangeInserted(positionStart, itemCount)
                    binding.rvMessages.scrollToPosition(positionStart)
                }
            })

        }
    }

    private fun sendMessage(content: String) {
        val message = hashMapOf(
            "user" to userID(),
            "content" to content,
            "timestamp" to "${System.currentTimeMillis()}",
            "dono" to FirebaseAuth.getInstance().currentUser?.uid.toString()
        )

        val db = Firebase.firestore
        val id: String = db.collection("message").document().id
        db.collection("message").document(id)
            .set(message)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document. Error:$e") }

    }

}