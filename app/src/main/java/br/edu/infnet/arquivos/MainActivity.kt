package br.edu.infnet.arquivos

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.edu.infnet.arquivos.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    val TAG = "arquivos"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view  = binding.root
        setContentView(view)

        setup()

        Log.i(TAG, getMainDir())
        Log.i(TAG, getMainDirFiles())
    }

    private fun setup() {
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnGravar.setOnClickListener {
            val titulo = binding.inputTitulo.text.toString()
            val conteudo = binding.inputConteudo.text.toString()
            salvarArquivo(titulo, conteudo)
        }

        binding.btnLer.setOnClickListener {
            val titulo = binding.inputTitulo.text.toString()
            val conteudo = lerArquivo(titulo)
            binding.inputConteudo.setText(conteudo)
        }

        binding.btnListar.setOnClickListener {
            binding.textView.text = listarDiretorios()
        }

        //binding.btnCache.setOnClickListener {
        //    val titulo = binding.inputTitulo.text.toString()
        //    val conteudo = binding.inputConteudo.text.toString()
        //    salvarArquivoCache(titulo, conteudo)
        //}

        // binding.btnDeletar.setOnClickListener {
        //     val titulo = binding.inputTitulo.text.toString()
        //     val conteudo = binding.inputConteudo.text.toString()
        //     salvarArquivo(titulo, conteudo)
        // }

    }

    fun getMainDir(): String {
        return filesDir.path
    }

    fun getMainDirFiles(): String {
        val files = File(getMainDir()).listFiles()
        var resposta = ""
        files.forEach { file ->
            resposta = "${resposta}$file\n"
        }
        return resposta
    }

    fun salvarArquivo(
        inputTitulo: String,
        inputConteudo: String
    ){
        val file = File(filesDir, "${inputTitulo}.txt")
        openFileOutput(file.name, Context.MODE_PRIVATE).use {
            it.write(inputConteudo.toByteArray())
        }
    }

    fun lerArquivo(inputTitulo: String): String {
        var conteudo = ""

        val file = File(filesDir, "${inputTitulo}.txt")
        val fis = openFileInput(file.name)
        conteudo = fis.bufferedReader().readText()


        return conteudo
    }

    fun listarDiretorios(): String {

        val files = File(getMainDir()).listFiles()
        val listaArquivos = files.filter{ it.isFile ==true}
        val listaDiretorios = files.filter{ it.isDirectory ==true}

        var retorno = ""

        retorno = "${retorno}Diretorios:\n"
        listaDiretorios.forEach {
            retorno = "${retorno}${it.name}\n"
        }

        retorno = "${retorno}\nArquivos:\n"
        listaArquivos.forEach {
            retorno = "${retorno}${it.name}\n"
        }

        return retorno
    }

    fun salvarArquivoCache(
        inputTitulo: String,
        inputConteudo: String
    ){
        val file = File(cacheDir, "${inputTitulo}.txt")

        Log.i(TAG, "CACHE: ${file.path}")
        openFileOutput(file.name, Context.MODE_PRIVATE).use {
            it.write(inputConteudo.toByteArray())
        }
    }

    fun deleteArquivo(inputTitulo: String) {
        val file = File(filesDir, "${inputTitulo}.txt")
        file.delete()
    }

}