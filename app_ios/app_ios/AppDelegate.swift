
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
        
        let window = UIWindow(frame: UIScreen.main.bounds)
        let lifecycle = ApplicationLifecycle()
        let backDispatcher = BackDispatcherKt.BackDispatcher()
        let componentContext = DefaultComponentContext(
            lifecycle: lifecycle,
            stateKeeper: nil,
            instanceKeeper: nil,
            backHandler: backDispatcher
        )
        let component = FactoryKt.cocktailsComponentFactory(
            componentContext: componentContext
        )
        let shakeDetector = ShakeDetectorKt.ShakeDetectorFactory(lifecycle: lifecycle)
        let cocktailsViewController = CocktailsViewControllerKt.CocktailsViewController(
            component: component,
            shakeDetector: shakeDetector,
            backDispatcher: backDispatcher
        )
        
        window.rootViewController = cocktailsViewController
        window.makeKeyAndVisible()
        
        return true
    }
}

extension UIWindow {
     open override func motionEnded(_ motion: UIEvent.EventSubtype, with event: UIEvent?) {
        if motion == .motionShake {
            NotificationCenter.default.post(name: Notification.Name("deviceDidShakeNotification"), object: nil)
        }
     }
}
