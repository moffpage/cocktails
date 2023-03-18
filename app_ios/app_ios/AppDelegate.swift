//
//  AppDelegate.swift
//  app_ios
//
//  Created by Artur Mavlyuchenko on 27.02.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import common
import UIKit

private class DarwinContext: PlatformContext { }

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    internal var window: UIWindow?
    
    private let timeTravelServer = TimeTravelServer(
        controller: TimeTravelControllerProviderKt.timeTravelController,
        port: BuildKt.timeTravelPort,
        onError: { throwable in
            print("TIMETRAVEL ERROR: \(throwable.description())")
        }
    )
    
    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil
    ) -> Bool {
        ModulesKt.initializeKoin()
        LoggingKt.enableLogging()
        ThemeConfigurator.configureTheme(
            shapes: ShapesFactoryKt.ShapesFactory,
            typography: TypographyFactoryKt.TypographyFactory
        )
        
        timeTravelServer.start()
        
        let lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        let componentContext = DefaultComponentContext(
            lifecycle: lifecycle,
            stateKeeper: nil,
            instanceKeeper: nil,
            backHandler: nil
        )
        
        let cocktailsComponent = FactoryKt.cocktailsComponent(
            platformContext: DarwinContext(),
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
