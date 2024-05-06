
import shared
import SwiftUI

struct Typography {
    private let factory: shared.Typography
    private let palette: ColorPalette
    
    init(factory: shared.Typography, palette: ColorPalette) {
        self.factory = factory
        self.palette = palette
    }
    
    func h1(color: SwiftUI.Color) -> some ViewModifier {
        return TextStyle(
            textStyle: factory.h1,
            textColor: color
        )
    }
    
    func h2(color: SwiftUI.Color) -> some ViewModifier {
        return TextStyle(
            textStyle: factory.h2,
            textColor: color
        )
    }
    
    func h3(color: SwiftUI.Color) -> some ViewModifier {
        return TextStyle(
            textStyle: factory.h3,
            textColor: color
        )
    }
    
    func h4(color: SwiftUI.Color) -> some ViewModifier {
        return TextStyle(
            textStyle: factory.h4,
            textColor: color
        )
    }
    
    func body1(color: SwiftUI.Color) -> some ViewModifier {
        return TextStyle(
            textStyle: factory.body1,
            textColor: color
        )
    }
}

extension Typography {
    func h1() -> some ViewModifier {
        return h1(color: palette.onSurface)
    }
    
    func h2() -> some ViewModifier {
        return h2(color: palette.onSurface)
    }
    
    func h3() -> some ViewModifier {
        return h3(color: palette.onSurface)
    }
    
    func h4() -> some ViewModifier {
        return h4(color: palette.onSurface)
    }
    
    func body1() -> some ViewModifier {
        return body1(color: palette.onSurface)
    }
}
