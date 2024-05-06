
import shared
import SwiftUI

final class ColorPalette: ObservableObject {
    static let dark: ColorPalette = ColorSchemeFactoryKt.darkColorScheme.toColorPalette()
    static let light: ColorPalette = ColorSchemeFactoryKt.lightColorScheme.toColorPalette()
    
    @Published
    var primary: SwiftUI.Color
    @Published
    var onPrimary: SwiftUI.Color
    
    @Published
    var surface: SwiftUI.Color
    @Published
    var onSurface: SwiftUI.Color
    
    @Published
    var background: SwiftUI.Color
    @Published
    var onBackground: SwiftUI.Color
    
    @Published
    var secondary: SwiftUI.Color
    
    @Published
    var error: SwiftUI.Color
    
    init(
        primary: SwiftUI.Color,
        onPrimary: SwiftUI.Color,
        surface: SwiftUI.Color,
        onSurface: SwiftUI.Color,
        background: SwiftUI.Color,
        onBackground: SwiftUI.Color,
        secondary: SwiftUI.Color,
        error: SwiftUI.Color
    ) {
        self.primary = primary
        self.onPrimary = onPrimary
        self.surface = surface
        self.onSurface = onSurface
        self.background = background
        self.onBackground = onBackground
        self.secondary = secondary
        self.error = error
    }
    
    func contentColor(for backgroundColor: SwiftUI.Color) -> SwiftUI.Color {
        switch backgroundColor {
        case primary: return onPrimary
        case surface: return onSurface
        case background: return onBackground
        case secondary: return .black
        case error: return .white
        default: return .clear
        }
    }
}
