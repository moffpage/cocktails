package kz.grandera.vlifetesttaskapp.resources

import dev.icerock.moko.resources.StringResource

import kz.grandera.vlifetesttaskapp.SharedRes

public object Strings {
    public val retry: StringResource = SharedRes.strings.retry
    public val search: StringResource = SharedRes.strings.search
    public val cocktails: StringResource = SharedRes.strings.cocktails
    public val alcoholic: StringResource = SharedRes.strings.alcoholic
    public val nonAlcoholic: StringResource = SharedRes.strings.non_alcoholic
    public val instructions: StringResource = SharedRes.strings.instructions_label
    public val errorOccurred: StringResource = SharedRes.strings.error_occurred
    public val timeTravelExportSubject: StringResource = SharedRes.strings.time_travel_export_subject
    public val timeTravelClipboardEmpty: StringResource = SharedRes.strings.time_travel_clipboard_empty
    public val timeTravelSerializeError: StringResource = SharedRes.strings.time_travel_serialize_error
    public val timeTravelDeserializeError: StringResource = SharedRes.strings.time_travel_deserialize_error
    public val timeTravelExportChooserTitle: StringResource = SharedRes.strings.time_travel_export_chooser_title
}
