
import SwiftUI

extension View {
    func placeholder<Content: View>(
        visible: Bool,
        alignment: Alignment = .leading,
        @ViewBuilder
        content: () -> Content) -> some View
    {
        ZStack(alignment: alignment) {
            content().opacity(visible ? 1 : 0)
            self
        }
    }
}
