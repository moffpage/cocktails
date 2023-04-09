package kz.grandera.vlifetesttaskapp.utils

import java.io.File
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

import android.content.Context

import androidx.core.content.FileProvider

internal actual typealias Uri = android.net.Uri

public actual class FileManager actual constructor(private val context: Context) {
    public actual fun readText(fileUri: Uri): String {
        var resultString = ""
        val inputStream = context.contentResolver.openInputStream(fileUri)

        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            bufferedReader.lineSequence()
                .forEach { line -> resultString += line }
        }

        return resultString
    }

    public actual fun createWriteText(fileName: String, text: String): Uri {
        val fileOutput = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        val outputWriter = OutputStreamWriter(fileOutput)

        outputWriter.write(text)
        outputWriter.close()

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            File("${context.filesDir.absolutePath}/$fileName")
        )
    }
}