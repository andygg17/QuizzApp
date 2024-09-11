package garcia.andrea.quizzapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import garcia.andrea.quizzapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val bancoPreguntas = listOf(
        Pregunta(R.string.pregunta_promedio, respuesta = true),
        Pregunta(R.string.pregunta_valles, respuesta = false),
        Pregunta(R.string.pregunta_estado, respuesta = true)
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.trueButton.setOnClickListener { view: View->
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener { view: View->
            checkAnswer(false)
        }
        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % bancoPreguntas.size
            updateQuestion()
        }
        binding.prevButton.setOnClickListener {
            currentIndex = if (currentIndex == 0) {
                bancoPreguntas.size -1
            } else
                    (currentIndex-1) % bancoPreguntas.size
            updateQuestion()
        }
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = bancoPreguntas[currentIndex].textoPreguntaResId
        binding.textoPreguntaVista.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = bancoPreguntas[currentIndex].respuesta

        val messageResId = if(userAnswer == correctAnswer){
            R.string.correct_toast
        }else {
            R.string.incorrect_toast
             }
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

    }
}


