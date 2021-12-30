package project.stn991516745.final_project_modaniel.ui.weights

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeightsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is weights Fragment"
    }
    val text: LiveData<String> = _text
}