//
//  AppTheme.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 17.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import common

struct AppTheme: Theme {
    static func light(shapes: Shapes, typography: Typography) -> any Theme {
        return AppTheme(
            mode: .light,
            shapes: shapes,
            colors: .light,
            typography: typography
        )
    }
    static func dark(shapes: Shapes, typography: Typography) -> any Theme {
        return AppTheme(
            mode: .dark,
            shapes: shapes,
            colors: .dark,
            typography: typography
        )
    }
    @available(iOS 13.0, *)
    static func adaptive(shapes: Shapes, typography: Typography) -> any Theme {
        return AppTheme(
            mode: .adaptive,
            shapes: shapes,
            colors: .adaptive,
            typography: typography
        )
    }
    
    let mode: Mode
    let shapes: Shapes
    let colors: ColorPalette
    let typography: Typography
    
    init(mode: Mode,
         shapes: Shapes,
         colors: ColorPalette,
         typography: Typography
    ) {
        self.mode = mode
        self.shapes = shapes
        self.colors = colors
        self.typography = typography
    }
}

extension AppTheme: Equatable {
    static func == (lhs: AppTheme, rhs: AppTheme) -> Bool {
        return lhs.mode == rhs.mode
    }
}
