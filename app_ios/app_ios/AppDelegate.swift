
import shared
import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    internal var window: UIWindow?
    
    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil
    ) -> Bool {
        InitializationKt.initializeKoin()
        InitializationKt.enableLogging()
        ThemeConfigurator.configureTheme(
            shapes: ShapesFactoryKt.ShapesFactory,
            typography: TypographyFactoryKt.TypographyFactory
        )
        
        let lifecycle = ApplicationLifecycle()
        let componentContext = DefaultComponentContext(
            lifecycle: lifecycle,
            stateKeeper: nil,
            instanceKeeper: nil,
            backHandler: nil
        )
        
        let cocktailsComponent = FactoryKt.cocktailsComponentFactory(
            componentContext: componentContext
        )
        let cocktailsViewController = CocktailsViewController(
            component: cocktailsComponent
        )
        
        let window = ThemeWindow(frame: UIScreen.main.bounds)
        window.rootViewController = cocktailsViewController
        
        self.window = window
        
        window.makeKeyAndVisible()
        
        themeProvider.register(observer: self)
        
        return true
    }
}

extension AppDelegate: Themeable {
    func apply(theme: any Theme) {
        window?.backgroundColor = theme.colors.background
    }
}
