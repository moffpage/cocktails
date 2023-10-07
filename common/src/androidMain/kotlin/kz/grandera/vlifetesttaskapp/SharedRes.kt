package kz.grandera.vlifetesttaskapp

import dev.icerock.moko.resources.AssetResource
import dev.icerock.moko.resources.ColorResource
import dev.icerock.moko.resources.FileResource
import dev.icerock.moko.resources.FontResource
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.ResourceContainer
import dev.icerock.moko.resources.StringResource

internal actual object SharedRes {
    internal actual object strings : ResourceContainer<StringResource> {
        actual val retry: StringResource = StringResource(resourceId = R.string.retry)
        actual val search: StringResource = StringResource(resourceId = R.string.search)
        actual val cocktails: StringResource = StringResource(resourceId = R.string.cocktails)
        actual val alcoholic: StringResource = StringResource(resourceId = R.string.alcoholic)
        actual val non_alcoholic: StringResource = StringResource(resourceId = R.string.non_alcoholic)
        actual val error_occurred: StringResource = StringResource(resourceId = R.string.error_occurred)
        actual val instructions_label: StringResource = StringResource(resourceId = R.string.instructions_label)
        actual val time_travel_export_subject: StringResource = StringResource(resourceId = R.string.time_travel_export_subject)
        actual val time_travel_clipboard_empty: StringResource = StringResource(resourceId = R.string.time_travel_clipboard_empty)
        actual val time_travel_serialize_error: StringResource = StringResource(resourceId = R.string.time_travel_serialize_error)
        actual val time_travel_deserialize_error: StringResource = StringResource(resourceId = R.string.time_travel_deserialize_error)
        actual val time_travel_export_chooser_title: StringResource = StringResource(resourceId = R.string.time_travel_export_chooser_title)
    }

    internal actual object plurals : ResourceContainer<PluralsResource>
    internal actual object images : ResourceContainer<ImageResource>
    internal actual object fonts : ResourceContainer<FontResource> {
        public actual object Alice {
            actual val regular: FontResource = FontResource(fontResourceId = R.font.alice_regular)
        }
    }

    internal actual object files : ResourceContainer<FileResource> {
        actual val cocktail_animation: FileResource = FileResource(rawResId = R.raw.cocktail_animation)
    }

    internal actual object colors : ResourceContainer<ColorResource>
    internal actual object assets : ResourceContainer<AssetResource>
}