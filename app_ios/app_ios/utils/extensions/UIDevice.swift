
import UIKit

extension UIDevice {
    static let deviceDidShakeNotification = Notification.Name(
        rawValue: "deviceDidShakeNotification"
    )
}

extension UIWindow {
    open override func motionBegan(_ motion: UIEvent.EventSubtype, with event: UIEvent?) {
        if motion == .motionShake {
            NotificationCenter.default.post(
                name: UIDevice.deviceDidShakeNotification,
                object: nil
            )
        }
    }
}
