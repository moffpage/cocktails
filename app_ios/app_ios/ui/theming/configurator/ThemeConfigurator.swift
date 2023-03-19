//
//  ThemeConfigurator.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 10.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import common
import UIKit

struct ThemeConfigurator {
    static func configureTheme(shapes: Shapes, typography: Typography) {
        let theme: any Theme
        
        if #available(iOS 13.0, *) {
            switch UITraitCollection.current.userInterfaceStyle {
            case .dark: theme = AppTheme.dark(shapes: shapes, typography: typography)
            case .light: theme = AppTheme.light(shapes: shapes, typography: typography)
            case .unspecified: theme = AppTheme.light(shapes: shapes, typography: typography)
            @unknown default: theme = AppTheme.light(shapes: shapes, typography: typography)
            }
        } else {
            theme = AppTheme.light(shapes: shapes, typography: typography)
        }
        
        ThemeProviderCompat.shared.setTheme(theme)
    }
}
