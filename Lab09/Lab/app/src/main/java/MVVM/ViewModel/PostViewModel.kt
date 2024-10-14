package MVVM.ViewModel

import MVVM.API.PostApiService
import MVVM.Model.PostModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class PostViewModel(private val servicio: PostApiService) : ViewModel() {
    var listaPosts = mutableStateListOf<PostModel>()
    var postDetail = mutableStateOf<PostModel?>(null)

    fun obtenerPosts() {
        viewModelScope.launch {
            listaPosts.clear() // Limpiar lista antes de cargar nuevos datos
            listaPosts.addAll(servicio.getUserPosts())
        }
    }

    fun obtenerPostPorId(id: Int) {
        viewModelScope.launch {
            postDetail.value = servicio.getUserPostById(id)
        }
    }
}
