package kz.grandera.vlifetesttaskapp.core.files

import kz.grandera.vlifetesttaskapp.core.platform.PlatformContext

public expect abstract class Uri

public expect class FileManager public constructor(context: PlatformContext) {
    public fun readText(fileUri: Uri): String
    public fun createWriteText(fileName: String, text: String): Uri
}