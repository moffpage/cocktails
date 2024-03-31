
import shared
import UIKit

struct ColorPalette {
    let primary: UIColor
    let onPrimary: UIColor
    
    let surface: UIColor
    let onSurface: UIColor
    
    let background: UIColor
    let onBackground: UIColor
    
    let secondary: UIColor
    
    let error: UIColor
    
    static let dark: ColorPalette = ColorSchemeFactoryKt.darkColorScheme.toColorPalette()
    static let light: ColorPalette = ColorSchemeFactoryKt.lightColorScheme.toColorPalette()
    
    @available(iOS 13.0, *)
    static let adaptive: ColorPalette = .init(
        primary: UIColor(
            dynamicProvider: { traitCollection in
                adaptivePalette(traitCollection: traitCollection).primary
            }
        ),
        onPrimary: UIColor(
            dynamicProvider: { traitCollection in
                adaptivePalette(traitCollection: traitCollection).onPrimary
            }
        ),
        surface: UIColor(
            dynamicProvider: { traitCollection in
                adaptivePalette(traitCollection: traitCollection).surface
            }
        ),
        onSurface: UIColor(
            dynamicProvider: { traitCollection in
                adaptivePalette(traitCollection: traitCollection).onSurface
            }
        ),
        background: UIColor(
            dynamicProvider: { traitCollection in
                adaptivePalette(traitCollection: traitCollection).background
            }
        ),
        onBackground: UIColor(
            dynamicProvider: { traitCollection in
                adaptivePalette(traitCollection: traitCollection).onBackground
            }
        ),
        secondary: UIColor(
            dynamicProvider: { traitCollection in
                adaptivePalette(traitCollection: traitCollection).secondary
            }
        ),
        error: UIColor(
            dynamicProvider: { traitCollection in
                adaptivePalette(traitCollection: traitCollection).secondary
            }
        )
    )

    @available(iOS 13.0, *)
    private static func adaptivePalette(traitCollection: UITraitCollection) -> ColorPalette {
        switch traitCollection.userInterfaceStyle {
        case .dark:
            return .dark
        default:
            return .light
        }
    }
}
