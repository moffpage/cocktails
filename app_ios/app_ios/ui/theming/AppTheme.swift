
import shared
import SwiftUI

class AppTheme: ObservableObject {
    let shapes: Shapes
    let colors: ColorPalette
    let typography: Typography
    let colorScheme: SwiftUI.ColorScheme
    
    fileprivate init(
        shapes: Shapes,
        colors: ColorPalette,
        typography: Typography,
        colorScheme: SwiftUI.ColorScheme
    ) {
        self.shapes = shapes
        self.colors = colors
        self.typography = typography
        self.colorScheme = colorScheme
    }
    
    func opposite() -> AppTheme {
        switch self.colorScheme {
        case .light:
            return AppTheme.dark(
                shapes: self.shapes,
                typography: self.typography
            )
        case .dark:
            return AppTheme.light(
                shapes: self.shapes,
                typography: self.typography
            )
        @unknown default:
            return AppTheme.light(
                shapes: self.shapes,
                typography: self.typography
            )
        }
    }
}

extension AppTheme {
    static func light(shapes: Shapes, typography: Typography) -> AppTheme {
        return AppTheme(
            shapes: shapes,
            colors: .light,
            typography: typography,
            colorScheme: .light
        )
    }
    static func dark(shapes: Shapes, typography: Typography) -> AppTheme {
        return AppTheme(
            shapes: shapes,
            colors: .dark,
            typography: typography,
            colorScheme: .dark
        )
    }
}

extension AppTheme: Equatable {
    static func == (lhs: AppTheme, rhs: AppTheme) -> Bool {
        return lhs.colorScheme == rhs.colorScheme
    }
}
