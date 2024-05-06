
import shared
import SwiftUI

public class ObservableValue<T: AnyObject> : ObservableObject {
    @Published
    var value: T

    private var observer: ((T) -> Void)?
    
    private var cancellation: Cancellation?
    private let observableValue: Value<T>
    
    init(_ value: Value<T>) {
        self.observableValue = value
        self.value = observableValue.value
        self.observer = { [weak self] value in self?.value = value }
        self.cancellation = observableValue.subscribe(observer_: observer!)
    }

    deinit {
        self.cancellation?.cancel()
    }
}
