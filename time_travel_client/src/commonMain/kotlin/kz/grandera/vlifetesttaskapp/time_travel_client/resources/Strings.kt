package kz.grandera.vlifetesttaskapp.time_travel_client.resources

import dev.icerock.moko.resources.StringResource

import kz.grandera.vlifetesttaskapp.time_travel_client.SharedRes

internal object Strings {
    public val timeTravelExportSubject: StringResource = SharedRes.strings.time_travel_export_subject
    public val timeTravelClipboardEmpty: StringResource = SharedRes.strings.time_travel_clipboard_empty
    public val timeTravelSerializeError: StringResource = SharedRes.strings.time_travel_serialize_error
    public val timeTravelDeserializeError: StringResource = SharedRes.strings.time_travel_deserialize_error
    public val timeTravelExportChooserTitle: StringResource = SharedRes.strings.time_travel_export_chooser_title
}