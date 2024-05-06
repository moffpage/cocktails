
import shared
import SwiftUI

final class ThemeProvider: ObservableObject {
    @Published
    var currentTheme: AppTheme
    
    init(
        shapes: Shapes,
        typography: Typography,
        colorScheme: SwiftUI.ColorScheme
    ) {
        self.currentTheme = colorScheme == .light ?
            AppTheme.light(
                shapes: shapes,
                typography: typography
            ) :
            AppTheme.dark(
                shapes: shapes,
                typography: typography
            )
    }
    
    func toggleAppTheme() {
        currentTheme = currentTheme.opposite()
    }
}
