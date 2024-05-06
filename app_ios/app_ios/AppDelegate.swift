
import shared
import UIKit
import SwiftUI

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?
    
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil
    ) -> Bool
    {
        InitializationKt.initializeKoin()
        InitializationKt.enableLogging()
        
        let window = UIWindow(frame: UIScreen.main.bounds)
        let component = FactoryKt.cocktailsComponentFactory(
            componentContext: DefaultComponentContext(
                lifecycle: ApplicationLifecycle(),
                stateKeeper: nil,
                instanceKeeper: nil,
                backHandler: nil
            )
        )
        let systemTheme = UIScreen.main.traitCollection.userInterfaceStyle
        let themeProvider = ThemeProvider(
            shapes: ShapesFactoryKt.ShapesFactory,
            typography: Typography(
                factory: TypographyFactoryKt.TypographyFactory,
                palette: systemTheme.toColorPalette()
            ),
            colorScheme: systemTheme.toColorScheme()
        )
        let contentView = CocktailsView(
            component: component,
            themeProvider: themeProvider
        )
        let rootViewController = UIHostingController(rootView: contentView)
        
        window.rootViewController = rootViewController
        
        self.window = window
        
        window.makeKeyAndVisible()
        
        return true
    }
}
