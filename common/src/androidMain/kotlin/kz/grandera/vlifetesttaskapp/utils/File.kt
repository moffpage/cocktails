package kz.grandera.vlifetesttaskapp.utils

import java.io.File
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

import android.content.Context

import androidx.core.content.FileProvider

internal actual typealias Uri = android.net.Uri

public fun fileManager(context: Context): FileManager =
    FileManagerImpl(context = context)

internal class FileManagerImpl(private val context: Context) : FileManager {
    override fun readText(fileUri: Uri): String {
        val inputStream = context.contentResolver.openInputStream(fileUri)
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)

        var resultString = ""

        bufferedReader.lineSequence()
            .forEach { line -> resultString += line }

        return resultString
    }

    override fun createWriteText(fileName: String, text: String): Uri {
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