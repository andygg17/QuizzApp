package garcia.andrea.quizzapp

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel" //

const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val bancoPreguntas = listOf(
        Pregunta(R.string.pregunta_promedio, respuesta = true),
        Pregunta(R.string.pregunta_valles, respuesta = false),
        Pregunta(R.string.pregunta_estado, respuesta = true)
    )

    //Guarda en el indice
    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?:0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = bancoPreguntas[currentIndex].respuesta
    val currentQuestionText: Int
        get() = bancoPreguntas[currentIndex].textoPreguntaResId
    fun moveToNext() {
        currentIndex = (currentIndex + 1) % bancoPreguntas.size
    }

    fun moveToForward () {
        currentIndex = if (currentIndex == 0) {
            bancoPreguntas.size -1
        } else
            (currentIndex-1) % bancoPreguntas.size
    }

}