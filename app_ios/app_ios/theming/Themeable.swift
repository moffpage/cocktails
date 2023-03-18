//
//  Themeable.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 10.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

protocol Themeable: AnyObject {
    func apply(theme: any Theme)
}

extension Themeable {
    var themeProvider: ThemeProvider {
        return ThemeProviderCompat.shared
    }
}
