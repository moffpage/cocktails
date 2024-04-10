package kz.grandera.vlifetesttaskapp.ui.details

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.Surface
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons as MaterialIcons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.displayCutoutPadding

import dev.icerock.moko.resources.compose.stringResource
import dev.icerock.moko.resources.compose.painterResource

import com.arkivanov.decompose.extensions.compose.subscribeAsState

import coil3.compose.SubcomposeAsyncImage

import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent.DrinkCategory
import kz.grandera.vlifetesttaskapp.resources.Icons
import kz.grandera.vlifetesttaskapp.resources.icons.Beer
import kz.grandera.vlifetesttaskapp.resources.icons.Cocoa
import kz.grandera.vlifetesttaskapp.resources.icons.Coffee
import kz.grandera.vlifetesttaskapp.resources.icons.Cocktail
import kz.grandera.vlifetesttaskapp.resources.icons.Liqueur
import kz.grandera.vlifetesttaskapp.resources.icons.Other
import kz.grandera.vlifetesttaskapp.resources.icons.Punch
import kz.grandera.vlifetesttaskapp.resources.icons.RegularDrink
import kz.grandera.vlifetesttaskapp.resources.icons.Shot
import kz.grandera.vlifetesttaskapp.resources.icons.Shake
import kz.grandera.vlifetesttaskapp.resources.icons.SoftDrink
import kz.grandera.vlifetesttaskapp.resources.CommonStrings
import kz.grandera.vlifetesttaskapp.ui_components.chip.Chip
import kz.grandera.vlifetesttaskapp.ui_components.error.ErrorContent
import kz.grandera.vlifetesttaskapp.ui_components.theming.AppTheme
import kz.grandera.vlifetesttaskapp.ui_components.modifier.verticalFadingEdge
import kz.grandera.vlifetesttaskapp.ui_components.resources.UiComponentImages

@Composable
internal fun CocktailDetailsContent(
    modifier: Modifier = Modifier,
    component: CocktailDetailsComponent
) {
    val model by component.model.subscribeAsState()

    Box(modifier = modifier.fillMaxSize()) {
        if (model.isError) {
            ErrorContent(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .padding(horizontal = 64.dp),
                onRetry = { component.refetchDetails() }
            )
        } else {
            Surface(color = MaterialTheme.colors.background) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box {
                        var imageHeight by remember { mutableFloatStateOf(value = 0f) }
                        SubcomposeAsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(ratio = 1f)
                                .onSizeChanged { size ->
                                    imageHeight = size.height.toFloat()
                                }
                                .verticalFadingEdge(
                                    startY = imageHeight*(2/3),
                                    endY = imageHeight,
                                    endColor = MaterialTheme.colors.background,
                                    startColor = Color.Transparent,
                                ),
                            model = model.imageUrl,
                            error = {
                                Image(
                                    painter = painterResource(
                                        imageResource = UiComponentImages.cocktailPlaceholder(
                                            theme = AppTheme(
                                                isLight = MaterialTheme.colors.isLight
                                            )
                                        )
                                    ),
                                    contentDescription = model.cocktailName
                                )
                            },
                            success = { state ->
                                Image(
                                    painter = state.painter,
                                    contentDescription = model.cocktailName
                                )
                            },
                            contentDescription = model.cocktailName
                        )
                        Text(
                            modifier = Modifier
                                .align(alignment = Alignment.BottomCenter)
                                .padding(bottom = 16.dp)
                                .padding(horizontal = 16.dp),
                            text = model.cocktailName,
                            style = MaterialTheme.typography.h1
                                .copy(textAlign = TextAlign.Center)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
                    ) {
                        Chip(
                            text = model.glassType,
                            iconPainter = model.category?.iconPainter
                                ?.let { imageVector ->
                                    rememberVectorPainter(
                                        image = imageVector
                                    )
                                }
                        )
                        Chip(
                            text = if (model.isAlcoholic) {
                                stringResource(resource = CommonStrings.alcoholic)
                            } else {
                                stringResource(resource = CommonStrings.nonAlcoholic)
                            },
                            iconPainter = null
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(top = 36.dp)
                            .padding(horizontal = 8.dp),
                        text = stringResource(resource = CommonStrings.instructions),
                        style = MaterialTheme.typography.h2
                            .copy(color = MaterialTheme.colors.primary)
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(horizontal = 8.dp),
                        text = model.preparationInstruction,
                        style = MaterialTheme.typography.h4
                    )
                }
            }
        }
        IconButton(
            modifier = Modifier
                .padding(start = 12.dp)
                .statusBarsPadding()
                .displayCutoutPadding()
                .clip(shape = CircleShape)
                .background(
                    color = MaterialTheme.colors.primary.copy(alpha = 0.5f),
                    shape = CircleShape
                ),
            onClick = { component.navigateBack() }
        ) {
            Icon(
                tint = contentColorFor(
                    backgroundColor = MaterialTheme.colors.primary
                ),
                imageVector = MaterialIcons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null
            )
        }
    }
}

private val DrinkCategory.iconPainter: ImageVector get() =
    when (this) {
        DrinkCategory.Beer -> Icons.Beer
        DrinkCategory.Shot -> Icons.Shot
        DrinkCategory.Shake -> Icons.Shake
        DrinkCategory.Soft -> Icons.SoftDrink
        DrinkCategory.Cocoa -> Icons.Cocoa
        DrinkCategory.Coffee -> Icons.Coffee
        DrinkCategory.Other -> Icons.Other
        DrinkCategory.Punch -> Icons.Punch
        DrinkCategory.Liqueur -> Icons.Liqueur
        DrinkCategory.Cocktail -> Icons.Cocktail
        DrinkCategory.Ordinary -> Icons.RegularDrink
    }