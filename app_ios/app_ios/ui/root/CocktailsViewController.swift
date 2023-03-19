//
//  CocktailsViewController.swift
//  app_ios
//
//  Created by Artur Mavlyuchenko on 27.02.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import common
import UIKit

class CocktailsViewController: UINavigationController {
    private let component: CocktailsComponent
    
    init(component: CocktailsComponent) {
        self.component = component
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationBar.isHidden = true
        observeChildren()
    }
    
    private func observeChildren() {
        component.model.subscribe { [unowned self] childStack in
            switch childStack.active.instance {
            case let child as CocktailsComponentChildCocktailsList:
                let cocktailsListViewController = CocktailsListViewController(
                    component: child.component
                )
                if (self.viewControllers.isEmpty) {
                    self.viewControllers = [cocktailsListViewController]
                }
                
            case let child as CocktailsComponentChildCocktailDetails:
                let cocktailDetailsViewController = CocktailDetailsViewController(
                    component: child.component
                )
                cocktailDetailsViewController.modalPresentationStyle = .fullScreen
                self.present(cocktailDetailsViewController, animated: true)
            case let child as CocktailsComponentChildTimeTravelLookOver:
                let timeTravelClientViewController = TimeTravelClientViewController(
                    component: child.component
                )
                self.present(timeTravelClientViewController, animated: true)
            default: break
            }
        }
    }
}
