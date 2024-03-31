
import shared
import UIKit
import SnapKit

final class CocktailDetailsViewController: UIViewController, ViewHolder {
    typealias RootViewType = CocktailDetailsView
    
    private let component: CocktailDetailsComponent
    
    init(component: CocktailDetailsComponent) {
        self.component = component
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func loadView() {
        view = CocktailDetailsView(
            onBackClick: { [unowned self] in
                self.component.navigateBack()
            }
        )
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        component.model.observe { [unowned self] model in
            self.rootView.bind(model: model)
        }
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        if isMovingFromParent {
            self.component.navigateBack()
        }
    }
}
