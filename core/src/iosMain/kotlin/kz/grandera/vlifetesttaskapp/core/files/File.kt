package kz.grandera.vlifetesttaskapp.core.files

import kz.grandera.vlifetesttaskapp.core.platform.PlatformContext

public actual abstract class Uri

public actual class FileManager actual constructor(context: PlatformContext) {
    public actual fun readText(fileUri: Uri): String {
        TODO("Not yet implemented")
    }

    public actual fun createWriteText(fileName: String, text: String): Uri {
        TODO("Not yet implemented")
    }
}