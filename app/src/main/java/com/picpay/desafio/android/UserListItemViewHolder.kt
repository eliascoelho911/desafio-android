package com.picpay.desafio.android

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UserListItemViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    val name by lazy { itemView.findViewById<TextView>(R.id.name) }
    val username by lazy { itemView.findViewById<TextView>(R.id.username) }
    val progressBar by lazy { itemView.findViewById<ProgressBar>(R.id.progressBar) }
    val picture by lazy { itemView.findViewById<CircleImageView>(R.id.picture) }

    fun bind(user: com.picpay.desafio.android.contacts.domain.repositories.entities.User) {
        name.text = user.name
        username.text = user.username
        progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(picture, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progressBar.visibility = View.GONE
                }
            })
    }
}