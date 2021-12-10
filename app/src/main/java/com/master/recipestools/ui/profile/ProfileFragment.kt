package com.master.recipestools.ui.createingredient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.master.recipestools.databinding.FragmentProfileBinding
import com.master.recipestools.session.SessionManager


class ProfileFragment : Fragment() {

    private lateinit var notificationsViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        notificationsViewModel.sessionManager = SessionManager(requireContext())
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textEmail
        notificationsViewModel.textEmail.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val textView2: TextView = binding.textUsername
        notificationsViewModel.textName.observe(viewLifecycleOwner, Observer {
            textView2.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}