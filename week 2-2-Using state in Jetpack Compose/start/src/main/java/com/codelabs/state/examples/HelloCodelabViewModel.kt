package com.codelabs.state.examples

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codelabs.state.databinding.ActivityHelloCodelabBinding

class HelloCodeLabViewModel: ViewModel() {

    // LiveData는 UI에서 관찰되는 상태를 유지합니다.
    // (상태는 ViewModel에서 아래로 흐릅니다)
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    // onNameChanged는 UI가 호출할 수 있도록 정의하는 이벤트입니다.
    // (이벤트는 UI에서 위로 흐릅니다)
    // Update State – onNameChanged가 처리를 수행한 다음 _name의 상태를 설정합니다.
    fun onNameChanged(newName: String) {
        _name.value = newName
    }
}

class HellowCodeLabActivityWithViewModel : AppCompatActivity() {

    private lateinit var binding: ActivityHelloCodelabBinding
    private val helloViewModel by viewModels<HelloCodeLabViewModel>()

    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHelloCodelabBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*
        Event – 텍스트 입력이 변경될 때 UI에서 onNameChanged가 호출됩니다.
        Update State – onNameChanged가 처리를 수행한 다음 _name의 상태를 설정합니다.
        Display State – 상태 변경을 UI에 알리는 이름의 관찰자가 호출됩니다.
        */

        // Event – 텍스트 입력이 변경될 때 UI에서 onNameChanged가 호출됩니다.
        binding.textInput.doAfterTextChanged {
            helloViewModel.onNameChanged(it.toString())
        }

        // Display State – 상태 변경을 UI에 알리는 이름의 관찰자가 호출됩니다.
        helloViewModel.name.observe(this) { name ->
            binding.helloText.text = "Hello, $name"
        }
    }
}