
import shared
import SwiftUI
import Kingfisher

struct CocktailItemView: View {
    @EnvironmentObject
    var theme: AppTheme
    
    private let cocktail: CocktailsListComponentCocktailModel
    private let onClick: (CocktailsListComponentCocktailModel) -> Void
    
    init(
        cocktail: CocktailsListComponentCocktailModel,
        onClick: @escaping (CocktailsListComponentCocktailModel) -> Void
    ) {
        self.cocktail = cocktail
        self.onClick = onClick
    }
    
    var body: some View {
        ZStack(alignment: .center) {
            KingfisherView(url: cocktail.imageUrl)
            Text(cocktail.name)
                .modifier(theme.typography.h4(color: theme.colors.onPrimary))
        }
        .clip(with: theme.shapes.large)
        .onTapGesture(perform: { onClick(cocktail) })
    }
}
