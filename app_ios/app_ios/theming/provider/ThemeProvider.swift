//
//  ThemeProvider.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 10.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

protocol ThemeProvider: AnyObject {
    var theme: any Theme { get }
    
    func register<Observer: Themeable>(observer: Observer)
    func setTheme(_ theme: any Theme)
}
