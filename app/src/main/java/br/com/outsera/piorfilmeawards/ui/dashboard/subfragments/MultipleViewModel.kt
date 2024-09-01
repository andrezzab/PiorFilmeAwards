package br.com.outsera.piorfilmeawards.ui.dashboard.subfragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MultipleViewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "bla bla bla"
    }

    val text: LiveData<String> = _text

}