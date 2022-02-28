package com.example.retrofitproject.Adapters

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitproject.AlbumActivity
import com.example.retrofitproject.Models.Album
import com.example.retrofitproject.Models.User
import com.example.retrofitproject.databinding.AddressDialogBinding
import com.example.retrofitproject.databinding.CompanyDialogBinding
import com.example.retrofitproject.databinding.UserItemBinding

class UserAdapter(
    private val userList: ArrayList<User>,
    public val context: Context
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.txtName.text = userList[position].name.toString()
        holder.binding.txtUsername.text = userList[position].username.toString()
        holder.binding.txtEmail.text = userList[position].email.toString()
        holder.binding.txtPhone.text = userList[position].phone.toString()
        holder.binding.txtWebsite.text = userList[position].website.toString()

        holder.binding.btnAddress.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val dialog = p0?.context?.let { Dialog(it) }
                dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
                val inflater = p0?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val dialogBinding :AddressDialogBinding = AddressDialogBinding .inflate(inflater)
                dialog?.setContentView(dialogBinding.root)
                dialogBinding.txtStreet.text = userList[position].address?.street
                dialogBinding.txtSuite.text = userList[position].address?.suite
                dialogBinding.txtCity.text = userList[position].address?.city
                dialogBinding.txtZipcode.text = userList[position].address?.zipcode
                dialog?.show()
            }
        })

        holder.binding.btnCompany.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val dialog = p0?.context?.let { Dialog(it) }
                dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
                val inflater = p0?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val dialogBinding :CompanyDialogBinding = CompanyDialogBinding .inflate(inflater)
                dialog?.setContentView(dialogBinding.root)
                dialogBinding.txtCompanyName.text = userList[position].company?.name
                dialogBinding.txtCatchPhrase.text = userList[position].company?.catchPhrase
                dialogBinding.txtBs.text = userList[position].company?.bs
                dialog?.show()
            }
        })

        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                var intent = Intent(context, AlbumActivity::class.java)
                intent.putExtra("userID", userList[position].id)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        })
    }

    override fun getItemCount() = userList.size

    inner class UserViewHolder(val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}