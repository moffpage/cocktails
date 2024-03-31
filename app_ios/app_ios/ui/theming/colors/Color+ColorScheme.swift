
import shared
import UIKit

extension shared.Color {
    func toUIColor() -> UIColor {
        return UIColor(hex: UInt32(hexValue))
    }
}

extension shared.ColorScheme {
    func toColorPalette() -> ColorPalette {
        return ColorPalette(
            primary: self.primary.toUIColor(),
            onPrimary: self.onPrimary.toUIColor(),
            surface: self.surface.toUIColor(),
            onSurface: self.onSurface.toUIColor(),
            background: self.background.toUIColor(),
            onBackground: self.onBackground.toUIColor(),
            secondary: self.secondary.toUIColor(),
            error: self.error.toUIColor()
        )
    }
}
