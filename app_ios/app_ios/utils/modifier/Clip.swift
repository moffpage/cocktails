
import shared
import SwiftUI

struct ClipModifier: ViewModifier {
    private let shape: RoundedCornerShape
    
    init(shape: RoundedCornerShape) {
        self.shape = shape
    }
    
    func body(content: Content) -> some View {
        return content
            .clipShape(
                RoundedRectangle(
                    cornerRadius: CGFloat(
                        self.shape.cornerRadius
                    )
                )
            )
    }
}

extension View {
    func clip(with shape: RoundedCornerShape) -> some View {
        return self.modifier(ClipModifier(shape: shape))
    }
}
