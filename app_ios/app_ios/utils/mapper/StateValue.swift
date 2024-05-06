
import shared
import SwiftUI

@propertyWrapper
struct StateValue<T: AnyObject>: DynamicProperty {
    @ObservedObject
    private var observableValue: ObservableValue<T>

    var wrappedValue: T { observableValue.value }

    init(_ value: Value<T>) {
        self.observableValue = ObservableValue(value)
    }
}
