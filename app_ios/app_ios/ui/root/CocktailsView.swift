
import shared
import SwiftUI

struct CocktailsView: View {
    private let component: CocktailsComponent
    
    @ObservedObject
    private var themeProvider: ThemeProvider
    
    @StateValue
    private var childStack: ChildStack<AnyObject, CocktailsComponentChild>
    
    init(component: CocktailsComponent, themeProvider: ThemeProvider) {
        self.component = component
        self.themeProvider = themeProvider
        self._childStack = StateValue(component.model)
    }
    
    var body: some View {
        StackView(
            onBack: { component.onBackInvoked() },
            stackValue: StateValue(component.model),
            childContent: { child in
                switch child {
                case let child as CocktailsComponentChildCocktailsList:
                    CocktailsListView(component: child.component)
                        .edgesIgnoringSafeArea(.all)
                case let child as CocktailsComponentChildCocktailDetails:
                    CocktailDetailsView(component: child.component)
                        .edgesIgnoringSafeArea(.all)
                default: EmptyView()
                }
            }
        )
        .onShake { themeProvider.toggleAppTheme() }
        .environmentObject(themeProvider.currentTheme)
    }
}
