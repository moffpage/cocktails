
import shared
import SwiftUI

extension shared.TextStyle {
    func toFont() -> SwiftUI.Font{
        return Font(
            self.fontFamily.fonts.first!
                .resource.uiFont(withSize: self.fontSize)
        )
    }
}

struct TextStyle: ViewModifier {
    @EnvironmentObject
    var theme: AppTheme
    
    private let textStyle: shared.TextStyle
    private let textColor: SwiftUI.Color?
    
    init(
        textStyle: shared.TextStyle? = nil,
        textColor: SwiftUI.Color? = nil
    ) {
        self.textStyle = textStyle ?? TypographyFactoryKt.TypographyFactory.body1
        self.textColor = textColor
    }
    
    func body(content: Content) -> some View {
        return content.font(textStyle.toFont())
            .foregroundColor(textColor ?? theme.colors.onSurface)
            .multilineTextAlignment(.leading)
    }
}
