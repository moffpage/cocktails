
import shared
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
        observeChildren()
        hideNavigationBar()
    }
    
    func navigationController(
        _ navigationController: UINavigationController,
        didShow viewController: UIViewController, animated: Bool
    ) {
        interactivePopGestureRecognizer?.isEnabled = viewControllers.count > 1
    }
    
    private func observeChildren() {
        component.model.observe { [unowned self] childStack in
            switch childStack.active.instance {
            case let child as CocktailsComponentChildCocktailsList:
                let cocktailsListViewController = CocktailsListViewController(
                    component: child.component
                )
                if self.viewControllers.isEmpty {
                    self.viewControllers = [cocktailsListViewController]
                } else {
                    self.popViewController(animated: true)
                }
                
            case let child as CocktailsComponentChildCocktailDetails:
                let cocktailDetailsViewController = CocktailDetailsViewController(
                    component: child.component
                )
                self.pushViewController(cocktailDetailsViewController, animated: true)
            default: break
            }
        }
    }
    
    private func hideNavigationBar() {
        navigationBar.isHidden = true
    }
}
