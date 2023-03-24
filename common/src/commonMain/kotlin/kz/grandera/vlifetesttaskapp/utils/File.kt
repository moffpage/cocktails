package kz.grandera.vlifetesttaskapp.utils

@Suppress("NO_ACTUAL_FOR_EXPECT")
public expect abstract class Uri

public interface FileManager {
    public fun readText(fileUri: Uri): String
    public fun createWriteText(fileName: String, text: String): Uri
}