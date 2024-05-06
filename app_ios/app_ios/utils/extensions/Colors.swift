
import shared
import UIKit
import SwiftUI

extension UIColor {
    convenience init(hex: UInt32) {
        self.init(
            red: CGFloat((hex & 0xFF0000) >> 16) / 255.0,
            green: CGFloat((hex & 0x00FF00) >> 8) / 255.0,
            blue: CGFloat(hex & 0x0000FF) / 255.0,
            alpha: CGFloat(1.0)
        )
    }
}

extension shared.Color {
    func toColor() -> SwiftUI.Color {
        return SwiftUI.Color(
            UIColor(hex: UInt32(hexValue))
        )
    }
}

extension shared.ColorScheme {
    func toColorPalette() -> ColorPalette {
        return ColorPalette(
            primary: self.primary.toColor(),
            onPrimary: self.onPrimary.toColor(),
            surface: self.surface.toColor(),
            onSurface: self.onSurface.toColor(),
            background: self.background.toColor(),
            onBackground: self.onBackground.toColor(),
            secondary: self.secondary.toColor(),
            error: self.error.toColor()
        )
    }
}

extension SwiftUI.Color {
    func uiColor() -> UIColor {
        if #available(iOS 14.0, *) {
            return UIColor(self)
        }
        
        let components = components()
        return UIColor(
            red: components.red,
            green: components.green,
            blue: components.blue,
            alpha: components.alpha
        )
    }

    private func components() -> (red: CGFloat, green: CGFloat, blue: CGFloat, alpha: CGFloat) {
        let scanner = Scanner(
            string: self.description.trimmingCharacters(
                in: CharacterSet.alphanumerics.inverted
            )
        )
        
        var red: CGFloat = 0.0,
            green: CGFloat = 0.0,
            blue: CGFloat = 0.0,
            alpha: CGFloat = 0.0
        
        var hexNumber: UInt64 = 0

        let result = scanner.scanHexInt64(&hexNumber)
        
        if result {
            red = CGFloat((hexNumber & 0xff000000) >> 24) / 255
            green = CGFloat((hexNumber & 0x00ff0000) >> 16) / 255
            blue = CGFloat((hexNumber & 0x0000ff00) >> 8) / 255
            alpha = CGFloat(hexNumber & 0x000000ff) / 255
        }
        
        return (red, green, blue, alpha)
    }
}
