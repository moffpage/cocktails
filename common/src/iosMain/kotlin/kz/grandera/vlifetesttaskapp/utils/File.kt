package kz.grandera.vlifetesttaskapp.utils

public actual abstract class Uri

public fun fileManager(): FileManager = FileManagerImpl()

internal class FileManagerImpl : FileManager {
    override fun readText(fileUri: Uri): String {
        TODO("Not yet implemented")
    }

    override fun createWriteText(fileName: String, text: String): Uri {
        TODO("Not yet implemented")
    }
}