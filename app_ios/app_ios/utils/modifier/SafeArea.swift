
import UIKit
import SwiftUI

extension View {
    func statusBarPadding() -> some View {
        return modifier(StatusBarPaddingModifier())
    }
    
    func navigationBarPadding() -> some View {
        return modifier(NavigationBarPaddingModifier())
    }
}

private struct StatusBarPaddingModifier: ViewModifier {
    func body(content: Content) -> some View {
        return content.padding(.top, getSafeAreaTop())
    }
}

private struct NavigationBarPaddingModifier: ViewModifier {
    func body(content: Content) -> some View {
        return content.padding(.bottom, getSafeAreaBottom())
    }
}

private func getSafeAreaTop() -> CGFloat {
    return UIApplication.shared.windows
        .filter { $0.isKeyWindow }
        .first?
        .safeAreaInsets
        .top ?? 0
}

private func getSafeAreaBottom() -> CGFloat {
    return UIApplication.shared.windows
        .filter { $0.isKeyWindow }
        .first?
        .safeAreaInsets
        .bottom ?? 0
}
