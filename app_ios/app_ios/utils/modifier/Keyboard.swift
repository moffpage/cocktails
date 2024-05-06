
import UIKit
import SwiftUI

extension View {
    func hideKeyboardOnTap() -> some View {
        return onTapGesture {
            UIApplication.shared.sendAction(
                #selector(UIResponder.resignFirstResponder),
                to: nil,
                from: nil,
                for: nil
            )
        }
    }
    
    func resignKeyboardOnDrag() -> some View {
        return modifier(ResignKeyboardOnDragGesture())
    }
}

private extension UIApplication {
    func endEditing(_ force: Bool) {
        delegate?.window??.endEditing(force)
    }
}

private struct ResignKeyboardOnDragGesture: ViewModifier {
    private let gesture = DragGesture()
        .onChanged { _ in UIApplication.shared.endEditing(true) }
    
    func body(content: Content) -> some View {
        content.gesture(gesture)
    }
}
