package com.example.recyclerview.presentation

import android.content.Context
import android.graphics.Color
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.recyclerview.R

/**
 * Actividad de detalles
 */
class DetailActivity : ComponentActivity() {

    companion object {
        const val EXTRA_ITEM_TITLE = "extra_item_title"
        const val EXTRA_ITEM_DESCRIPTION = "extra_item_description"
        const val EXTRA_ITEM_ID = "extra_item_id"
    }

    private var countDownTimer: CountDownTimer? = null
    private var counter = 0
    private var soundIndex = 0
    private val colors = arrayOf("#000000", "#FF5722", "#4CAF50", "#2196F3", "#9C27B0", "#FF9800")
    private var toneGenerator: ToneGenerator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)
        setContentView(R.layout.activity_detail_enhanced)

        val itemTitle = intent.getStringExtra(EXTRA_ITEM_TITLE) ?: "Sin título"
        val itemDescription = intent.getStringExtra(EXTRA_ITEM_DESCRIPTION) ?: "Sin descripción"
        val itemId = intent.getIntExtra(EXTRA_ITEM_ID, -1)

        setupViews(itemTitle, itemDescription, itemId)

        try {
            toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 80)
        } catch (e: Exception) {
            // Si hay error, continuamos sin sonidos
        }
    }

    private fun setupViews(title: String, description: String, id: Int) {
        val titleTextView = findViewById<TextView>(R.id.tv_detail_title)
        val descriptionTextView = findViewById<TextView>(R.id.tv_detail_description)
        val idTextView = findViewById<TextView>(R.id.tv_detail_id)
        val actionButton = findViewById<Button>(R.id.btn_action)

        titleTextView.text = title
        descriptionTextView.text = description
        idTextView.text = "ID: $id"

        when (id) {
            1 -> {
                actionButton.text = "🔢 Contar +"
                actionButton.setOnClickListener { incrementCounter() }
            }
            2 -> {
                actionButton.text = "🎵 Reproducir Sonido"
                actionButton.setOnClickListener { playNextSound() }
            }
            4 -> {
                actionButton.text = "🎨 Cambiar Color"
                actionButton.setOnClickListener { changeBackgroundColor() }
            }
            else -> {
                actionButton.text = "✨ Acción Especial"
                actionButton.setOnClickListener {
                    descriptionTextView.text = "¡Acción ejecutada con éxito!"
                }
            }
        }
    }

    /**
     * Contador interactivo
     */
    private fun incrementCounter() {
        counter++
        val descriptionTextView = findViewById<TextView>(R.id.tv_detail_description)
        descriptionTextView.text = """
            🔢 CONTADOR ACTIVO
            
            Número actual: $counter
            
        """.trimIndent()

        // Vibración ligera en cada toque
        try {
            val vibrator = getSystemService(VIBRATOR_SERVICE) as android.os.Vibrator
            vibrator.vibrate(50)
        } catch (e: Exception) {
            // Ignorar si no puede vibrar
        }
    }

    /**
     * Cambiar color de fondo dinámicamente
     */
    private fun changeBackgroundColor() {
        val mainLayout = findViewById<View>(R.id.main_background)
            ?: findViewById<View>(android.R.id.content) // Fallback al root
        val descriptionTextView = findViewById<TextView>(R.id.tv_detail_description)

        val currentColorIndex = (mainLayout.tag as? Int) ?: 0
        val nextColorIndex = (currentColorIndex + 1) % colors.size
        val newColor = colors[nextColorIndex]

        mainLayout.setBackgroundColor(Color.parseColor(newColor))
        mainLayout.tag = nextColorIndex

        val colorNames = arrayOf("Negro", "Naranja", "Verde", "Azul", "Morado", "Amarillo")

        descriptionTextView.text = """
            🎨 COLOR CAMBIADO
            
            Color actual: ${colorNames[nextColorIndex]}
                        
            Total de colores: ${colors.size}
        """.trimIndent()

        when (newColor) {
            "#FF9800" -> {
                descriptionTextView.setTextColor(Color.BLACK)
                findViewById<TextView>(R.id.tv_detail_title)?.setTextColor(Color.BLACK)
                findViewById<TextView>(R.id.tv_detail_id)?.setTextColor(Color.BLACK)
            }
            "#4CAF50" -> {
                descriptionTextView.setTextColor(Color.BLACK)
                findViewById<TextView>(R.id.tv_detail_title)?.setTextColor(Color.BLACK)
                findViewById<TextView>(R.id.tv_detail_id)?.setTextColor(Color.BLACK)
            }
            else -> {
                descriptionTextView.setTextColor(Color.WHITE)
                findViewById<TextView>(R.id.tv_detail_title)?.setTextColor(Color.WHITE)
                findViewById<TextView>(R.id.tv_detail_id)?.setTextColor(Color.GRAY)
            }
        }
    }

    /**
     * Reproduce diferentes sonidos/tonos musicales
     */
    private fun playNextSound() {
        val descriptionTextView = findViewById<TextView>(R.id.tv_detail_description)

        soundIndex = (soundIndex % 8) + 1

        try {
            when (soundIndex) {
                1 -> {
                    toneGenerator?.startTone(ToneGenerator.TONE_DTMF_1, 300)
                    descriptionTextView.text = """
                        🎵 NOTA: DO
                        
                        Sonido 1/8
                        
                    """.trimIndent()
                }
                2 -> {
                    toneGenerator?.startTone(ToneGenerator.TONE_DTMF_2, 300)
                    descriptionTextView.text = """
                        🎶 NOTA: RE
                        
                        Sonido 2/8
                        
                    """.trimIndent()
                }
                3 -> {
                    toneGenerator?.startTone(ToneGenerator.TONE_DTMF_3, 300)
                    descriptionTextView.text = """
                        🎵 NOTA: MI
                        
                        Sonido 3/8
                        
                    """.trimIndent()
                }
                4 -> {
                    toneGenerator?.startTone(ToneGenerator.TONE_DTMF_4, 300)
                    descriptionTextView.text = """
                        🎶 NOTA: FA
                        
                        Sonido 4/8
                        Tono medio-alto
                        
                    """.trimIndent()
                }
                5 -> {
                    toneGenerator?.startTone(ToneGenerator.TONE_DTMF_5, 300)
                    descriptionTextView.text = """
                        🎵 NOTA: SOL
                        
                        Sonido 5/8
                        
                    """.trimIndent()
                }
                6 -> {
                    toneGenerator?.startTone(ToneGenerator.TONE_DTMF_6, 300)
                    descriptionTextView.text = """
                        🎶 NOTA: LA
                        
                        Sonido 6/8
                                                
                    """.trimIndent()
                }
                7 -> {
                    toneGenerator?.startTone(ToneGenerator.TONE_DTMF_7, 300)
                    descriptionTextView.text = """
                        🎵 NOTA: SI
                        
                        Sonido 7/8
                        
                    """.trimIndent()
                }
                8 -> {
                    toneGenerator?.startTone(ToneGenerator.TONE_DTMF_8, 300)
                    descriptionTextView.text = """
                        🎶 OCTAVA COMPLETA
                        
                        Sonido 8/8
                        ¡Escala terminada!
                        
                        Toca para reiniciar 🔄
                    """.trimIndent()
                }
            }
        } catch (e: Exception) {
            descriptionTextView.text = """
                
                No se puede reproducir audio
                en este dispositivo.
                
            """.trimIndent()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
        toneGenerator?.release()
    }
}