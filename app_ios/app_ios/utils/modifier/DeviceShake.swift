
import SwiftUI

extension View {
    func onShake(perform action: @escaping () -> Void) -> some View {
        return self.modifier(DeviceShakeViewModifier(action: action))
    }
}

private struct DeviceShakeViewModifier: ViewModifier {
    private let action: () -> Void
    
    init(action: @escaping () -> Void) {
        self.action = action
    }

    func body(content: Content) -> some View {
        content
            .onAppear()
            .onReceive(
                NotificationCenter.default.publisher(
                    for: UIDevice.deviceDidShakeNotification
                )
            ) { _ in action() }
    }
}
