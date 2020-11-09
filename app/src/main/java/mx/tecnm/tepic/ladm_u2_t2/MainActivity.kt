package mx.tecnm.tepic.ladm_u2_t2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var contadorHilo = 0
    var hilito = Hilo(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttoninicio.setOnClickListener {
            try {
                hilito.start()
            } catch (e:Exception){
                AlertDialog.Builder(this)
                    .setMessage("SI QUIERE VOLVER A EJECUTAR EL HILO PRESIONE 'REINICIAR'")
                    .setTitle("ATENCIÓN")
                    .setPositiveButton("ok"){d,i->
                    }
                    .show()
            }
        }
        buttonpause.setOnClickListener {
            hilito.pausar()
        }
        buttondetener.setOnClickListener {
            hilito.terminarHilo()
        }
        buttonreinicio.setOnClickListener {
            hilito.reinicio()
        }
    }
}
class Hilo(p:MainActivity) : Thread(){
    var puntero = p
    var mantener = true
    var despausar = true
    fun terminarHilo(){
        mantener=false
        puntero.textView.text = "Hilo: 0"
    }
    fun pausar(){
        despausar=!despausar
    }
    fun reinicio(){
        puntero.contadorHilo=0
        despausar=true
    }
    override fun run() {
        super.run()
        while (mantener){
            if (despausar==true) {
                puntero.contadorHilo++
                puntero.runOnUiThread {
                    puntero.textView.text = "Hilo: " + puntero.contadorHilo
                }
            }
            sleep(1000)
        }
    }
}