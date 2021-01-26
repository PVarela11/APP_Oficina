package com.example.safecar

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.safecar.model.Oficina
import com.example.safecar.model.OficinaAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar_user.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings_user -> {
                    activity?.let{
                        val intent = Intent (it, PreferencesActivity::class.java)
                        it.startActivity(intent)
                    }
                    true
                }
                R.id.favorito -> {
                    activity?.let{
                        val intent = Intent (it, FavoriteActivity::class.java)
                        it.startActivity(intent)
                    }
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }

}