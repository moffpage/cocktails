
import UIKit
import SwiftUI

extension UIUserInterfaceStyle {
    func toColorPalette() -> ColorPalette {
        switch self {
        case .dark: return .dark
        case .light, .unspecified: return .light
        @unknown default: return .light
        }
    }
    
    func toColorScheme() -> ColorScheme {
        switch self {
        case .dark: return .dark
        case .light, .unspecified: return .light
        @unknown default: return .light
        }
    }
}

extension ColorScheme {
    func toUiUserInterfaceStyle() -> UIUserInterfaceStyle {
        switch self {
        case .dark: return .dark
        case .light: return .light
        @unknown default: return .light
        }
    }
}
