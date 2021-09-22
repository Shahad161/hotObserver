package com.example.hotobservertask6.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.hotobservertask6.InterfaceData
import com.example.hotobservertask6.databinding.FragmentTopBinding
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class TopFragment : Fragment() {

    lateinit var binding: FragmentTopBinding
    lateinit var data: InterfaceData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = activity as InterfaceData
        hotObservable()
    }


    private fun hotObservable() {
        val subject = PublishSubject.create<String> { emitter ->
            binding.editText.doOnTextChanged { text, _, _, _ ->
                emitter.onNext(text.toString())
            }
        }.debounce(1500, TimeUnit.MILLISECONDS)

        subject.subscribe(
            { t -> data.data(t)},
            { e -> Log.i("TAG", "ON error: ${e.message}") }
        )
    }
}

