
import shared
import SwiftUI

struct CocktailDetailsView: View {
    @EnvironmentObject
    private var theme: AppTheme
    
    @State
    private var imageLoaded = false
    
    @StateValue
    private var model: CocktailDetailsComponentModel
    
    private let component: CocktailDetailsComponent
    
    init(component: CocktailDetailsComponent) {
        self.component = component
        self._model = StateValue(component.model)
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            ZStack(alignment: .bottom) {
                KingfisherView(
                    url: model.imageUrl,
                    onSuccess: { _ in
                        imageLoaded = true
                    }
                )
                
                if imageLoaded {
                    LinearGradient(
                        gradient: Gradient(
                            colors: [
                                .clear,
                                theme.colors.background
                            ]
                        ),
                        startPoint: .init(x: 0.5, y: 0),
                        endPoint: .init(x: 0.5, y: 1)
                    )
                    .frame(
                        maxWidth: .infinity,
                        maxHeight: 125
                    )
                }
                
                Text(model.cocktailName)
                    .modifier(theme.typography.h1(color: theme.colors.onBackground))
                    .padding(.bottom, 16)
            }
            .overlay(
                BackButton {
                    component.navigateBack()
                }
                .frame(
                    width: 40,
                    height: 40
                )
                .padding(.top, 16)
                .padding(.leading, 12)
                .statusBarPadding(),
                alignment: .topLeading
            )
            
            HStack(alignment: .center) {
                ChipView(
                    text: model.glassType,
                    iconResource: model.category?.iconName,
                    surfaceColor: theme.colors.surface,
                    contentColor: theme.colors.primary
                )
                ChipView(
                    text: model.isAlcoholic ?
                        CommonStrings.shared.alcoholic.desc().localized() :
                        CommonStrings.shared.nonAlcoholic.desc().localized(),
                    surfaceColor: theme.colors.surface,
                    contentColor: theme.colors.secondary
                )
            }
            .frame(maxWidth: .infinity)
            
            Text(CommonStrings.shared.instructions
                .desc().localized())
                .padding(.top, 32)
                .padding(.leading, 16)
                .modifier(theme.typography.h2(color: theme.colors.primary))
            
            Text(model.preparationInstruction)
                .padding(.top, 4)
                .padding(.horizontal, 16)
                .modifier(theme.typography.h4(color: theme.colors.onBackground))
            
            Spacer()
        }
        .background(theme.colors.background)
        .navigationBarHidden(true)
    }
}

private extension shared.CocktailDetailsComponentDrinkCategory {
    var iconName: String {
        switch self {
        case .soft: return "soft_drink"
        case .ordinary: return "regular_drink"
        default: return self.name.lowercased()
        }
    }
}
