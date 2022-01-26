package com.peter.digicarUser.ui.view.fragment.home

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.peter.digicarUser.MainViewModel
import com.peter.digicarUser.R
import com.peter.digicarUser.ui.intent.MainIntent
import com.peter.digicarUser.ui.viewState.MainViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
open class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog  = Dialog(requireActivity())
        addConsultation.setOnClickListener {
            showDialog()
        }
        observeViewModel()
    }

    private fun showDialog() {

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.sendBtn.setOnClickListener {

            if (dialog.edText.text.isNotEmpty())
                lifecycleScope.launch {
                    mainViewModel.mainIntent.send(
                        MainIntent.AddConsultation(
                            dialog.edText.text.toString()
                        )
                    )
                }
        }
        dialog.show()
    }


    private fun observeViewModel() {

        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainViewState.Idle -> {

                    }
                    is MainViewState.Loading -> {
                        //progressDialog.show()
                    }

                    is MainViewState.AddConsultation -> {
                        // progressDialog.dismiss()
                        dialog.dismiss()
                        Toast.makeText(requireActivity(), "Added Successfully", Toast.LENGTH_LONG)
                            .show()
                    }
                    is MainViewState.Error -> {
                        // progressDialog.dismiss()
                        Toast.makeText(requireActivity(), it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    // region variable
    private val mainViewModel: MainViewModel by viewModels()
    lateinit var dialog:Dialog
    //endregion
}