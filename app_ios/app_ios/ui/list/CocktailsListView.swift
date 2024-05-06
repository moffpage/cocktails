
import shared
import UIKit
import SwiftUI

struct CocktailsListView: View {
    @EnvironmentObject
    private var theme: AppTheme
    
    @StateValue
    private var model: CocktailsListComponentModel
    
    private let component: CocktailsListComponent
    
    init(component: CocktailsListComponent) {
        self.component = component
        self._model = StateValue(component.model)
    }
    
    var body: some View {
        ZStack {
            if model.isError {
                ErrorView(
                    onRetry: {
                        component.refetchCocktails()
                    },
                    errorText: UiComponentsStrings.shared.retry
                        .desc().localized()
                )
                .padding(.horizontal, 16)
            } else {
                ScrollView(showsIndicators: false) {
                    VStack {
                        Text(CommonStrings.shared.cocktails
                            .desc().localized())
                            .modifier(theme.typography.h1(color: theme.colors.onBackground))
                            .frame(
                                maxWidth: .infinity,
                                alignment: .leading
                            )
                            .padding(.top, 20)
                            .padding(.leading, 16)
                        
                        SearchBar(
                            value: model.searchQuery,
                            onValueChange: { searchQuery in
                                withAnimation(.easeInOut) {
                                    component.findCocktail(
                                        searchQuery: searchQuery
                                    )
                                }
                            },
                            placeholder: UiComponentsStrings.shared.search
                                .desc().localized(),
                            onTrailingItemClicked: {
                                component.clearSearch()
                            }
                        )
                        .padding(.top, 10)
                        .padding(.horizontal, 8)
                        
                        SegmentedControl(
                            titles: [
                                CommonStrings.shared.nonAlcoholic.desc().localized(),
                                CommonStrings.shared.alcoholic.desc().localized()
                            ],
                            selected: { index in
                                if index == 0 {
                                    !model.listsAlcoholicCocktails
                                } else {
                                    model.listsAlcoholicCocktails
                                }
                            },
                            onSegmentClick: { index in
                                if index == 0 {
                                    component.displayNonAlcoholicCocktails()
                                } else {
                                    component.displayAlcoholicCocktails()
                                }
                            }
                        )
                        .padding(.top, 8)
                        .padding(.horizontal, 8)
                        
                        VerticalGrid(
                            items: model.cocktails,
                            columns: 2,
                            itemSpacing: 8,
                            contentPadding: 8
                        ) { cocktail in
                            CocktailItemView(
                                cocktail: cocktail,
                                onClick: { cocktail in
                                    component.showCocktail(
                                        cocktailId: cocktail.id
                                    )
                                }
                            )
                        }
                        .frame(maxWidth: .infinity)
                        .padding(.top, 8)
                    }
                    .statusBarPadding()
                    .navigationBarPadding()
                }
                .background(
                    PullRefreshView(
                        action: { component.reload() },
                        isShowing: model.isRefreshing
                    )
                )
                .hideKeyboardOnTap()
                .resignKeyboardOnDrag()
            }
            
            if model.isLoading {
                LoadingView(
                    backgroundColor: theme.colors.background
                )
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(theme.colors.background)
    }
}
